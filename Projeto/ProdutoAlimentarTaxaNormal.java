package Projeto;

import java.util.Scanner;

public class ProdutoAlimentarTaxaNormal extends ProdutoAlimentar {
    public ProdutoAlimentarTaxaNormal(String codigo, String nome, String descricao, String quantidade, String valorSemIVA, String biologico, String taxa){
        super(codigo, nome, descricao, quantidade, valorSemIVA, biologico, taxa);
    }

    protected double obtemValorComIVA(Cliente clienteRecebido){
        final int[] arrayTaxas = {23,22,16};
        int taxaAplicada = TaxaAplicada.getTaxaAplicada(clienteRecebido, arrayTaxas);

        int quantidadeProduto = Integer.parseInt(this.getQuantidade());
        double valorPorUnidade = Double.parseDouble(this.getValorSemIVA());

        double taxaAplicadaDecimal = (taxaAplicada / 100.0);

        double precoFinalComIVA;

        double valorImposto = (valorPorUnidade * taxaAplicadaDecimal);

        String biologico = this.getBiologico();
        if(biologico.equalsIgnoreCase("Sim")){
            double valorImpostoComDesconto = (valorImposto - (valorImposto * 0.1));
            precoFinalComIVA = ((valorPorUnidade + valorImpostoComDesconto) * quantidadeProduto);
        } else {
            precoFinalComIVA = (quantidadeProduto * (valorPorUnidade + valorImposto));
        }

        return precoFinalComIVA;
    }

    protected static ProdutoAlimentarTaxaNormal criaProdutoTaxaNormal(){
        String tipoTaxa = "Normal";

        String[] arrayInformacoesProdutoAlimentar = ProdutoAlimentar.obterInformacaoProdutoAlimentar();

        return new ProdutoAlimentarTaxaNormal(arrayInformacoesProdutoAlimentar[0], arrayInformacoesProdutoAlimentar[1], arrayInformacoesProdutoAlimentar[2], arrayInformacoesProdutoAlimentar[3], arrayInformacoesProdutoAlimentar[4], arrayInformacoesProdutoAlimentar[5], tipoTaxa);
    }

    protected String getTipo() {
        return "Produto Alimentar Taxa Normal";
    }
}
