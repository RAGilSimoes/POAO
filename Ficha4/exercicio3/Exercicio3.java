package Ficha4.exercicio3;

import java.util.Scanner;

public class Exercicio3 {
    public static void main(String[] args) {
        System.out.print("Introduza uma fração no seguinte formato (numerador/denominador): ");
        Scanner sc = new Scanner(System.in);
        String primeiraFracaoString = sc.nextLine();

        System.out.print("Introduza outra fração no mesmo formato: ");
        String segundaFracaoString = sc.nextLine();

        Fracao primeiraFracao = new Fracao(primeiraFracaoString);

        Fracao segundaFracao = new Fracao(segundaFracaoString);

        primeiraFracao.soma(segundaFracao);
        primeiraFracao.subtracao(segundaFracao);
        primeiraFracao.multiplicacao(segundaFracao);
        primeiraFracao.divisao(segundaFracao);
    }
}

