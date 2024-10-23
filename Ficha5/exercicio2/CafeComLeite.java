package Ficha5.exercicio2;

public class CafeComLeite extends Bebida{
    public CafeComLeite(String nome, int temperatura, int quantidade) {
        super(nome, temperatura, quantidade);
    }

    public String toString(){
        return ("O " + this.nome + " é servido a " + this.temperatura + " graus Celsius em doses de " + this.quantidade + "ml");
    }
}

class Cappuccino extends CafeComLeite {
    private Cafe cafe;
    private Leite leite;
    public Cappuccino(String nome, int temperatura, int quantidade, Cafe CafeExpresso, Leite EspumaLeite) {
        super(nome, temperatura, quantidade);
        this.cafe = CafeExpresso;
        this.leite = EspumaLeite;
    }

    public String toString(){
        return ("O " + this.nome + " é constituido por um " + this.cafe.nome + " e por " + this.leite.nome);
    }
}

class LatteMacchiato extends CafeComLeite {
    private Cafe cafe;
    private Leite leite;
    private Leite leite2;
    public LatteMacchiato(String nome, int temperatura, int quantidade, Cafe CafeExpresso, Leite EspumaLeite, Leite leite) {
        super(nome, temperatura, quantidade);
        this.cafe = CafeExpresso;
        this.leite = EspumaLeite;
        this.leite2 = leite;
    }

    public String toString(){
        return ("O " + this.nome + " é constituido por um " + this.cafe.nome + ", por " + this.leite.nome + " e por " + this.leite2.nome);
    }
}
