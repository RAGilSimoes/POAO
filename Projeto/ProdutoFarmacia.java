package Projeto;

abstract class ProdutoFarmacia extends Produto {
    protected String prescricao;

    public ProdutoFarmacia(String codigo, String nome, String descricao, String quantidade, String valorSemIVA, String prescricao){
        super(codigo, nome, descricao, quantidade, valorSemIVA);
        this.prescricao = prescricao;
    }

    protected String[] obterInformacaoProdutoFarmacia() {
        return this.obterInformacoesProduto();
    }

    protected String getTipo() {
        return "Produto Farmacia";
    }

    public double calculaPrecoFinalComIVA(int taxaAplicada, ProdutoFarmacia produtoFarmacia){
        int quantidadeProduto = Integer.parseInt(produtoFarmacia.getQuantidade());
        double valorPorUnidade = Double.parseDouble(produtoFarmacia.getValorSemIVA());

        double taxaAplicadaDecimal = (taxaAplicada / 100.0);

        double valorImposto = (valorPorUnidade * taxaAplicadaDecimal);

        double precoFinalComIVA = (quantidadeProduto * (valorPorUnidade + valorImposto));

        return precoFinalComIVA;
    }
}

