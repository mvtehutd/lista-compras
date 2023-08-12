package br.ufscar.dc.compiladores.lc.gerador;

import br.ufscar.dc.compiladores.parser.LcBaseVisitor;
import br.ufscar.dc.compiladores.parser.LcParser.LojaContext;
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
        if(tabela.existeLoja(nomeLoja)){
            LcSemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(), "Loja " + nomeLoja + " já declarada anteriormente");
        } else{
            // adiciona a loja com a tabela vazia
            // foreach para ir adicionando os produtos nas tabela adicional
        }
        return super.visitLoja(ctx);
    }

}