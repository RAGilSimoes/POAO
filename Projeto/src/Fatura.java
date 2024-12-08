import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe que representa o objeto Data
 */
public class Fatura implements Serializable {
    ListarClientes listarClientes = new ListarClientes();
    FuncoesUteis funcoesUteis = new FuncoesUteis();
    /**
     * Numero da fatura
     */
    private String nFatura;
    /**
     * Cliente
     */
    private Cliente cliente;
    /**
     * Data da fatura
     */
    private Data dataFatura;
    /**
     * Lista de produtos
     */
    private ArrayList<Produto> listaProdutos;
    /**
     * Valor total sem iva
     */
    private double valorTotalSemIVA;
    /**
     * Valor total com iva
     */
    private double valorTotalComIVA;


    /**
     * Contrutor da fatura
     *
     * @param nFatura          numero da fatura
     * @param cliente          cliente
     * @param dataFatura       data da fatura
     * @param listaProdutos    lista de produtos
     * @param valorTotalSemIVA valor total sem iva
     * @param valorTotalComIVA valor total com iva
     */
    protected Fatura(String nFatura, Cliente cliente, Data dataFatura, ArrayList<Produto> listaProdutos, double valorTotalSemIVA, double valorTotalComIVA) {
        this.nFatura = nFatura;
        this.cliente = cliente;
        this.dataFatura = dataFatura;
        this.listaProdutos = listaProdutos;
        this.valorTotalSemIVA = valorTotalSemIVA;
        this.valorTotalComIVA = valorTotalComIVA;
    }

    /**
     * Getter da lista produtos
     *
     * @return lista produtos
     */
    protected ArrayList<Produto> getListaProdutos() {
        return this.listaProdutos;
    }

    /**
     * Getter do cliente
     *
     * @return cliente
     */
    protected Cliente getCliente() {
        return this.cliente;
    }

    /**
     * Getter da data fatura
     *
     * @return data fatura
     */
    protected Data getDataFatura() {
        return this.dataFatura;
    }

    /**
     * Getter da fatura
     *
     * @return fatura
     */
    protected String getnFatura() {
        return this.nFatura;
    }

    /**
     * Getter do valor total com iva
     *
     * @return valor total com iva
     */
    protected double getValorTotalComIVA() {
        return this.valorTotalComIVA;
    }

    /**
     * Getter do valor total sem iva
     *
     * @return valor total sem iva
     */
    protected double getValorTotalSemIVA() {
        return this.valorTotalSemIVA;
    }

    /**
     * Setter do cliente
     *
     * @param cliente cliente
     */
    protected void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Setter da data fatura
     *
     * @param dataFatura data fatura
     */
    protected void setDataFatura(Data dataFatura) {
        this.dataFatura = dataFatura;
    }

    /**
     * Setter da lista produtos
     *
     * @param listaProdutos lista produtos
     */
    protected void setListaProdutos(ArrayList<Produto> listaProdutos) {
        if(listaProdutos != null){
            this.listaProdutos = listaProdutos;
        }
    }

    /**
     * Setter da fatura
     *
     * @param nFatura n fatura
     */
    protected void setnFatura(String nFatura) {
        this.nFatura = nFatura;
    }

    /**
     * Setter do valor total com iva
     *
     * @param valorTotalComIVA valor total com iva
     */
    protected void setValorTotalComIVA(double valorTotalComIVA) {
        this.valorTotalComIVA = valorTotalComIVA;
    }

    /**
     * Setter do valor total sem iva
     *
     * @param valorTotalSemIVA valor total sem iva
     */
    protected void setValorTotalSemIVA(double valorTotalSemIVA) {
        this.valorTotalSemIVA = valorTotalSemIVA;
    }

    public String toString(){
        return ("Número de Fatura -> " + this.getnFatura() + "; Cliente -> " + this.getCliente() + "; Data Fatura -> " + this.getDataFatura().getDia() + "/" + this.getDataFatura().getMes() + "/" + this.getDataFatura().getAno() + "; Produtos -> " + this.getListaProdutos() + "; Valor Sem IVA -> " + this.getValorTotalSemIVA() + "; Valor Com IVA -> " + this.getValorTotalComIVA());
    }

