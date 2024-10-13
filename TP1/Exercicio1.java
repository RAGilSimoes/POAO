package TP1;

public class Exercicio1 {
    public static void main(String[] args) {
        String[] especialidadesInseridas = {"Radiologia/2030/50", "Oftalmologia/2500/70", "Pediatria/2700/75"};
        String[] medicosInseridos = {"Vasco Santana/radiologia/15/10", "Laura Alves/oftalmologia/5/7", "António Silva/oftalmologia/12/5"};

        String[][] informacoesEspecialidades = tratarInformacoesRecebidas(especialidadesInseridas); //matriz com informacoes das especialidades
        String[][] informacoesMedicos = tratarInformacoesRecebidas(medicosInseridos); //matriz com informacoes dos médicos

        String[][] salariosMedicos = calcularSalariosMedicos(informacoesMedicos, informacoesEspecialidades); //matriz com o salário dos médicos

        exibirSalariosMedicos(salariosMedicos);

        System.out.println();
        
        String[][] gastoEspecialidade = calcularGastosEspecialidades(informacoesEspecialidades, informacoesMedicos, salariosMedicos); //matriz com o custo de cada especialidade

        exibirGastoEspecialidade(gastoEspecialidade);
    }

    //---------------------------------------------------------------------------------

    public static String[][] tratarInformacoesRecebidas(String[] origem) {
        String[][] destino = new String[origem.length][]; //cria uma matriz com o nº de linhas equivalente à quantidade de índices do array passado como argumento
        for (int i = 0; i < origem.length; i++){
            destino[i] = origem[i].split("/"); //por cada linha da matriz, vai dividir a string no índice do array pelos "/" e meter cada parte da string na linha correspondente da matriz
        }
        return destino; //devolve a matriz que contém as informações tratadas
    }

    //---------------------------------------------------------------------------------

    public static String[][] calcularSalariosMedicos(String[][] informacoesMedicos, String[][] informacoesEspecialidades){
        String[][] destino = new String[informacoesMedicos.length][2]; //cria uma matriz com o nº de linhas equivalente à quantidade de índices do array passado como argumento

        for(int i = 0; i < informacoesMedicos.length; i++){
            String nome = informacoesMedicos[i][0]; //obtém o nome do médico;
            String especialidade = informacoesMedicos[i][1]; //obtém a especialidade do médico
            int anosServico = Integer.parseInt(informacoesMedicos[i][2]); //obtém os anos de serviço do médico
            int horasExtra = Integer.parseInt(informacoesMedicos[i][3]); //obtém a quantidade de horas extras efetuadas pelo médico
            
            int anosServicoMultiplo5 = (anosServico / 5); //divide os anos de serviço por 5 para saber quantas vezes recebe os 4% do salário base
            double salario = 0.0;

            for(int l = 0; l < informacoesEspecialidades.length; l++){
                if(especialidade.equalsIgnoreCase(informacoesEspecialidades[l][0])){ //compara a especialidade do médico com a especialidade do índice l para verificar se correspondem
                    double salarioBase = Double.parseDouble(informacoesEspecialidades[l][1]); //obtém o salário base da especialidade
                    double valorHoraExtra = Double.parseDouble(informacoesEspecialidades[l][2]); //obtém o custo de cada hora extra

                    for(int m = 0; m < anosServicoMultiplo5; m++){
                        salarioBase += (salarioBase * 0.04); //multiplica o salário base por 4% do mesmo por cada conjunto de 5 anos de serviço do médico
                    }

                    salario = (salarioBase + (valorHoraExtra * horasExtra)); //adiciona ao salário o salário base final mais o total recebido pelas horas feitas realizadas
                    salario = Math.round(salario * 10.0) / 10.0; //arredonda para 1 casa decimal
                    break;
                }
            }
            destino[i][0] = nome; //passa o nome do médico para a primeira coluna da respetiva linha
            destino[i][1] = String.valueOf(salario); //passa o salário do médico para a segunda coluna da respetiva linha
        }
        return destino;
    }

    //---------------------------------------------------------------------------------

    public static String[][] calcularGastosEspecialidades(String[][] informacoesespecialidadesInseridas, String[][] informacoesMedicos, String[][] salariosMedicos){
        String[][] destino = new String[informacoesespecialidadesInseridas.length][2]; //cria uma matriz com o nº de linhas equivalente à quantidade de índices do array passado como argumento

        for(int i = 0; i < informacoesespecialidadesInseridas.length; i++){
            String especialidade = informacoesespecialidadesInseridas[i][0]; //obtém a especialidade
            double valorGastoEspecialidade = 0.0;

            for(int l = 0; l < informacoesMedicos.length; l++){
                if(especialidade.equalsIgnoreCase(informacoesMedicos[l][1])){ //compara a especialidade com a especialidade do do médico do índice l para ver se correspondem
                    valorGastoEspecialidade += Double.parseDouble(salariosMedicos[l][1]); //adiciona ao valor gasto na especialidade o salario do medico dessa especialidade
                    break;
                }
            }
            destino[i][0] = especialidade; //passa a especialidade para a primeira coluna da respetiva linha
            destino[i][1] = String.valueOf(valorGastoEspecialidade); //passa o valor gasto da especialidade para a segunda coluna da respetiva linha
        }
        return destino;
    }

    //---------------------------------------------------------------------------------

    public static void exibirSalariosMedicos(String[][] salariosMedicos){
        for(int i = 0; i < salariosMedicos.length; i++){
            System.out.printf("%s: %s€", salariosMedicos[i][0], salariosMedicos[i][1]); //percorre a matriz dos salários dos médicos e apresenta as informações
            System.out.println();
        }
    }

    //---------------------------------------------------------------------------------

    public static void exibirGastoEspecialidade(String[][] valorEspecialidade){
        for(int i = 0; i < valorEspecialidade.length; i++){
            if(Double.parseDouble(valorEspecialidade[i][1]) != 0.0) {
                System.out.printf("%s: %s€", valorEspecialidade[i][0], valorEspecialidade[i][1]); //percorre a matriz dos valores gastos em cada especialidade e apresenta as informações
                System.out.println();
            }
        }
    }
}