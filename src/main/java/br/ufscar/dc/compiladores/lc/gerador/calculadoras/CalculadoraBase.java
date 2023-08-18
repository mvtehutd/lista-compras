package br.ufscar.dc.compiladores.lc.gerador.calculadoras;

import java.util.List;

import br.ufscar.dc.compiladores.lc.gerador.TabelaDeSimbolos;

public abstract class CalculadoraBase implements CalculadoraEconomia{

    @Override
    public String retornaListaDeCompras(TabelaDeSimbolos tabela) {
        // TODO Auto-generated method stub
        return null;
    }

    protected String retornaListaHtmlMercado(String nomeMercado, List<Produtos> produtos){
        return "s";
    }
    
}
