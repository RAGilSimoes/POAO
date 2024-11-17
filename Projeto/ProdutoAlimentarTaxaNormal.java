package Projeto;

import java.util.Scanner;

public class ProdutoAlimentarTaxaNormal extends ProdutoAlimentar {
    protected int[] arrayTaxas = {23,22,16};

    public ProdutoAlimentarTaxaNormal(String codigo, String nome, String descricao, String quantidade, String valorSemIVA, String biologico, String taxa){
        super(codigo, nome, descricao, quantidade, valorSemIVA, biologico, taxa);
    }

    protected static ProdutoAlimentarTaxaNormal criaProdutoTaxaNormal(){
        String tipoTaxa = "Normal";

        String[] arrayInformacoesProdutoAlimentar = ProdutoAlimentar.obterInformacaoProdutoAlimentar();

        return new ProdutoAlimentarTaxaNormal(arrayInformacoesProdutoAlimentar[0], arrayInformacoesProdutoAlimentar[1], arrayInformacoesProdutoAlimentar[2], arrayInformacoesProdutoAlimentar[3], arrayInformacoesProdutoAlimentar[4], arrayInformacoesProdutoAlimentar[5], tipoTaxa);
    }
}
