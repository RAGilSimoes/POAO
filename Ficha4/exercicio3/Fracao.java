package POAO.Ficha4.exercicio3;

public class Fracao {
    private int numerador;
    private int denominador;

    public Fracao(String fracao) {
        this.numerador = Integer.parseInt(fracao.split("/")[0]);
        this.denominador = Integer.parseInt(fracao.split("/")[1]);
    }

    public int getNumerador() {
        return this.numerador;
    }

    public int getDenominador() {
        return this.denominador;
    }

    public void setNumerador(int numerador) {
        this.numerador = numerador;
    }

    public void setDenominador (int denominador) {
        this.denominador = denominador;
    }

    public void soma(Fracao fracaoAdicionar) {
        int denominador1 = this.getDenominador();
        int denominador2 = fracaoAdicionar.getDenominador();
        if(denominador1 != denominador2) {
            this.setNumerador(this.getNumerador() * denominador2);
            this.setDenominador(this.getDenominador() * denominador2);
            fracaoAdicionar.setNumerador(fracaoAdicionar.getNumerador() * denominador1);
            fracaoAdicionar.setDenominador(fracaoAdicionar.getDenominador() * denominador1);

            System.out.printf("Soma é %d/%d ", this.getNumerador() + fracaoAdicionar.getNumerador(), this.getDenominador());
            System.out.println();
        } else {
            System.out.printf("Soma é %d/%d ", this.getNumerador() + fracaoAdicionar.getNumerador(), this.getDenominador());
            System.out.println();
        }
    }

    public void subtracao(Fracao fracaoSubtrair) {
        int denominador1 = this.getDenominador();
        int denominador2 = fracaoSubtrair.getDenominador();
        if(denominador1 != denominador2) {
            this.setNumerador(this.getNumerador() * denominador2);
            this.setDenominador(this.getDenominador() * denominador2);
            fracaoSubtrair.setNumerador(fracaoSubtrair.getNumerador() * denominador1);
            fracaoSubtrair.setDenominador(fracaoSubtrair.getDenominador() * denominador1);

            System.out.printf("Subtracao é %d/%d ", this.getNumerador() - fracaoSubtrair.getNumerador(), this.getDenominador());
            System.out.println();
        } else {
            System.out.printf("Subtracao é %d/%d ", this.getNumerador() - fracaoSubtrair.getNumerador(), this.getDenominador());
            System.out.println();
        }
    }
}
