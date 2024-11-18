package Projeto;

public class TaxaAplicada {
    protected static int getTaxaAplicada(Cliente clienteRecebido, int[] arrayTaxas){
        int taxaAplicada = 0;
        String localizacaoCliente = clienteRecebido.getLocalizacao();
        switch (localizacaoCliente) {
            case "Continente":
                taxaAplicada = arrayTaxas[0];
                break;

            case "Madeira":
                taxaAplicada = arrayTaxas[1];
                break;

            case "Açores":
                taxaAplicada = arrayTaxas[2];
                break;

            default:
                break;
        }

        return taxaAplicada;
    }
}
