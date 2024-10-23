package Ficha5.exercicio1;

public class Docente extends Pessoa{
    private int numeroMecanografico;
    public Docente(String nome, int numero){
        super(nome);
        this.numeroMecanografico = numero;
    }

    public String comunica() { //substitui metodo comunica da classe Pai (pessoa)
        return (this.nome + " a comunicar é um docente.");
    }

    public String missao(){
        return "ensinar";
    }

    public String toString(){
        return ("O Docente " + this.nome + " com o número mecanográfico " + this.numeroMecanografico + " tem uma missão de " + this.missao());
    }
}
