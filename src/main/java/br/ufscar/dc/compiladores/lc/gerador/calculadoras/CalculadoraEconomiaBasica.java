package br.ufscar.dc.compiladores.lc.gerador.calculadoras;

import java.util.ArrayList;

import br.ufscar.dc.compiladores.lc.gerador.TabelaDeSimbolos;

public class CalculadoraEconomiaBasica extends CalculadoraBase{

    @Override
    public String retornaListaDeCompras(TabelaDeSimbolos tabela) {
        super.retornaListaHtmlMercado("nome", new ArrayList<Produtos>());
        return "super.retornaListaDeCompras(tabela)";
    }
    
}
