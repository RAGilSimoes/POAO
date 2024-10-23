package Ficha5.exercicio1;

public class Pessoa {
    protected String nome;
    public Pessoa(String nome) {
        this.nome = nome;
    }
    public String comunica(){
        return (this.nome + " a comunicar é uma pessoa.");
    }
}

