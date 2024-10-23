package Ficha5.exercicio2;

public class Cafe extends Bebida{
    public Cafe(String nome, int temperatura, int quantidade){
        super(nome, temperatura, quantidade);
    }

    public String toString(){
        return ("O " + this.nome + " é servido a " + this.temperatura + " graus Celsius em doses de " + this.quantidade + "ml");
    }
}
//-------------------------------------------------------------------
class CafeExpresso extends Cafe{
    public CafeExpresso(String nome, int temperatura, int quantidade) {
        super(nome, temperatura, quantidade);
    }
}

