package Projeto;

import java.util.ArrayList;
import java.util.Scanner;

public class Fatura {
    protected String nFatura;
    protected Cliente cliente;
    protected Data dataFatura;
    ArrayList<Produto> listaProdutos;


    public Fatura(String nFatura, Cliente cliente, Data dataFatura, ArrayList<Produto> listaProdutos) {
        this.nFatura = nFatura;
        this.cliente = cliente;
        this.dataFatura = dataFatura;
        this.listaProdutos = listaProdutos;
    }

    public ArrayList<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Data getDataFatura() {
        return dataFatura;
    }

    public String getnFatura() {
        return nFatura;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDataFatura(Data dataFatura) {
        this.dataFatura = dataFatura;
    }

    public void setListaProdutos(ArrayList<Produto> listaProdutos) {
        if(listaProdutos != null){
            this.listaProdutos = listaProdutos;
        }
    }

    public void setnFatura(String nFatura) {
        this.nFatura = nFatura;
    }

    public String toString(){
        return ("Número de Fatura -> " + this.getnFatura() + "; Cliente -> " + this.getCliente().getNome() + "; Localização do Cliente -> " + this.getCliente().getLocalizacao() + "; Número de produtos -> " + this.getListaProdutos().size() + "; Valor total sem IVA -> " + "; Valor total com IVA -> ");
    }

    protected Fatura criaFatura(ArrayList<Cliente> arrayClientes, POOFS poofs){
        VerificaDigitos verificaDigitos = new VerificaDigitos();
        Fatura fatura;

        Scanner scannerObterResposta = new Scanner(System.in);

        System.out.print("\nInsira o número da fatura: ");
        String numeroFatura = scannerObterResposta.nextLine();

        poofs.listarClientes();
        System.out.print("\nIntroduza o número do cliente que pretende associar a esta fatura: ");
        int numeroEscolhaCliente = poofs.protecaoEscolha(1, arrayClientes.size());
        Cliente cliente = arrayClientes.get(numeroEscolhaCliente - 1);

        System.out.print("\nIntroduza a data da fatura no formato dd/mm/aaaa: ");
        String data = scannerObterResposta.nextLine();
        String[] dataSeparada = data.split("/");
        Data dataFatura = new Data(Integer.parseInt(dataSeparada[0]), Integer.parseInt(dataSeparada[1]), Integer.parseInt(dataSeparada[2]));

        System.out.print("\nIntroduza a quantidade de produtos que pretende inserir na fatura: ");
        int quantidadeProdutosInserir = scannerObterResposta.nextInt();
        ArrayList<Produto> arrayProdutos= new ArrayList<Produto>();

        for(int i = 0; i < quantidadeProdutosInserir; i++){
            System.out.print("\nIntroduza o tipo de produto que prentede inserir (Alimentar/Farmacia): ");
            String tipoProduto = scannerObterResposta.next();
            switch (tipoProduto) {
                case "Alimentar":
                    System.out.print("\nIntroduza o tipo de taxa associada ao produto (Reduzida/Intermedia/Normal): ");
                    String tipoTaxa = scannerObterResposta.next();
                    switch (tipoTaxa){
                        case "Reduzida":
                            ProdutoAlimentarTaxaReduzida produtoAlimentarTaxaReduzida = new ProdutoAlimentarTaxaReduzida(0, null, null, 0, 0, null, null, 0, null);
                            produtoAlimentarTaxaReduzida = produtoAlimentarTaxaReduzida.criaProdutoTaxaReduzida();

                            arrayProdutos.add(produtoAlimentarTaxaReduzida);
                    }
            }
        }
        return new Fatura(numeroFatura, cliente, dataFatura, arrayProdutos);
    }
}

