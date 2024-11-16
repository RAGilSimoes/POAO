package Projeto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class POOFS {
    ArrayList<Cliente> arrayClientes = new ArrayList<Cliente>();

    private void menus(String tipoMenu){
        switch (tipoMenu) {
            case "Menu Principal":
                System.out.println("\nIntroduz a ação que pretende efetuar: ");
                System.out.println("1 - Criar/Editar/Eliminar Cliente");
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
                System.out.println("3 - Eliminar cliente");
                System.out.println("4 - Voltar para o Menu Principal");
                break;
        }
    }


    private int protecaoEscolha(int menor, int maior){
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
        String escolha;
        boolean sair = false;

        do {
            menus("Menu Principal");
            Scanner scannerEscolha = new Scanner(System.in);
            escolha = scannerEscolha.nextLine();

            switch (escolha){
                case "1":
                    criarEditarEliminarCliente();
                    break;

                case "2":
                    listarClientes();
                    break;

                case "3":
                    break;

                case "4":
                    break;

                case "5":
                    break;

                case "6":
                    break;

                case "7":
                    break;

                case "8":
                    break;

                case "9":
                    System.out.println("\nAdeus 😢");
                    sair = true;
                    break;

                default:
                    System.out.println("\nIntroduza um caracter numérico válido!");
                    break;
            }
        } while(!sair);
    }


    private void criarEditarEliminarCliente() {
        boolean sair = false;
        do {
            String escolha;

            Scanner scannerCliente = new Scanner(System.in);

            String nome = null;
            String nif = null;
            String localizacao = null;
            String escolhaLocalizacao = null;

            boolean verificacaoNome= false;
            boolean verificacaoNIF = false;

            menus("Menu Clientes");

            Scanner scannerEscolha = new Scanner(System.in);
            escolha = scannerEscolha.nextLine();

            switch(escolha) {
                case "1":
                    Cliente clienteNovo = new Cliente(null, null, null);
                    while (!verificacaoNome) {
                        System.out.print("\nIntroduza o nome do cliente: ");
                        nome = scannerCliente.nextLine();
                        verificacaoNome = clienteNovo.verificaNome(nome, verificacaoNome);
                    }


                    while (!verificacaoNIF) {
                        System.out.print("Introduza o nif do cliente: ");
                        nif = scannerCliente.nextLine();
                        verificacaoNIF = clienteNovo.verificaNif(nif, verificacaoNIF, arrayClientes);
                    }

                    while(localizacao == null){
                        System.out.print("\nEscolha a localização do cliente: \n1-> Portugal | 2-> Madeira | 3-> Açores\n");
                        escolhaLocalizacao = scannerCliente.nextLine();
                        localizacao = clienteNovo.escolherLocalizao(escolhaLocalizacao);
                    }

                    clienteNovo = new Cliente(nome, nif, localizacao);
                    arrayClientes.add(clienteNovo);

                    System.out.println("\nCliente adicionado com sucesso!");
                    break;

                case "2":
                    if(arrayClientes.isEmpty()){
                        System.out.println("\nA lista de clientes está vazia.");
                        break;
                    } else {
                        listarClientes();

                        System.out.println("\nIntroduza o número do cliente que pretende alterar: ");
                        int numeroClienteProcurar = protecaoEscolha(1, arrayClientes.size());

                        Cliente clienteAlterar = arrayClientes.get(numeroClienteProcurar - 1);

                        System.out.println("\nCliente " + numeroClienteProcurar + ": " +  clienteAlterar);

                        clienteAlterar.alteraInformacoesCliente(clienteAlterar, arrayClientes);

                        System.out.println("\nCliente editado com sucesso!");
                        break;
                    }

                case "3":
                    if(arrayClientes.isEmpty()){
                        System.out.println("\nA lista de clientes está vazia.");
                        break;
                    } else {
                        listarClientes();

                        System.out.println("\nIntroduza o número do cliente que pretende eliminar: ");
                        int numeroClienteEliminar = protecaoEscolha(1, arrayClientes.size());

                        Cliente clienteEliminar = arrayClientes.get(numeroClienteEliminar - 1);

                        System.out.println("\nCliente " + numeroClienteEliminar + ": " +  clienteEliminar);

                        arrayClientes.remove(clienteEliminar);

                        System.out.println("\nCliente eliminado com sucesso!");
                        break;
                    }

                case "4":
                    sair = true;
                    break;

                default:
                    System.out.println("\nIntroduza um caracter numérico válido!");
                    break;
            }
        }while(!sair);
    }


    private void listarClientes() {
        if (arrayClientes.isEmpty()) {
            System.out.println("\nA lista de clientes está vazia.");
        }
        else {
            System.out.println("\nLista dos clientes:");
            for (int i = 0; i < arrayClientes.size(); i++) {
                System.out.println("Cliente " + (i+1) + ": " + arrayClientes.get(i));
            }
        }
    }
}