package Projeto.src;

import java.util.Scanner;

/**
 * The type Data.
 */
public class Data {
    /**
     * The Dia.
     */
    protected int dia, /**
     * The Mes.
     */
    mes, /**
     * The Ano.
     */
    ano;

    /**
     * Instantiates a new Data.
     *
     * @param dia the dia
     * @param mes the mes
     * @param ano the ano
     */
    public Data(int dia, int mes, int ano){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    /**
     * Verifica data data.
     *
     * @return the data
     */
    protected Data verificaData(){
        FuncoesUteis funcoesUteis = new FuncoesUteis();
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

                for(String string: dataSeparada){
                    verificaDataIntroduzida = funcoesUteis.verificaInt(string);
                }

                if(!verificaDataIntroduzida){
                    System.out.println("\nData inválida.");
                } else {
                    int dia = Integer.parseInt(dataSeparada[0]);
                    int mes = Integer.parseInt(dataSeparada[1]);
                    int ano = Integer.parseInt(dataSeparada[2]);
                    if((dia < 1 || dia > 31) || (mes < 1 || mes > 12) || (ano > 2024)){
                        System.out.println("\nData inválida.");
                        verificaDataIntroduzida = false;
                    } else {
                        data = new Data(dia, mes, ano);
                    }
                }
            }
        } while(!verificaDataIntroduzida);

        return data;
    }
}
