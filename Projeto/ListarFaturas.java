package Projeto;

import java.util.ArrayList;

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
}