    /**
     * Verificad a existencia do numero da fatura
     *
     * @param nFaturaProcurar numero da fatura que se pretende procurar
     * @param arrayFaturas    array de faturas
     * @return devolve booleano consoante a existencia do numero da fatura
     */
    private boolean existeNumeroFatura(String nFaturaProcurar, ArrayList<Fatura> arrayFaturas){
        boolean nFaturaValido = true;
        for (Fatura fatura: arrayFaturas){
            String nFaturaExistente = fatura.getnFatura();
            if (nFaturaExistente.equals(nFaturaProcurar)){
                System.out.println("\nNúmero de fatura já existente.");
                nFaturaValido = false;
                break;
            }
        }
        return nFaturaValido;
    }

    /**
     * Verifica a validade do numero da fatura
     *
     * @param numeroFatura numero da fatura
     * @param arrayFaturas array de faturas
     * @return devolve booleano consoante a validade do nif
     */
    protected boolean verificaNumeroFatura(String numeroFatura, ArrayList<Fatura> arrayFaturas){
        boolean verificacao = true;
        if (numeroFatura.length() != 9 || funcoesUteis.verificaCaracteres(numeroFatura,'0', '9') || numeroFatura.equalsIgnoreCase("000000000")){
            System.out.println("\nO número de fatura introduzido não é válido");
            verificacao = false;
        } else {
            verificacao = existeNumeroFatura(numeroFatura, arrayFaturas);
        }
        return verificacao;
    }

    /**
     * Calcular valor total sem iva
     *
     * @param arrayProdutos array de produtos
     * @return valor total sem iva
     */
    protected double calcularValorTotalSemIVA(ArrayList<Produto> arrayProdutos){
        double valorTotalSemIVA = 0;
        for(Produto produto: arrayProdutos){
            valorTotalSemIVA += produto.obtemValorSemIVA();
        }
        valorTotalSemIVA = (Math.floor(valorTotalSemIVA * 100) /100);

        return valorTotalSemIVA;
    }

    /**
     * Calcular valor total com iva
     *
     * @param arrayProdutos   array de produtos
     * @param clienteRecebido cliente recebido
     * @return valor total com iva
     */
    protected double calcularValorTotalComIVA(ArrayList<Produto> arrayProdutos, Cliente clienteRecebido){
        double valorTotalComIVA = 0;
        for(Produto produto: arrayProdutos){
            valorTotalComIVA += produto.obtemValorComIVA(clienteRecebido);
        }
        valorTotalComIVA = (Math.floor(valorTotalComIVA * 100) /100);

        return valorTotalComIVA;
    }

