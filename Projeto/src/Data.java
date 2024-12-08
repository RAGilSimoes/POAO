import java.io.Serializable;

/**
 * Classe que representa o objeto Data
 */
public class Data implements Serializable {
    FuncoesUteis funcoesUteis = new FuncoesUteis();
    /**
     * Dia
     */
    private int dia; 
    /**
     * Mes
     */
    private int mes; 
    /**
     * Ano
     */
    private int ano;

    /**
     * Contrutor da Data
     *
     * @param dia dia
     * @param mes mes
     * @param ano ano
     */
    protected Data(int dia, int mes, int ano){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    /**
     * Setter do ano
     *
     * @param ano  ano
     */
    private void setAno(int ano) {
        this.ano = ano;
    }

    /**
     * Setter do dia
     *
     * @param dia  dia
     */
    private void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * Setter do mes
     *
     * @param mes  mes
     */
    private void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * Getter do ano
     *
     * @return  ano
     */
    protected int getAno() {
        return ano;
    }

    /**
     * Getter do dia
     *
     * @return  dia
     */
    protected int getDia() {
        return dia;
    }

    /**
     * Getter do mes
     *
     * @return  mes
     */
    protected int getMes() {
        return mes;
    }

    /**
     * Verifica se o formato da data esta correto
     *
     * @param dataIntroduzida  data introduzida
     * @return  data
     */
    protected boolean verificaData(String dataIntroduzida){
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

    /**
     * Passa a string escolhida para um objeto da classe Data
     *
     * @param dataIntroduzida  data introduzida
     * @return  data
     */
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