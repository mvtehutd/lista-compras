package br.ufscar.dc.compiladores.lc.gerador.calculadoras;

import br.ufscar.dc.compiladores.lc.gerador.TabelaDeSimbolos;

/**
 * Interface com metodo para retornar a listaDeCompras em HTml
 */
public interface Calculavel {

    String retornaListaDeCompras(TabelaDeSimbolos tabela);
    
}
