package Ficha2;

import java.util.Scanner;

public class Exercicio2 {
    public static void main(String[] args) {
        System.out.print("Introduza a quantidade de números inteiros das tabelas: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] tabela1 = new int[n];
        int[] tabela2 = new int[n];
        int[] tabela3 = new int[n * 2];

        for(int i = 0; i < n; i++) {
            tabela1[i] = (int) (Math.random() * 100);
        }

        for(int i = 0; i < n; i++) {
            tabela2[i] = (int) (Math.random() * 100);
        }

        System.out.print("Tabela 1: ");
        for(int i = 0; i < n; i++) {
            System.out.print(tabela1[i] + " ");
        }

        System.out.println();

        System.out.print("Tabela 2: ");
        for(int i = 0; i < n; i++) {
            System.out.print(tabela2[i] + " ");
        }

        int primeira = 0;
        int segunda = 0;

        for(int i = 1; i < (n*2)+1; i++){
            if(i % 2 != 0) {
                tabela3[i -1] = tabela1[primeira];
                primeira++;
            }else {
                tabela3[i -1] = tabela2[segunda];
                segunda++;
            }
        }

        System.out.println();
        for(int i = 0; i < (n * 2); i++) {
            System.out.print(tabela3[i] + " ");
        }
    }
}
