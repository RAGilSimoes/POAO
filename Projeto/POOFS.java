package Projeto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class POOFS {
    ArrayList<Cliente> clientes = new ArrayList<Cliente>();

    public void menus(String tipoMenu){
        switch (tipoMenu) {
            case "Menu Principal":
                System.out.println("\nIntroduz a ação que pretende efetuar: ");
                System.out.println("1 - Criar/Editar Cliente");
                System.out.println("2 - Listar Clientes");
                System.out.println("3 - Criar/Editar Faturas");
                System.out.println("4 - Listar Faturas");
                System.out.println("5 - Visualizar Fatura");
                System.out.println("6 - Importar Fatura");
                System.out.println("7 - Exportar Fatura");
                System.out.println("8 - Estatísticas");
                System.out.println("9 - Sair");
                break;

            case "Menu Clientes":
                System.out.println("\nIntroduza a ação que pretende efetuar: ");
                System.out.println("1 - Criar cliente");
                System.out.println("2 - Editar cliente");
                System.out.println("3 - Voltar para o Menu Principal");
                break;
        }
    }


    public int protecaoEscolhaMenus(int menor, int maior, String tipoMenu){
        int escolha = 0;
        boolean sairVerificacao = false;
        do {
            try {
                Scanner scannerEscolha = new Scanner(System.in);
                escolha = scannerEscolha.nextInt();
                if(escolha < menor || escolha > maior){
                    System.out.println("\nIntroduza um caracter numérico válido!");
                    menus(tipoMenu);
                } else {
                    sairVerificacao = true;
                }
            } catch (InputMismatchException exception) {
                System.out.println("\nIntroduza um caracter numérico válido!");
                menus(tipoMenu);
            }
        } while(!sairVerificacao);

        return escolha;
    }


    public int protecaoEscolha(int menor, int maior){
        int escolha = 0;
        boolean sairVerificacao = false;
        do {
            try {
                Scanner scannerEscolha = new Scanner(System.in);
                escolha = scannerEscolha.nextInt();
                if(escolha < menor || escolha > maior){
                    System.out.println("\nIntroduza um caracter numérico válido!");
                } else {
                    sairVerificacao = true;
                }
            } catch (InputMismatchException exception) {
                System.out.println("\nIntroduza um caracter numérico válido!");
            }
        } while(!sairVerificacao);

        return escolha;
    }


    public void menu() {
        int escolha;
        boolean sair = false;

        do {
            menus("Menu Principal");
            escolha = protecaoEscolhaMenus(1, 9, "Menu Principal");

            switch (escolha){
                case 1:
                    criarEEditarCliente();
                    break;

                case 2:
                    listarClientes();
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
                    break;

                case 7:
                    break;

                case 8:
                    break;

                case 9:
                    System.out.println("\nAdeus 😢");
                    sair = true;
                    break;

                default:
                    break;
            }
        } while(!sair);
    }


    public void alteraInformacoesCliente(Cliente clienteRecebido) {
        String nome;
        String nif;
        String localizacao;

        Scanner editarCliente = new Scanner(System.in);

        System.out.print("\nIntroduza o novo nome do cliente: ");
        nome = editarCliente.nextLine();
        clienteRecebido.setNome(nome);
        System.out.print("Introduza o novo nif do cliente: ");
        nif = editarCliente.nextLine();
        clienteRecebido.setNif(nif);
        System.out.print("Introduza a nova localização do cliente: ");
        localizacao = editarCliente.nextLine();
        clienteRecebido.setLocalizacao(localizacao);
    }


    public void criarEEditarCliente() {
        menus("Menu Clientes");
        int escolha = protecaoEscolhaMenus(1,3, "Menu Clientes");

        Scanner scannerCliente = new Scanner(System.in);

        String nome;
        String nif;
        String localizacao;

        switch(escolha) {
            case 1:
                System.out.print("\nIntroduza o nome do cliente: ");
                nome = scannerCliente.nextLine();
                System.out.print("Introduza o nif do cliente: ");
                nif = scannerCliente.nextLine();
                System.out.print("Introduza a localização do cliente: ");
                localizacao = scannerCliente.nextLine();
                Cliente cliente = new Cliente(nome, nif, localizacao);
                clientes.add(cliente);

                System.out.println("\nCliente adicionado com sucesso!");
                break;

            case 2:
                if(clientes.isEmpty()){
                    System.out.println("\nA lista de clientes está vazia.");
                    break;
                } else {
                    listarClientes();

                    System.out.println("\nIntroduza o número do cliente que pretende alterar: ");
                    int numeroClienteProcurar = protecaoEscolha(1, clientes.size());

                    Cliente clienteAlterar = clientes.get(numeroClienteProcurar - 1);

                    System.out.println("\nCliente " + numeroClienteProcurar + ": " +  clienteAlterar);

                    alteraInformacoesCliente(clienteAlterar);

                    System.out.println("\nCliente editado com sucesso!");
                    break;
                }

            case 3:
                break;

            default:
                System.out.println("\nIntroduza um caracter numérico válido!");
        }
    }
}
