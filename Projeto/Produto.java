package Projeto;

import java.util.Scanner;

abstract class Produto{
    protected int codigo;
    protected String nome;
    protected String descricao;
    protected int quantidade;
    protected double valorSemIVA;

    public Produto(int codigo, String nome, String descricao, int quantidade, double valorSemIVA){
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorSemIVA = valorSemIVA;
    }

    public String toString() {
        return ("Codigo -> " + this.codigo + "; Nome -> " + this.nome + "; Descricao -> " + this.descricao + "; Quantidade -> " + this.quantidade + "; Valor sem IVA -> " + this.valorSemIVA);
    }
}

