package POAO.TP1;

public class Exercicio1 {
    public static void main(String[] args) {
        String[] especialidadesInseridas = {"Radiologia/2030/50", "Oftalmologia/2500/70", "Pediatria/2700/75"};
        String[] medicosInseridos = {"Vasco Santana/radiologia/15/10", "Laura Alves/oftalmologia/5/7", "António Silva/oftalmologia/12/5"};
        String[][] informacoesEspecialidades = new String[especialidadesInseridas.length][especialidadesInseridas[0].length()]; //matriz com informacoes
        String[][] informacoesMedicos = new String[medicosInseridos.length][medicosInseridos[0].length()]; //matriz com informacoes
        String[][] salariosMedicos = new String[informacoesMedicos.length][2];
        String[][] gastoEspecialidade = new String[informacoesEspecialidades.length][2];

        preencherInformacoesespecialidadesInseridas(especialidadesInseridas, informacoesEspecialidades);

        preencherInformacoesMedicos(medicosInseridos, informacoesMedicos);

        calcularSalariosMedicos(informacoesMedicos, informacoesEspecialidades, salariosMedicos);

        exibirSalariosMedicos(salariosMedicos);

        calcularGastosEspecialidades(informacoesEspecialidades, informacoesMedicos, salariosMedicos, gastoEspecialidade);

        exibirGastoEspecialidade(gastoEspecialidade);
    }

    //---------------------------------------------------------------------------------

    public static void preencherInformacoesespecialidadesInseridas(String[] especialidadesInseridas, String[][] informacoesespecialidadesInseridas) {
        for (int i = 0; i < especialidadesInseridas.length; i++){
            informacoesespecialidadesInseridas[i] = especialidadesInseridas[i].split("/");
            for (int l = 0; l < informacoesespecialidadesInseridas[i].length; l++){
                informacoesespecialidadesInseridas[i][l] = informacoesespecialidadesInseridas[i][l];
            }
        }
    }

    //---------------------------------------------------------------------------------

    public static void preencherInformacoesMedicos(String[] medicos, String[][] informacoesMedicos) {
        for (int i = 0; i < medicos.length; i++){
            informacoesMedicos[i] = medicos[i].split("/");
            for (int l = 0; l < informacoesMedicos[i].length; l++){
                informacoesMedicos[i][l] = informacoesMedicos[i][l];
            }
        }
    }

    //---------------------------------------------------------------------------------

    public static void calcularSalariosMedicos(String[][] informacoesMedicos, String[][] informacoesespecialidadesInseridas, String[][] salariosMedicos){
        String nome;
        String especialidade;

        for(int i = 0; i < informacoesMedicos.length; i++){
            nome = informacoesMedicos[i][0];
            especialidade = informacoesMedicos[i][1];
            int anosServico = Integer.parseInt(informacoesMedicos[i][2]);
            int anosServicoMultiplo5 = (anosServico / 5);
            int horasExtra = Integer.parseInt(informacoesMedicos[i][3]);
            double salario;


            for(int l = 0; l < informacoesespecialidadesInseridas.length; l++){
                if(especialidade.equalsIgnoreCase(informacoesespecialidadesInseridas[l][0])){
                    double salarioBase = Integer.parseInt(informacoesespecialidadesInseridas[l][1]);
                    int valorHoraExtra = Integer.parseInt(informacoesespecialidadesInseridas[l][2]);

                    for(int m = 0; m < anosServicoMultiplo5; m++){
                        salarioBase += (salarioBase * 0.04);
                    }

                    salario = (double) (salarioBase + (valorHoraExtra * horasExtra));
                    salario = Math.round(salario * 10.0) / 10.0;

                    salariosMedicos[i][0] = nome;
                    salariosMedicos[i][1] = String.valueOf(salario);
                }
            }

        }
    }

    //---------------------------------------------------------------------------------

    public static void calcularGastosEspecialidades(String[][] informacoesespecialidadesInseridas, String[][] informacoesMedicos, String[][] salariosMedicos, String[][] valorEspecialidade){
        String especialidades;

        for(int i = 0; i < informacoesespecialidadesInseridas.length; i++){
            especialidades = informacoesespecialidadesInseridas[i][0];
            double valorGastoEspecialidade = 0;

            for(int l = 0; l < informacoesMedicos.length; l++){
                if(especialidades.equalsIgnoreCase(informacoesMedicos[l][1])){
                    valorGastoEspecialidade += Double.parseDouble(salariosMedicos[l][1]);
                }
            }
            valorEspecialidade[i][0] = especialidades;
            valorEspecialidade[i][1] = String.valueOf(valorGastoEspecialidade);
        }
    }

    //---------------------------------------------------------------------------------

    public static void exibirSalariosMedicos(String[][] salariosMedicos){
        for(int i = 0; i < salariosMedicos.length; i++){
            System.out.printf("%s: %s€", salariosMedicos[i][0], salariosMedicos[i][1]);
            System.out.println();
        }
        System.out.println();
    }

    //---------------------------------------------------------------------------------

    public static void exibirGastoEspecialidade(String[][] valorEspecialidade){
        for(int i = 0; i < valorEspecialidade.length; i++){
            if(Double.parseDouble(valorEspecialidade[i][1]) != 0.0) {
                System.out.printf("%s: %s€", valorEspecialidade[i][0], valorEspecialidade[i][1]);
                System.out.println();
            }
        }
    }
}