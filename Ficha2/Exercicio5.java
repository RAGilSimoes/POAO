package POAO.Ficha2;

import java.util.Scanner;

public class Exercicio5 {
    public static void main(String[] args) {
        int row = 5;
        int column = 10;
        int[][] tableA = new int[row][column];
        int[][] tableB = new int[row][column];

        tabelaA(tableA, row, column);
        printTabela(tableA, row, column);

        System.out.println();

        tabelaB(tableB, row, column);
        printTabela(tableB, row, column);
    }

    public static void tabelaA(int[][] table, int row, int column){
        for(int i = 0; i < row; i++){
            for(int l = 0; l < column; l++){
                table[i][l] = (int) (Math.random() * 100);
            }
        }
    }

    public static void tabelaB(int[][] table, int row, int column) {
        int k = 0;
        for(int i = 0; i < row; i++){
            for(int l = 0; l < column; l++){
                table[i][l] = k;
                k++;
            }
        }
    }

    public static void printTabela(int[][] table, int row, int column){
        for(int i = 0; i < row; i++){
            for(int l = 0; l < column; l++){
                System.out.print(table[i][l] + " ");
            }
            System.out.println();
        }
    }
}
