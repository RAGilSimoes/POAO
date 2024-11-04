package TP2;

/**
 * @author Guilherme Carvalho e Ricardo Simões
 * @version 1.0
 */


/**
 * Classe para correr o programa
 */
public class Exercicio2 {
    /**
     * Método principal do programa
     * @param args Argumentos passados para a execução
     */
    public static void main(String[] args) {
        /**
         * Criação do objeto ncsLab do tipo NCSLAB
         */
        NCSLab ncsLab = new NCSLab();
        /**
         * Método para impressão de todos os computadores
         */
        ncsLab.imprimeComputadores();

        System.out.println();
        /**
         * Método para impressão de todos os computadores de arquitetura x64
         */
        ncsLab.imprimeComputadoresx64();

        System.out.println();
        /**
         * Método para impressão do consumo de cada computador
         */
        ncsLab.imprimeConsumosComputadores();
    }
}