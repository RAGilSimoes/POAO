package Projeto;

import java.util.Scanner;

public class ProdutoFarmaciaComPrescricao extends ProdutoFarmacia {
    protected String medico;
    protected final int[] arrayTaxas = {6,5,4};

    public ProdutoFarmaciaComPrescricao(String codigo, String nome, String descricao, String quantidade, String valorSemIVA, String prescricao, String medico){
        super(codigo, nome, descricao, quantidade, valorSemIVA, prescricao);
        this.medico = medico;
    }

    protected double obtemValorComIVA(Cliente clienteRecebido){
        final int[] arrayTaxas = {6,5,4};

        int taxaAplicada = this.getTaxaAplicada(clienteRecebido, arrayTaxas);

        double precoFinalComIVA = this.calculaPrecoFinalComIVA(taxaAplicada, this);
        return precoFinalComIVA;
    }

    protected static ProdutoFarmaciaComPrescricao criaProdutoComPrescricao(){
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        String prescricao = "Sim";
        boolean verificacaoNome = false;
        String medicoPrescritor = null;

        Scanner scannerObterResposta = new Scanner(System.in);

        String[] arrayInformacoesProdutoFarmacia = ProdutoFarmacia.obterInformacaoProdutoFarmacia();

        do {
            System.out.print("Introduza o nome do médico que prescreveu o produto: ");
            medicoPrescritor = scannerObterResposta.nextLine();
            verificacaoNome = funcoesUteis.verificaNome(medicoPrescritor);
        } while(!verificacaoNome);

        return new ProdutoFarmaciaComPrescricao(arrayInformacoesProdutoFarmacia[0], arrayInformacoesProdutoFarmacia[1], arrayInformacoesProdutoFarmacia[2], arrayInformacoesProdutoFarmacia[3], arrayInformacoesProdutoFarmacia[4], prescricao, medicoPrescritor);
    }

    protected String getTipo() {
        return "Produto Farmacia Com Prescricao";
    }
}
