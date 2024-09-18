package Ficha1;

import java.util.Scanner;

public class Exercicio4 {
    public static void main(String[] args) {
        int n = 0;
        System.out.print("Introduza o número: ");
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        while(n < 0 || n > 100){
            System.out.print("Introduza o número: ");
            Scanner sc2 = new Scanner(System.in);
            n = sc2.nextInt();
        }

        int multiplo = 0;
        for(int i = 0; i < 4; i++) {
            multiplo = n * i;
            System.out.println(multiplo);
        }
    }
}
