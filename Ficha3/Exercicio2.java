package Ficha3;

import java.util.Scanner;

public class Exercicio2 {
    public static void main(String[] args) {
        int quantidadeVogais = 0;
        String vogais = "aeiouAEIOU";
        System.out.print("Introduza uma string: ");
        Scanner sc = new Scanner(System.in);
        String recebida = sc.nextLine();
        char[] recebidaCaracteres = recebida.toCharArray();
        for(char caracter : recebidaCaracteres){
            if(vogais.indexOf(caracter) != -1){
                quantidadeVogais++;
            }
        }
        System.out.println("Existem " + quantidadeVogais + " vogais na string: " + recebida);
    }
}
