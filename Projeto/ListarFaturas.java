package Projeto;

import java.util.ArrayList;
import java.util.Scanner;

public class ListarFaturas {
    protected void listarFaturas(ArrayList<Fatura> arrayFaturas) {
        if(arrayFaturas.isEmpty()) {
            System.out.println("\nA lista de faturas está vazia.");
        } else {
            System.out.println("Lista de faturas: ");
            for(Fatura fatura: arrayFaturas) {
                System.out.println("Fatura " + (arrayFaturas.indexOf(fatura) + 1) + ": " + fatura);
            }
        }
    }
    protected void mostraFatura(ArrayList<Fatura> arrayFaturas) {
        Scanner scannerEscolha = new Scanner(System.in);
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        listarFaturas(arrayFaturas);
        System.out.println("Escolha uma fatura: ");
        int escolha = funcoesUteis.protecaoEscolha(1, arrayFaturas.size());
        Fatura faturaEscolhida = arrayFaturas.get(escolha);

    }


}