    private void obtemProduto(ArrayList<Produto> arrayProdutos, ArrayList<Produto> arrayProdutosFatura){
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
                                produtoAlimentarTaxaReduzida = produtoAlimentarTaxaReduzida.criaProdutoTaxaReduzida(arrayProdutos);

                                arrayProdutos.add(produtoAlimentarTaxaReduzida);
                                arrayProdutosFatura.add(produtoAlimentarTaxaReduzida);
                                System.out.println("\nProduto adicionado com sucesso!");
                                verificaEscolhaProduto = true;
                                break;

                            case "2":
                                ProdutoAlimentarTaxaIntermedia produtoAlimentarTaxaIntermedia = new ProdutoAlimentarTaxaIntermedia(null, null, null, null, null, null, null, null);
                                produtoAlimentarTaxaIntermedia = produtoAlimentarTaxaIntermedia.criaProdutoTaxaIntermedia(arrayProdutos);

                                arrayProdutos.add(produtoAlimentarTaxaIntermedia);
                                arrayProdutosFatura.add(produtoAlimentarTaxaIntermedia);
                                System.out.println("\nProduto adicionado com sucesso!");
                                verificaEscolhaProduto = true;
                                break;

                            case "3":
                                ProdutoAlimentarTaxaNormal produtoAlimentarTaxaNormal = new ProdutoAlimentarTaxaNormal(null, null, null, null, null, null, null);
                                produtoAlimentarTaxaNormal = produtoAlimentarTaxaNormal.criaProdutoTaxaNormal(arrayProdutos);

                                arrayProdutos.add(produtoAlimentarTaxaNormal);
                                arrayProdutosFatura.add(produtoAlimentarTaxaNormal);
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
                                produtoFarmaciaComPrescricao = produtoFarmaciaComPrescricao.criaProdutoComPrescricao(arrayProdutos);

                                arrayProdutos.add(produtoFarmaciaComPrescricao);
                                arrayProdutosFatura.add(produtoFarmaciaComPrescricao);
                                System.out.println("\nProduto adicionado com sucesso!");
                                verificaEscolhaProduto = true;
                                break;

                            case "2":
                                ProdutoFarmaciaSemPrescricao produtoFarmaciaSemPrescricao = new ProdutoFarmaciaSemPrescricao(null, null, null, null, null, null, null);
                                produtoFarmaciaSemPrescricao = produtoFarmaciaSemPrescricao.criaProdutoSemPrescricao(arrayProdutos);

                                arrayProdutos.add(produtoFarmaciaSemPrescricao);
                                arrayProdutosFatura.add(produtoFarmaciaSemPrescricao);
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

    private void listaProdutos(ArrayList<Produto> arrayProdutos) {
        for(Produto produto: arrayProdutos){
            System.out.println("Produto " + (arrayProdutos.indexOf(produto) + 1) + ": " + produto);
        }
    }

    /**
     * Cria fatura
     *
     * @param arrayClientes array de clientes
     * @param arrayFaturas  array de faturas
     * @param arrayProdutos array de produtos
     * @return fatura
     */
    protected Fatura criaFatura(ArrayList<Cliente> arrayClientes, ArrayList<Fatura> arrayFaturas, ArrayList<Produto> arrayProdutos){
        Scanner scannerObterResposta = new Scanner(System.in);

        String numeroFatura = null;
        String quantidadeProdutosInserir = null;
        int quantidadeProdutos = 0;

        boolean verificacaoNumeroFatura = false;
        boolean verificacaoCliente = false;
        boolean verificacaoQuantidade = false;
        boolean verificacaoData = false;

        Cliente cliente = new Cliente(null, null, null);

        while (!verificacaoNumeroFatura) {
            System.out.print("\nIntroduza o número da fatura (tamanho 9 dígitos): ");
            numeroFatura = scannerObterResposta.nextLine();
            verificacaoNumeroFatura = verificaNumeroFatura(numeroFatura, arrayFaturas);
        }

        while(!verificacaoCliente) {
            listarClientes.listarClientes(arrayClientes);
            System.out.print("\nIntroduza o número do cliente que pretende associar a esta fatura: ");
            int numeroEscolhaCliente = funcoesUteis.protecaoEscolha(1, arrayClientes.size());
            try {
                cliente = arrayClientes.get(numeroEscolhaCliente - 1);
                verificacaoCliente = true;
            } catch (IndexOutOfBoundsException exception) {
                System.out.println("Opção inválida");
            }
        }

        Data dataFatura = new Data(0,0,0);
        while (!verificacaoData) {
            System.out.print("\nIntroduza a data da fatura no formato dd/mm/aaaa: ");
            String dataIntroduzida = scannerObterResposta.nextLine();
            verificacaoData = dataFatura.verificaData(dataIntroduzida);
            if (verificacaoData) {
                dataFatura = dataFatura.passaParaObjetoData(dataIntroduzida);
            }
        }

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
            }
        }

        ArrayList<Produto> arrayProdutosFatura = new ArrayList<Produto>();

