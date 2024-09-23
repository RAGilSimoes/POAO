package POAO.Ficha2;

import java.util.Scanner;

public class Exercicio4 {
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

    public static int[] Eratosthenes(int n) {
        int[] numeros = new int[n];
        for(int i = 2; i <= n; i++){
            int verdade = ePrimo(i);
            if(verdade == 1){
                numeros[i-2] = i;
            }
        }

        int quantidadeZeros = 0;
        for(int i = 0; i < (numeros.length - 1); i++){
            if(numeros[i] == 0){
                quantidadeZeros++;
            }
        }

        int[] tabela = new int[n - quantidadeZeros - 1];

        int numeroZerosPassados = 0;
        for(int i = 0; i < (numeros.length - 1); i++){
            if(numeros[i] == 0){
                numeroZerosPassados++;
            } else{
                tabela[i - numeroZerosPassados] = numeros[i];
            }
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
