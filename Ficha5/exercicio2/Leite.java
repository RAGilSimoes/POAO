package Ficha5.exercicio2;

public class Leite extends Bebida{
    public Leite(String nome, int temperatura, int quantidade) {
        super(nome, temperatura, quantidade);
    }

    public String toString(){
        return ("O " + this.nome + " é servido a " + this.temperatura + " graus Celsius em doses de " + this.quantidade + "ml");
    }
}

class EspumaLeite extends Leite {
    public EspumaLeite(String nome, int temperatura, int quantidade){
        super(nome, temperatura, quantidade);
    }

    public String toString(){
        return ("A " + this.nome + " é servida a " + this.temperatura + " graus Celsius em doses de " + this.quantidade + "ml");
    }
}
