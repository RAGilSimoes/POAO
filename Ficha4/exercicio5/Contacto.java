package POAO.Ficha4.exercicio5;

public class Contacto {
    private String nome, email, morada, telefone;
    public Contacto (String nome, String email, String morada, String telefone) {
        this.nome = nome;
        this.email = email;
        this.morada = morada;
        this.telefone = telefone;
    }

    public String getNome(){
        return this.nome;
    }
    public String getEmail(){
        return this.email;
    }
    public String getMorada(){
        return this.morada;
    }
    public String getTelefone(){
        return this.telefone;
    }
}
