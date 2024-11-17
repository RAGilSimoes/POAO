package Projeto;

import java.util.Scanner;

abstract class ProdutoAlimentar extends Produto{
    protected String biologico;
    protected String taxa;

    public ProdutoAlimentar(String codigo, String nome, String descricao, String quantidade, String valorSemIVA, String biologico, String taxa){
        super(codigo, nome, descricao, quantidade, valorSemIVA);
        this.biologico = biologico;
        this.taxa = taxa;
    }

    public String toString() {
        return (super.toString() + "; Biologico -> " + this.biologico + "; Taxa -> " + this.taxa);
    }

    protected static String[] obterInformacaoProdutoAlimentar() {
        Scanner scannerObterResposta = new Scanner(System.in);

        boolean verificaBiologico = false;
        String biologicoProduto = null;

        String[] arrayInformacoesProduto = Produto.obterInformacoesProduto();

        do {
            System.out.print("O produto é biológico? (Sim/Nao) -> ");
            biologicoProduto = scannerObterResposta.nextLine();

            switch (biologicoProduto) {
                case "Sim":
                    verificaBiologico = true;
                    break;

                case "Nao":
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
}

