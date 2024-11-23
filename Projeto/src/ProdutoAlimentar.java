package Projeto.src;

import java.util.Scanner;

/**
 * The type Produto alimentar.
 */
abstract class ProdutoAlimentar extends Produto{
    /**
     * The Biologico.
     */
    protected String biologico;
    /**
     * The Tipo taxa.
     */
    protected String tipoTaxa;

    /**
     * Instantiates a new Produto alimentar.
     *
     * @param codigo      the codigo
     * @param nome        the nome
     * @param descricao   the descricao
     * @param quantidade  the quantidade
     * @param valorSemIVA the valor sem iva
     * @param biologico   the biologico
     * @param tipoTaxa    the tipo taxa
     */
    public ProdutoAlimentar(String codigo, String nome, String descricao, String quantidade, String valorSemIVA, String biologico, String tipoTaxa){
        super(codigo, nome, descricao, quantidade, valorSemIVA);
        this.biologico = biologico;
        this.tipoTaxa = tipoTaxa;
    }

    public String toString() {
        return (super.toString() + "; Biologico -> " + this.biologico + "; Taxa -> " + this.tipoTaxa);
    }

    /**
     * Gets biologico.
     *
     * @return the biologico
     */
    public String getBiologico() {
        return biologico;
    }

    /**
     * Gets tipo taxa.
     *
     * @return the tipo taxa
     */
    public String getTipoTaxa() {
        return tipoTaxa;
    }

    /**
     * Sets biologico.
     *
     * @param biologico the biologico
     */
    public void setBiologico(String biologico) {
        this.biologico = biologico;
    }

    /**
     * Sets tipo taxa.
     *
     * @param tipoTaxa the tipo taxa
     */
    public void setTipoTaxa(String tipoTaxa) {
        this.tipoTaxa = tipoTaxa;
    }

    /**
     * Obter informacao produto alimentar string [ ].
     *
     * @return the string [ ]
     */
    protected String[] obterInformacaoProdutoAlimentar() {
        Scanner scannerObterResposta = new Scanner(System.in);

        boolean verificaBiologico = false;
        String biologicoProduto = null;

        String[] arrayInformacoesProduto = obterInformacoesProduto();

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
     * Calcula preco final com iva double.
     *
     * @param taxaAplicada             the taxa aplicada
     * @param produtoAlimentarRecebido the produto alimentar recebido
     * @return the double
     */
    protected double calculaPrecoFinalComIVA(int taxaAplicada, ProdutoAlimentar produtoAlimentarRecebido){
        int quantidadeProduto = Integer.parseInt(produtoAlimentarRecebido.getQuantidade());
        double valorPorUnidade = Double.parseDouble(produtoAlimentarRecebido.getValorSemIVA());

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

        return precoFinalComIVA;
    }
}

