package POAO.Ficha4.exercicio1;

public class Data {
    private int dia;
    private int mes;
    private int ano;
    private String[] meses = {"janeiro","fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"};

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public String toString() {
        return (String.valueOf(this.dia) + " de " + meses[this.mes - 1] + " de " + String.valueOf(this.ano));
    }
}
