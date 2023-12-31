package br.ufscar.dc.compiladores.lc.gerador;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import br.ufscar.dc.compiladores.parser.LcLexer;
import br.ufscar.dc.compiladores.parser.LcParser;
import br.ufscar.dc.compiladores.parser.LcParser.ProgramaContext;


public class Principal {
    public static void main(String args[]) throws IOException {
        // args[0] é o primeiro argumento da linha de comando que representa o CAMINHO PARA O ARQUIVO DE ENTRADA COM A LINGUAGEM
        CharStream cs = CharStreams.fromFileName(args[0]);
        LcLexer lexer = new LcLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LcParser parser = new LcParser(tokens);
        ProgramaContext arvore = parser.programa();
        LcSemantico as = new LcSemantico();
        as.visitPrograma(arvore);
        // Pega o segundo argumento da linha de comando que representa o CAMINHO PARA O ARQUIVO DE SAÍDA
        // E Cria um objeto para escrever no arquivo
        if(LcSemanticoUtils.errosSemanticos.isEmpty()) {
            LcGerador agc = new LcGerador();
            agc.visitPrograma(arvore);
            try(PrintWriter pw = new PrintWriter(args[1], StandardCharsets.UTF_8)) {
                pw.print(agc.saida.toString());
            }
        } else{
            for (String string : LcSemanticoUtils.errosSemanticos) {
                System.out.println(string);
            }
            if(!LcSemanticoUtils.errosSemanticos.isEmpty()){
                System.out.println("Fim da compilacao");
            }
        }
    }
}