package Projeto;

import java.util.ArrayList;
import java.util.Scanner;

public class Fatura {
    protected String nFatura;
    protected Cliente cliente;
    protected Data dataFatura;
    ArrayList<Produto> listaProdutos;
    protected double valorTotalSemIVA = 0;
    protected double valorTotalComIVA = 0;


    public Fatura(String nFatura, Cliente cliente, Data dataFatura, ArrayList<Produto> listaProdutos, double valorTotalSemIVA, double valorTotalComIVA) {
        this.nFatura = nFatura;
        this.cliente = cliente;
        this.dataFatura = dataFatura;
        this.listaProdutos = listaProdutos;
        this.valorTotalSemIVA = valorTotalSemIVA;
        this.valorTotalComIVA = valorTotalComIVA;
    }

    public ArrayList<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Data getDataFatura() {
        return dataFatura;
    }

    public String getnFatura() {
        return nFatura;
    }

    public double getValorTotalComIVA() {
        return valorTotalComIVA;
    }

    public double getValorTotalSemIVA() {
        return valorTotalSemIVA;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDataFatura(Data dataFatura) {
        this.dataFatura = dataFatura;
    }

    public void setListaProdutos(ArrayList<Produto> listaProdutos) {
        if(listaProdutos != null){
            this.listaProdutos = listaProdutos;
        }
    }

    public void setnFatura(String nFatura) {
        this.nFatura = nFatura;
    }

    public void setValorTotalComIVA(double valorTotalComIVA) {
        this.valorTotalComIVA = valorTotalComIVA;
    }

    public void setValorTotalSemIVA(double valorTotalSemIVA) {
        this.valorTotalSemIVA = valorTotalSemIVA;
    }

    public String toString(){
        return ("Número de Fatura -> " + this.getnFatura() + "; Cliente -> " + this.getCliente().getNome() + "; Localização do Cliente -> " + this.getCliente().getLocalizacao() + "; Número de produtos -> " + this.getListaProdutos().size() + "; Valor total sem IVA -> " + this.valorTotalSemIVA + "; Valor total com IVA -> " + this.valorTotalComIVA);
    }

    protected static boolean existeNumeroFatura(String nFaturaProcurar, ArrayList<Fatura> arrayFaturas){
        boolean nFaturaValido = true;
        for (Fatura fatura: arrayFaturas){
            String nFaturaExistente = fatura.getnFatura();
            if (nFaturaExistente.equals(nFaturaProcurar)){
                System.out.println("\nNúmero de fatura já existente.");
                nFaturaValido = false;
            }
        }
        return nFaturaValido;
    }

    protected static boolean verificaNumeroFatura(String numeroFatura, ArrayList<Fatura> arrayFaturas){
        boolean verificacao;
        if (numeroFatura.length() != 9 || FuncoesUteis.verificaCaracteres(numeroFatura,'0', '9')){
            System.out.println("\nO número de fatura introduzido não é válido");
            verificacao = false;
        } else {
            verificacao = existeNumeroFatura(numeroFatura, arrayFaturas);
        }
        return verificacao;
    }

    protected static double calcularValorTotalSemIVA(ArrayList<Produto> arrayProdutos){
        double valorTotalSemIVA = 0;
        for(Produto produto: arrayProdutos){
            valorTotalSemIVA += produto.obtemValorSemIVA();
        }

        return valorTotalSemIVA;
    }

    protected static double calcularValorTotalComIVA(ArrayList<Produto> arrayProdutos, Cliente clienteRecebido){
        double valorTotalComIVA = 0;
        for(Produto produto: arrayProdutos){
            valorTotalComIVA += produto.obtemValorComIVA(clienteRecebido);
        }

        return valorTotalComIVA;
    }

    protected static void obtemProduto(ArrayList<Produto> arrayProdutos){
        Scanner scannerObterResposta = new Scanner(System.in);
        boolean verificaEscolhaProduto = false;
        do{
            System.out.print("\nIntroduza o tipo de produto que prentede inserir \n1-> Alimentar | 2-> Farmacia: ");
            String tipoProduto = scannerObterResposta.nextLine();
            switch (tipoProduto) {
                case "1":
                    do {
                        System.out.print("\nIntroduza o tipo de tipoTaxa associada ao produto \n1-> Reduzida | 2-> Intermedia | 3-> Normal: ");
                        String tipoTaxa = scannerObterResposta.nextLine();
                        switch (tipoTaxa){
                            case "1":
                                ProdutoAlimentarTaxaReduzida produtoAlimentarTaxaReduzida = ProdutoAlimentarTaxaReduzida.criaProdutoTaxaReduzida();

                                arrayProdutos.add(produtoAlimentarTaxaReduzida);
                                System.out.println("\nProduto adicionado com sucesso!");
                                verificaEscolhaProduto = true;
                                break;

                            case "2":
                                ProdutoAlimentarTaxaIntermedia produtoAlimentarTaxaIntermedia = ProdutoAlimentarTaxaIntermedia.criaProdutoTaxaIntermedia();

                                arrayProdutos.add(produtoAlimentarTaxaIntermedia);
                                System.out.println("\nProduto adicionado com sucesso!");
                                verificaEscolhaProduto = true;
                                break;

                            case "3":
                                ProdutoAlimentarTaxaNormal produtoAlimentarTaxaNormal = ProdutoAlimentarTaxaNormal.criaProdutoTaxaNormal();

                                arrayProdutos.add(produtoAlimentarTaxaNormal);
                                System.out.println("\nProduto adicionado com sucesso!");
                                verificaEscolhaProduto = true;
                                break;

                            default:
                                System.out.println("\nOpção inválida.");
                                break;
                        }
                    } while(!verificaEscolhaProduto);
                    break;

                case "2":
                    do {
                        System.out.print("\nO produto tem prescrição médica? \n1-> Sim | 2-> Nao: ");
                        String prescricao = scannerObterResposta.nextLine();
                        switch (prescricao){
                            case "1":
                                ProdutoFarmaciaComPrescricao produtoFarmaciaComPrescricao = ProdutoFarmaciaComPrescricao.criaProdutoComPrescricao();

                                arrayProdutos.add(produtoFarmaciaComPrescricao);
                                System.out.println("\nProduto adicionado com sucesso!");
                                verificaEscolhaProduto = true;
                                break;

                            case "2":
                                ProdutoFarmaciaSemPrescricao produtoFarmaciaSemPrescricao = ProdutoFarmaciaSemPrescricao.criaProdutoSemPrescricao();

                                arrayProdutos.add(produtoFarmaciaSemPrescricao);
                                System.out.println("\nProduto adicionado com sucesso!");
                                verificaEscolhaProduto = true;
                                break;

                            default:
                                System.out.println("\nOpção inválida.");
                                break;
                        }
                    } while(!verificaEscolhaProduto);
                    break;

                default:
                    System.out.println("\nOpção inválida.");
                    break;
            }
        } while(!verificaEscolhaProduto);
    }

    private static void listaProdutos(ArrayList<Produto> arrayProdutos){
        for(Produto produto: arrayProdutos){
            System.out.println(produto);
        }
    }

    protected static Fatura criaFatura(ArrayList<Cliente> arrayClientes, ArrayList<Fatura> arrayFaturas){
        Scanner scannerObterResposta = new Scanner(System.in);

        String numeroFatura = null;
        String quantidadeProdutosInserir = null;
        int quantidadeProdutos = 0;

        boolean verificacaoNumeroFatura = false;
        boolean verificacaoQuantidade = false;

        while (!verificacaoNumeroFatura) {
            System.out.print("\nIntroduza o número da fatura (tamanho 9 dígitos): ");
            numeroFatura = scannerObterResposta.nextLine();
            verificacaoNumeroFatura = verificaNumeroFatura(numeroFatura, arrayFaturas);
        }

        POOFS.listarClientes(arrayClientes);
        System.out.print("\nIntroduza o número do cliente que pretende associar a esta fatura: ");
        int numeroEscolhaCliente = FuncoesUteis.protecaoEscolha(1, arrayClientes.size());
        Cliente cliente = arrayClientes.get(numeroEscolhaCliente - 1);

        Data dataFatura = Data.verificaData();

        while (!verificacaoQuantidade) {
            System.out.print("\nIntroduza a quantidade de produtos que pretende inserir na fatura (mínimo 1 produto): ");
            quantidadeProdutosInserir = scannerObterResposta.nextLine();
            verificacaoQuantidade = FuncoesUteis.verificaInt(quantidadeProdutosInserir);
            if(verificacaoQuantidade) {
                if(quantidadeProdutosInserir.equals("0")){
                    System.out.println("\nO valor introduzido não é válido.");
                } else {
                    quantidadeProdutos = Integer.parseInt(quantidadeProdutosInserir);
                }
            } else {
                System.out.println("\nO valor introduzido não é válido.");
            }
        }

        ArrayList<Produto> arrayProdutos= new ArrayList<Produto>();

        for(int i = 0; i < quantidadeProdutos; i++){
            obtemProduto(arrayProdutos);
        }

        double valorTotalSemIVA = calcularValorTotalSemIVA(arrayProdutos);
        double valorTotalComIVA = calcularValorTotalComIVA(arrayProdutos, cliente);
        return new Fatura(numeroFatura, cliente, dataFatura, arrayProdutos, valorTotalSemIVA, valorTotalComIVA);
    }

    protected void alteraInformacaoFatura(String tipo, Fatura faturaRecebida, ArrayList<Fatura> arrayFaturas, ArrayList<Cliente> arrayClientes){
        Scanner scannerObterResposta = new Scanner(System.in);

        switch (tipo){
            case "Numero Fatura":
                String numeroFatura = null;
                boolean verificacaoNumeroFatura = false;
                while (!verificacaoNumeroFatura) {
                    System.out.print("\nIntroduza o novo número da fatura (tamanho 9 dígitos): ");
                    numeroFatura = scannerObterResposta.nextLine();
                    verificacaoNumeroFatura = verificaNumeroFatura(numeroFatura, arrayFaturas);

                    if (numeroFatura.equals("0")) {
                        System.out.println("Número da fatura não alterado.");
                        break;
                    } else {
                        if(verificacaoNumeroFatura){
                            faturaRecebida.setnFatura(numeroFatura);
                            System.out.println("Número da fatura alterado com sucesso.");
                        }
                    }
                }
                break;

            case "Cliente":
                boolean verificacaoCliente = false;
                while (!verificacaoCliente) {
                    POOFS.listarClientes(arrayClientes);
                    System.out.print("\nIntroduza o novo cliente que pretende associar a esta fatura: ");
                    int numeroEscolhaCliente = FuncoesUteis.protecaoEscolha(0, arrayClientes.size());

                    if (numeroEscolhaCliente == 0) {
                        System.out.println("Cliente associado à fatura não alterado.");
                        verificacaoCliente = true;
                    } else {
                        Cliente cliente = arrayClientes.get(numeroEscolhaCliente - 1);
                        faturaRecebida.setCliente(cliente);
                        verificacaoCliente = true;
                    }
                }
                break;

            case "Data":
                System.out.println("\nIntroduza a nova data da fatura (se não pretender alterar introduza a mesma data): ");
                Data data = Data.verificaData();
                faturaRecebida.setDataFatura(data);
                break;

            case "Produtos":
                ArrayList<Produto> arrayProdutos = faturaRecebida.getListaProdutos();
                listaProdutos(arrayProdutos);

                boolean verificacaoProdutoAlterar = false;
                while (!verificacaoProdutoAlterar) {
                    System.out.print("\nIntroduza o número do produto que pretende alterar: ");
                    int numeroProdutoAlterar = FuncoesUteis.protecaoEscolha(0, arrayProdutos.size());

                    if (numeroProdutoAlterar == 0) {
                        System.out.println("Nenhum produto alterado.");
                        break;
                    } else {
                        boolean verificacaoEscolha = false;
                        do{
                            System.out.println("Pretende introduzir um produto de que tipo? \n1-> Alimentar com Taxa Reduzida \n2-> Alimentar com Taxa Intermedia \n3-> Alimentar com Taxa Normal \n4-> Farmacia Com Prescricao \n5-> Farmacia Sem Prescricao");
                            String escolha = scannerObterResposta.nextLine();
                            switch (escolha){
                                case "1":
                                    ProdutoAlimentarTaxaReduzida produtoAlimentarTaxaReduzida = ProdutoAlimentarTaxaReduzida.criaProdutoTaxaReduzida();
                                    arrayProdutos.set(numeroProdutoAlterar, produtoAlimentarTaxaReduzida);
                                    verificacaoEscolha = true;
                                    break;

                                case "2":
                                    ProdutoAlimentarTaxaIntermedia produtoAlimentarTaxaIntermedia = ProdutoAlimentarTaxaIntermedia.criaProdutoTaxaIntermedia();
                                    arrayProdutos.set(numeroProdutoAlterar, produtoAlimentarTaxaIntermedia);
                                    verificacaoEscolha = true;
                                    break;

                                case "3":
                                    ProdutoAlimentarTaxaNormal produtoAlimentarTaxaNormal = ProdutoAlimentarTaxaNormal.criaProdutoTaxaNormal();
                                    arrayProdutos.set(numeroProdutoAlterar, produtoAlimentarTaxaNormal);
                                    verificacaoEscolha = true;
                                    break;

                                case "4":
                                    ProdutoFarmaciaComPrescricao produtoFarmaciaComPrescricao = ProdutoFarmaciaComPrescricao.criaProdutoComPrescricao();
                                    arrayProdutos.set(numeroProdutoAlterar, produtoFarmaciaComPrescricao);
                                    verificacaoEscolha = true;
                                    break;

                                case "5":
                                    ProdutoFarmaciaSemPrescricao produtoFarmaciaSemPrescricao = ProdutoFarmaciaSemPrescricao.criaProdutoSemPrescricao();
                                    arrayProdutos.set(numeroProdutoAlterar, produtoFarmaciaSemPrescricao);
                                    verificacaoEscolha = true;
                                    break;

                                default:
                                    System.out.println("\nOpção inválida.");
                                    break;
                            }
                        } while(!verificacaoEscolha);
                    }
                }
                break;

            default:
                break;
        }
    }

    protected void alteraInformacoesFatura(Fatura faturaRecebida, ArrayList<Fatura> arrayFaturas, ArrayList<Cliente> arrayClientes) {
        Scanner scannerEscolha = new Scanner(System.in);
        boolean verificacao = false;

        System.out.print("\n(Pressione 0 para não alterar alguma informacão)");
        alteraInformacaoFatura("Numero Fatura", faturaRecebida, arrayFaturas, arrayClientes);
        alteraInformacaoFatura("Cliente", faturaRecebida, arrayFaturas, arrayClientes);
        alteraInformacaoFatura("Data", faturaRecebida, arrayFaturas, arrayClientes);

        while(!verificacao){
            System.out.print("\nIntroduza a quantidade de produtos que pretende alterar: ");
            String escolha = scannerEscolha.nextLine();
            verificacao = FuncoesUteis.verificaInt(escolha);
            if(verificacao){
                int quantidade = Integer.parseInt(escolha);
                if(quantidade == 0){
                    System.out.println("\nNenhum produto vai ser alterado.");
                } else {
                    for(int i = 0; i < quantidade; i++){
                        alteraInformacaoFatura("Produtos", faturaRecebida, arrayFaturas, arrayClientes);
                    }
                }
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }
}