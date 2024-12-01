import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Poofs.
 */
public class POOFS {
    /**
     * The Array clientes.
     */
    ArrayList<Cliente> arrayClientes = new ArrayList<Cliente>();
    /**
     * The Array faturas.
     */
    ArrayList<Fatura> arrayFaturas = new ArrayList<Fatura>();

    ArrayList<Produto> arrayProdutos = new ArrayList<Produto>();


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
                System.out.println("9 - Help");
                System.out.println("0 - Sair");
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

            case "Help":
                System.out.println("\nFormatação de cada objeto no ficheiro texto das informações: ");
                System.out.println("Cliente -> C#Nome/NIF/Localização");
                System.out.println("Produto Alimentar Taxa Reduzida -> R#Código/Nome/Descrição/Quantidade/ValorPorUnidade/Biológico/Certificações");
                System.out.println("Produto Alimentar Taxa Intermedia -> I#Código/Nome/Descrição/Quantidade/ValorPorUnidade/Biológico/Categoria");
                System.out.println("Produto Alimentar Taxa Normal -> N#Código/Nome/Descrição/Quantidade/ValorPorUnidade/Biológico");
                System.out.println("Produto Farmácia Com Prescrição -> P#Código/Nome/Descrição/Quantidade/ValorPorUnidade/Prescrição/NomeMedico");
                System.out.println("Produto Farmácia Sem Prescrição -> S#Código/Nome/Descrição/Quantidade/ValorPorUnidade/Prescrição/Categoria");
                System.out.println("Fatura -> F#NFatura;NIFCliente;Data;CódigoProduto/CódigoProduto");
        }
    }


    /**
     * Menu.
     */
    public void menu() {
        String escolha;
        boolean sair = false;
        ListarFaturas listarFaturas = new ListarFaturas();
        ListarClientes listarClientes = new ListarClientes();
        TrataInformacoesFicheiros trataInformacoesFicheiros = new TrataInformacoesFicheiros();
        importaInformacoesAutomaticamente(arrayFaturas, arrayClientes, arrayProdutos);

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
                    trataInformacoesFicheiros.leFicheiroTexto(arrayClientes, arrayFaturas, arrayProdutos);
                    break;

                case "7":
                    trataInformacoesFicheiros.escreveFicheiroTextoInformacoes(arrayClientes, arrayFaturas, arrayProdutos);
                    break;

                case "8":
                    estatistica(arrayFaturas);
                    break;

                case "9":
                    menus("Help");
                    break;

                case "0":
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
                        if(!arrayFaturas.isEmpty()){
                            for(Fatura fatura: arrayFaturas){
                                if(fatura.getCliente() == clienteEliminar){
                                    arrayFaturas.remove(fatura);
                                    System.out.println("\nFatura Nº " + fatura.getnFatura() + " removida com sucesso!");
                                    temFaturas = true;
                                    break;
                                }
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
        Scanner scannerEscolha = new Scanner(System.in);
        do {
            String escolha;

            menus("Menu Faturas");

            escolha = scannerEscolha.nextLine();

            switch(escolha) {
                case "1":
                    if(arrayClientes.isEmpty()){
                        System.out.println("\nA lista de clientes está vazia, por favor crie um cliente antes de tentar inserir uma fatura.");
                        break;
                    } else {
                        Fatura fatura = new Fatura(null, null, null, null, 0, 0);
                        fatura = fatura.criaFatura(arrayClientes, arrayFaturas, arrayProdutos);
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

                        faturaAlterar.alteraInformacoesFatura(faturaAlterar, arrayFaturas, arrayClientes, arrayProdutos);

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

    private void mostraFatura(ArrayList<Fatura> arrayFaturas) {
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        ListarFaturas listarFaturas = new ListarFaturas();
        listarFaturas.listarFaturas(arrayFaturas);
        if (!arrayFaturas.isEmpty()) {
            System.out.print("\nEscolha uma fatura: ");
            int escolha = funcoesUteis.protecaoEscolha(1, arrayFaturas.size());
            Fatura faturaEscolhida = arrayFaturas.get(escolha - 1);
            System.out.println("\n" + faturaEscolhida);
            ArrayList<Produto> arrayProdutos = faturaEscolhida.getListaProdutos();
            for (Produto produto : arrayProdutos) {
                System.out.println(produto);
            }
            double valorSemIVA = faturaEscolhida.getValorTotalSemIVA();
            double valorComIVA = faturaEscolhida.getValorTotalComIVA();
            double valorIVA = (valorComIVA - valorSemIVA);
            System.out.printf("Valor total sem IVA da fatura -> %.2f€", valorSemIVA);
            System.out.printf("; Valor total do IVA da fatura -> %.2f€", valorIVA);
            System.out.printf("; Valor total com IVA da fatura -> %.2f€\n", valorComIVA);
        }
    }

    private void estatistica(ArrayList<Fatura> arrayFaturas){
        int numFaturas = arrayFaturas.size();
        int numProdutos = 0;
        double valorSemIVA = 0.0;
        double valorIVA = 0.0;
        double valorComIVA = 0.0;
        ArrayList<Produto> arrayProdutos = null;
        for (Fatura fatura: arrayFaturas){
             arrayProdutos = fatura.getListaProdutos();
             numProdutos += arrayProdutos.size();
             Cliente cliente = fatura.getCliente();
             valorSemIVA += fatura.calcularValorTotalSemIVA(arrayProdutos);
             valorIVA += (valorComIVA - valorSemIVA);
             valorComIVA += fatura.calcularValorTotalComIVA(arrayProdutos, cliente);
        }
        System.out.print("\nNúmero de faturas -> " + numFaturas);
        System.out.print("\nNúmero de produtos -> " + numProdutos);
        System.out.print("\nValor total sem IVA -> " + valorSemIVA);
        System.out.print("\nValor total do IVA -> " + valorIVA);
        System.out.println("\nValor total com IVA -> " + valorComIVA);
    }

    private void importaInformacoesAutomaticamente(ArrayList<Fatura> arrayFaturas, ArrayList<Cliente> arrayClientes, ArrayList<Produto> arrayProdutos) {
        TrataInformacoesFicheiros tratamentoInformacoesFicheiros = new TrataInformacoesFicheiros();
        boolean existeFicheiroObjeto = tratamentoInformacoesFicheiros.verificaExistenciaFicheiroObjeto();
        if(existeFicheiroObjeto){
            System.out.println("Se estás a ver isto é porque existe ficheiro objeto");
        } else {
            tratamentoInformacoesFicheiros.leFicheiroTexto(arrayClientes, arrayFaturas, arrayProdutos);
        }
    }
}