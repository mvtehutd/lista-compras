package br.ufscar.dc.compiladores.lc.gerador;

import java.io.IOException;
import java.io.PrintWriter;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import br.ufscar.dc.compiladores.parser.LcLexer;
import br.ufscar.dc.compiladores.parser.LcParser;
import br.ufscar.dc.compiladores.parser.LcParser.ProgramaContext;


// public class Principal {
//     public static void main(String args[]) throws IOException {
//         // args[0] é o primeiro argumento da linha de comando que representa o CAMINHO PARA O ARQUIVO DE ENTRADA COM A LINGUAGEM
//         CharStream cs = CharStreams.fromFileName(args[0]);
//         LcLexer lexer = new LcLexer(cs);
//         CommonTokenStream tokens = new CommonTokenStream(lexer);
//         LcParser parser = new LcParser(tokens);
//         ProgramaContext arvore = parser.programa();
//         LcSemantico as = new LcSemantico();
//         as.visitPrograma(arvore);
//         // Pega o segundo argumento da linha de comando que representa o CAMINHO PARA O ARQUIVO DE SAIDA DA ANALISE SEMÂNTICA
//         // E Cria um objeto para escrever no arquivo
//         try (PrintWriter pw = new PrintWriter(new File(args[1]))) {
//             // Verifica se há erros
//             for (String string : LcSemanticoUtils.errosSemanticos) {
//                 pw.println(string);
//             }
//             if(!LcSemanticoUtils.errosSemanticos.isEmpty()){
//                 pw.println("Fim da compilacao");
//             }
//         } catch (Exception e) {
//             System.out.println(e.getMessage());
//         }
//     }
// }

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
            try(PrintWriter pw = new PrintWriter(args[1])) {
                pw.print(agc.saida.toString());
                System.out.println(agc.saida.toString());
            }
        }
    }
}