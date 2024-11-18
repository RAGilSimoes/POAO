package Projeto;

import java.util.Scanner;

abstract class ProdutoFarmacia extends Produto {
    protected String prescricao;

    public ProdutoFarmacia(String codigo, String nome, String descricao, String quantidade, String valorSemIVA, String prescricao){
        super(codigo, nome, descricao, quantidade, valorSemIVA);
        this.prescricao = prescricao;
    }

    protected static String[] obterInformacaoProdutoFarmacia() {
        return Produto.obterInformacoesProduto();
    }

    protected String getTipo() {
        return "Produto Farmacia";
    }
}

