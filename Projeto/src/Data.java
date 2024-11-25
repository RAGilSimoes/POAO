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

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    /**
     * Verifica data data.
     *
     * @return the data
     */
    protected boolean verificaData(String dataIntroduzida){
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        boolean verificaDataIntroduzida = true;
        if((dataIntroduzida.length() != 10) || (dataIntroduzida.charAt(2) != '/' || dataIntroduzida.charAt(5) != '/')) {
            System.out.println("\nData introduzida no formato inválido.");
            verificaDataIntroduzida = false;
        } else {
            String[] dataSeparada = dataIntroduzida.split("/");

            for(String string: dataSeparada){
                verificaDataIntroduzida = funcoesUteis.verificaInt(string);
                if(!verificaDataIntroduzida){
                    verificaDataIntroduzida = false;
                    break;
                }
            }

            if(!verificaDataIntroduzida){
                System.out.println("\nData inválida.");
                verificaDataIntroduzida = false;
            } else {
                int dia = Integer.parseInt(dataSeparada[0]);
                int mes = Integer.parseInt(dataSeparada[1]);
                int ano = Integer.parseInt(dataSeparada[2]);
                if((dia < 1 || dia > 31) || (mes < 1 || mes > 12) || (ano > 2024)){
                    System.out.println("\nData inválida.");
                    verificaDataIntroduzida = false;
                }
            }
        }
        return verificaDataIntroduzida;
    }

    protected Data passaParaObjetoData(String dataIntroduzida) {
        Data dataFatura = new Data(0,0,0);
        String[] dataSeparada = dataIntroduzida.split("/");
        int dia = Integer.parseInt(dataSeparada[0]);
        int mes = Integer.parseInt(dataSeparada[1]);
        int ano = Integer.parseInt(dataSeparada[2]);
        dataFatura.setDia(dia);
        dataFatura.setMes(mes);
        dataFatura.setAno(ano);
        return dataFatura;
    }
}
