package TP2;

/**
 * @author Guilherme Carvalho e Ricardo Simões
 * @version 1.0
 */


import java.util.ArrayList;
import java.util.Random;


/**
 * Classe para representar um objeto NCSLab
 */
public class NCSLab{
    /**
     * Objeto do tipo Random
     */
    Random random = new Random();
    /**
     * Controlo dos IDs
     */
    private int lastID = 1;
    /**
     * Array para guardar as informações dos computadores
     */
    private String[] arrayInformacoes;
    /**
     * Array para guardar os computadores
     */
    private ArrayList<Computador> arrayComputadoresNCSLab = new ArrayList<Computador>(15);

    /**
     * Construtor do array de computadores
     */
    public NCSLab() {
        /**
         * Criação de 5 servidores
         */
        for(int i = 0; i < 5; i++){
            /**
             * Método para criar array com variáveis inicializadas conforme a tabela de valores do servidor
             */
            arrayInformacoes = inicializarValores("Servidor");
            /**
             * Criação do objeto do tipo Servidor com os valores do arrayInformacoes
             */
            Servidor servidor = new Servidor(Integer.parseInt(arrayInformacoes[0]), Integer.parseInt(arrayInformacoes[1]), Integer.parseInt(arrayInformacoes[2]), Double.parseDouble(arrayInformacoes[3]), arrayInformacoes[4]);
            /**
             * Passagem do objeto Servidor para o arrayComputadorsNCSLab
             */
            arrayComputadoresNCSLab.add(servidor);
        }

        /**
         * Criação de 5 laptops
         */
        for(int i = 0; i < 5; i++){
            /**
             * Método para criar array com variáveis inicializadas conforme a tabela de valores do laptop
             */
            arrayInformacoes = inicializarValores("Laptop");
            /**
             * Criação do objeto do tipo Laptop com os valores do arrayInformacoes
             */
            Laptop laptop = new Laptop(Integer.parseInt(arrayInformacoes[0]), Integer.parseInt(arrayInformacoes[1]), Integer.parseInt(arrayInformacoes[2]), Double.parseDouble(arrayInformacoes[3]), arrayInformacoes[4], arrayInformacoes[5]);
            /**
             * Passagem do objeto Laptop para o arrayComputadorsNCSLab
             */
            arrayComputadoresNCSLab.add(laptop);
        }

        /**
         * Criação de 5 RaspberryPi
         */
        for(int i = 0; i < 5; i++){
            /**
             * Método para criar array com variáveis inicializadas conforme a tabela de valores do RaspberryPi
             */
            arrayInformacoes = inicializarValores("Raspberry Pi");
            /**
             * Criação do objeto do tipo RaspberryPi com os valores do arrayInformacoes
             */
            RaspberryPi raspberryPi = new RaspberryPi(Integer.parseInt(arrayInformacoes[0]), Integer.parseInt(arrayInformacoes[1]), Integer.parseInt(arrayInformacoes[2]), Double.parseDouble(arrayInformacoes[3]), arrayInformacoes[4]);
            /**
             * Passagem do objeto RaspberryPi para o arrayComputadorsNCSLab
             */
            arrayComputadoresNCSLab.add(raspberryPi);
        }
    }

    /**
     * Método para inicializar os valores dos objetos consoante o seu tipo
     * @param tipoComputador String com o tipo de computador
     * @return Array de Strings com valores para o computador
     */
    public String[] inicializarValores(String tipoComputador){
        /**
         * ID do computador
         */
        int id;
        /**
         * Ram do computador
         */
        int ram = 0;
        /**
         * Armazenamento do computador
         */
        int armazenamento = 0;
        /**
         * Cpu do computador
         */
        double cpu = 0.0;
        /**
         * Arquitetura do computador
         */
        String arquitetura;
        /**
         * Criação de um array de strings para guardar as informções do computador
         */
        String[] informacoes;
        /**
         * Verificação da existência de gpu
         */
        String gpu = null;

        /**
         * Aumentar o ID
         */
        id = lastID++;
        /**
         * Atribuação de arquitetura ao computador
         */
        arquitetura = criaArquitetura();

        /**
         * Inicialização das variáveis consoante o tipo de computador
         */
        switch (tipoComputador) {
            case "Servidor": {
                ram = (int) Math.pow(2, random.nextInt(3) + 7);
                armazenamento = (int) Math.pow(2, random.nextInt(5) + 10);
                cpu = (double) Math.round(((random.nextDouble() + 3) * 10)) / 10;
                break;
            }

            case "Laptop": {
                ram = (int) Math.pow(2, random.nextInt(3) + 4);
                armazenamento = (int) Math.pow(2, random.nextInt(3) + 8);
                cpu = (double) Math.round(((random.nextDouble() + 2) * 10)) / 10;
                if (random.nextInt(2) == 1) {
                    gpu = "Sim";
                } else {
                    gpu = "Nao";
                }
                break;
            }

            case "Raspberry Pi": {
                ram = (int) Math.pow(2, random.nextInt(3) + 1);
                armazenamento = (int) Math.pow(2, random.nextInt(4) + 4);
                cpu = (double) Math.round(((random.nextDouble() + 1)* 10)) / 10;
                break;
            }
        }
        informacoes = new String[]{String.valueOf(id), String.valueOf(ram), String.valueOf(armazenamento), String.valueOf(cpu), arquitetura, gpu};
        return informacoes;
    }

    /**
     * Atribuição de forma aleatória de uma arquitetura para o computador
     * @return Arquitetura do computador
     */
    public String criaArquitetura() {
        String arquitetura;
        if (random.nextInt(2) == 1) {
            arquitetura = "x64";
        } else {
            arquitetura = "ARM";
        }
        return arquitetura;
    }

    /**
     * Método para imprimir os computadores existentes no array de computadores
     */
    public void imprimeComputadores() {
        System.out.println("Computadores: ");
        for(Computador computador: arrayComputadoresNCSLab){
            System.out.println(computador.toString());
        }
    }

    /**
     * Método para imprimir os computadores de arquitetura x64 existentes no array de computadores
     */
    public void imprimeComputadoresx64() {
        System.out.println("Computadores arquitetura x64: ");
        for(Computador computador: arrayComputadoresNCSLab){
            String arquiteturaComputadorTrabalhado = computador.getArquitetura();
            if(arquiteturaComputadorTrabalhado.equalsIgnoreCase("x64")){
                System.out.println(computador.toString());
            }
        }
    }

    /**
     * Método para imprimir o consumo de cada computador no array de computadores
     */
    public void imprimeConsumosComputadores() {
        System.out.println("Consumos dos computadores: ");
        for(Computador computador: arrayComputadoresNCSLab){
            System.out.print("ID -> " + computador.getId() + "; Tipo -> " + computador.getTipo() + "; CPU -> " + computador.getCPU() + "GHz; Consumo -> " + computador.getConsumo() + "W");
            System.out.println();
        }
    }

    /**
     * Getter para obter o array de computadores
     * @return Array de computadores
     */
    public ArrayList<Computador> getArrayComputadoresNCSLab() {
        return arrayComputadoresNCSLab;
    }

    /**
     * Setter para alterar o array de computadores
     * @param arrayComputadoresNCSLab Array de computadores
     */
    public void setArrayComputadoresNCSLab(ArrayList<Computador> arrayComputadoresNCSLab) {
        if(arrayComputadoresNCSLab != null) {
            this.arrayComputadoresNCSLab = arrayComputadoresNCSLab;
        }
    }
}