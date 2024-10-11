package POAO.Ficha3;

import java.util.Scanner;

public class Exercicio5 {
    public static void main(String[] args) {
        System.out.print("Introduza uma frase: ");
        Scanner sc = new Scanner(System.in);
        String fraseOriginal = sc.nextLine();
        int tamanhoFrase = fraseOriginal.length();

        System.out.print("Introduza uma palavra: ");
        Scanner sc2 = new Scanner(System.in);
        String palavra = sc2.nextLine();
        int tamanhoPalavra = palavra.length();

        int indice = 0;
        int contadorVezes = 0;
        String frase = fraseOriginal;

        while(indice != -1){
            indice = frase.indexOf(palavra);
            contadorVezes++;

            frase = (frase.substring(0, indice) + frase.substring(indice+tamanhoPalavra));

            indice = frase.indexOf(palavra);
        }

        System.out.println("A palavra: " + palavra + " aparece " + contadorVezes + " vezes na frase: " + fraseOriginal);
    }
}
