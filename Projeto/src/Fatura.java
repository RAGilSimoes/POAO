package Projeto.src;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Fatura.
 */
public class Fatura {
    /**
     * The N fatura.
     */
    protected String nFatura;
    /**
     * The Cliente.
     */
    protected Cliente cliente;
    /**
     * The Data fatura.
     */
    protected Data dataFatura;
    /**
     * The Lista produtos.
     */
    ArrayList<Produto> listaProdutos;
    /**
     * The Valor total sem iva.
     */
    protected double valorTotalSemIVA = 0;
    /**
     * The Valor total com iva.
     */
    protected double valorTotalComIVA = 0;


    /**
     * Instantiates a new Fatura.
     *
     * @param nFatura          the n fatura
     * @param cliente          the cliente
     * @param dataFatura       the data fatura
     * @param listaProdutos    the lista produtos
     * @param valorTotalSemIVA the valor total sem iva
     * @param valorTotalComIVA the valor total com iva
     */
    public Fatura(String nFatura, Cliente cliente, Data dataFatura, ArrayList<Produto> listaProdutos, double valorTotalSemIVA, double valorTotalComIVA) {
        this.nFatura = nFatura;
        this.cliente = cliente;
        this.dataFatura = dataFatura;
        this.listaProdutos = listaProdutos;
        this.valorTotalSemIVA = valorTotalSemIVA;
        this.valorTotalComIVA = valorTotalComIVA;
    }

    /**
     * Gets lista produtos.
     *
     * @return the lista produtos
     */
    public ArrayList<Produto> getListaProdutos() {
        return this.listaProdutos;
    }

    /**
     * Gets cliente.
     *
     * @return the cliente
     */
    public Cliente getCliente() {
        return this.cliente;
    }

    /**
     * Gets data fatura.
     *
     * @return the data fatura
     */
    public Data getDataFatura() {
        return this.dataFatura;
    }

    /**
     * Gets fatura.
     *
     * @return the fatura
     */
    public String getnFatura() {
        return this.nFatura;
    }

    /**
     * Gets valor total com iva.
     *
     * @return the valor total com iva
     */
    public double getValorTotalComIVA() {
        return this.valorTotalComIVA;
    }

    /**
     * Gets valor total sem iva.
     *
     * @return the valor total sem iva
     */
    public double getValorTotalSemIVA() {
        return this.valorTotalSemIVA;
    }

    /**
     * Sets cliente.
     *
     * @param cliente the cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Sets data fatura.
     *
     * @param dataFatura the data fatura
     */
    public void setDataFatura(Data dataFatura) {
        this.dataFatura = dataFatura;
    }

    /**
     * Sets lista produtos.
     *
     * @param listaProdutos the lista produtos
     */
    public void setListaProdutos(ArrayList<Produto> listaProdutos) {
        if(listaProdutos != null){
            this.listaProdutos = listaProdutos;
        }
    }

    /**
     * Sets fatura.
     *
     * @param nFatura the n fatura
     */
    public void setnFatura(String nFatura) {
        this.nFatura = nFatura;
    }

    /**
     * Sets valor total com iva.
     *
     * @param valorTotalComIVA the valor total com iva
     */
    public void setValorTotalComIVA(double valorTotalComIVA) {
        this.valorTotalComIVA = valorTotalComIVA;
    }

    /**
     * Sets valor total sem iva.
     *
     * @param valorTotalSemIVA the valor total sem iva
     */
    public void setValorTotalSemIVA(double valorTotalSemIVA) {
        this.valorTotalSemIVA = valorTotalSemIVA;
    }

    public String toString(){
        return ("Número de Fatura -> " + this.getnFatura() + "; Cliente -> " + this.getCliente().getNome() + "; Localização do Cliente -> " + this.getCliente().getLocalizacao() + "; Número de produtos -> " + this.getListaProdutos().size() + "; Valor total sem IVA -> " + this.valorTotalSemIVA + "; Valor total com IVA -> " + this.valorTotalComIVA + " ");
    }

