package Projeto.src;

import java.util.ArrayList;

/**
 * The type Listar clientes.
 */
public class ListarClientes {
    /**
     * Listar clientes.
     *
     * @param arrayClientes the array clientes
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
