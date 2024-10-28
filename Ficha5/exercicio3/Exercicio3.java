package Ficha5.exercicio3;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercicio3 {
    public static void main(String[] args) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String[][] arrayClientes = {{"Ricardo", "202", "Prazo", "505.5"}, {"Manel", "203", "Ordem", "400"}};
        for(int i = 0; i < arrayClientes.length; i++) {
            Cliente cliente = new Cliente(arrayClientes[i][0], Integer.parseInt(arrayClientes[i][1]), arrayClientes[i][2], Double.parseDouble(arrayClientes[i][3]));
            clientes.add(cliente);
        }
        for(int i = 0; i < clientes.size(); i++) {
            Cliente clienteTrabalhado = clientes.get(i);
            System.out.println(clienteTrabalhado.toString());
            clienteTrabalhado.levantamentos();
            System.out.println("Saldo -> " + clienteTrabalhado.getSaldo());
            System.out.println("-------------------");
        }

        ArrayList<Cliente> clientesComValor = new ArrayList<>();
        System.out.println("Introduza um valor: ");
        Scanner sc = new Scanner(System.in);
        double valor = sc.nextDouble();
        for(int i = 0; i < clientes.size(); i++) {
           Cliente clienteTrabalhado = clientes.get(i);
           if(clienteTrabalhado.getSaldo() >= valor) {
               clientesComValor.add(clienteTrabalhado);
           }
        }

        System.out.println("Lista de clientes com saldo superior a " + valor +"€: ");
        for(int i = 0; i < clientesComValor.size(); i++) {
            System.out.println(clientesComValor.get(i).toString());
        }
    }
}