        for(int i = 0; i < quantidadeProdutos; i++) {
            if(arrayProdutos.isEmpty()) {
                obtemProduto(arrayProdutos, arrayProdutosFatura);
            } else {
                System.out.println("\nPretende associar ou criar um produto? 1-> Associar | 2-> Criar");
                boolean verificacao = false;
                while (!verificacao) {
                    String escolha = scannerObterResposta.nextLine();
                    boolean verificaEscolha = funcoesUteis.verificaInt(escolha);
                    if (verificaEscolha) {
                        switch (escolha) {
                            case "1":
                                int controloIndices = 1;
                                System.out.println("Lista de Produtos: ");
                                listaProdutos(arrayProdutos);
                                System.out.print("\nIntroduza o indice do produto: ");
                                int indice = funcoesUteis.protecaoEscolha(1, arrayProdutos.size());
                                Produto produto = arrayProdutos.get(indice - controloIndices);
                                arrayProdutosFatura.add(produto);
                                System.out.println("\nProduto associado com sucesso");
                                verificacao = true;
                                break;
                            case "2":
                                obtemProduto(arrayProdutos, arrayProdutosFatura);
                                verificacao = true;
                                break;
                            default:
                                System.out.println("Opção inválida.");
                                break;
                        }
                    }
                }
            }
        }

