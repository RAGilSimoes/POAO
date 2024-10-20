package POAO.Ficha4.exercicio2;

/**
 * Classe para criar um angulo
 */

public class Angulo {
    /**
    * Grau do angulo
     */
    private double graus;
    /**
     * construtor da classe recebe um numero gerado pelo Math.random() como argumento
     * @param grausIntroduzido graus introduzidos na funcao
     */
    public Angulo(double grausIntroduzido) {
        this.graus = grausIntroduzido;
    }
    /**
    * metodo para escrever o valor do angulo
    * @return String com os graus do angulo
     */
    public String toString() {
        return ("angulo de " + graus + " graus");
    }
    /**
    * metodo para devolver os graus do angulo
    * @return graus do angulo
     */
    public double getGraus() {
        return graus;
    }
    /**
    * metodo para adicionar dois angulos
     * @param anguloIntroduzido graus introduzidos na funcao
    * @return novo angulo soma
     */
    public Angulo adicao(Angulo anguloIntroduzido) {
        return new Angulo(this.getGraus() + anguloIntroduzido.getGraus());
    }
    /**
     * metodo para subtrair dois angulos
     * @param anguloIntroduzido graus introduzidos na funcao
     * @return novo angulo subtracao
     */
    public Angulo subtracao(Angulo anguloIntroduzido) {
        return new Angulo(this.getGraus() - anguloIntroduzido.getGraus());
    }
    /**
     * calcula o correspondente em radianos
     * @return valor em radianos
     */
    public double toRadianos(){
        return (this.getGraus() * (Math.PI / 180));
    }
    /**
     * metodo para comparar dois angulos
     * @param anguloIntroduzido graus introduzidos na funcao
     * @return verdadeiro ou falso
     */
    public boolean equals(Angulo anguloIntroduzido) {
        return (this.getGraus() == anguloIntroduzido.getGraus());
    }
    /**
     * metodo para calcular o seno do angulo
     * @return seno do angulo
     */
    public double sin(){
        return (Math.sin(this.toRadianos()));
    }
    /**
     * metodo para calcular o cosseno do angulo
     * @return cosseno do angulo
     */
    public double cos(){
        return (Math.cos(this.toRadianos()));
    }
    /**
     * metodo para calcular a tangente do angulo
     * @return tangente do angulo
     */
    public double tg(){
        return (Math.tan(this.toRadianos()));
    }
}
