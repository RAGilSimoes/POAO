package POAO.Ficha4.exercicio2;

import java.util.ArrayList;

/**
 * @author Ricardo Simoes
 * @version 1.0
 */

/**
 * lorem ipsum
 */
public class Exercicio2 {
    /**
     * codigo principal
     * @param args argumentos principais
     */
    public static void main(String[] args) {
        //-------------------------------------------------------------
        //Angulo anguloIntroduzido = new Angulo(Math.random() * 360);
        //System.out.println(anguloIntroduzido.toString());
        //a e b
        //-------------------------------------------------------------

        //-------------------------------------------------------------
        //c até h
        //ArrayList<Angulo> arrayAngulos = new ArrayList<Angulo>(2);
        //arrayAngulos.add(0, new Angulo(Math.random() * 360));
        //arrayAngulos.add(1, new Angulo(Math.random() * 360));
        //System.out.printf("A soma entre o ângulo %.1f e o ângulo %.1f dá %.1f", arrayAngulos.get(0).getGraus(), arrayAngulos.get(1).getGraus(), arrayAngulos.get(0).adicao(arrayAngulos.get(1).getGraus()).getGraus());
        //System.out.println();
        //System.out.printf("A subtração entre o ângulo %.1f e o ângulo %.1f dá %.1f", arrayAngulos.get(0).getGraus(), arrayAngulos.get(1).getGraus(), arrayAngulos.get(0).subtracao(arrayAngulos.get(1).getGraus()).getGraus());
        //System.out.println();
        //System.out.printf("O ângulo %.1f em graus corresponde a %.1f radianos", arrayAngulos.get(0).getGraus(), arrayAngulos.get(0).radianos());
        //-------------------------------------------------------------

        //-------------------------------------------------------------
        /**
        * cria dois angulos
         */
        Angulo angulo1 = new Angulo(Math.random() * 360);
        Angulo angulo2 = new Angulo(Math.random() * 360);

        /**
        * apresenta o valor dos dois angulos
         */
        System.out.printf("Ângulo 1: %.1f", angulo1.getGraus());
        System.out.println();
        System.out.printf("Ângulo 2: %.1f", angulo2.getGraus());
        System.out.println();

        /**
        * compara o valor dos dois angulos e devolve o resultado
         */
        if(angulo1.equals(angulo2)){
            System.out.println("O ângulo 1 é igual ao ângulo 2");
        } else {
            System.out.println("O ângulo 1 não é igual ao ângulo 2");
        }

        /**
        * cria um novo angulo a partir da soma dos dois anteriores e apresenta o resultado
         */
        Angulo anguloSoma1com2 = angulo1.adicao(angulo2);
        System.out.printf("A soma entre o ângulo %.2f e o ângulo %.2f é %.2f", angulo1.getGraus(), angulo2.getGraus(), anguloSoma1com2.getGraus());

        /**
        * calcula o seno do angulo resultante da soma
         */
        System.out.println();
        System.out.printf("O seno do ângulo %.2f é %.2f", anguloSoma1com2.getGraus(), anguloSoma1com2.sin());

        /**
         * calcula o cosseno do angulo resultante da soma
         */
        System.out.println();
        System.out.printf("O cosseno do ângulo %.2f é %.2f", anguloSoma1com2.getGraus(), anguloSoma1com2.cos());

        /**
         * calcula a tangente do angulo resultante da soma
         */
        System.out.println();
        System.out.printf("A tangente do ângulo %.2f é %.2f", anguloSoma1com2.getGraus(), anguloSoma1com2.tg());
        //l
        //-------------------------------------------------------------
    }
}

