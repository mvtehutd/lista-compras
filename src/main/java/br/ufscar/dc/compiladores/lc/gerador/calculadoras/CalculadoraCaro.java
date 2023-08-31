package br.ufscar.dc.compiladores.lc.gerador.calculadoras;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.ufscar.dc.compiladores.lc.gerador.TabelaDeSimbolos;
import br.ufscar.dc.compiladores.lc.gerador.TabelaDeSimbolos.TipoLc;

/**
 * Calcula a lista de compras baseado no Fator Caro
 */
public class CalculadoraCaro extends CalculadoraBase {

    @Override
    public String retornaListaDeCompras(TabelaDeSimbolos tabela) {

        // Extrai a lista de lojas da tabela de simbolos
        List<Loja> listaDeLojas = tabela.getTabela().values().stream()
                .filter(entrada -> entrada.tipo.equals(TipoLc.LOJA))
                .map(loja -> new Loja(loja.nome,
                        loja.produtos.getTabela().values()
                                .stream()
                                .map(Produto::new)
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());

        // Extrai a lista de compras da tabela de simbolos
        List<Produto> listaDeCompra = tabela.getTabela().values().stream()
                .filter(entrada -> !entrada.tipo.equals(TipoLc.LOJA))
                .map(Produto::new)
                .collect(Collectors.toList());

        List<Loja> listaDeCompraPorLoja = new ArrayList<>();
        Loja lojaOndeComprar = null;
        Produto produtoParaCompra = null;
        Float custoBeneficoAtual = 0.0f;

        // Itera sobre a lista de compra para procurar cada produto na loja
        for (Produto produto : listaDeCompra) {

            produtoParaCompra = null;
            custoBeneficoAtual = Float.MAX_VALUE;
            lojaOndeComprar = null;
            // Itera sobre as lojas para verificar se o produto está presente
            for (Loja loja : listaDeLojas) {
                // Verifica se o produto esta presente na loja e se o custo beneficio dele é
                // melhor que o produto separado para compra atual
                for (Produto produtoLoja : loja.getProdutos()) {
                    // Armazena o produto, a loja que vao ser comprados e qual seu custo beneficio
                    if (produtoLoja.equals(produto) && produtoLoja.getCustoBeneficio() < custoBeneficoAtual) {
                        produtoParaCompra = produtoLoja;
                        custoBeneficoAtual = produtoLoja.getCustoBeneficio();
                        lojaOndeComprar = loja;
                    }
                }
            }
            if (produtoParaCompra != null) {
                // Calcula quantas unidades comprar daquele produto para suprir a lista
                produtoParaCompra.setQuantoComprar(produto.getMedida());

                // Adiciona uma nova loja com o produto na lista de compra por loja gerada no
                // final, caso a loja ainda noa esteja nessa lista
                // Se nao, adiciona o produto na loja que já esta na lista
                if (listaDeCompraPorLoja.contains(lojaOndeComprar)) {
                    listaDeCompraPorLoja.get(listaDeCompraPorLoja.indexOf(lojaOndeComprar))
                            .adicionarProduto(produtoParaCompra);
                } else {
                    listaDeCompraPorLoja.add(
                            new Loja(lojaOndeComprar.getNome(), new ArrayList<>(Arrays.asList(produtoParaCompra))));
                }

            }

        }
        // Retorna o html Gerado pelo metodo a partir da lista de comprar separada por
        // loja
        return super.retornaListaHtmlMercado(listaDeCompraPorLoja);
    }
}
