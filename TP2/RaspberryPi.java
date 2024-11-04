package TP2;

/**
 * @author Guilherme Carvalho e Ricardo Simões
 * @version 1.0
 */

/**
 * Classe que representa um RaspeberryPi
 */
public class RaspberryPi extends Computador {
    /**
     * Construtor de um RaspberryPi com nivel predefinido como "IOT"
     * @param id ID do RaspberryPi
     * @param ram Ram do RaspberryPi
     * @param armazenamento Armazenamento do RaspberryPi
     * @param cpu Cpu do RaspberryPi
     * @param arquitetura Arquitetura do RaspberryPi
     */
    public RaspberryPi(int id, int ram, int armazenamento, double cpu, String arquitetura) {
        super("IOT", id, ram, armazenamento, cpu, arquitetura);
    }

    @Override
    /**
     * Método que calcula e retorna o consumo do RaspberryPi
     * @return Consumo do RaspberryPi
     */
    public double getConsumo(){
        return Math.round((20 * this.getCPU()));
    }

    @Override
    /**
     * Método que retorna o tipo do RaspberryPi
     * @return Tipo do RaspberryPi
     */
    public String getTipo(){
        return "Raspberry Pi";
    }
}