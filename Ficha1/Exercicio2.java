package Ficha1;

import java.util.Scanner;

public class Exercicio2 {
    public static void main(String[] args) {
        int limite = 0;
        System.out.print("Introduza o limite da soma: ");
        Scanner sc = new Scanner(System.in);
        limite = sc.nextInt();
        int soma = 0, i = 1;
        while(soma <= limite) {
            soma += i;
            i++;
        }
        System.out.printf("A soma deu %d e parou no número %d", soma, i-1);
    }
}
