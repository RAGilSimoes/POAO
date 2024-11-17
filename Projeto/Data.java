package Projeto;

import java.util.Scanner;

public class Data {
    protected int dia, mes, ano;

    public Data(int dia, int mes, int ano){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    protected static Data verificaData(){
        boolean verificaDataIntroduzida = false;
        Data data = new Data(0, 0, 0);
        Scanner scannerObterResposta = new Scanner(System.in);
        do {
            System.out.print("\nIntroduza a data da fatura no formato dd/mm/aaaa: ");
            String dataIntroduzida = scannerObterResposta.nextLine();
            if((dataIntroduzida.length() != 10) || (dataIntroduzida.charAt(2) != '/' || dataIntroduzida.charAt(5) != '/')) {
                System.out.println("\nData introduzida no formato inválido.");
            } else {
                String[] dataSeparada = dataIntroduzida.split("/");
                int dia = Integer.parseInt(dataSeparada[0]);
                int mes = Integer.parseInt(dataSeparada[1]);
                int ano = Integer.parseInt(dataSeparada[2]);

                if((dia < 1 || dia > 31) || (mes < 1 || mes > 12) || (ano > 2024)){
                    System.out.println("\nData inválida.");
                } else {
                    data = new Data(dia, mes, ano);
                    verificaDataIntroduzida = true;
                }
            }
        } while(!verificaDataIntroduzida);

        return data;
    }
}
