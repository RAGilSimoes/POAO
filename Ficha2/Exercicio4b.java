package POAO.Ficha2;

import java.util.Scanner;

public class Exercicio4b {
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

    public static int[] Eratosthenes(int[] n) {
        int[] numerosPrimos = new int[n.length];
        for(int i = 0; i < n.length; i++){
            if(ePrimo(i) == 1){
                numerosPrimos[i] = i;
            }
        }

        int quantidadeZeros = 0;
        for(int i = 0; i < numerosPrimos.length; i++){
            if(numerosPrimos[i] == 0){
                quantidadeZeros++;
            }
        }   

        int[] tabela = new int[numerosPrimos.length - quantidadeZeros];

        int numeroZerosPassados = 0;
        for(int i = 0; i < numerosPrimos.length; i++){
            if(numerosPrimos[i] == 0){
                numeroZerosPassados++;
            } else{
                tabela[i - numeroZerosPassados] = numerosPrimos[i];
            }
        }

        return tabela;
    }

    public static void main(String[] args) {
        System.out.print("Introduza a quantidade de numeros que pretende inserir: ");
        Scanner sc = new Scanner(System.in);
        int quantidadeNumeros = sc.nextInt();

        int[] tabela = new int[quantidadeNumeros];

        for(int i = 0; i < quantidadeNumeros; i++){
            System.out.print("Número: ");
            Scanner sc1 = new Scanner(System.in);
            int numero = sc1.nextInt();
            tabela[i] = numero;
        }

        int[] tabelaFinal = Eratosthenes(tabela);

        for(int i = 0; i < tabelaFinal.length; i++){
            System.out.printf("Número: %d ", tabelaFinal[i]);
        }
    }
}
