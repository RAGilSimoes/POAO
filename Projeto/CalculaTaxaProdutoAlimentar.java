package Projeto;

public class CalculaTaxaProdutoAlimentar {
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
