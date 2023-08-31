package br.ufscar.dc.compiladores.lc.gerador;

import br.ufscar.dc.compiladores.lc.gerador.TabelaDeSimbolos.TipoLc;
import br.ufscar.dc.compiladores.lc.gerador.calculadoras.Calculavel;
import br.ufscar.dc.compiladores.lc.gerador.calculadoras.CalculadoraCaro;
import br.ufscar.dc.compiladores.lc.gerador.calculadoras.CalculadoraEconomia;
import br.ufscar.dc.compiladores.parser.LcBaseVisitor;
import br.ufscar.dc.compiladores.parser.LcParser.LojaContext;
import br.ufscar.dc.compiladores.parser.LcParser.Produto_listaContext;
import br.ufscar.dc.compiladores.parser.LcParser.Produto_lojaContext;
import br.ufscar.dc.compiladores.parser.LcParser.ProgramaContext;

public class LcGerador extends LcBaseVisitor<Void> {

    TabelaDeSimbolos tabela;

    StringBuilder saida;

    public LcGerador() {
        saida = new StringBuilder();
        this.tabela = new TabelaDeSimbolos();
    }

    @Override
    public Void visitPrograma(ProgramaContext ctx) {
        // Adiciona o Início do HTML (até chegar na tabela)
        String nomeLista = ctx.lista().IDENT().getText();
        saida.append("<!DOCTYPE html>\n<html lang='pt'>\n<head>\n<style>\nbody{ background-color: #5ab2fe; }\n" +
            "table { background-color: white; font-family: Arial, Helvetica, sans-serif; border-collapse: collapse; width: 70%; }\n" +
            "td, th { padding: 8px; text-align: center; }\n tr:nth-child(even){background-color: #f2f2f2;}\n" + 
            "th { padding-top: 12px; padding-bottom: 12px; text-align: center; background-color: #04AA6D; color: white; }\n" + 
            "h1, h3 { text-align: center; color: white; text-shadow: -2px 0 black, 0 2px black, 2px 0 black, 0 -2px black; " +
            "font-family:Verdana, Geneva, Tahoma, sans-serif;padding: 20px 20px 10px 20px; }\n </style>\n<meta charset='UTF-8'>" +
            "<meta name='viewport' content='width=device-width, initial-scale=1.0'>\n<title>" + nomeLista +
            "</title>\n</head>\n<body>\n<h1>" + nomeLista + "</h1>\n");

        // Adiciona os produtos da loja na tabela de símbolos
        for (LojaContext loja : ctx.loja()) {
            visitLoja(loja);
        }

        // Adiciona os produtos da lista na tabela de símbolos
        for (Produto_listaContext produtoLista : ctx.lista().produto_lista()) {
            tabela.adicionar(produtoLista.IDENT().getText(), LcSemanticoUtils.verificaTipo(produtoLista.UNIDADE().getText()), null, null, produtoLista.quant.getText());
        }

        // Fazer o cálculo para montar a lista desejada nas lojas e suas tabelas HTML
        String propriedadeLista = ctx.lista().PROPRIEDADE().getText();
        saida.append("<h3>" + propriedadeLista + "</h3>\n<br>\n");
        Calculavel calculoEcnomia = null;
        // Define como vai ser calculado as listas de produtos a serem comprados
        switch (propriedadeLista) {
            case "Economia":
                calculoEcnomia = new CalculadoraEconomia();
                break;
            case "Caro":
                calculoEcnomia = new CalculadoraCaro();
            default:
                break;
        }

        saida.append(calculoEcnomia.retornaListaDeCompras(tabela));

        // Adiciona o restante do HTML
        saida.append("</body>\n</html>");
        return null;
    }
    
    @Override
    public Void visitLoja(LojaContext ctx) {
        String nomeLoja = ctx.IDENT().getText();
        TabelaDeSimbolos tabelaProdutos = new TabelaDeSimbolos();
        // Adiciona a loja e os produtos dela na tabela
        for (Produto_lojaContext produto : ctx.produto_loja()) {
            tabelaProdutos.adicionar(produto.IDENT().getText(),
                    LcSemanticoUtils.verificaTipo(produto.UNIDADE().getText()), null,
                    produto.valor.getText(), produto.quant.getText());
        }
        tabela.adicionar(nomeLoja, TipoLc.LOJA, tabelaProdutos, null, null);
        return null;
    }
}
