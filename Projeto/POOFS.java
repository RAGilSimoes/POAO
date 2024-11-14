package Projeto;

import java.util.ArrayList;
import java.util.Scanner;

public class POOFS {
    ArrayList<Cliente> clientes = new ArrayList<Cliente>();

    public Cliente criarEEditarCliente() {
        int escolha;
        System.out.println("Introduza a ação que pretende efetuar: ");
        System.out.println("1 - Criar cliente;");
        System.out.println("2 - Editar cliente;");
        Scanner scannerEscolha = new Scanner(System.in);
        escolha = scannerEscolha.nextInt();
        Scanner scannerCliente = new Scanner(System.in);

        Cliente cliente = null;
        switch(escolha) {
            case 1:
                String nome;
                String nif;
                String localizacao;

                System.out.print("Introduza o nome do cliente: ");
                nome = scannerCliente.nextLine();
                System.out.print("Introduza o nif do cliente: ");
                nif = scannerCliente.nextLine();
                System.out.print("Introduza a localização do cliente: ");
                localizacao = scannerCliente.nextLine();
                cliente = new Cliente(nome, nif, localizacao);
                clientes.add(cliente);
                break;

            case 2:
                System.out.println("Adeus;");
                break;

        }

        return cliente;
    }
}
