package Projeto;

public class CalculaTaxaProdutoFarmacia {
    public double calculaPrecoFinalComIVA(int taxaAplicada, ProdutoFarmacia produtoFarmacia){
        int quantidadeProduto = Integer.parseInt(produtoFarmacia.getQuantidade());
        double valorPorUnidade = Double.parseDouble(produtoFarmacia.getValorSemIVA());

        double taxaAplicadaDecimal = (taxaAplicada / 100.0);

        double valorImposto = (valorPorUnidade * taxaAplicadaDecimal);

        double precoFinalComIVA = (quantidadeProduto * (valorPorUnidade + valorImposto));

        return precoFinalComIVA;
    }
}
