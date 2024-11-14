package Projeto;

public class Cliente {
    protected String nome;
    protected String nif;
    protected String localizacao;

    public Cliente(String nome, String nif, String localizacao) {
        this.nome = nome;
        this.nif = nif;
        this.localizacao = localizacao;
    }

    public String toString() {
        return ("Nome -> " + this.nome + "; NIF -> " + this.nif + "; Localização -> " + this.localizacao);
    }
}
