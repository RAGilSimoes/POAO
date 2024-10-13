package Ficha3;

import java.util.Scanner;

public class Exercicio3 {
    public static void main(String[] args) {
        System.out.print("Introduza uma string: ");
        Scanner sc = new Scanner(System.in);
        String recebida = sc.nextLine();
        char[] recebidaCaracteres = recebida.toCharArray();

        int palindromo = 0;

        int tamanho = 0;

        if((recebida.length() % 2) == 0){
            tamanho = (recebida.length() / 2);
        } else {
            tamanho = ((recebida.length() / 2) + 1);
        }

        for(int i = 0; i < tamanho; i++){
            if(recebidaCaracteres[i] == recebidaCaracteres[recebida.length() - 1]){
                palindromo = 1;
            }
            else {
                palindromo = 0;
            }
        }
        if(palindromo == 0){
            System.out.println("A palavra " + recebida + " não é palindroma");
        } else {
            System.out.println("A palavra " + recebida + " é palindroma");
        }
    }
}
