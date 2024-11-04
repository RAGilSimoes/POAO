package TP2;

/**
 * @author Guilherme Carvalho e Ricardo Simões
 * @version 1.0
 */

/**
 * Classe que representa um servidor
 */
public class Servidor extends Computador {
    /**
     * Construtor de um servidor com nivel predefinido como "Cloud"
     * @param id ID do servidor
     * @param ram Ram do servidor
     * @param armazenamento Amazenamento do servidor
     * @param cpu Cpu do servidor
     * @param arquitetura Arquitetura do servidor
     */
    public Servidor(int id, int ram, int armazenamento, double cpu, String arquitetura) {
        super("Cloud", id, ram, armazenamento, cpu, arquitetura);
    }

    @Override
    /**
     * Getter para obter o consumo do servidor a partir da fórmula
     * @return Consumo do servidor
     */
    public double getConsumo(){
        return Math.round((80 * this.getCPU()));
    }

    @Override
    /**
     * Getter para obter o tipo do servidor
     * @return Tipo do servidor
     */
    public String getTipo(){
        return "Servidor";
    }
}