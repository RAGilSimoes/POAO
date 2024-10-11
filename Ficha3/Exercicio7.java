package POAO.Ficha3;

import java.io.DataInput;
import java.util.Scanner;

public class Exercicio7 {
    public static void main(String[] args) {
        System.out.print("Introduza uma data: ");
        Scanner sc = new Scanner(System.in);
        String dataIntroduzida = sc.nextLine();

        String[] dataIntroduzidaSeparada = dataIntroduzida.split("/");
        int dia = Integer.parseInt(dataIntroduzidaSeparada[0]);
        int mes = Integer.parseInt(dataIntroduzidaSeparada[1]);
        int ano = Integer.parseInt(dataIntroduzidaSeparada[2]);

        String mesEscolhido = "";

        switch (mes){
            case 1:
                mesEscolhido = "janeiro";
                break;
            case 2:
                mesEscolhido = "fevereiro";
                break;
            case 3:
                mesEscolhido = "março";
                break;
            case 4:
                mesEscolhido = "abril";
                break;
            case 5:
                mesEscolhido = "maio";
                break;
            case 6:
                mesEscolhido = "junho";
                break;
            case 7:
                mesEscolhido = "julho";
                break;
            case 8:
                mesEscolhido = "agosto";
                break;
            case 9:
                mesEscolhido = "setembro";
                break;
            case 10:
                mesEscolhido = "outubro";
                break;
            case 11:
                mesEscolhido = "novembro";
                break;
            case 12:
                mesEscolhido = "dezembro";
                break;
        }

        System.out.println(dia + " de " + mesEscolhido + " de " + ano);

    }
}
