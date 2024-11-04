package TP2;

/**
 * @author Guilherme Carvalho e Ricardo Simões
 * @version 1.0
 */


/**
 * Classe que representa um laptop
 */
public class Laptop extends Computador {
    /**
     * Verificação da existência de gpu no computador
     */
    private String gpu;

    /**
     * Construtor de um laptop com nível predefinido como "Edge"
     * @param id ID do laptop
     * @param ram Ram do laptop
     * @param armazenamento Armazenamento do laptop
     * @param cpu Cpu do laptop
     * @param arquitetura Arquitetura do laptop
     * @param gpu Gpu do laptop
     */
    public Laptop(int id, int ram, int armazenamento, double cpu, String arquitetura, String gpu) {
        super("Edge" , id, ram, armazenamento, cpu, arquitetura);
        this.gpu = gpu;
    }

    /**
     * Getter para obter a verificação do gpu do laptop
     * @return Gpu do laptop
     */
    public String getGpu() {
        return gpu;
    }

    /**
     * Setter para alterar a verificação do gpu do laptop
     * @param gpu Gpu do laptop
     */
    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    /**
     * Método para calcular o consumo do laptop e para devolver o valor consoante a existência de gpu no laptop
     * @return Consumo do laptop
     */
    public double getConsumo(){
        if(this.getGpu().equals("Sim")) {
            return Math.round((50 * this.getCPU() * 1.2));
        } else {
            return Math.round((50 * this.getCPU()));
        }
    }

    @Override
    /**
     * Getter para obter o tipo do computador
     * @return Tipo do computador
     */
    public String getTipo(){
        return "Laptop";
    }
}