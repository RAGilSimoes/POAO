package Ficha5.exercicio2;

public class Exercicio2 {
    public static void main(String[] args) {
        Cafe cafe = new Cafe("cafe", 60, 100);
        System.out.println(cafe.toString());
        CafeExpresso cafeExpressoCurto = new CafeExpresso("cafe expresso curto", 70, 25);
        System.out.println(cafeExpressoCurto.toString());
        CafeExpresso cafeExpressoCheio = new CafeExpresso("cafe expresso cheio", 70, 50);
        System.out.println(cafeExpressoCheio.toString());
        CafeExpresso cafeExpressoNormal = new CafeExpresso("cafe expresso normal", 70, 35);
        System.out.println(cafeExpressoNormal.toString());

        System.out.println();

        Leite leite = new Leite("leite", 55, 150);
        System.out.println(leite.toString());
        EspumaLeite espumaLeite = new EspumaLeite("espuma de leite", 60, 80);
        System.out.println(espumaLeite.toString());

        System.out.println();

        Cappuccino cappuccino = new Cappuccino("cappucino", ((cafeExpressoCheio.temperatura + espumaLeite.temperatura) / 2), (cafeExpressoCheio.quantidade + espumaLeite.quantidade), cafeExpressoCheio, espumaLeite);
        System.out.println(cappuccino.toString());

        System.out.println();

        LatteMacchiato latteMacchiato = new LatteMacchiato("cappucino", ((cafeExpressoCheio.temperatura + espumaLeite.temperatura + leite.temperatura) / 3), (cafeExpressoCheio.quantidade + espumaLeite.quantidade + leite.quantidade), cafeExpressoCheio, espumaLeite, leite);
        System.out.println(latteMacchiato.toString());
    }
}

