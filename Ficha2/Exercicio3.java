package Ficha2;

import java.util.Scanner;

public class Exercicio3 {
    public static void main(String[] args) {
       System.out.print("Introduza a quantidade de linhas da primeira matriz: ");
       Scanner linhas1 = new Scanner(System.in);
       int n1 = linhas1.nextInt();

        System.out.print("Introduza a quantidade de colunas da primeira matriz: ");
        Scanner colunas1 = new Scanner(System.in);
        int m1 = colunas1.nextInt();

        System.out.print("Introduza a quantidade de linhas da segunda matriz: ");
        Scanner linhas2 = new Scanner(System.in);
        int n2 = linhas2.nextInt();

        System.out.print("Introduza a quantidade de colunas da segunda matriz: ");
        Scanner colunas2 = new Scanner(System.in);
        int m2 = colunas2.nextInt();

        if(m1 != n2){
            System.out.println("O número de colunas da primeira matriz não pode ser diferente do número de linhas da segunda matriz!");
        }else{
            int[][] matriz1 = new int[n1][m1];
            int[][] matriz2 = new int[n2][m2];
            int[][] matriz3 = new int[n1][m2];

            for(int i = 0; i < n1; i++){
                for(int l = 0; l < m1; l++) {
                    matriz1[i][l] = (int)((Math.random() * 10) + 1);
                }
            }

            for(int i = 0; i < n2; i++){
                for(int l = 0; l < m2; l++) {
                    matriz2[i][l] = (int)((Math.random() * 10) + 1);
                }
            }

            System.out.println("Tabela 1: ");
            for(int i = 0; i < n1; i++){
                for(int l = 0; l < m1; l++) {
                    System.out.print(matriz1[i][l] + " ");
                }
                System.out.println();
            }

            System.out.println("Tabela 2: ");
            for(int i = 0; i < n2; i++){
                for(int l = 0; l < m2; l++) {
                    System.out.print(matriz2[i][l] + " ");
                }
                System.out.println();
            }

            for(int i = 0; i < matriz3.length; i++) {
                for(int l = 0; l < matriz3[i].length; l++){
                    for(int k = 0; k < matriz1[i].length; k++){
                        matriz3[i][l] += matriz1[i][k] * matriz2[k][l];
                    }
                }
            }

            System.out.println("Tabela final: ");
            for(int i = 0; i < matriz3.length; i++){
                for(int l = 0; l < matriz3[i].length; l++) {
                    System.out.print(matriz3[i][l] + " ");
                }
                System.out.println();
            }

        }
    }
}
