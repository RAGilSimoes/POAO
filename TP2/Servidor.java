package TP2;

public class Servidor extends Computador {
    public Servidor(int id, int ram, int armazenamento, double cpu, String arquitetura) {
        super("Cloud", id, ram, armazenamento, cpu, arquitetura);
    }

    public double getConsumo(){
        return Math.round((80 * this.getCPU()));
    }

    public String getTipo(){
        return "Servidor";
    }
}