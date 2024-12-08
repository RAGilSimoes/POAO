/**
 * @author Guilherme Carvalho e Ricardo Simoes
 * @version 1.0
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe que representa o objeto Produto Alimentar
 */
abstract class ProdutoAlimentar extends Produto implements Serializable {
    /**
     * Biologico
     */
    private String biologico;
    /**
     * Tipo taxa
     */
    private String tipoTaxa;

    /**
     * Construtor do produto alimentar
     *
     * @param codigo       codigo
     * @param nome         nome
     * @param descricao    descricao
     * @param quantidade   quantidade
     * @param valorUnidade valor sem iva
     * @param biologico    caracteristica biologica
     * @param tipoTaxa     tipo da taxa
     */
    protected ProdutoAlimentar(String codigo, String nome, String descricao, String quantidade, String valorUnidade, String biologico, String tipoTaxa){
        super(codigo, nome, descricao, quantidade, valorUnidade);
        this.biologico = biologico;
        this.tipoTaxa = tipoTaxa;
    }

    public String toString() {
        return (super.toString() + "; Biologico -> " + this.biologico + "; Taxa -> " + this.tipoTaxa);
    }

    /**
     * Getter da caracteriscica biologica
     *
     * @return caracteristica biologica
     */
    protected String getBiologico() {
        return biologico;
    }

    /**
     * Getter do tipo de taxa
     *
     * @return tipo de taxa
     */
    protected String getTipoTaxa() {
        return tipoTaxa;
    }

    /**
     * Setter caracteristica biologica
     *
     * @param biologico caracteristica biologica
     */
    protected void setBiologico(String biologico) {
        this.biologico = biologico;
    }

    /**
     * Setter tipo de taxa
     *
     * @param tipoTaxa tipo de taxa
     */
    protected void setTipoTaxa(String tipoTaxa) {
        this.tipoTaxa = tipoTaxa;
    }

    /**
     * Obter informacao do produto alimentar
     *
     * @param arrayProdutos array de produtos
     * @return informacao do produto alimentar
     */
    protected String[] obterInformacaoProdutoAlimentar(ArrayList<Produto> arrayProdutos) {
        Scanner scannerObterResposta = new Scanner(System.in);

        boolean verificaBiologico = false;
        String biologicoProduto = null;

        String[] arrayInformacoesProduto = obterInformacoesProduto(arrayProdutos);

        do {
            System.out.print("O produto é biológico? \n1-> Sim | 2-> Nao ");
            biologicoProduto = scannerObterResposta.nextLine();

            switch (biologicoProduto) {
                case "1":
                    biologicoProduto = "Sim";
                    verificaBiologico = true;
                    break;

                case "2":
                    biologicoProduto = "Nao";
                    verificaBiologico = true;
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while(!verificaBiologico);


        String[] arrayInformacoesProdutoAlimentar = new String[6];
        System.arraycopy(arrayInformacoesProduto, 0, arrayInformacoesProdutoAlimentar, 0, arrayInformacoesProduto.length);
        arrayInformacoesProdutoAlimentar[5] = biologicoProduto;

        return arrayInformacoesProdutoAlimentar;
    }


    protected String getTipo() {
        return "Produto Alimentar";
    }

    /**
     * Calcula preco final com iva
     *
     * @param taxaAplicada             taxa aplicada
     * @param produtoAlimentarRecebido produto alimentar recebido
     * @return preco final com iva
     */
    protected double calculaPrecoFinalComIVA(int taxaAplicada, ProdutoAlimentar produtoAlimentarRecebido){
        int quantidadeProduto = Integer.parseInt(produtoAlimentarRecebido.getQuantidade());
        double valorPorUnidade = Double.parseDouble(produtoAlimentarRecebido.getValorUnidade());

        double taxaAplicadaDecimal = (taxaAplicada / 100.0);

        double precoFinalComIVA;

        double valorImposto = (valorPorUnidade * taxaAplicadaDecimal);

        String biologico = produtoAlimentarRecebido.getBiologico();
        if(biologico.equalsIgnoreCase("Sim")){
            double valorImpostoComDesconto = (valorImposto - (valorImposto * 0.1));
            precoFinalComIVA = ((valorPorUnidade + valorImpostoComDesconto) * quantidadeProduto);
        } else {
            precoFinalComIVA = (quantidadeProduto * (valorPorUnidade + valorImposto));
        }
        precoFinalComIVA = (Math.floor(precoFinalComIVA * 100) /100);
        return precoFinalComIVA;
    }
}