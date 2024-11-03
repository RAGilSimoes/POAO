package TP2;

public class RaspberryPi extends Computador {
    private double consumo;
    public RaspberryPi(int id, int ram, int armazenamento, double cpu, String arquitetura) {
        super("IOT", id, ram, armazenamento, cpu, arquitetura);
    }

    public double getConsumo(){
        return Math.round((20 * this.getCPU()));
    }

    public String getTipo(){
        return "Raspberry Pi";
    }
}