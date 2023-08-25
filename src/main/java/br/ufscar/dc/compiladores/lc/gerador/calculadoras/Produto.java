package br.ufscar.dc.compiladores.lc.gerador.calculadoras;

import br.ufscar.dc.compiladores.lc.gerador.TabelaDeSimbolos;
import br.ufscar.dc.compiladores.lc.gerador.TabelaDeSimbolos.TipoLc;

public class Produto {

    private String nome;
    private Float preco;
    private Float medida;
    
    private TipoLc tipo;
    private Float custoBeneficio;
    private Float quantoComprar = 1f;
    
    private Float sobra = 0f;
    
    public Produto(String nome, Float preco, Float medida) {
        this.nome = nome;
        this.preco = preco;
        this.medida = medida;
        this.custoBeneficio = preco / medida;
    }
    
    public Produto(TabelaDeSimbolos.EntradaTabelaDeSimbolos entradaTabelaDeSimbolos) {
        System.out.println("Entrada Tabela:\n");
        System.out.println("\tNome: " + entradaTabelaDeSimbolos.nome + "\n");
        System.out.println("\tPreco: " + entradaTabelaDeSimbolos.preco + "\n");
        System.out.println("\tMedida: " + entradaTabelaDeSimbolos.quantidade + "\n");
        
        this.nome = entradaTabelaDeSimbolos.nome;
        this.preco = entradaTabelaDeSimbolos.preco != null ? entradaTabelaDeSimbolos.preco : null;
        this.medida = entradaTabelaDeSimbolos.quantidade;
        this.tipo = entradaTabelaDeSimbolos.tipo;
        this.custoBeneficio = preco != null ? medida / preco : null;
    }
    
    public Produto() {
    }
    
    public Float getSobra() {
        return sobra;
    }

    public void setQuantoComprar(Float precisaComprar) {
        Float diferença = this.medida - precisaComprar;
        Float medidaAux = this.medida;
        Float quantoComprarAux = this.quantoComprar;
        if(diferença < 0){
            do {
                medidaAux += this.medida;
                quantoComprarAux ++;
                
            } while (medidaAux - precisaComprar < 0);

            this.sobra = medidaAux - precisaComprar;

            this.quantoComprar = quantoComprarAux;
        }

        if(diferença > 0){
            this.sobra = diferença;
        }
    }


    public TipoLc getTipo() {
        return tipo;
    }
    
    public String getNome() {
        return nome;
    }

    public Float getQuantoComprar() {
        return quantoComprar;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Float getMedida() {
        return medida;
    }

    public void setMedida(Float medida) {
        this.medida = medida;
    }

    public Float getCustoBeneficio() {
        return custoBeneficio;
    }

    public void setCustoBeneficio(Float custoBeneficio) {
        this.custoBeneficio = custoBeneficio;
    }

    @Override
    public String toString() {
        return "Produto [nome=" + nome + ", preco=" + preco + ", medida=" + medida + ", tipo=" + tipo
                + ", custoBeneficio=" + custoBeneficio + ", quantoComprar=" + quantoComprar + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produto other = (Produto) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

}
