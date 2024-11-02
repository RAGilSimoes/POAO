package TP2;

public class Computador{
    private String nivel;
    private int id;
    private double ram;
    private double armazenamento;
    private double cpu;
    private String arquitetura;
    public Computador(String nivel, int id, double ram, double armazenamento, double cpu, String arquitetura){
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

    public double getRam(){
        return this.ram;
    }

    public double getArmazenamento(){
        return this.armazenamento;
    }

    public double getCPU(){
        return this.cpu;
    }

    public String getArquitetura(){
        return this.arquitetura;
    }

    public String toString(){
        return ("Nível -> " + this.getNivel() + "; ID -> " + this.getId() + "; RAM -> " + this.getRam() + "; Armazenamento -> " + this.getArmazenamento() + "; CPU -> " + this.getCPU() + "; Arquitetura -> " + this.getArquitetura());
    }
}
