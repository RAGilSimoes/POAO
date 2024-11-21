package Projeto;

import javax.management.loading.PrivateClassLoader;
import java.util.ArrayList;
import java.util.Scanner;

public class ListarFaturas {
    protected void listarFaturas(ArrayList<Fatura> arrayFaturas) {
        if (arrayFaturas.isEmpty()) {
            System.out.println("\nA lista de faturas está vazia.");

        } else {
            System.out.println("\nLista de faturas: ");
            for (Fatura fatura : arrayFaturas) {
                System.out.println("Fatura " + (arrayFaturas.indexOf(fatura) + 1) + ": " + fatura);
            }
        }
    }
}

