import java.io.*;
import java.util.ArrayList;

public class TrataInformacoesFicheiros {
    final String nomeFicheiroObjetos = "projeto.obj";

    //--------------------------------------------------------------------------------------------------
    protected boolean verificaExistenciaFicheiroObjeto() {
        File ficheiroObjetos = new File(nomeFicheiroObjetos);
        boolean existe = (ficheiroObjetos.exists() && ficheiroObjetos.isFile());

        return existe;
    }
    //--------------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------------
    protected Cliente trataInformacoesClientes(ArrayList<Cliente> arrayClientes, String linha) throws IOException{
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
        return cliente;
    }

    protected Produto trataInformacoesProduto(String[] linha, Produto produto, ArrayList<Produto> arrayProdutos) throws IOException{
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        boolean verificaCodigo = produto.verificaCodigo(linha[0], arrayProdutos);
        if(verificaCodigo) {
            produto.setCodigo(linha[0]);
        } else {
            throw new IOException();
        }
        boolean verificaNome = funcoesUteis.verificaNome(linha[1]);
        if(verificaNome) {
            produto.setNome(linha[1]);
        } else {
            throw new IOException();
        }

        if(linha[2].isEmpty()) {
            produto.setDescricao("Este produto não tem descrição");
        } else {
            produto.setDescricao(linha[2]);
        }

        boolean verificaQuantidade = funcoesUteis.verificaInt(linha[3]);
        if(verificaQuantidade) {
            produto.setQuantidade(linha[3]);
        } else {
            throw new IOException();
        }

        boolean verificaValorUnidade = funcoesUteis.verificaDouble(linha[4]);
        if(verificaValorUnidade) {
            produto.setValorUnidade(linha[4]);
        } else {
            throw new IOException();
        }
        return produto;
    }

    protected ProdutoAlimentar trataInformacoesProdutoAlimentar(String[] linha, ProdutoAlimentar produtoAlimentar, ArrayList<Produto> arrayProdutos, String taxa) throws IOException{
        ProdutoAlimentar produto = (ProdutoAlimentar) trataInformacoesProduto(linha, produtoAlimentar,arrayProdutos);
        if(linha[5].equalsIgnoreCase("Sim") || linha[5].equalsIgnoreCase("Nao")) {
            produto.setBiologico(linha[5]);
        } else {
            throw new IOException();
        }

        produto.setTipoTaxa(taxa);

        return produto;
    }

    protected ProdutoFarmacia trataInformacoesProdutoFarmacia(String[] linha, ProdutoFarmacia produtoFarmacia,ArrayList<Produto> arrayProdutos) throws IOException{
        ProdutoFarmacia produto = (ProdutoFarmacia) trataInformacoesProduto(linha, produtoFarmacia,arrayProdutos);
        if(linha[5].equalsIgnoreCase("Sim") || linha[5].equalsIgnoreCase("Nao")) {
            produto.setPrescricao(linha[5]);
        } else {
            throw new IOException();
        }

        return produto;
    }

    protected ProdutoAlimentarTaxaReduzida trataInformacoesTaxaReduzida(ArrayList<Produto> arrayProdutos, String linha, String taxa) throws IOException{
        ProdutoAlimentarTaxaReduzida produtoAlimentarTaxaReduzida = new ProdutoAlimentarTaxaReduzida(null, null, null, null, null, null, null, 0, null);
        String[] informacoesLinha = linha.split("/");
        if(informacoesLinha.length != 7){
            throw new IOException();
        } else {
            produtoAlimentarTaxaReduzida = (ProdutoAlimentarTaxaReduzida) trataInformacoesProdutoAlimentar(informacoesLinha, produtoAlimentarTaxaReduzida, arrayProdutos, taxa);
            String[] certificacoesSeparadas = informacoesLinha[6].split(",");
            ArrayList<String> arrayCertificacoesInseridas = new ArrayList<String>();
            for(String certificacoes: certificacoesSeparadas) {
                arrayCertificacoesInseridas.add(certificacoes);
            }
            boolean verificaCertificacoes = produtoAlimentarTaxaReduzida.verificaCertificacoes(arrayCertificacoesInseridas);
            if(verificaCertificacoes) {
                produtoAlimentarTaxaReduzida.setCertificacoes(arrayCertificacoesInseridas);
            } else {
                throw new IOException();
            }
        }
        return produtoAlimentarTaxaReduzida;
    }

