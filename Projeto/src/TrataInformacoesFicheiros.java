import java.io.*;
import java.util.ArrayList;

public class TrataInformacoesFicheiros {
    final String nomeFicheiroObjetos = "projeto.obj";
    final String nomeFicheiroTextoInformacoes = "informacoes.txt";

    protected boolean verificaExistenciaFicheiroObjeto() {
        File ficheiroObjetos = new File(nomeFicheiroObjetos);
        boolean existe = (ficheiroObjetos.exists() && ficheiroObjetos.isFile());

        return existe;
    }

    protected void trataInformacoesClientes(ArrayList<Cliente> arrayClientes, String linha) throws IOException{
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        Cliente cliente = new Cliente(null, null, null);
        String[] informacoesLinha = linha.split("/");
        boolean verificaNome = funcoesUteis.verificaNome(informacoesLinha[0]);
        if(verificaNome) {
            cliente.setNome(informacoesLinha[0]);
        } else {
            throw new IOException();
        }
        boolean verificaNIF = cliente.verificaNif(informacoesLinha[1], arrayClientes);
        if(verificaNIF) {
            cliente.setNif(informacoesLinha[1]);
        } else {
            throw new IOException();
        }
        boolean verificaLocalizacao = cliente.escolherLocalizao(informacoesLinha[2]);
        if(verificaLocalizacao) {
            cliente.setLocalizacao(informacoesLinha[2]);
        } else {
            throw new IOException();
        }
        arrayClientes.add(cliente);
    }

    protected void trataInformacoesTaxaReduzida(ArrayList<Cliente> arrayClientes, String linha) throws IOException{
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        Cliente cliente = new Cliente(null, null, null);
        String[] informacoesLinha = linha.split("/");
        boolean verificaNome = funcoesUteis.verificaNome(informacoesLinha[0]);
        if(verificaNome) {
            cliente.setNome(informacoesLinha[0]);
        } else {
            throw new IOException();
        }
        boolean verificaNIF = cliente.verificaNif(informacoesLinha[1], arrayClientes);
        if(verificaNIF) {
            cliente.setNif(informacoesLinha[1]);
        } else {
            throw new IOException();
        }
        boolean verificaLocalizacao = cliente.escolherLocalizao(informacoesLinha[2]);
        if(verificaLocalizacao) {
            cliente.setLocalizacao(informacoesLinha[2]);
        } else {
            throw new IOException();
        }
        arrayClientes.add(cliente);
    }

    protected String criaInformacaoCliente(Cliente cliente){
        String nome = cliente.getNome();
        String NIF = cliente.getNif();
        String localizacao = cliente.getLocalizacao();
        String resultado = ("C#" + nome + "/" + NIF + "/" + localizacao);
        return resultado;
    }

    protected String criaInformacaoProduto(Produto produto){
        String codigo = produto.getCodigo();
        String nome = produto.getNome();
        String descricao = produto.getDescricao();
        String quantidade = produto.getQuantidade();
        String valorPorUnidade = produto.getValorUnidade();
        String resultado = (codigo + "/"  + nome + "/" + descricao + "/" + quantidade + "/" + valorPorUnidade);
        return resultado;
    }

    protected String criaInformacaoProdutoAlimentar(ProdutoAlimentar produtoAlimentar) {
        String informacaoProduto = criaInformacaoProduto(produtoAlimentar);
        String biologico = produtoAlimentar.getBiologico();
        String taxa = produtoAlimentar.getTipoTaxa();
        String resultado = (informacaoProduto + "/" + biologico + "/" + taxa);
        return resultado;
    }

    protected String criaInformacaoProdutoAlimentarTaxaReduzida(ProdutoAlimentarTaxaReduzida produtoAlimentarTaxaReduzida) {
        String informacaoProduto = criaInformacaoProdutoAlimentar(produtoAlimentarTaxaReduzida);
        ArrayList<String> certificacoes = produtoAlimentarTaxaReduzida.getCertificacoes();
        String resultado = ("R#" + informacaoProduto + "/" + certificacoes);
        return resultado;
    }

