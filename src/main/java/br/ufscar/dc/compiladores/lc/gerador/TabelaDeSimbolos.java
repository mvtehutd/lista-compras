package br.ufscar.dc.compiladores.lc.gerador;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class TabelaDeSimbolos {
    public enum TipoLc {
        UN,
        KG,
        L,
        INVALIDO,
        LOJA;
    }

    class EntradaTabelaDeSimbolos {
        String nome;
        TipoLc tipo;
        TabelaDeSimbolos produtos;
        Float preco;
        Float quantidade;

        private EntradaTabelaDeSimbolos(String nome, TipoLc tipo, TabelaDeSimbolos produtos, Float preco, Float quantidade) {
            this.nome = nome;
            this.tipo = tipo;
            this.produtos = produtos;
            this.preco = preco;
            this.quantidade = quantidade;
        }

    }

    private final Map<String, EntradaTabelaDeSimbolos> tabela;

    public TabelaDeSimbolos() {
        this.tabela = new HashMap<>();
    }

    public void adicionar(String nome, TipoLc tipo, TabelaDeSimbolos produtos, String preco, String quantidade) {
        Float precoConvertido = preco != null ? Float.parseFloat(preco.replace(",", ".")): null;
        Float quantidadeConvertida = quantidade != null ? Float.parseFloat(quantidade.replace(",", ".")): null;
        tabela.put(nome, new EntradaTabelaDeSimbolos(nome, tipo, produtos, precoConvertido, quantidadeConvertida));
    }

    public boolean existeLoja(String nomeLoja){
        if (tabela.containsKey(nomeLoja)){
            return tabela.get(nomeLoja).tipo.equals(TipoLc.LOJA);
        }
        return false;
    }

    public boolean existe(String nome){
        return tabela.containsKey(nome);
    }

    public TipoLc verificaTipo(String nome){
       return tabela.get(nome).tipo;
    }

    public TipoLc verificaTipoProdutoLojas(String nome){
        for (Entry<String, EntradaTabelaDeSimbolos> entry : tabela.entrySet()) {
            String chave = entry.getKey();
            EntradaTabelaDeSimbolos entrada = tabela.get(chave);
            if(entrada.tipo.equals(TipoLc.LOJA)){
                TabelaDeSimbolos produtos = entrada.produtos;
                if(produtos.tabela.containsKey(nome)){
                    return produtos.verificaTipo(nome);
                }
            }
        }
        return null;
    }

    // public boolean existeNaLoja(String nome) {
    //     if (tabela.containsKey(nome)) {
    //         return true;
    //     }
    //     for (Map.Entry<String, EntradaTabelaDeSimbolos> entry : tabela.entrySet()) {
    //         String key = entry.getKey();
    //         TabelaDeSimbolos tabelaAdicional = tabela.get(key).registro;
    //         if (tabelaAdicional != null && tabelaAdicional.existe(nome)) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    // public boolean existeNaTabelaPrincipal(String nome) {
    //     return tabela.containsKey(nome);
    // }

    // public TipoLa verificar(String nome) {
    //     TipoLa tipo = TipoLa.INVALIDO;
    //     if (tabela.get(nome) != null) {
    //         return tabela.get(nome).tipo;
    //     } else {
    //         for (Map.Entry<String, EntradaTabelaDeSimbolos> entry : tabela.entrySet()) {
    //             String key = entry.getKey();
    //             TabelaDeSimbolos tabelaAdicional = tabela.get(key).registro;
    //             if (tabelaAdicional != null && tabelaAdicional.existe(nome)) {
    //                 return tabelaAdicional.tabela.get(nome).tipo;
    //             }
    //         }
    //     }
    //     return tipo;
    // }

}