    protected ProdutoAlimentarTaxaIntermedia trataInformacoesTaxaIntermedia(ArrayList<Produto> arrayProdutos, String linha, String taxa) throws IOException{
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        ProdutoAlimentarTaxaIntermedia produtoAlimentarTaxaIntermedia = new ProdutoAlimentarTaxaIntermedia(null, null, null, null, null, null, null, null);
        String[] informacoesLinha = linha.split("/");
        if(informacoesLinha.length != 7){
            throw new IOException();
        } else {
            produtoAlimentarTaxaIntermedia = (ProdutoAlimentarTaxaIntermedia) trataInformacoesProdutoAlimentar(informacoesLinha, produtoAlimentarTaxaIntermedia, arrayProdutos, taxa);
            String categoria = informacoesLinha[6];
            boolean verificaCategoria = funcoesUteis.verificaCategoria(categoria);
            if(verificaCategoria) {
                produtoAlimentarTaxaIntermedia.setCategoria(categoria);
            } else {
                throw new IOException();
            }
        }
        return produtoAlimentarTaxaIntermedia;
    }

    protected ProdutoAlimentarTaxaNormal trataInformacoesTaxaNormal(ArrayList<Produto> arrayProdutos, String linha, String taxa) throws IOException{
        ProdutoAlimentarTaxaNormal produtoAlimentarTaxaNormal = new ProdutoAlimentarTaxaNormal(null, null, null, null, null, null, null);
        String[] informacoesLinha = linha.split("/");
        if(informacoesLinha.length != 6){
            throw new IOException();
        } else {
            produtoAlimentarTaxaNormal = (ProdutoAlimentarTaxaNormal) trataInformacoesProdutoAlimentar(informacoesLinha, produtoAlimentarTaxaNormal, arrayProdutos, taxa);
        }
        return produtoAlimentarTaxaNormal;
    }

    protected ProdutoFarmaciaComPrescricao trataInformacoesComPrescricao(ArrayList<Produto> arrayProdutos, String linha) throws IOException{
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        ProdutoFarmaciaComPrescricao produtoFarmaciaComPrescricao = new ProdutoFarmaciaComPrescricao(null, null, null, null, null, null, null);
        String[] informacoesLinha = linha.split("/");
        if(informacoesLinha.length != 7){
            throw new IOException();
        } else {
            produtoFarmaciaComPrescricao = (ProdutoFarmaciaComPrescricao) trataInformacoesProdutoFarmacia(informacoesLinha, produtoFarmaciaComPrescricao, arrayProdutos);
            String medico = informacoesLinha[6];
            boolean verificaMedico = funcoesUteis.verificaNome(medico);
            if(verificaMedico) {
                produtoFarmaciaComPrescricao.setMedico(medico);
            } else {
                throw new IOException();
            }
        }
        return produtoFarmaciaComPrescricao;
    }

    protected ProdutoFarmaciaSemPrescricao trataInformacoesSemPrescricao(ArrayList<Produto> arrayProdutos, String linha) throws IOException{
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        ProdutoFarmaciaSemPrescricao produtoFarmaciaSemPrescricao = new ProdutoFarmaciaSemPrescricao(null, null, null, null, null, null, null);
        String[] informacoesLinha = linha.split("/");
        if(informacoesLinha.length != 7){
            throw new IOException();
        } else {
            produtoFarmaciaSemPrescricao = (ProdutoFarmaciaSemPrescricao) trataInformacoesProdutoFarmacia(informacoesLinha, produtoFarmaciaSemPrescricao, arrayProdutos);
            String categoria = informacoesLinha[6];
            boolean verificaCategoria = funcoesUteis.verificaCategoria(categoria);
            if(verificaCategoria) {
                produtoFarmaciaSemPrescricao.setCategoria(categoria);
            } else {
                throw new IOException();
            }
        }
        return produtoFarmaciaSemPrescricao;
    }