        double valorTotalSemIVA = calcularValorTotalSemIVA(arrayProdutos);
        double valorTotalComIVA = calcularValorTotalComIVA(arrayProdutos, cliente);
        return new Fatura(numeroFatura, cliente, dataFatura, arrayProdutosFatura, valorTotalSemIVA, valorTotalComIVA);
    }

    private void alteraInformacaoFatura(String tipo, Fatura faturaRecebida, ArrayList<Fatura> arrayFaturas, ArrayList<Cliente> arrayClientes, ArrayList<Produto> arrayProdutosPOOFS, ArrayList<Produto> arrayProdutosFatura){
        Scanner scannerObterResposta = new Scanner(System.in);

        switch (tipo){
            case "Cliente":
                boolean verificacaoCliente = false;
                while (!verificacaoCliente) {
                    listarClientes.listarClientes(arrayClientes);
                    System.out.print("\nIntroduza o número do novo cliente que pretende associar a esta fatura: ");
                    int numeroEscolhaCliente = funcoesUteis.protecaoEscolha(0, arrayClientes.size());

                    Cliente cliente = arrayClientes.get(numeroEscolhaCliente - 1);
                    faturaRecebida.setCliente(cliente);
                    verificacaoCliente = true;
                }
                break;

            case "Data":
                boolean verificacaoData = false;
                Data dataFatura = new Data(0,0,0);
                while (!verificacaoData) {
                    System.out.print("\nIntroduza a nova data da fatura (se não pretender alterar introduza a mesma data): ");
                    String dataIntroduzida = scannerObterResposta.nextLine();
                    verificacaoData = dataFatura.verificaData(dataIntroduzida);
                    if (verificacaoData) {
                        dataFatura = dataFatura.passaParaObjetoData(dataIntroduzida);
                        faturaRecebida.setDataFatura(dataFatura);
                    }
                }
                break;

            case "Produtos":
                int variavelControloIndices = 1;

                System.out.print("\nIntroduza o número do produto que pretende alterar: ");
                int numeroProdutoAlterar = funcoesUteis.protecaoEscolha(0, arrayProdutosFatura.size());

                Produto produtoFatura = arrayProdutosFatura.get(numeroProdutoAlterar - variavelControloIndices);
                String codigoProdutoFatura = produtoFatura.getCodigo();

                boolean verificacao = false;
                while(!verificacao) {
                    System.out.print("\nIntroduza a nova quantidade de unidades do produto: ");
                    String quantidade = scannerObterResposta.nextLine();
                    boolean verificaQuantidade = funcoesUteis.verificaInt(quantidade);
                    if(verificaQuantidade) {
                        int quantidadeUnidade = Integer.parseInt(quantidade);
                        produtoFatura.setQuantidade(String.valueOf(quantidadeUnidade));
                        verificacao = true;
                    } else {
                        System.out.println("Opção inválida");
                    }
                }

                verificacao = false;
                while(!verificacao) {
                    System.out.print("\nIntroduza o novo preço por Unidade do produto: ");
                    String preco = scannerObterResposta.nextLine();
                    boolean verificaPreco = funcoesUteis.verificaDouble(preco);
                    if(verificaPreco) {
                        double precoUnidade = Double.parseDouble(preco);
                        produtoFatura.setValorUnidade(String.valueOf(precoUnidade));
                        verificacao = true;
                    } else {
                        System.out.println("Opção inválida");
                    }
                }


                for(Produto produto: arrayProdutosPOOFS) {
                    String codigoProdutoPOOFS = produto.getCodigo();
                    if(codigoProdutoFatura.equalsIgnoreCase(codigoProdutoPOOFS)) {
                        int indiceProdutoPOOFS = arrayProdutosPOOFS.indexOf(produto);
                        arrayProdutosPOOFS.set(indiceProdutoPOOFS, produtoFatura);
                        break;
                    }
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
     * Altera as informacoes da fatura
     *
     * @param faturaRecebida fatura recebida
     * @param arrayFaturas   array de faturas
     * @param arrayClientes  array de clientes
     * @param arrayProdutos  array de produtos
     */
    protected void alteraInformacoesFatura(Fatura faturaRecebida, ArrayList<Fatura> arrayFaturas, ArrayList<Cliente> arrayClientes, ArrayList<Produto> arrayProdutos) {
        Scanner scannerEscolha = new Scanner(System.in);
        ArrayList<Produto> arrayProdutosFatura = faturaRecebida.getListaProdutos();

        boolean verificacao = false;
        String escolha;

        System.out.println("\nPretende alterar o Cliente associado à fatura? (S/N)");
        while(!verificacao) {
            escolha = scannerEscolha.nextLine();
            switch (escolha) {
                case "S", "s":
                    alteraInformacaoFatura("Cliente", faturaRecebida, arrayFaturas, arrayClientes, arrayProdutos, arrayProdutosFatura);
                    verificacao = true;
                    break;
                case "N", "n":
                    System.out.println("Cliente associado à fatura não alterado");
                    verificacao = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        verificacao = false;
        System.out.println("\nPretende alterar a Data da fatura? (S/N)");
        while(!verificacao) {
            escolha = scannerEscolha.nextLine();
            switch (escolha) {
                case "S", "s":
                    alteraInformacaoFatura("Data", faturaRecebida, arrayFaturas, arrayClientes, arrayProdutos, arrayProdutosFatura);
                    verificacao = true;
                    break;
                case "N", "n":
                    System.out.println("Data da fatura não alterada");
                    verificacao = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }


        verificacao = false;
        System.out.println("\nPretende alterar algum produto da Fatura? (S/N)");
        while(!verificacao) {
            escolha = scannerEscolha.nextLine();
            switch (escolha) {
                case "S", "s":
                    System.out.println("\nLista de Produtos da fatura: ");
                    listaProdutos(arrayProdutosFatura);
                    while(!verificacao){
                        System.out.print("\nIntroduza a quantidade de produtos existentes na fatura que pretende alterar: ");
                        escolha = scannerEscolha.nextLine();
                        verificacao = funcoesUteis.verificaInt(escolha);
                        if(verificacao){
                            int quantidade = Integer.parseInt(escolha);
                            if(quantidade == 0){
                                System.out.println("\nNenhum produto vai ser alterado.");
                            } else {
                                if(quantidade > arrayProdutosFatura.size() || quantidade < 0) {
                                    System.out.println("\nOpção inválida.");
                                    verificacao = false;
                                } else {
                                    for(int i = 0; i < quantidade; i++){
                                        alteraInformacaoFatura("Produtos", faturaRecebida, arrayFaturas, arrayClientes, arrayProdutos, arrayProdutosFatura);
                                    }
                                    alteraInformacaoFatura("Valor Sem IVA", faturaRecebida, arrayFaturas, arrayClientes, arrayProdutos,arrayProdutosFatura);
                                    alteraInformacaoFatura("Valor Com IVA", faturaRecebida, arrayFaturas, arrayClientes, arrayProdutos, arrayProdutosFatura);
                                }
                            }
                        }
                    }
                    break;

                case "N", "n":
                    System.out.println("Produtos da fatura não alterados");
                    verificacao = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}