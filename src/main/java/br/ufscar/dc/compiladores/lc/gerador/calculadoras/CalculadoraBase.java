package br.ufscar.dc.compiladores.lc.gerador.calculadoras;

import java.text.DecimalFormat;
import java.util.List;

import br.ufscar.dc.compiladores.lc.gerador.TabelaDeSimbolos;

public abstract class CalculadoraBase implements Calculavel {

    @Override
    public String retornaListaDeCompras(TabelaDeSimbolos tabela) {
        return null;
    }

    // Gera o Html com as tabelas com os produtos a serem comprados em cada mercado
    protected String retornaListaHtmlMercado(List<Loja> listaDeCompraPorLoja) {
        StringBuilder listHtmlLoja = new StringBuilder();

        // Itera sobre as lojas da lista armazenando cada tabela como uma loja
        listaDeCompraPorLoja.forEach(loja -> {
            listHtmlLoja.append("<table align=\"center\">\n<thead>\n<tr>\n<th>" + loja.getNome()
                    + "</th>\n<th></th>\n<th></th>\n" +
                    "<th></th>\n<th></th>\n<th></th>\n</tr>\n<tr>\n<th>Produto</th>\n<th>Medida</th>\n<th>Quantidade</th>\n<th>Sobra</th>\n"
                    +
                    "<th>Valor Unitário</th>\n<th>Valor Total</th>\n</tr>\n</thead>\n<tbody>\n");

            // Itera sobre os produtos da loja, armazenando cada produto como uma linha na
            // tabela
            loja.getProdutos().forEach(produto -> {
                listHtmlLoja.append("<tr>\n" +
                        "<td>" + produto.getNome() + "</td>\n" +
                        "<td>" + formatNumber(produto.getMedida(), "0.0") + " " + produto.getTipo() + "</td>\n" +
                        "<td>" + produto.getQuantoComprar() + "</td>\n" +
                        "<td>" + formatNumber(produto.getSobra(), "0.0") + " " + produto.getTipo() + "</td>\n" +
                        "<td>R$ " + formatNumber(produto.getPreco(), "0.00") + "</td>\n" +
                        "<td>R$ " + formatNumber(produto.getQuantoComprar() * produto.getPreco(), "0.00") + "</td>\n" +
                        "</tr>\n");
            });

            // Calcula e armazena o valor total gasto nessa loja
            listHtmlLoja
                    .append("<tr>\n<th></th>\n<th></th>\n<th></th>\n<th></th>\n<th>Total</th>\n<th>R$  " + formatNumber(
                            loja.getProdutos().stream()
                                    .mapToDouble(produto -> produto.getQuantoComprar() * produto.getPreco()).sum(),
                            "0.00") + "</th>\n</tr>\n");
            listHtmlLoja.append("</tbody>\n</table>\n<br><br><br>\n");
        });

        // Calcula e armazena o valor total gasto na compra geral
        Double valorTotal = listaDeCompraPorLoja.stream()
                .mapToDouble(loja -> loja.getProdutos().stream()
                        .mapToDouble(produto -> produto.getQuantoComprar() * produto.getPreco())
                        .sum())
                .sum();

        listHtmlLoja.append("<table align=\"center\">\n<tr>\n<th>Valor Total</th>\n<th>R$ "
                + formatNumber(valorTotal, "0.00")
                + "</th>\n</tr>\n</table>\n");

        return listHtmlLoja.toString();
    }

    // Formata a saida do numero para um formato especificado
    private String formatNumber(Number numero, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);

        return decimalFormat.format(numero);
    }
}
