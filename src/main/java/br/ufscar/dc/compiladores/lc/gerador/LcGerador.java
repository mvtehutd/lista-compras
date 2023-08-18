package br.ufscar.dc.compiladores.lc.gerador;

import br.ufscar.dc.compiladores.lc.gerador.calculadoras.CalculadoraEconomia;
import br.ufscar.dc.compiladores.lc.gerador.calculadoras.CalculadoraEconomiaBasica;
import br.ufscar.dc.compiladores.parser.LcBaseVisitor;
import br.ufscar.dc.compiladores.parser.LcParser.ProgramaContext;

public class LcGerador extends LcBaseVisitor<Void> {

    TabelaDeSimbolos tabela;

    String propriedadeLista;

    StringBuilder saida;

    @Override
    public Void visitPrograma(ProgramaContext ctx) {
        /////////////
        CalculadoraEconomia calculoEcnomia = null;
        switch (propriedadeLista) {
            case "Economia":
                calculoEcnomia = new CalculadoraEconomiaBasica();
                break;
        
            default:
                break;
        }

        saida.append(calculoEcnomia.retornaListaDeCompras(tabela));
        ///////////
        return null;
    }
    
}
