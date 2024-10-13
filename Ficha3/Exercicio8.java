package Ficha3;

import java.util.Scanner;

public class Exercicio8 {
    public static void main(String[] args) {
        System.out.print("Introduza o ISBN do livro: ");
        Scanner sc = new Scanner(System.in);
        String isbn = sc.nextLine();
        String[] isbnSeparado = isbn.split(" ");


        int[] s1 = new int[isbnSeparado.length];
        s1[0] = Integer.parseInt(isbnSeparado[0]);

        for(int i = 1; i < s1.length; i++){
            s1[i] = (s1[i-1] + Integer.parseInt(isbnSeparado[i]));
        }

        int[] s2 = new int[isbnSeparado.length];
        s2[0] = s1[0];

        for(int i = 1; i < s2.length; i++){
            s2[i] = (s2[i - 1] + s1[i]);
        }

        for(int i = 0; i < s1.length; i++){
            System.out.print(s1[i] + " ");
        }

        System.out.println();

        for(int i = 0; i < s2.length; i++){
            System.out.print(s2[i] + " ");
        }

        System.out.println();

        if((s2[s2.length - 1] % 11) != 0) {
            System.out.println("O ISBN fornecido é inválido!");
        } else {
            System.out.println("O ISBN fornecido é válido!");
        }
    }
}