    private boolean existeNumeroFatura(String nFaturaProcurar, ArrayList<Fatura> arrayFaturas){
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

    private boolean verificaNumeroFatura(String numeroFatura, ArrayList<Fatura> arrayFaturas){
        boolean verificacao;
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        if (numeroFatura.length() != 9 || funcoesUteis.verificaCaracteres(numeroFatura,'0', '9')){
            System.out.println("\nO número de fatura introduzido não é válido");
            verificacao = false;
        } else {
            verificacao = existeNumeroFatura(numeroFatura, arrayFaturas);
        }
        return verificacao;
    }

    /**
     * Calcular valor total sem iva double.
     *
     * @param arrayProdutos the array produtos
     * @return the double
     */
    protected double calcularValorTotalSemIVA(ArrayList<Produto> arrayProdutos){
        double valorTotalSemIVA = 0;
        for(Produto produto: arrayProdutos){
            valorTotalSemIVA += produto.obtemValorSemIVA();
        }

        return valorTotalSemIVA;
    }

    /**
     * Calcular valor total com iva double.
     *
     * @param arrayProdutos   the array produtos
     * @param clienteRecebido the cliente recebido
     * @return the double
     */
    protected double calcularValorTotalComIVA(ArrayList<Produto> arrayProdutos, Cliente clienteRecebido){
        double valorTotalComIVA = 0;
        for(Produto produto: arrayProdutos){
            valorTotalComIVA += produto.obtemValorComIVA(clienteRecebido);
        }

        return valorTotalComIVA;
    }

    private void obtemProduto(ArrayList<Produto> arrayProdutos){
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
                                ProdutoAlimentarTaxaReduzida produtoAlimentarTaxaReduzida = new ProdutoAlimentarTaxaReduzida(null, null, null, null, null, null, null, 0, null);
                                produtoAlimentarTaxaReduzida = produtoAlimentarTaxaReduzida.criaProdutoTaxaReduzida();

                                arrayProdutos.add(produtoAlimentarTaxaReduzida);
                                System.out.println("\nProduto adicionado com sucesso!");
                                verificaEscolhaProduto = true;
                                break;

                            case "2":
                                ProdutoAlimentarTaxaIntermedia produtoAlimentarTaxaIntermedia = new ProdutoAlimentarTaxaIntermedia(null, null, null, null, null, null, null, null);
                                produtoAlimentarTaxaIntermedia = produtoAlimentarTaxaIntermedia.criaProdutoTaxaIntermedia();

                                arrayProdutos.add(produtoAlimentarTaxaIntermedia);
                                System.out.println("\nProduto adicionado com sucesso!");
                                verificaEscolhaProduto = true;
                                break;

                            case "3":
                                ProdutoAlimentarTaxaNormal produtoAlimentarTaxaNormal = new ProdutoAlimentarTaxaNormal(null, null, null, null, null, null, null);
                                produtoAlimentarTaxaNormal = produtoAlimentarTaxaNormal.criaProdutoTaxaNormal();

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
                                ProdutoFarmaciaComPrescricao produtoFarmaciaComPrescricao = new ProdutoFarmaciaComPrescricao(null, null, null, null, null, null, null);
                                produtoFarmaciaComPrescricao = produtoFarmaciaComPrescricao.criaProdutoComPrescricao();

                                arrayProdutos.add(produtoFarmaciaComPrescricao);
                                System.out.println("\nProduto adicionado com sucesso!");
                                verificaEscolhaProduto = true;
                                break;

                            case "2":
                                ProdutoFarmaciaSemPrescricao produtoFarmaciaSemPrescricao = new ProdutoFarmaciaSemPrescricao(null, null, null, null, null, null, null);
                                produtoFarmaciaSemPrescricao = produtoFarmaciaSemPrescricao.criaProdutoSemPrescricao();

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

    private void listaProdutos(ArrayList<Produto> arrayProdutos){
        for(Produto produto: arrayProdutos){
            System.out.println("Produto " + (arrayProdutos.indexOf(produto) + 1) + ": " + produto);
        }
    }

    /**
     * Cria fatura fatura.
     *
     * @param arrayClientes the array clientes
     * @param arrayFaturas  the array faturas
     * @return the fatura
     */
    protected Fatura criaFatura(ArrayList<Cliente> arrayClientes, ArrayList<Fatura> arrayFaturas){
        Scanner scannerObterResposta = new Scanner(System.in);
        FuncoesUteis funcoesUteis = new FuncoesUteis();

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

        ListarClientes listarClientes = new ListarClientes();
        listarClientes.listarClientes(arrayClientes);
        System.out.print("\nIntroduza o número do cliente que pretende associar a esta fatura: ");
        int numeroEscolhaCliente = funcoesUteis.protecaoEscolha(1, arrayClientes.size());
        Cliente cliente = arrayClientes.get(numeroEscolhaCliente - 1);

        Data dataFatura = new Data(0,0,0);
        dataFatura = dataFatura.verificaData();

        while (!verificacaoQuantidade) {
            System.out.print("\nIntroduza a quantidade de produtos que pretende inserir na fatura (mínimo 1 produto): ");
            quantidadeProdutosInserir = scannerObterResposta.nextLine();
            verificacaoQuantidade = funcoesUteis.verificaInt(quantidadeProdutosInserir);
            if(verificacaoQuantidade) {
                if(quantidadeProdutosInserir.equals("0")){
                    verificacaoQuantidade = false;
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

    private void alteraInformacaoFatura(String tipo, Fatura faturaRecebida, ArrayList<Fatura> arrayFaturas, ArrayList<Cliente> arrayClientes){
        Scanner scannerObterResposta = new Scanner(System.in);
        FuncoesUteis funcoesUteis = new FuncoesUteis();

        switch (tipo){
            case "Numero Fatura":
                String numeroFatura = null;
                boolean verificacaoNumeroFatura = false;
                while (!verificacaoNumeroFatura) {
                    System.out.print("\nIntroduza o novo número da fatura (tamanho 9 dígitos, se não pretender alterar introduza 9 0's): ");
                    numeroFatura = scannerObterResposta.nextLine();
                    verificacaoNumeroFatura = verificaNumeroFatura(numeroFatura, arrayFaturas);

                    if (numeroFatura.equals("000000000")) {
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
                ListarClientes listarClientes = new ListarClientes();
                while (!verificacaoCliente) {
                    listarClientes.listarClientes(arrayClientes);
                    System.out.print("\nIntroduza o novo cliente que pretende associar a esta fatura: ");
                    int numeroEscolhaCliente = funcoesUteis.protecaoEscolha(0, arrayClientes.size());

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
                Data data = new Data(0,0,0);
                data = data.verificaData();
                faturaRecebida.setDataFatura(data);
                break;

            case "Produtos":
                int variavelControloIndices = 1;
                ArrayList<Produto> arrayProdutos = faturaRecebida.getListaProdutos();
                listaProdutos(arrayProdutos);

                System.out.print("\nIntroduza o número do produto que pretende alterar: ");
                int numeroProdutoAlterar = funcoesUteis.protecaoEscolha(0, arrayProdutos.size());

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
                                ProdutoAlimentarTaxaReduzida produtoAlimentarTaxaReduzida = new ProdutoAlimentarTaxaReduzida(null, null, null, null, null, null, null, 0, null);
                                produtoAlimentarTaxaReduzida = produtoAlimentarTaxaReduzida.criaProdutoTaxaReduzida();
                                arrayProdutos.set(numeroProdutoAlterar - variavelControloIndices, produtoAlimentarTaxaReduzida);
                                verificacaoEscolha = true;
                                break;

                            case "2":
                                ProdutoAlimentarTaxaIntermedia produtoAlimentarTaxaIntermedia = new ProdutoAlimentarTaxaIntermedia(null, null, null, null, null, null, null, null);
                                produtoAlimentarTaxaIntermedia = produtoAlimentarTaxaIntermedia.criaProdutoTaxaIntermedia();
                                arrayProdutos.set(numeroProdutoAlterar - variavelControloIndices, produtoAlimentarTaxaIntermedia);
                                verificacaoEscolha = true;
                                break;

                            case "3":
                                ProdutoAlimentarTaxaNormal produtoAlimentarTaxaNormal = new ProdutoAlimentarTaxaNormal(null, null, null, null, null, null, null);
                                produtoAlimentarTaxaNormal = produtoAlimentarTaxaNormal.criaProdutoTaxaNormal();
                                arrayProdutos.set(numeroProdutoAlterar - variavelControloIndices, produtoAlimentarTaxaNormal);
                                verificacaoEscolha = true;
                                break;

                            case "4":
                                ProdutoFarmaciaComPrescricao produtoFarmaciaComPrescricao = new ProdutoFarmaciaComPrescricao(null, null, null, null, null, null, null);
                                produtoFarmaciaComPrescricao = produtoFarmaciaComPrescricao.criaProdutoComPrescricao();
                                arrayProdutos.set(numeroProdutoAlterar - variavelControloIndices, produtoFarmaciaComPrescricao);
                                verificacaoEscolha = true;
                                break;

                            case "5":
                                ProdutoFarmaciaSemPrescricao produtoFarmaciaSemPrescricao = new ProdutoFarmaciaSemPrescricao(null, null, null, null, null, null, null);
                                produtoFarmaciaSemPrescricao = produtoFarmaciaSemPrescricao.criaProdutoSemPrescricao();
                                arrayProdutos.set(numeroProdutoAlterar - variavelControloIndices, produtoFarmaciaSemPrescricao);
                                verificacaoEscolha = true;
                                break;

                            default:
                                System.out.println("\nOpção inválida.");
                                break;
                        }
                    } while(!verificacaoEscolha);
                }
                break;

            case "Valor Sem IVA":
                double valorSemIVA = faturaRecebida.calcularValorTotalSemIVA(faturaRecebida.getListaProdutos());
                faturaRecebida.setValorTotalSemIVA(valorSemIVA);
                break;

            case "Valor Com IVA":
                double valorComIVA = faturaRecebida.calcularValorTotalComIVA(faturaRecebida.getListaProdutos(), faturaRecebida.getCliente());
                faturaRecebida.setValorTotalComIVA(valorComIVA);
                break;

            default:
                break;
        }
    }

    /**
     * Altera informacoes fatura.
     *
     * @param faturaRecebida the fatura recebida
     * @param arrayFaturas   the array faturas
     * @param arrayClientes  the array clientes
     */
    protected void alteraInformacoesFatura(Fatura faturaRecebida, ArrayList<Fatura> arrayFaturas, ArrayList<Cliente> arrayClientes) {
        Scanner scannerEscolha = new Scanner(System.in);
        FuncoesUteis funcoesUteis = new FuncoesUteis();

        boolean verificacao = false;

        System.out.print("\n(Pressione 0 para não alterar alguma informacão)");
        alteraInformacaoFatura("Numero Fatura", faturaRecebida, arrayFaturas, arrayClientes);
        alteraInformacaoFatura("Cliente", faturaRecebida, arrayFaturas, arrayClientes);
        alteraInformacaoFatura("Data", faturaRecebida, arrayFaturas, arrayClientes);

        while(!verificacao){
            System.out.print("\n: ");
            String escolha = scannerEscolha.nextLine();
            verificacao = funcoesUteis.verificaInt(escolha);
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
        alteraInformacaoFatura("Valor Sem IVA", faturaRecebida, arrayFaturas, arrayClientes);
        alteraInformacaoFatura("Valor Com IVA", faturaRecebida, arrayFaturas, arrayClientes);
    }
}