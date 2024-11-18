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

    protected static String[] obterInformacaoProdutoAlimentar() {
        Scanner scannerObterResposta = new Scanner(System.in);

        boolean verificaBiologico = false;
        String biologicoProduto = null;

        String[] arrayInformacoesProduto = Produto.obterInformacoesProduto();

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
}

