package Ficha5.exercicio1;

public class Aluno extends Pessoa {
    private int numero;
    public Aluno(String nome, int numero) {
        super(nome); //cria objeto da classe Pai (pessoa)
        this.numero = numero; //valor proprio da classe Aluno
    }

    public String comunica() { //substitui metodo comunica da classe Pai (pessoa)
        return (this.nome + " a comunicar é um aluno.");
    }

    public int getNumero(){ //metodo proprio do classe Aluno
        return this.numero;
    }
}
