package br.ufscar.dc.compiladores.lc.gerador.calculadoras;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.ufscar.dc.compiladores.lc.gerador.TabelaDeSimbolos;
import br.ufscar.dc.compiladores.lc.gerador.TabelaDeSimbolos.TipoLc;

public class CalculadoraCaro extends CalculadoraBase {

    @Override
    public String retornaListaDeCompras(TabelaDeSimbolos tabela) {

        List<Loja> listaDeLojas = tabela.getTabela().values().stream()
                .filter(entrada -> entrada.tipo.equals(TipoLc.LOJA))
                .map(loja -> new Loja(loja.nome,
                        loja.produtos.getTabela().values()
                                .stream()
                                .map(Produto::new)
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());

        List<Produto> listaDeCompra = tabela.getTabela().values().stream()
                .filter(entrada -> !entrada.tipo.equals(TipoLc.LOJA))
                .map(Produto::new)
                .collect(Collectors.toList());

        List<Loja> listaDeCompraPorLoja = new ArrayList<>();
        Loja lojaOndeComprar = null;
        Produto produtoParaCompra = null;
        Float custoBeneficoAtual = 0.0f;

        System.out.println("listaDeCompra: " + listaDeCompra);
        System.out.println("listaDeLojas: " + listaDeLojas);

        for (Produto produto : listaDeCompra) {
            
            produtoParaCompra = null;
            custoBeneficoAtual = Float.MAX_VALUE;
            lojaOndeComprar = null;

            for (Loja loja : listaDeLojas) {
                for (Produto produtoLoja : loja.getProdutos()) {
                    if (produtoLoja.equals(produto) && produtoLoja.getCustoBeneficio() < custoBeneficoAtual) {
                        produtoParaCompra = produtoLoja;
                        custoBeneficoAtual = produtoLoja.getCustoBeneficio();
                        lojaOndeComprar = loja;
                    }
                }
            }
            if (produtoParaCompra != null) {
                produtoParaCompra.setQuantoComprar(produto.getMedida());
                if (listaDeCompraPorLoja.contains(lojaOndeComprar)) {
                    listaDeCompraPorLoja.get(listaDeCompraPorLoja.indexOf(lojaOndeComprar))
                            .adicionarProduto(produtoParaCompra);
                } else {
                    listaDeCompraPorLoja.add(new Loja(lojaOndeComprar.getNome(), new ArrayList<>(Arrays.asList(produtoParaCompra))));
                }

            }

        }
        System.out.println("listaDeCompraPorLoja: " + listaDeCompraPorLoja);

        return super.retornaListaHtmlMercado(listaDeCompraPorLoja);
    }
}
