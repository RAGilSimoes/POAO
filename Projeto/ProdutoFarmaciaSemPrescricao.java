package Projeto;

import java.util.Scanner;

public class ProdutoFarmaciaSemPrescricao extends ProdutoFarmacia {
    protected String categoria;
    protected final int[] arrayTaxas = {23,23,23};
    protected final int reducaoTaxa = -1;

    public ProdutoFarmaciaSemPrescricao(String codigo, String nome, String descricao, String quantidade, String valorSemIVA, String prescricao, String categoria){
        super(codigo, nome, descricao, quantidade, valorSemIVA, prescricao);
        this.categoria  = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    protected double obtemValorComIVA(Cliente clienteRecebido){
        final int[] arrayTaxas = {23,23,23};
        final int reducaoTaxa = -1;
        int taxaAplicada = TaxaAplicada.getTaxaAplicada(clienteRecebido, arrayTaxas);

        String categoria = this.getCategoria();
        if(categoria.equalsIgnoreCase("animais")){
            taxaAplicada += reducaoTaxa;
        }

        CalculaTaxaProdutoFarmacia calculaTaxaProdutoFarmacia = new CalculaTaxaProdutoFarmacia();
        double precoFinalComIVA = calculaTaxaProdutoFarmacia.calculaPrecoFinalComIVA(taxaAplicada, this);
        return precoFinalComIVA;
    }

    protected static ProdutoFarmaciaSemPrescricao criaProdutoSemPrescricao(){
        String prescricao = "Nao";
        Scanner scannerObterResposta = new Scanner(System.in);

        String[] arrayInformacoesProdutoFarmacia = ProdutoFarmacia.obterInformacaoProdutoFarmacia();

        System.out.print("Introduza a categoria do produto: ");
        String categoriaProduto = scannerObterResposta.nextLine();

        return new ProdutoFarmaciaSemPrescricao(arrayInformacoesProdutoFarmacia[0], arrayInformacoesProdutoFarmacia[1], arrayInformacoesProdutoFarmacia[2], arrayInformacoesProdutoFarmacia[3], arrayInformacoesProdutoFarmacia[4], prescricao, categoriaProduto);
    }

    protected String getTipo() {
        return "Produto Farmacia Sem Prescricao";
    }
}
