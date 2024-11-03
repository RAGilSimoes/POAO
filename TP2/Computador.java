package TP2;

public class Computador{
    private String nivel;
    private int id;
    private int ram;
    private int armazenamento;
    private double cpu;
    private String arquitetura;

    public Computador(String nivel, int id, int ram, int armazenamento, double cpu, String arquitetura){
        this.nivel = nivel;
        this.id = id;
        this.ram = ram;
        this.armazenamento = armazenamento;
        this.cpu = cpu;
        this.arquitetura = arquitetura;
    }

    public String getNivel(){
        return this.nivel;
    }

    public int getId(){
        return this.id;
    }

    public int getRam(){
        return this.ram;
    }

    public int getArmazenamento(){
        return this.armazenamento;
    }

    public double getCPU(){
        return this.cpu;
    }

    public String getArquitetura(){
        return this.arquitetura;
    }

    public double getCpu() {
        return cpu;
    }

    public void setArmazenamento(int armazenamento) {
        this.armazenamento = armazenamento;
    }

    public void setArquitetura(String arquitetura) {
        this.arquitetura = arquitetura;
    }

    public void setCpu(double cpu) {
        this.cpu = cpu;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public String toString(){
        return ("ID -> " + this.getId() + "; RAM -> " + this.getRam() + "GB; Armazenamento -> " + this.getArmazenamento() + "GB; CPU -> " + this.getCPU() + "GHz; Nível -> " + this.getNivel() + "; Arquitetura -> " + this.getArquitetura());
    }

    public String getTipo(){
        return "TP2.Computador";
    }
}