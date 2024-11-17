package Projeto;

import java.util.ArrayList;
import java.util.Scanner;

public class Fatura {
    protected String nFatura;
    protected Cliente cliente;
    protected Data dataFatura;
    ArrayList<Produto> listaProdutos;


    public Fatura(String nFatura, Cliente cliente, Data dataFatura, ArrayList<Produto> listaProdutos) {
        this.nFatura = nFatura;
        this.cliente = cliente;
        this.dataFatura = dataFatura;
        this.listaProdutos = listaProdutos;
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

    public String toString(){
        return ("Número de Fatura -> " + this.getnFatura() + "; Cliente -> " + this.getCliente().getNome() + "; Localização do Cliente -> " + this.getCliente().getLocalizacao() + "; Número de produtos -> " + this.getListaProdutos().size() + "; Valor total sem IVA -> " + "; Valor total com IVA -> ");
    }

    protected boolean existeNumeroFatura(String nFaturaProcurar, ArrayList<Fatura> arrayFaturas){
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

    protected boolean verificaNumeroFatura(String numeroFatura, ArrayList<Fatura> arrayFaturas){
        boolean verificacao;
        if (numeroFatura.length() != 9 || FuncoesUteis.verificaCaracteres(numeroFatura,'0', '9')){
            System.out.println("\nO número de fatura introduzido não é válido");
            verificacao = false;
        } else {
            verificacao = existeNumeroFatura(numeroFatura, arrayFaturas);
        }
        return verificacao;
    }

    protected Fatura criaFatura(ArrayList<Cliente> arrayClientes, ArrayList<Fatura> arrayFaturas){
        Scanner scannerObterResposta = new Scanner(System.in);

        String numeroFatura = null;
        String quantidadeProdutosInserir = null;
        int quantidadeProdutos = 0;

        boolean verificacaoNumeroFatura = false;
        boolean verificacaoQuantidade = false;
        boolean verificaEscolhaProduto = false;

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
                quantidadeProdutos = Integer.parseInt(quantidadeProdutosInserir);
            }
        }

        ArrayList<Produto> arrayProdutos= new ArrayList<Produto>();

        for(int i = 0; i < quantidadeProdutos; i++){
            do{
                System.out.print("\nIntroduza o tipo de produto que prentede inserir (Alimentar/Farmacia): ");
                String tipoProduto = scannerObterResposta.nextLine();
                switch (tipoProduto) {
                    case "Alimentar":
                        do {
                            System.out.print("\nIntroduza o tipo de taxa associada ao produto (Reduzida/Intermedia/Normal): ");
                            String tipoTaxa = scannerObterResposta.nextLine();
                            switch (tipoTaxa){
                                case "Reduzida":
                                    ProdutoAlimentarTaxaReduzida produtoAlimentarTaxaReduzida = ProdutoAlimentarTaxaReduzida.criaProdutoTaxaReduzida();

                                    arrayProdutos.add(produtoAlimentarTaxaReduzida);
                                    System.out.println("\nProduto adicionado com sucesso!");
                                    verificaEscolhaProduto = true;
                                    break;

                                case "Intermedia":
                                    ProdutoAlimentarTaxaIntermedia produtoAlimentarTaxaIntermedia = ProdutoAlimentarTaxaIntermedia.criaProdutoTaxaIntermedia();

                                    arrayProdutos.add(produtoAlimentarTaxaIntermedia);
                                    System.out.println("\nProduto adicionado com sucesso!");
                                    verificaEscolhaProduto = true;
                                    break;

                                case "Normal":
                                    ProdutoAlimentarTaxaNormal produtoAlimentarTaxaNormal = ProdutoAlimentarTaxaNormal.criaProdutoTaxaNormal();

                                    arrayProdutos.add(produtoAlimentarTaxaNormal);
                                    System.out.println("\nProduto adicionado com sucesso!");
                                    verificaEscolhaProduto = true;
                                    break;

                                default:
                                    System.out.println("Opção inválida.");
                                    break;
                            }
                        } while(!verificaEscolhaProduto);
                        break;

                    case "Farmacia":
                        do {
                            System.out.print("\nO produto tem prescrição médica? (Sim/Nao) -> ");
                            String prescricao = scannerObterResposta.next();
                            switch (prescricao){
                                case "Sim":
                                    ProdutoFarmaciaComPrescricao produtoFarmaciaComPrescricao = ProdutoFarmaciaComPrescricao.criaProdutoComPrescricao();

                                    arrayProdutos.add(produtoFarmaciaComPrescricao);
                                    System.out.println("\nProduto adicionado com sucesso!");
                                    verificaEscolhaProduto = true;
                                    break;

                                case "Nao":
                                    ProdutoFarmaciaSemPrescricao produtoFarmaciaSemPrescricao = ProdutoFarmaciaSemPrescricao.criaProdutoSemPrescricao();

                                    arrayProdutos.add(produtoFarmaciaSemPrescricao);
                                    System.out.println("\nProduto adicionado com sucesso!");
                                    verificaEscolhaProduto = true;
                                    break;

                                default:
                                    System.out.println("Opção inválida.");
                                    break;
                            }
                        } while(!verificaEscolhaProduto);
                        break;

                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } while(!verificaEscolhaProduto);
        }
        return new Fatura(numeroFatura, cliente, dataFatura, arrayProdutos);
    }
}