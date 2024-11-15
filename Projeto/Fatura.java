package Projeto;

import java.util.ArrayList;

public class Fatura {
    protected int nFatura;
    protected Cliente cliente;
    protected Data dataFatura;
    ArrayList<Produto> listaProdutos = new ArrayList<Produto>();


    public Fatura(int nFatura, Cliente cliente, Data dataFatura, ArrayList<Produto> listaProdutos) {
        this.nFatura = nFatura;
        this.cliente = cliente;
        this.dataFatura = dataFatura;
        this.listaProdutos = listaProdutos;
    }
}

