package POAO.Ficha1;

import java.util.Scanner;

public class Exercicio9 {
    public static int ePrimo (int x) {
        int resultado = 0;
        int verdade = 0;
        for(int i = 1; i <= x; i++) {
            resultado = (x % i);
            if(resultado == 0 && i == 1 || i == x) {
                verdade = 1;
            }else {
                if(resultado == 0) {
                    verdade = 0;
                    break;
                }
            }
        }
        return verdade;
    }
    public static void main(String[] args) {
        int numero = 0;
        System.out.print("Introduza um número: ");
        Scanner sc = new Scanner(System.in);
        numero = sc.nextInt();

        int resultado = ePrimo(numero);
        if(resultado == 1) {
            System.out.println("Primo");
        }else {
            System.out.println("Não Primo");
        }
    }
}
