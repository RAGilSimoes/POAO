package Projeto;

import java.util.ArrayList;

public class ListarClientes {
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
