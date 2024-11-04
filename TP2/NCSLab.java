package TP2;

import java.util.ArrayList;
import java.util.Random;

public class NCSLab{
    Random random = new Random();
    private int lastID = 1;
    private String[] arrayInformacoes;
    private ArrayList<Computador> arrayComputadoresNCSLab = new ArrayList<Computador>(15);

    public NCSLab() {
        for(int i = 0; i < 5; i++){
            arrayInformacoes = inicializarValores("Servidor");
            Servidor servidor = new Servidor(Integer.parseInt(arrayInformacoes[0]), Integer.parseInt(arrayInformacoes[1]), Integer.parseInt(arrayInformacoes[2]), Double.parseDouble(arrayInformacoes[3]), arrayInformacoes[4]);
            arrayComputadoresNCSLab.add(servidor);
        }

        for(int i = 0; i < 5; i++){
            arrayInformacoes = inicializarValores("Laptop");
            Laptop laptop = new Laptop(Integer.parseInt(arrayInformacoes[0]), Integer.parseInt(arrayInformacoes[1]), Integer.parseInt(arrayInformacoes[2]), Double.parseDouble(arrayInformacoes[3]), arrayInformacoes[4], arrayInformacoes[5]);
            arrayComputadoresNCSLab.add(laptop);
        }

        for(int i = 0; i < 5; i++){
            arrayInformacoes = inicializarValores("Raspberry Pi");
            RaspberryPi raspberryPi = new RaspberryPi(Integer.parseInt(arrayInformacoes[0]), Integer.parseInt(arrayInformacoes[1]), Integer.parseInt(arrayInformacoes[2]), Double.parseDouble(arrayInformacoes[3]), arrayInformacoes[4]);
            arrayComputadoresNCSLab.add(raspberryPi);
        }
    }

    public String[] inicializarValores(String tipoComputador){ //melhorar criação do array
        int id;
        int ram = 0;
        int armazenamento = 0;
        double cpu = 0.0;
        String arquitetura;
        String[] informacoes;
        String gpu = null;

        id = lastID++;
        arquitetura = criaArquitetura();

        switch (tipoComputador) {
            case "Servidor": {
                ram = (int) Math.pow(2, random.nextInt(3) + 7); //7 a 9
                armazenamento = (int) Math.pow(2, random.nextInt(5) + 10); //10 a 14
                cpu = (double) Math.round(((random.nextDouble() + 3) * 10)) / 10; //3 a 4
                break;
            }

            case "Laptop": {
                ram = (int) Math.pow(2, random.nextInt(3) + 4); //4 a 6
                armazenamento = (int) Math.pow(2, random.nextInt(3) + 8); //8 a 10
                cpu = (double) Math.round(((random.nextDouble() + 2) * 10)) / 10; //2 a 3
                if (random.nextInt(2) == 1) { //0 a 1
                    gpu = "Sim";
                } else {
                    gpu = "Nao";
                }
                break;
            }

            case "Raspberry Pi": {
                ram = (int) Math.pow(2, random.nextInt(3) + 1); //1 a 3
                armazenamento = (int) Math.pow(2, random.nextInt(4) + 4); //4 a 7
                cpu = (double) Math.round(((random.nextDouble() + 1)* 10)) / 10; //1 a 2
                break;
            }
        }
        informacoes = new String[]{String.valueOf(id), String.valueOf(ram), String.valueOf(armazenamento), String.valueOf(cpu), arquitetura, gpu};
        return informacoes;
    }

    public String criaArquitetura() {
        String arquitetura;
        if (random.nextInt(2) == 1) {
            arquitetura = "x64";
        } else {
            arquitetura = "ARM";
        }
        return arquitetura;
    }

    public void imprimeComputadores() {
        System.out.println("Computadores: ");
        for(Computador computador: arrayComputadoresNCSLab){
            System.out.println(computador.toString());
        }
    }

    public void imprimeComputadoresx64() {
        System.out.println("Computadores arquitetura x64: ");
        for(Computador computador: arrayComputadoresNCSLab){
            String arquiteturaComputadorTrabalhado = computador.getArquitetura();
            if(arquiteturaComputadorTrabalhado.equalsIgnoreCase("x64")){
                System.out.println(computador.toString());
            }
        }
    }

    public void imprimeConsumosComputadores() {
        System.out.println("Consumos dos computadores: ");
        for(Computador computador: arrayComputadoresNCSLab){
            System.out.print("ID -> " + computador.getId() + "; Tipo -> " + computador.getTipo());
            switch (computador.getTipo()) {
                case "Servidor": {
                    Servidor servidor = (Servidor) computador;
                    System.out.println("; Consumo -> " + servidor.getConsumo() + "W");
                    break;
                }

                case "Laptop": {
                    Laptop laptop = (Laptop) computador;
                    System.out.println("; Consumo -> " + laptop.getConsumo() + "W");
                    break;
                }

                case "Raspberry Pi": {
                    RaspberryPi raspberryPi = (RaspberryPi) computador;
                    System.out.println("; Consumo -> " + raspberryPi.getConsumo() + "W");
                    break;
                }
            }
        }
    }

    public ArrayList<Computador> getArrayComputadoresNCSLab() {
        return arrayComputadoresNCSLab;
    }

    public void setArrayComputadoresNCSLab(ArrayList<Computador> arrayComputadoresNCSLab) {
        if(arrayComputadoresNCSLab != null) {
            this.arrayComputadoresNCSLab = arrayComputadoresNCSLab;
        }
    }
}