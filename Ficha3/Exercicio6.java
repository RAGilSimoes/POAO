package Ficha3;

import java.util.Scanner;

public class Exercicio6 {
    public static void main(String[] args) {
        System.out.print("Introduza uma frase: ");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] stringSeparada = s.split(" ");
        String[] novaString = new String[stringSeparada.length];

        int introduzidas = 0;
        for(int i = 0; i < stringSeparada.length; i++){
            int contaNumeroA = 0;
            for(int l = 0; l < stringSeparada[i].length(); l++){
                if(stringSeparada[i].toCharArray()[l] == 'a'){
                    contaNumeroA++;
                }
            }

            if(contaNumeroA >= 2){
                novaString[introduzidas] = stringSeparada[i];
                introduzidas++;
            }
        }

        int quantidadeBrancos = 0;
        for(int i = 0; i < novaString.length; i++){
            if (novaString[i] == null) {
                quantidadeBrancos++;
            }
        }
        String[] stringFinal = new String[novaString.length - quantidadeBrancos];
        for(int i = 0; i < stringFinal.length; i++){
            stringFinal[i] = novaString[i];
        }

        for(int i = 0; i < stringFinal.length; i++){
            System.out.print(stringFinal[i] + " ");
        }

    }
}
