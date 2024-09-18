package Ficha1;

import java.util.Scanner;

public class Exercicio3 {
    public static void main(String[] args) {
        int decimal = 0;
        System.out.print("Introduza o número em decimal: ");
        Scanner sc = new Scanner(System.in);
        decimal = sc.nextInt();
        int resultado = 0;
        int i = 0;

        while(decimal > 0) {
            if(decimal % 10 == 1){
                resultado += Math.pow(2, i);
            }
            decimal /= 10;
            i++;
        }
        System.out.println(resultado);
    }
}
