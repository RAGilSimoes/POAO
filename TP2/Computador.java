package TP2;

/**
 * @author Guilherme Carvalho e Ricardo Simões
 * @version 1.0
 */


/**
 * Classe que representa um computador
 */
public class Computador{
    /**
     * Nível do computador dentro da infraestrutura
     */
    private String nivel;
    /**
     * ID do computador
     */
    private int id;
    /**
     * Ram do computador
     */
    private int ram;
    /**
     * Armazenamento do computador
     */
    private int armazenamento;
    /**
     * Cpu do computador
     */
    private double cpu;
    /**
     * Arquitetura do computador
     */
    private String arquitetura;

    /**
     * Construtor de um computador
     * @param nivel Nível do computador dentro da infraestrutura
     * @param id ID do computador
     * @param ram Ram do computador
     * @param armazenamento Armazenamento do computador
     * @param cpu Cpu do computador
     * @param arquitetura Arquitetura do computador
     */
    public Computador(String nivel, int id, int ram, int armazenamento, double cpu, String arquitetura){
        this.nivel = nivel;
        this.id = id;
        this.ram = ram;
        this.armazenamento = armazenamento;
        this.cpu = cpu;
        this.arquitetura = arquitetura;
    }

    /**
     * Getter para obter o nivel do computador
     * @return Nível do computador
     */
    public String getNivel(){
        return this.nivel;
    }

    /**
     * Getter para obter o id do computador
     * @return ID do computador
     */
    public int getId(){
        return this.id;
    }

    /**
     * Getter para obter a ram do computador
     * @return Ram do computador
     */
    public int getRam(){
        return this.ram;
    }

    /**
     * Getter para obter o armazenamento do computador
     * @return Armazenamento do computador
     */
    public int getArmazenamento(){
        return this.armazenamento;
    }

    /**
     * Getter para obter o cpu do computador
     * @return Cpu do computador
     */
    public double getCPU(){
        return this.cpu;
    }

    /**
     * Getter para obter a arquitetura
     * @return Arquitetura do computador
     */
    public String getArquitetura(){
        return this.arquitetura;
    }


    /**
     * Setter para definir o armazenamento do computador
     * @param armazenamento Armazenamento do computador
     */
    public void setArmazenamento(int armazenamento) {
        this.armazenamento = armazenamento;
    }

    /**
     * Setter para definir a arquitetura do computador
     * @param arquitetura Arquitetura do computador
     */
    public void setArquitetura(String arquitetura) {
        this.arquitetura = arquitetura;
    }

    /**
     * Setter para definir o cpu do computador
     * @param cpu Cpu do computador
     */
    public void setCpu(double cpu) {
        this.cpu = cpu;
    }

    /**
     * Setter para definir o id do computador
     * @param id ID do computador
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter para definir o nível do computador dentro da infraestrutura
     * @param nivel Nível do computador dentro da infraestrutura
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    /**
     * Setter para definir a ram do computador
     * @param ram Ram do computador
     */
    public void setRam(int ram) {
        this.ram = ram;
    }

    @Override
    /**
     * Método para apresentar informações do computador numa string
     * @return String com os dados do computador
     */
    public String toString(){
        return ("ID -> " + this.getId() + "; RAM -> " + this.getRam() + "GB; Armazenamento -> " + this.getArmazenamento() + "GB; CPU -> " + this.getCPU() + "GHz; NÃvel -> " + this.getNivel() + "; Arquitetura -> " + this.getArquitetura());
    }

    /**
     * Método para verificar o tipo de computador
     * @return String do tipo do computador
     */
    public String getTipo(){
        return "Computador";
    }

    /**
     * Getter para obter o consumo do computador
     * @return Consumo do computador
     */
    public double getConsumo(){
        return 0;
    }
}