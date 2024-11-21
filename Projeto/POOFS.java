package Projeto;

import java.util.ArrayList;
import java.util.Scanner;

public class POOFS {
    ArrayList<Cliente> arrayClientes = new ArrayList<Cliente>();
    ArrayList<Fatura> arrayFaturas = new ArrayList<Fatura>();


    private void menus(String tipoMenu){
        switch (tipoMenu) {
            case "Menu Principal":
                System.out.println("\nIntroduz a ação que pretende efetuar: ");
                System.out.println("1 - Criar/Editar/Eliminar Cliente");
                System.out.println("2 - Listar Clientes");
                System.out.println("3 - Criar/Editar/Eliminar Fatura");
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

            case "Menu Faturas":
                System.out.println("\nIntroduza a ação que pretende efetuar: ");
                System.out.println("1 - Criar fatura");
                System.out.println("2 - Editar fatura");
                System.out.println("3 - Eliminar fatura");
                System.out.println("4 - Voltar para o Menu Principal");
                break;
        }
    }


    public void menu() {
        String escolha;
        boolean sair = false;
        ListarFaturas listarFaturas = new ListarFaturas();
        ListarClientes listarClientes = new ListarClientes();

        do {
            menus("Menu Principal");
            Scanner scannerEscolha = new Scanner(System.in);
            escolha = scannerEscolha.nextLine();

            switch (escolha){
                case "1":
                    criarEditarEliminarCliente();
                    break;

                case "2":
                    listarClientes.listarClientes(arrayClientes);
                    break;

                case "3":
                    criarEditarEliminarFaturas();
                    break;

                case "4":
                    listarFaturas.listarFaturas(arrayFaturas);
                    break;

                case "5":
                    mostraFatura(arrayFaturas);
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
        ListarClientes listarClientes = new ListarClientes();
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        boolean sair = false;
        do {
            String escolha;

            menus("Menu Clientes");

            Scanner scannerEscolha = new Scanner(System.in);
            escolha = scannerEscolha.nextLine();

            switch(escolha) {
                case "1":
                    Cliente clienteNovo = new Cliente(null, null, null);

                    clienteNovo = clienteNovo.criaCliente(arrayClientes);
                    arrayClientes.add(clienteNovo);

                    System.out.println("\nCliente adicionado com sucesso!");
                    break;

                case "2":
                    if(arrayClientes.isEmpty()){
                        System.out.println("\nA lista de clientes está vazia.");
                        break;
                    } else {
                        listarClientes.listarClientes(arrayClientes);

                        System.out.println("\nIntroduza o número do cliente que pretende editar: ");
                        int numeroClienteProcurar = funcoesUteis.protecaoEscolha(1, arrayClientes.size());

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
                        listarClientes.listarClientes(arrayClientes);

                        System.out.println("\nIntroduza o número do cliente que pretende eliminar: ");

                        int numeroClienteEliminar = funcoesUteis.protecaoEscolha(1, arrayClientes.size());

                        Cliente clienteEliminar = arrayClientes.get(numeroClienteEliminar - 1);

                        System.out.println("\nCliente " + numeroClienteEliminar + ": " +  clienteEliminar);

                        boolean temFaturas = false;
                        for(Fatura fatura: arrayFaturas){
                            if(fatura.getCliente() == clienteEliminar){
                                arrayFaturas.remove(fatura);
                                System.out.println("\nFatura Nº " + fatura.getnFatura() + " removida com sucesso!");
                                temFaturas = true;
                            }
                        }
                        if(!temFaturas){
                            System.out.println("\nO cliente não tinha faturas associadas.");
                        }
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


    private void criarEditarEliminarFaturas() {
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        boolean sair = false;
        ListarFaturas listarFaturas = new ListarFaturas();
        do {
            String escolha;

            menus("Menu Faturas");

            Scanner scannerEscolha = new Scanner(System.in);
            escolha = scannerEscolha.nextLine();

            switch(escolha) {
                case "1":
                    if(arrayClientes.isEmpty()){
                        System.out.println("\nA lista de clientes está vazia, por favor crie um cliente antes de tentar inserir uma fatura.");
                        break;
                    } else {
                        Fatura fatura = new Fatura(null, null, null, null, 0, 0);
                        fatura = fatura.criaFatura(arrayClientes, arrayFaturas);
                        arrayFaturas.add(fatura);
                        System.out.println("\nFatura adicionada com sucesso!");
                        break;
                    }

                case "2":
                    if(arrayFaturas.isEmpty()){
                        System.out.println("\nA lista de faturas está vazia.");
                        break;
                    } else {
                        listarFaturas.listarFaturas(arrayFaturas);

                        System.out.println("\nIntroduza o número da fatura que pretende alterar: ");
                        int numeroFaturaProcurar = funcoesUteis.protecaoEscolha(1, arrayClientes.size());

                        Fatura faturaAlterar = arrayFaturas.get(numeroFaturaProcurar - 1);

                        System.out.println("\nFatura " + numeroFaturaProcurar + ": " +  faturaAlterar);

                        faturaAlterar.alteraInformacoesFatura(faturaAlterar, arrayFaturas, arrayClientes);

                        System.out.println("\nFatura editada com sucesso!");
                        break;
                    }

                case "3":
                    if(arrayFaturas.isEmpty()){
                        System.out.println("\nA lista de faturas está vazia.");
                        break;
                    } else {
                        listarFaturas.listarFaturas(arrayFaturas);

                        System.out.println("\nIntroduza o número da fatura que pretende eliminar: ");

                        int numeroFaturaEliminar = funcoesUteis.protecaoEscolha(1, arrayClientes.size());

                        Fatura faturaEliminar = arrayFaturas.get(numeroFaturaEliminar - 1);

                        System.out.println("\nFatura " + numeroFaturaEliminar + ": " + faturaEliminar);

                        arrayFaturas.remove(faturaEliminar);

                        System.out.println("\nFatura eliminada com sucesso!");
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

    protected void mostraFatura(ArrayList<Fatura> arrayFaturas) {
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        ListarFaturas listarFaturas = new ListarFaturas();
        listarFaturas.listarFaturas(arrayFaturas);
        if (!arrayFaturas.isEmpty()) {
            System.out.print("\nEscolha uma fatura: ");
            int escolha = funcoesUteis.protecaoEscolha(1, arrayFaturas.size());
            Fatura faturaEscolhida = arrayFaturas.get(escolha - 1);
            System.out.print("\nFatura: " + faturaEscolhida);
            ArrayList<Produto> arrayProdutos = faturaEscolhida.getListaProdutos();
            for (Produto produto : arrayProdutos) {
                System.out.print(produto);
            }
            System.out.print("; Valor total sem IVA da fatura -> " + faturaEscolhida.getValorTotalSemIVA() + "€");
            System.out.print("; Valor total do IVA da fatura -> " + faturaEscolhida.valorTotalIVA(arrayFaturas) + "%");
            System.out.println("; Valor total com IVA da fatura -> " + faturaEscolhida.getValorTotalComIVA() + "€");
        }
    }

    private void estatistica(ArrayList<Fatura> arrayFaturas){
        int numFaturas = arrayFaturas.size();
        int numProdutos = 0;
        double valorSemIVA = 0.0;
        ArrayList<Produto> arrayProdutos = null;
        for (Fatura fatura: arrayFaturas){
             arrayProdutos = fatura.getListaProdutos();
             numProdutos += arrayProdutos.size();
             valorSemIVA += fatura.getValorTotalSemIVA();

        }

    }
}