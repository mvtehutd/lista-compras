package br.ufscar.dc.compiladores.lc.gerador.calculadoras;

import java.text.DecimalFormat;
import java.util.List;

import br.ufscar.dc.compiladores.lc.gerador.TabelaDeSimbolos;

public abstract class CalculadoraBase implements Calculavel {

    @Override
    public String retornaListaDeCompras(TabelaDeSimbolos tabela) {
        // TODO Auto-generated method stub
        return null;
    }

    protected String retornaListaHtmlMercado(List<Loja> listaDeCompraPorLoja) {
        StringBuilder listHtmlLoja = new StringBuilder();

        listaDeCompraPorLoja.forEach(loja -> {
            listHtmlLoja.append("<table align=\"center\">\n<thead>\n<tr>\n<th>" + loja.getNome()
                    + "</th>\n<th></th>\n<th></th>\n" +
                    "<th></th>\n<th></th>\n<th></th>\n</tr>\n<tr>\n<th>Produto</th>\n<th>Medida</th>\n<th>Quantidade</th>\n<th>Sobra</th>\n"
                    +
                    "<th>Valor Unitário</th>\n<th>Valor Total</th>\n</tr>\n</thead>\n<tbody>\n");
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
            listHtmlLoja
                    .append("<tr>\n<th></th>\n<th></th>\n<th></th>\n<th></th>\n<th>Total</th>\n<th>R$  " + formatNumber(
                            loja.getProdutos().stream()
                                    .mapToDouble(produto -> produto.getQuantoComprar() * produto.getPreco()).sum(),
                            "0.00") + "</th>\n</tr>\n");
            listHtmlLoja.append("</tbody>\n</table>\n<br><br><br>\n");
        });

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

    private String formatNumber(Number numero, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);

        return decimalFormat.format(numero);
    }
}
