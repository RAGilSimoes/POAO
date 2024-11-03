package TP2;

public class Laptop extends Computador {
    private double consumo;
    private String gpu;
    public Laptop(int id, int ram, int armazenamento, double cpu, String arquitetura, String gpu) {
        super("Edge" , id, ram, armazenamento, cpu, arquitetura);
        this.gpu = gpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public double getConsumo(){
        if(this.getGpu().equals("Sim")) {
            return Math.round((50 * this.getCPU() * 1.2));
        } else {
            return Math.round((50 * this.getCPU()));
        }
    }

    public String getTipo(){
        return "Laptop";
    }

    public String toString(){
        return (super.toString() + "; GPU -> " + this.getGpu());
    }
}