package POAO.Ficha2;

import java.util.Scanner;

public class Exercicio4 {
    public static int[] Eratosthenes(int n) {
        char[] numeros = new char[n];
        for(int i = 0; i < n; i++){
            if(i % 2 != 0){
                numeros[i] += 'a';
            }
        }

        int quantidade = 0;
        for(char c: numeros){
            if(c == 'a'){
                quantidade++;
            }
        }

        int[] tabela = new int[quantidade];

        for(int i = 0; i < quantidade; i++) {
            tabela[i] = (int) (Math.random() * 100);
        }

        return tabela;
    }

    public static void main(String[] args) {
        System.out.print("Introduza o limite: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] tabela = Eratosthenes(n);
        int quantidade = tabela.length;
        for(int i = 0; i < quantidade; i++){
            System.out.printf("Número: %d ", tabela[i]);
        }
    }
}
