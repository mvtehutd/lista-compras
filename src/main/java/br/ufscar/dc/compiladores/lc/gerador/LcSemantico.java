package br.ufscar.dc.compiladores.lc.gerador;

import br.ufscar.dc.compiladores.lc.gerador.TabelaDeSimbolos.TipoLc;
import br.ufscar.dc.compiladores.parser.LcBaseVisitor;
import br.ufscar.dc.compiladores.parser.LcParser.LojaContext;
import br.ufscar.dc.compiladores.parser.LcParser.Produto_listaContext;
import br.ufscar.dc.compiladores.parser.LcParser.Produto_lojaContext;
import br.ufscar.dc.compiladores.parser.LcParser.ProgramaContext;

public class LcSemantico extends LcBaseVisitor<Void> {

    TabelaDeSimbolos tabela;

    @Override
    public Void visitPrograma(ProgramaContext ctx) {
        tabela = new TabelaDeSimbolos();
        return super.visitPrograma(ctx);
    }

    @Override
    public Void visitLoja(LojaContext ctx) {
        String nomeLoja = ctx.IDENT().getText();
        if (tabela.existeLoja(nomeLoja)) {
            LcSemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(),
                    "Loja " + nomeLoja + " já declarada anteriormente");
        } else {
            // adiciona a loja com a tabela vazia
            // foreach para ir adicionando os produtos nas tabela adicional
            TabelaDeSimbolos tabelaProdutos = new TabelaDeSimbolos();
            for (Produto_lojaContext produto : ctx.produto_loja()) {
                adicionarProdutoLoja(tabelaProdutos, produto);
            }
            tabela.adicionar(nomeLoja, TipoLc.LOJA, tabelaProdutos, null, null);
        }
        return super.visitLoja(ctx);
    }

    @Override
    public Void visitProduto_lista(Produto_listaContext ctx) {
        adicionarProdutoLista(tabela, ctx);
        return super.visitProduto_lista(ctx);
    }

    private void adicionarProdutoLista(TabelaDeSimbolos tabelaProdutos, Produto_listaContext produto) {
        String nomeProduto = produto.IDENT().getText();
        if (!tabelaProdutos.existe(nomeProduto)) { // verifica se já foi declarado na lista
            String unidade = produto.UNIDADE().getText();
            if (!(unidade.equals("UN") && produto.quant.getText().contains(","))) { // se não tem frações de uma unidade
                TipoLc tipoProduto = tabela.verificaTipoProdutoLojas(nomeProduto);
                if(tipoProduto != null){
                    if(tipoProduto.equals(LcSemanticoUtils.verificaTipo(unidade))){
                        tabelaProdutos.adicionar(nomeProduto, LcSemanticoUtils.verificaTipo(unidade), null,
                    null, produto.quant.getText());
                    } else{
                        LcSemanticoUtils.adicionarErroSemantico(produto.UNIDADE().getSymbol(),
                    "Unidade " + unidade + " Incompatível para Medida " + tipoProduto);
                    }
                    
                } else{
                    LcSemanticoUtils.adicionarErroSemantico(produto.UNIDADE().getSymbol(),
                        "Produto " + nomeProduto + " não declarado anteriormente nas Lojas");
                }
            } else {
                LcSemanticoUtils.adicionarErroSemantico(produto.UNIDADE().getSymbol(),
                    "Valor Incompatível para Medida " + unidade);
            }
        } else {
            LcSemanticoUtils.adicionarErroSemantico(produto.UNIDADE().getSymbol(),
            "Produto " + nomeProduto + " já declarado anteriormente na Lista");
        }
    }

    private void adicionarProdutoLoja(TabelaDeSimbolos tabelaProdutos, Produto_lojaContext produto) {
        String nomeProduto = produto.IDENT().getText();
        if (!tabelaProdutos.existe(nomeProduto)) {
            String unidade = produto.UNIDADE().getText();
            if (unidade.equals("UN") && produto.quant.getText().contains(",")) {
            LcSemanticoUtils.adicionarErroSemantico(produto.UNIDADE().getSymbol(),
                    "Valor Incompatível para Medida " + unidade);
            } else{
                TipoLc tipoProduto = tabela.verificaTipoProdutoLojas(nomeProduto);
                if(tipoProduto == null || tipoProduto.equals(LcSemanticoUtils.verificaTipo(unidade))){
                    tabelaProdutos.adicionar(nomeProduto, LcSemanticoUtils.verificaTipo(unidade), null,
                produto.valor.getText(), produto.quant.getText());
                } else {
                    LcSemanticoUtils.adicionarErroSemantico(produto.UNIDADE().getSymbol(),
                        "Produto " + nomeProduto + " já declarado anteriormente em outra Loja com outra Medida");
                }
            }
        } else {
            LcSemanticoUtils.adicionarErroSemantico(produto.UNIDADE().getSymbol(),
            "Produto " + nomeProduto + " já declarado anteriormente na Loja");
        }
    }

}