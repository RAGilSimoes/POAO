package Projeto;

public class ProdutoAlimentarTaxaNormal extends ProdutoAlimentar {
    public ProdutoAlimentarTaxaNormal(String codigo, String nome, String descricao, String quantidade, String valorSemIVA, String biologico, String taxa){
        super(codigo, nome, descricao, quantidade, valorSemIVA, biologico, taxa);
    }

    protected double obtemValorComIVA(Cliente clienteRecebido){
        final int[] arrayTaxas = {23,22,16};

        int taxaAplicada = getTaxaAplicada(clienteRecebido, arrayTaxas);

        double precoFinalComIVA = calculaPrecoFinalComIVA(taxaAplicada, this);

        return precoFinalComIVA;
    }

    protected ProdutoAlimentarTaxaNormal criaProdutoTaxaNormal(){
        String tipoTaxa = "Normal";

        String[] arrayInformacoesProdutoAlimentar = obterInformacaoProdutoAlimentar();

        return new ProdutoAlimentarTaxaNormal(arrayInformacoesProdutoAlimentar[0], arrayInformacoesProdutoAlimentar[1], arrayInformacoesProdutoAlimentar[2], arrayInformacoesProdutoAlimentar[3], arrayInformacoesProdutoAlimentar[4], arrayInformacoesProdutoAlimentar[5], tipoTaxa);
    }

    protected String getTipo() {
        return "Produto Alimentar Taxa Normal";
    }
}
