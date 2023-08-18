package br.ufscar.dc.compiladores.lc.gerador.calculadoras;

public class Produtos {

    private String nome;
    private String preco;
    
    public String getNome() {
        return nome;
    }
    public Produtos(String nome, String preco) {
        this.nome = nome;
        this.preco = preco;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getPreco() {
        return preco;
    }
    public void setPreco(String preco) {
        this.preco = preco;
    }

    

}