    protected String criaInformacaoProdutoAlimentarTaxaIntermedia(ProdutoAlimentarTaxaIntermedia produtoAlimentarTaxaIntermedia) {
        String informacaoProduto = criaInformacaoProdutoAlimentar(produtoAlimentarTaxaIntermedia);
        String categoria = produtoAlimentarTaxaIntermedia.getCategoria();
        String resultado = ("I#" + informacaoProduto + "/" + categoria);
        return resultado;
    }

    protected String criaInformacaoProdutoAlimentarTaxaNormal(ProdutoAlimentarTaxaNormal produtoAlimentarTaxaNormal) {
        String informacaoProduto = criaInformacaoProdutoAlimentar(produtoAlimentarTaxaNormal);
        String resultado = ("N#" + informacaoProduto);
        return resultado;
    }

    protected String criaInformacaoProdutoFarmacia(ProdutoFarmacia produtoFarmacia) {
        String informacaoProduto = criaInformacaoProduto(produtoFarmacia);
        String prescricao = produtoFarmacia.getPrescricao();
        String resultado = (informacaoProduto + "/" + prescricao);
        return resultado;
    }

    protected String criaInformacaoProdutoFarmaciaComPrescricao(ProdutoFarmaciaComPrescricao produtoFarmaciaComPrescricao) {
        String informacaoProduto = criaInformacaoProdutoFarmacia(produtoFarmaciaComPrescricao);
        String medico = produtoFarmaciaComPrescricao.getMedico();
        String resultado = ("P#" + informacaoProduto + "/" + medico);
        return resultado;
    }

    protected String criaInformacaoProdutoFarmaciaSemPrescricao(ProdutoFarmaciaSemPrescricao produtoFarmaciaSemPrescricao) {
        String informacaoProduto = criaInformacaoProdutoFarmacia(produtoFarmaciaSemPrescricao);
        String categoria = produtoFarmaciaSemPrescricao.getCategoria();
        String resultado = ("S#" + informacaoProduto + "/" + categoria);
        return resultado;
    }

    protected Cliente verificaNIFFicheiros(String nif, ArrayList<Cliente> arrayClientes){
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        Cliente clienteFinal = new Cliente(null, null, null);
        if (nif.length() != 9 || funcoesUteis.verificaCaracteres(nif,'0', '9')){
            System.out.println("\nO nif introduzido não é válido");
        } else {
            for (Cliente cliente: arrayClientes){
                String nifExistente = cliente.getNif();
                if (nifExistente.equals(nif)){
                    clienteFinal = cliente;
                    break;
                }
            }
        }
        return clienteFinal;
    }

