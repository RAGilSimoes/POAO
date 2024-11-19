package Projeto;

import java.util.Scanner;

abstract class ProdutoAlimentar extends Produto{
    protected String biologico;
    protected String tipoTaxa;

    public ProdutoAlimentar(String codigo, String nome, String descricao, String quantidade, String valorSemIVA, String biologico, String tipoTaxa){
        super(codigo, nome, descricao, quantidade, valorSemIVA);
        this.biologico = biologico;
        this.tipoTaxa = tipoTaxa;
    }

    public String toString() {
        return (super.toString() + "; Biologico -> " + this.biologico + "; Taxa -> " + this.tipoTaxa);
    }

    public String getBiologico() {
        return biologico;
    }

    public String getTipoTaxa() {
        return tipoTaxa;
    }

    public void setBiologico(String biologico) {
        this.biologico = biologico;
    }

    public void setTipoTaxa(String tipoTaxa) {
        this.tipoTaxa = tipoTaxa;
    }

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

    public double calculaPrecoFinalComIVA(int taxaAplicada, ProdutoAlimentar produtoAlimentarRecebido){
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

