package Ficha4.exercicio1;

public class Data {
    private int[] dataIntroduzida = new int[3];
    private String mesString = "";
    private String[] meses = {"janeiro","fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"};

    public Data(int dia, int mes, int ano) {
        dataIntroduzida[0] = dia;
        dataIntroduzida[1] = mes;
        dataIntroduzida[2] = ano;
    }

    private String converteMesString(int mes) {
        return meses[mes];
    }

    private String dataFinal = "";
    public String toString() {
        mesString = this.converteMesString(dataIntroduzida[1]);
        dataFinal = (String.valueOf(dataIntroduzida[0]) + " de " + mesString + " de " + String.valueOf(dataIntroduzida[2]));
        return dataFinal;
    }
}
