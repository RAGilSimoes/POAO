/**
 * @author Guilherme Carvalho e Ricardo Simoes
 * @version 1.0
 */

import java.util.ArrayList;

/**
 * Classe que representa o objeto Listar Clientes
 */
public class ListarClientes {
    /**
     * Listar clientes
     *
     * @param arrayClientes array de clientes
     */
    protected void listarClientes(ArrayList<Cliente> arrayClientes) {
        if (arrayClientes.isEmpty()) {
            System.out.println("\nA lista de clientes está vazia.");
        }
        else {
            System.out.println("\nLista dos clientes:");
            for (Cliente cliente: arrayClientes) {
                System.out.println("Cliente " + (arrayClientes.indexOf(cliente)+1) + ": " + cliente);
            }
        }
    }
}