    protected void trataInformacoesFaturas(ArrayList<Fatura> arrayFaturas, ArrayList<Cliente> arrayClientes, ArrayList<Produto> arrayProdutos, String linha){
        try {
            FuncoesUteis funcoesUteis = new FuncoesUteis();
            String[] informacoesLinha = linha.split(";");
            Fatura fatura = new Fatura(null, null, null, null, 0, 0);
            boolean verificaNumeroFatura = fatura.verificaNumeroFatura(informacoesLinha[0], arrayFaturas);
            if(verificaNumeroFatura) {
                fatura.setnFatura(informacoesLinha[0]);
            } else {
                throw new IOException();
            }
            Cliente clienteFatura = verificaNIFFicheiros(informacoesLinha[1], arrayClientes);
            if(clienteFatura.getNome() != null) {
                fatura.setCliente(clienteFatura);
            } else {
                throw new IOException();
            }

            Data data = new Data(0,0,0);
            boolean verificaData = data.verificaData(informacoesLinha[2]);
            if(verificaData) {
                data = data.passaParaObjetoData(informacoesLinha[2]);
                fatura.setDataFatura(data);
            } else {
                throw new IOException();
            }

            String stringCodigosProdutos = informacoesLinha[3];
            String[] codigosProdutos = stringCodigosProdutos.split("/");
            ArrayList<Produto> arrayProdutosFatura = new ArrayList<Produto>();
            if(codigosProdutos.length != 0) {
                for(String codigoProduto: codigosProdutos) {
                    boolean verificaInteiro = funcoesUteis.verificaInt(codigoProduto);
                    if(verificaInteiro) {
                        for(Produto produto: arrayProdutos) {
                            String codigoProdutoAtual = produto.getCodigo();
                            if(codigoProdutoAtual.equalsIgnoreCase(codigoProduto)) {
                                arrayProdutosFatura.add(produto);
                            }
                        }
                        fatura.setListaProdutos(arrayProdutosFatura);
                    } else {
                        throw new IOException();
                    }
                }
            } else {
                throw new IOException();
            }

            double valorSemIVA = fatura.calcularValorTotalSemIVA(arrayProdutosFatura);
            fatura.setValorTotalSemIVA(valorSemIVA);

            double valorComIVA = fatura.calcularValorTotalComIVA(arrayProdutosFatura, clienteFatura);
            fatura.setValorTotalComIVA(valorComIVA);

            arrayFaturas.add(fatura);
        } catch (IOException exception) {
            System.out.println("Erro ao ler fatura");
        }
    }


    protected String criaInformacaoFatura(Fatura fatura){
        String numeroFatura = fatura.getnFatura();
        String NIF = fatura.getCliente().getNif();
        Data data = fatura.getDataFatura();
        ArrayList<Produto> arrayProdutos = fatura.getListaProdutos();
        String codigosProdutos = "";
        for(Produto produto: arrayProdutos) {
            if(arrayProdutos.indexOf(produto) == (arrayProdutos.size() - 1)){
                codigosProdutos += (produto.getCodigo());
            } else {
                codigosProdutos += (produto.getCodigo() + "/");
            }
        }
        String valorSemIVA = String.valueOf(fatura.getValorTotalSemIVA());
        String valorComIVA = String.valueOf(fatura.getValorTotalComIVA());
        String resultado = ("F#" + numeroFatura+ ";" + NIF + ";" + data.getDia() + "/" + data.getMes() + "/" + data.getAno() + ";" + codigosProdutos + ";" + valorSemIVA + ";" + valorComIVA);
        return resultado;
    }

    protected void leFicheiroTexto(ArrayList<Cliente> arrayClientes, ArrayList<Fatura> arrayFaturas, ArrayList<Produto> arrayProdutos){
        File ficheiroTexto = new File(nomeFicheiroTextoInformacoes);
        if(ficheiroTexto.exists() && ficheiroTexto.isFile()){
            try {
                FileReader fr = new FileReader(ficheiroTexto);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while((line = br.readLine()) != null){
                    String[] informacoesLinha = line.split("#");
                    String caracterControlo = informacoesLinha[0];
                    switch (caracterControlo) {
                        case "C":
                            trataInformacoesClientes(arrayClientes, informacoesLinha[1]);
                            break;

                        case "N":
                            System.out.println("Normal");
                            break;

                        case "I":
                            System.out.println("Intermedia");
                            break;

                        case "R":
                            System.out.println("Reduzida");
                            break;

                        case "P":
                            System.out.println("Prescrição");
                            break;

                        case "S":
                            System.out.println("Sem Prescricao");
                            break;

                        case "F":
                            trataInformacoesFaturas(arrayFaturas, arrayClientes, arrayProdutos, informacoesLinha[1]);
                            break;

                        default:
                            System.out.println("Tipo de objeto não existe");
                            break;
                    }
                }
                br.close();
            } catch (FileNotFoundException exception) {
                System.out.println("Erro ao abrir ficheiro de informações");
            } catch (IOException exception) {
                System.out.println("Erro ao ler ficheiro de informações");
            }
        } else {
            System.out.println("Ficheiro de informações não existe");
        }
    }

