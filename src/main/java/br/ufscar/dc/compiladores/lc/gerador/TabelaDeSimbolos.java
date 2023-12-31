package br.ufscar.dc.compiladores.lc.gerador;

import java.util.Collections;
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

    public class EntradaTabelaDeSimbolos {
        public String nome;
        public TipoLc tipo;
        public TabelaDeSimbolos produtos;
        public Float preco;
        public Float quantidade;

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

    public Map<String, EntradaTabelaDeSimbolos> getTabela() {
        return Collections.unmodifiableMap(tabela) ;
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


}