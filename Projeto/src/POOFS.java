/**
 * @author Guilherme Carvalho e Ricardo Simoes
 * @version 1.0
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe que representa o objeto POOFS
 */
public class POOFS {
    /**
     * Objeto Funcoes Uteis
     */
    FuncoesUteis funcoesUteis = new FuncoesUteis();
    /**
     * Objeto Listar Faturas
     */
    ListarFaturas listarFaturas = new ListarFaturas();
    /**
     * Objeto Listar Clientes
     */
    ListarClientes listarClientes = new ListarClientes();
    /**
     * Objeto Trata Informacoes Ficheiros
     */
    TrataInformacoesFicheiros trataInformacoesFicheiros = new TrataInformacoesFicheiros();
    /**
     * Array de clientes
     */
    private ArrayList<Cliente> arrayClientes = new ArrayList<Cliente>();
    /**
     * Array de faturas
     */
    private ArrayList<Fatura> arrayFaturas = new ArrayList<Fatura>();

    /**
     * Array produtos
     */
    private ArrayList<Produto> arrayProdutos = new ArrayList<Produto>();


    private void apresentaMenu(String tipoMenu){
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
     * Menu
     */
    protected void menu() {
        String escolha;
        boolean sair = false;
        final String nomeFicheiroTextoInformacoes = "informacoes.txt";
        final String nomeFicheiroFaturas = "faturas.txt";
        final String nomeFicheiroObjetos = "projeto.obj";
        importaInformacoesAutomaticamente(arrayFaturas, arrayClientes, arrayProdutos, nomeFicheiroTextoInformacoes, nomeFicheiroObjetos);

        do {
            apresentaMenu("Menu Principal");
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
                    importaFatura(arrayClientes, arrayProdutos, arrayFaturas, nomeFicheiroFaturas);
                    break;

                case "7":
                    exportaFatura(arrayFaturas, nomeFicheiroFaturas);
                    break;

                case "8":
                    estatistica(arrayFaturas);
                    break;

                case "9":
                    apresentaMenu("Help");
                    break;

                case "0":
                    System.out.println("\nAdeus 😢");
                    trataInformacoesFicheiros.escreveParaFicheiroObjeto(nomeFicheiroObjetos, arrayClientes, arrayProdutos, arrayFaturas);
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

            apresentaMenu("Menu Clientes");

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
                            for(int i = arrayFaturas.size(); i > 0; i--){
                                Fatura faturaObtida = arrayFaturas.get(i -1);
                                if(faturaObtida.getCliente() == clienteEliminar) {
                                    arrayFaturas.remove(faturaObtida);
                                    System.out.println("\nFatura Nº " + faturaObtida.getnFatura() + " removida com sucesso!");
                                    temFaturas = true;
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
        boolean sair = false;
        Scanner scannerEscolha = new Scanner(System.in);
        do {
            String escolha;

            apresentaMenu("Menu Faturas");

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

                        int numeroFaturaEliminar = funcoesUteis.protecaoEscolha(1, arrayFaturas.size());

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
        listarFaturas.listarFaturas(arrayFaturas);
        if (!arrayFaturas.isEmpty()) {
            System.out.print("\nEscolha uma fatura: ");
            int escolha = funcoesUteis.protecaoEscolha(1, arrayFaturas.size());
            Fatura faturaEscolhida = arrayFaturas.get(escolha - 1);
            double valorSemIVA = faturaEscolhida.getValorTotalSemIVA();
            double valorComIVA = faturaEscolhida.getValorTotalComIVA();
            double valorIVA = (valorComIVA - valorSemIVA);
            System.out.print("\n" + faturaEscolhida);
            System.out.printf("; Valor total do IVA da fatura -> %.2f€\n", valorIVA);
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

    private void importaInformacoesAutomaticamente(ArrayList<Fatura> arrayFaturas, ArrayList<Cliente> arrayClientes, ArrayList<Produto> arrayProdutos, String nomeFicheiroTexto, String nomeFicheiroObjetos) {
        boolean existeFicheiroObjeto = trataInformacoesFicheiros.verificaExistenciaFicheiroObjeto(nomeFicheiroObjetos);
        if(existeFicheiroObjeto){
            trataInformacoesFicheiros.leFicheiroObjeto(nomeFicheiroObjetos, arrayClientes, arrayProdutos, arrayFaturas);
        } else {
            trataInformacoesFicheiros.leFicheiroTexto(arrayClientes, arrayFaturas, arrayProdutos, nomeFicheiroTexto);
        }
    }

    private void importaFatura(ArrayList<Cliente> arrayClientes, ArrayList<Produto> arrayProdutos, ArrayList<Fatura> arrayFaturas, String nomeFicheiro) {
        File ficheiro = new File(nomeFicheiro);
        if(ficheiro.exists() && ficheiro.isFile()) {
            try {
                FileReader fileReader = new FileReader(ficheiro);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String linha;
                while((linha = bufferedReader.readLine()) != null) {
                    String[] informacoesLinha = linha.split("#");
                    String caracterControlo = informacoesLinha[0];
                    String informacoes = informacoesLinha[1];
                    switch (caracterControlo) {
                        case "C":
                            trataInformacoesFicheiros.trataInformacoesClientes(arrayClientes, informacoes);
                            break;

                        case "N":
                            trataInformacoesFicheiros.trataInformacoesTaxaNormal(arrayProdutos, informacoes, "Normal");
                            break;

                        case "I":
                            trataInformacoesFicheiros.trataInformacoesTaxaIntermedia(arrayProdutos, informacoes, "Intermedia");
                            break;

                        case "R":
                            trataInformacoesFicheiros.trataInformacoesTaxaReduzida(arrayProdutos, informacoes, "Reduzida");
                            break;

                        case "P":
                            trataInformacoesFicheiros.trataInformacoesComPrescricao(arrayProdutos, informacoes);
                            break;

                        case "S":
                            trataInformacoesFicheiros.trataInformacoesSemPrescricao(arrayProdutos, informacoes);
                            break;

                        case "F":
                            trataInformacoesFicheiros.trataInformacoesFaturas(arrayFaturas, arrayClientes, arrayProdutos, informacoes);
                            break;

                        default:
                            System.out.println("Tipo de objeto não existe");
                            break;
                    }
                }
                bufferedReader.close();
            } catch (FileNotFoundException exception) {
                System.out.println("Erro ao abrir ficheiro de texto");
            } catch (IOException exception) {
                System.out.println("Erro ao ler ficheiro de texto");
            }
        } else {
            System.out.println("Ficheiro texto não existe");
        }
    }

    private void exportaFatura(ArrayList<Fatura> arrayFaturas, String nomeFicheiro) {
        File ficheiroTexto = new File(nomeFicheiro);
        if(ficheiroTexto.exists() && ficheiroTexto.isFile()) {
            try {
                FileWriter fileWriter = new FileWriter(ficheiroTexto);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                for(Fatura fatura: arrayFaturas) {
                    Cliente cliente = fatura.getCliente();
                    String stringCliente = trataInformacoesFicheiros.criaInformacaoCliente(cliente);
                    bufferedWriter.write(stringCliente);
                    bufferedWriter.newLine();

                    ArrayList<Produto> arrayProdutosFatura = fatura.getListaProdutos();
                    for(Produto produto: arrayProdutosFatura) {
                        String tipoProduto = produto.getTipo();
                        switch (tipoProduto) {
                            case "Produto Alimentar Taxa Reduzida":
                                String stringTaxaReduzida = trataInformacoesFicheiros.criaInformacaoProdutoAlimentarTaxaReduzida((ProdutoAlimentarTaxaReduzida) produto);
                                bufferedWriter.write(stringTaxaReduzida);
                                bufferedWriter.newLine();
                                break;

                            case "Produto Alimentar Taxa Intermedia":
                                String stringTaxaIntermedia = trataInformacoesFicheiros.criaInformacaoProdutoAlimentarTaxaIntermedia((ProdutoAlimentarTaxaIntermedia) produto);
                                bufferedWriter.write(stringTaxaIntermedia);
                                bufferedWriter.newLine();
                                break;

                            case "Produto Alimentar Taxa Normal":
                                String stringTaxaNormal = trataInformacoesFicheiros.criaInformacaoProdutoAlimentarTaxaNormal((ProdutoAlimentarTaxaNormal) produto);
                                bufferedWriter.write(stringTaxaNormal);
                                bufferedWriter.newLine();
                                break;

                            case "Produto Farmacia Com Prescricao":
                                String stringComPrescricao = trataInformacoesFicheiros.criaInformacaoProdutoFarmaciaComPrescricao((ProdutoFarmaciaComPrescricao) produto);
                                bufferedWriter.write(stringComPrescricao);
                                bufferedWriter.newLine();
                                break;

                            case "Produto Farmacia Sem Prescricao":
                                String stringSemPrescricao = trataInformacoesFicheiros.criaInformacaoProdutoFarmaciaSemPrescricao((ProdutoFarmaciaSemPrescricao) produto);
                                bufferedWriter.write(stringSemPrescricao);
                                bufferedWriter.newLine();
                                break;

                            default:
                                break;
                        }
                    }

                    String stringFatura = trataInformacoesFicheiros.criaInformacaoFatura(fatura);
                    if(arrayFaturas.indexOf(fatura) == (arrayFaturas.size() - 1)){
                        bufferedWriter.write(stringFatura);
                    } else {
                        bufferedWriter.write(stringFatura);
                        bufferedWriter.newLine();
                    }
                }
                bufferedWriter.close();
            } catch (IOException exception) {
                System.out.println("Erro ao escrever no ficheiro texto");
            } catch (NullPointerException exception) {
                System.out.println("Erro ao abrir o ficheiro");
            }
        } else {
            System.out.println("Ficheiro texto não existe");
        }
    }
}