    protected void escreveFicheiroTextoInformacoes(ArrayList<Cliente> arrayClientes, ArrayList<Fatura> arrayFaturas, ArrayList<Produto> arrayProdutos) {
        File ficheiroTexto = new File(nomeFicheiroTextoInformacoes);
        try {
            FileWriter fileWriter = new FileWriter(ficheiroTexto);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Cliente cliente: arrayClientes) {
                String stringCliente = criaInformacaoCliente(cliente);
                bufferedWriter.write(stringCliente);
                bufferedWriter.newLine();
            }

            for(Produto produto: arrayProdutos) {
                String tipoProduto = produto.getTipo();
                switch (tipoProduto) {
                    case "Produto Alimentar Taxa Reduzida":
                        String stringTaxaReduzida = criaInformacaoProdutoAlimentarTaxaReduzida((ProdutoAlimentarTaxaReduzida) produto);
                        bufferedWriter.write(stringTaxaReduzida);
                        bufferedWriter.newLine();
                        break;

                    case "Produto Alimentar Taxa Intermedia":
                        String stringTaxaIntermedia = criaInformacaoProdutoAlimentarTaxaIntermedia((ProdutoAlimentarTaxaIntermedia) produto);
                        bufferedWriter.write(stringTaxaIntermedia);
                        bufferedWriter.newLine();
                        break;

                    case "Produto Alimentar Taxa Normal":
                        String stringTaxaNormal = criaInformacaoProdutoAlimentarTaxaNormal((ProdutoAlimentarTaxaNormal) produto);
                        bufferedWriter.write(stringTaxaNormal);
                        bufferedWriter.newLine();
                        break;

                    case "Produto Farmacia Com Prescricao":
                        String stringComPrescricao = criaInformacaoProdutoFarmaciaComPrescricao((ProdutoFarmaciaComPrescricao) produto);
                        bufferedWriter.write(stringComPrescricao);
                        bufferedWriter.newLine();
                        break;

                    case "Produto Farmacia Sem Prescricao":
                        String stringSemPrescricao = criaInformacaoProdutoFarmaciaSemPrescricao((ProdutoFarmaciaSemPrescricao) produto);
                        bufferedWriter.write(stringSemPrescricao);
                        bufferedWriter.newLine();
                        break;

                    default:
                        break;
                }
            }

            for(Fatura fatura: arrayFaturas) {
                String stringFatura = criaInformacaoFatura(fatura);
                if(arrayFaturas.indexOf(fatura) == (arrayFaturas.size() - 1)){
                    bufferedWriter.write(stringFatura);
                } else {
                    bufferedWriter.write(stringFatura);
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
        } catch (IOException exception) {
            System.out.println("Erro ao escrever no ficheiro texto");
        }
    }

    protected void escreveParaFicheiroObjeto(){
        File ficheiroObjetos = new File(nomeFicheiroObjetos);
        ProdutoAlimentarTaxaNormal produto = new ProdutoAlimentarTaxaNormal("1", "Banana", "Banana boa", "2", "0.1", "Nao", "Normal");
        try {
            FileOutputStream fos = new FileOutputStream(ficheiroObjetos);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(produto);
            oos.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Erro ao criar ficheiro");
        } catch (IOException exception) {
            System.out.println("Erro ao escrever para o ficheiro");
        }
    }

    protected void leFicheiroObjeto(){
        File ficheiroObjetos = new File(nomeFicheiroObjetos);
        try {
            FileInputStream fis = new FileInputStream(ficheiroObjetos);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ProdutoAlimentarTaxaNormal produto = (ProdutoAlimentarTaxaNormal) ois.readObject();
            System.out.println(produto);
            ois.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Erro ao abrir ficheiro");
        } catch (IOException exception) {
            System.out.println("Erro ao ler ficheiro");
        } catch (ClassNotFoundException exception) {
            System.out.println("Erro ao converter objeto");
        }
    }
}