    protected Fatura trataInformacoesFaturas(ArrayList<Fatura> arrayFaturas, ArrayList<Cliente> arrayClientes, ArrayList<Produto> arrayProdutos, String linha){
        Fatura fatura = new Fatura(null, null, null, null, 0, 0);
        try {
            String[] informacoesLinha = linha.split(";");
            if(informacoesLinha.length != 4) {
                throw new IOException();
            } else {
                FuncoesUteis funcoesUteis = new FuncoesUteis();
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
            }
        } catch (IOException exception) {
            System.out.println("Erro ao ler fatura");
        }
        return fatura;
    }
    //--------------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------------
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
        String resultado = (informacaoProduto + "/" + biologico);
        return resultado;
    }

    protected String criaInformacaoProdutoAlimentarTaxaReduzida(ProdutoAlimentarTaxaReduzida produtoAlimentarTaxaReduzida) {
        String informacaoProduto = criaInformacaoProdutoAlimentar(produtoAlimentarTaxaReduzida);
        ArrayList<String> certificacoes = produtoAlimentarTaxaReduzida.getCertificacoes();
        String stringCertificacoes = "";
        for(String string: certificacoes) {
            if (certificacoes.indexOf(string) == (certificacoes.size() - 1)) {
                stringCertificacoes += (string);
            } else {
                stringCertificacoes += (string + ",");
            }
        }
        String resultado = ("R#" + informacaoProduto + "/" + stringCertificacoes);
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
        String resultado = ("F#" + numeroFatura+ ";" + NIF + ";" + data.getDia() + "/" + data.getMes() + "/" + data.getAno() + ";" + codigosProdutos);
        return resultado;
    }
    //--------------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------------
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
    //--------------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------------
    protected void leFicheiroTexto(ArrayList<Cliente> arrayClientes, ArrayList<Fatura> arrayFaturas, ArrayList<Produto> arrayProdutos, String nomeFicheiro){
        File ficheiroTexto = new File(nomeFicheiro);
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
                            Cliente cliente = trataInformacoesClientes(arrayClientes, informacoesLinha[1]);
                            arrayClientes.add(cliente);
                            break;

                        case "N":
                            ProdutoAlimentarTaxaNormal produtoAlimentarTaxaNormal = trataInformacoesTaxaNormal(arrayProdutos, informacoesLinha[1], "Normal");
                            arrayProdutos.add(produtoAlimentarTaxaNormal);
                            break;

                        case "I":
                            ProdutoAlimentarTaxaIntermedia produtoAlimentarTaxaIntermedia = trataInformacoesTaxaIntermedia(arrayProdutos, informacoesLinha[1], "Intermedia");
                            arrayProdutos.add(produtoAlimentarTaxaIntermedia);
                            break;

                        case "R":
                            ProdutoAlimentarTaxaReduzida produtoAlimentarTaxaReduzida = trataInformacoesTaxaReduzida(arrayProdutos, informacoesLinha[1], "Reduzida");
                            arrayProdutos.add(produtoAlimentarTaxaReduzida);
                            break;

                        case "P":
                            ProdutoFarmaciaComPrescricao produtoFarmaciaComPrescricao = trataInformacoesComPrescricao(arrayProdutos, informacoesLinha[1]);
                            arrayProdutos.add(produtoFarmaciaComPrescricao);
                            break;

                        case "S":
                            ProdutoFarmaciaSemPrescricao produtoFarmaciaSemPrescricao = trataInformacoesSemPrescricao(arrayProdutos, informacoesLinha[1]);
                            arrayProdutos.add(produtoFarmaciaSemPrescricao);
                            break;

                        case "F":
                            Fatura fatura = trataInformacoesFaturas(arrayFaturas, arrayClientes, arrayProdutos, informacoesLinha[1]);
                            arrayFaturas.add(fatura);
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
    //--------------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------------
    protected void escreveFicheiroTextoInformacoes(ArrayList<Cliente> arrayClientes, ArrayList<Fatura> arrayFaturas, ArrayList<Produto> arrayProdutos, String nomeFicheiro) {
        File ficheiroTexto = new File(nomeFicheiro);
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
    //--------------------------------------------------------------------------------------------------

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

    protected void leFicheiroObjeto(String nomeFicheiroObjetos){
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
