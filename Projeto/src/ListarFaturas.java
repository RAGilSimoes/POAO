/**
 * @author Guilherme Carvalho e Ricardo Simoes
 * @version 1.0
 */

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que representa o objeto Listar faturas
 */
public class ListarFaturas implements Serializable {
    /**
     * Listar faturas
     *
     * @param arrayFaturas array de faturas
     */
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

