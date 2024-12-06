import java.io.*;
import java.util.ArrayList;

/**
 * The type Trata informacoes ficheiros.
 */
public class TrataInformacoesFicheiros {
    /**
     * Verifica existencia ficheiro objeto boolean.
     *
     * @param nomeFicheiroObjetos the nome ficheiro objetos
     * @return the boolean
     */
//--------------------------------------------------------------------------------------------------
    protected boolean verificaExistenciaFicheiroObjeto(String nomeFicheiroObjetos) {
        File ficheiroObjetos = new File(nomeFicheiroObjetos);
        boolean existe = (ficheiroObjetos.exists() && ficheiroObjetos.isFile());

        return existe;
    }
    //--------------------------------------------------------------------------------------------------

    /**
     * Trata informacoes clientes.
     *
     * @param arrayClientes the array clientes
     * @param linha         the linha
     * @throws IOException the io exception
     */
//--------------------------------------------------------------------------------------------------
    protected void trataInformacoesClientes(ArrayList<Cliente> arrayClientes, String linha) throws IOException{
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        Cliente cliente = new Cliente(null, null, null);
        String[] informacoesLinha = linha.split("/");

        String nome = informacoesLinha[0];
        String NIF = informacoesLinha[1];
        String localizacao = informacoesLinha[2];

        boolean verificaNIF = cliente.verificaNif(NIF, arrayClientes);

        if(!verificaNIF) {
            for(Cliente clienteObtido: arrayClientes) {
                String nomeClienteObtido = cliente.getNome();
                String nifClienteObtido = cliente.getNif();
                String localizacaoClienteObtido = cliente.getLocalizacao();
                if(nomeClienteObtido.equalsIgnoreCase(nome) && nifClienteObtido.equalsIgnoreCase(NIF) && localizacaoClienteObtido.equalsIgnoreCase(localizacao)) {
                    System.out.println("\nCliente já se encontra no sistema");
                    cliente = clienteObtido;
                }
            }
            if(cliente.getNif().isEmpty()) {
                throw new IOException();
            }
        } else {
            boolean verificaNome = funcoesUteis.verificaNome(nome);
            if(verificaNome) {
                cliente.setNome(nome);
            } else {
                throw new IOException();
            }

            cliente.setNif(NIF);

            boolean verificaLocalizacao = cliente.escolherLocalizao(localizacao);
            if(verificaLocalizacao) {
                cliente.setLocalizacao(localizacao);
            } else {
                throw new IOException();
            }
            arrayClientes.add(cliente);
        }
    }

    /**
     * Trata informacoes produto boolean.
     *
     * @param linha         the linha
     * @param produto       the produto
     * @param arrayProdutos the array produtos
     * @return the boolean
     * @throws IOException the io exception
     */
    protected boolean trataInformacoesProduto(String[] linha, Produto produto, ArrayList<Produto> arrayProdutos) throws IOException{
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        String codigo = linha[0];
        String nome = linha[1];
        String descricao = linha[2];
        String quantidade = linha[3];
        String valorUnidade = linha[4];

        boolean existeProduto = false;

        boolean verificaCodigo = produto.verificaCodigo(linha[0], arrayProdutos);

        if(!verificaCodigo) {
            for(Produto produtoObtido: arrayProdutos) {
                String codigoProdutoObtido = produtoObtido.getCodigo();
                String nomeProdutoObtido = produtoObtido.getNome();
                String descricaoProdutoObtido = produtoObtido.getDescricao();
                String quantidadeProdutoObtido = produtoObtido.getQuantidade();
                String valorUnidadeProdutoObtido = produtoObtido.getValorUnidade();
                if(codigoProdutoObtido.equalsIgnoreCase(codigo) && nomeProdutoObtido.equalsIgnoreCase(nome) && descricaoProdutoObtido.equalsIgnoreCase(descricao) && quantidadeProdutoObtido.equalsIgnoreCase(quantidade) && valorUnidadeProdutoObtido.equalsIgnoreCase(valorUnidade)) {
                    System.out.println("\nProduto já se encontra no sistema");
                    existeProduto = true;
                }
            }
            if(!existeProduto) {
                throw new IOException();
            }
        } else {
            produto.setCodigo(codigo);

            boolean verificaNome = funcoesUteis.verificaNome(nome);
            if(verificaNome) {
                produto.setNome(nome);
            } else {
                throw new IOException();
            }

            if(descricao.isEmpty()) {
                produto.setDescricao("Este produto não tem descrição");
            } else {
                produto.setDescricao(descricao);
            }

            boolean verificaQuantidade = funcoesUteis.verificaInt(quantidade);
            if(verificaQuantidade) {
                produto.setQuantidade(quantidade);
            } else {
                throw new IOException();
            }

            boolean verificaValorUnidade = funcoesUteis.verificaDouble(valorUnidade);
            if(verificaValorUnidade) {
                produto.setValorUnidade(valorUnidade);
            } else {
                throw new IOException();
            }
        }
        return existeProduto;
    }

    /**
     * Trata informacoes produto alimentar boolean.
     *
     * @param linha            the linha
     * @param produtoAlimentar the produto alimentar
     * @param arrayProdutos    the array produtos
     * @param taxa             the taxa
     * @return the boolean
     * @throws IOException the io exception
     */
    protected boolean trataInformacoesProdutoAlimentar(String[] linha, ProdutoAlimentar produtoAlimentar, ArrayList<Produto> arrayProdutos, String taxa) throws IOException{
        boolean existeProduto = trataInformacoesProduto(linha, produtoAlimentar,arrayProdutos);
        if(!existeProduto) {
            if(linha[5].equalsIgnoreCase("Sim") || linha[5].equalsIgnoreCase("Nao")) {
                produtoAlimentar.setBiologico(linha[5]);
            } else {
                throw new IOException();
            }

            produtoAlimentar.setTipoTaxa(taxa);
        }
        return existeProduto;
    }

    /**
     * Trata informacoes produto farmacia boolean.
     *
     * @param linha           the linha
     * @param produtoFarmacia the produto farmacia
     * @param arrayProdutos   the array produtos
     * @return the boolean
     * @throws IOException the io exception
     */
    protected boolean trataInformacoesProdutoFarmacia(String[] linha, ProdutoFarmacia produtoFarmacia,ArrayList<Produto> arrayProdutos) throws IOException{
        boolean existeProduto = trataInformacoesProduto(linha, produtoFarmacia,arrayProdutos);
        if(!existeProduto) {
            if(linha[5].equalsIgnoreCase("Sim") || linha[5].equalsIgnoreCase("Nao")) {
                produtoFarmacia.setPrescricao(linha[5]);
            } else {
                throw new IOException();
            }
        }

        return existeProduto;
    }

    /**
     * Trata informacoes taxa reduzida.
     *
     * @param arrayProdutos the array produtos
     * @param linha         the linha
     * @param taxa          the taxa
     * @throws IOException the io exception
     */
    protected void trataInformacoesTaxaReduzida(ArrayList<Produto> arrayProdutos, String linha, String taxa) throws IOException{
        ProdutoAlimentarTaxaReduzida produtoAlimentarTaxaReduzida = new ProdutoAlimentarTaxaReduzida(null, null, null, null, null, null, null, 0, null);
        String[] informacoesLinha = linha.split("/");
        if(informacoesLinha.length != 7){
            throw new IOException();
        } else {
            boolean existeProduto = trataInformacoesProdutoAlimentar(informacoesLinha, produtoAlimentarTaxaReduzida, arrayProdutos, taxa);
            if(!existeProduto) {
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
                arrayProdutos.add(produtoAlimentarTaxaReduzida);
            }
        }
    }

    /**
     * Trata informacoes taxa intermedia.
     *
     * @param arrayProdutos the array produtos
     * @param linha         the linha
     * @param taxa          the taxa
     * @throws IOException the io exception
     */
    protected void trataInformacoesTaxaIntermedia(ArrayList<Produto> arrayProdutos, String linha, String taxa) throws IOException{
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        ProdutoAlimentarTaxaIntermedia produtoAlimentarTaxaIntermedia = new ProdutoAlimentarTaxaIntermedia(null, null, null, null, null, null, null, null);
        String[] informacoesLinha = linha.split("/");
        if(informacoesLinha.length != 7){
            throw new IOException();
        } else {
            boolean existeProduto = trataInformacoesProdutoAlimentar(informacoesLinha, produtoAlimentarTaxaIntermedia, arrayProdutos, taxa);
            if(!existeProduto) {
                String categoria = informacoesLinha[6];
                boolean verificaCategoria = funcoesUteis.verificaCategoria(categoria);
                if(verificaCategoria) {
                    produtoAlimentarTaxaIntermedia.setCategoria(categoria);
                } else {
                    throw new IOException();
                }
                arrayProdutos.add(produtoAlimentarTaxaIntermedia);
            }
        }
    }

    /**
     * Trata informacoes taxa normal.
     *
     * @param arrayProdutos the array produtos
     * @param linha         the linha
     * @param taxa          the taxa
     * @throws IOException the io exception
     */
    protected void trataInformacoesTaxaNormal(ArrayList<Produto> arrayProdutos, String linha, String taxa) throws IOException{
        ProdutoAlimentarTaxaNormal produtoAlimentarTaxaNormal = new ProdutoAlimentarTaxaNormal(null, null, null, null, null, null, null);
        String[] informacoesLinha = linha.split("/");
        if(informacoesLinha.length != 6){
            throw new IOException();
        } else {
            boolean existeProduto = trataInformacoesProdutoAlimentar(informacoesLinha, produtoAlimentarTaxaNormal, arrayProdutos, taxa);
            if(!existeProduto) {
                arrayProdutos.add(produtoAlimentarTaxaNormal);
            }
        }
    }

    /**
     * Trata informacoes com prescricao.
     *
     * @param arrayProdutos the array produtos
     * @param linha         the linha
     * @throws IOException the io exception
     */
    protected void trataInformacoesComPrescricao(ArrayList<Produto> arrayProdutos, String linha) throws IOException{
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        ProdutoFarmaciaComPrescricao produtoFarmaciaComPrescricao = new ProdutoFarmaciaComPrescricao(null, null, null, null, null, null, null);
        String[] informacoesLinha = linha.split("/");
        if(informacoesLinha.length != 7){
            throw new IOException();
        } else {
            boolean existeProduto = trataInformacoesProdutoFarmacia(informacoesLinha, produtoFarmaciaComPrescricao, arrayProdutos);
            if(!existeProduto) {
                String medico = informacoesLinha[6];
                boolean verificaMedico = funcoesUteis.verificaNome(medico);
                if(verificaMedico) {
                    produtoFarmaciaComPrescricao.setMedico(medico);
                } else {
                    throw new IOException();
                }
                arrayProdutos.add(produtoFarmaciaComPrescricao);
            }
        }
    }

    /**
     * Trata informacoes sem prescricao.
     *
     * @param arrayProdutos the array produtos
     * @param linha         the linha
     * @throws IOException the io exception
     */
    protected void trataInformacoesSemPrescricao(ArrayList<Produto> arrayProdutos, String linha) throws IOException{
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        ProdutoFarmaciaSemPrescricao produtoFarmaciaSemPrescricao = new ProdutoFarmaciaSemPrescricao(null, null, null, null, null, null, null);
        String[] informacoesLinha = linha.split("/");
        if(informacoesLinha.length != 7){
            throw new IOException();
        } else {
            boolean existeProduto = trataInformacoesProdutoFarmacia(informacoesLinha, produtoFarmaciaSemPrescricao, arrayProdutos);
            if(!existeProduto) {
                String categoria = informacoesLinha[6];
                boolean verificaCategoria = funcoesUteis.verificaCategoria(categoria);
                if(verificaCategoria) {
                    produtoFarmaciaSemPrescricao.setCategoria(categoria);
                } else {
                    throw new IOException();
                }
                arrayProdutos.add(produtoFarmaciaSemPrescricao);
            }
        }
    }

    /**
     * Trata informacoes faturas.
     *
     * @param arrayFaturas  the array faturas
     * @param arrayClientes the array clientes
     * @param arrayProdutos the array produtos
     * @param linha         the linha
     */
    protected void trataInformacoesFaturas(ArrayList<Fatura> arrayFaturas, ArrayList<Cliente> arrayClientes, ArrayList<Produto> arrayProdutos, String linha){
        Fatura fatura = new Fatura(null, null, null, null, 0, 0);
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        try {
            String[] informacoesLinha = linha.split(";");
            if(informacoesLinha.length != 4) {
                throw new IOException();
            } else {
                boolean existeFatura = false;
                String numeroFatura = informacoesLinha[0];
                String nifCliente = informacoesLinha[1];
                String stringData = informacoesLinha[2];
                String stringCodigosProdutos = informacoesLinha[3];

                Data dataFatura = new Data(0,0,0);
                ArrayList<Produto> arrayProdutosFatura = new ArrayList<Produto>();
                boolean verificaNumeroFatura = fatura.verificaNumeroFatura(numeroFatura, arrayFaturas);

                Cliente clienteFatura = verificaNIFFicheiros(nifCliente, arrayClientes);

                boolean verificaData = dataFatura.verificaData(stringData);
                if(verificaData) {
                    dataFatura = dataFatura.passaParaObjetoData(stringData);
                } else {
                    throw new IOException();
                }

                String[] codigosProdutos = stringCodigosProdutos.split("/");
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
                        } else {
                            throw new IOException();
                        }
                    }
                } else {
                    throw new IOException();
                }

                if(!verificaNumeroFatura) {
                    for(Fatura faturaObtida: arrayFaturas) {
                        String numeroFaturaObtida = faturaObtida.getnFatura();
                        Cliente clienteFaturaObtida = faturaObtida.getCliente();
                        Data dataFaturaObtida = faturaObtida.getDataFatura();
                        ArrayList<Produto> arrayProdutosFaturaObtida = faturaObtida.getListaProdutos();

                        if(numeroFaturaObtida.equalsIgnoreCase(numeroFatura) && clienteFaturaObtida.equals(clienteFatura) && dataFaturaObtida.equals(dataFatura) && arrayProdutosFaturaObtida.equals(arrayProdutosFatura)){
                            System.out.println("Fatura já se encontra no sistema");
                            existeFatura = true;
                        }
                    }
                    if(!existeFatura) {
                        throw new IOException();
                    }
                } else{
                    fatura.setnFatura(numeroFatura);
                    fatura.setCliente(clienteFatura);
                    fatura.setDataFatura(dataFatura);
                    fatura.setListaProdutos(arrayProdutosFatura);
                    double valorSemIVA = fatura.calcularValorTotalSemIVA(arrayProdutosFatura);
                    fatura.setValorTotalSemIVA(valorSemIVA);

                    double valorComIVA = fatura.calcularValorTotalComIVA(arrayProdutosFatura, clienteFatura);
                    fatura.setValorTotalComIVA(valorComIVA);

                    arrayFaturas.add(fatura);
                }
            }
        } catch (IOException exception) {
            System.out.println("Erro ao ler fatura");
        }
    }
    //--------------------------------------------------------------------------------------------------

    /**
     * Cria informacao cliente string.
     *
     * @param cliente the cliente
     * @return the string
     */
//--------------------------------------------------------------------------------------------------
    protected String criaInformacaoCliente(Cliente cliente){
        String nome = cliente.getNome();
        String NIF = cliente.getNif();
        String localizacao = cliente.getLocalizacao();
        String resultado = ("C#" + nome + "/" + NIF + "/" + localizacao);
        return resultado;
    }

    /**
     * Cria informacao produto string.
     *
     * @param produto the produto
     * @return the string
     */
    protected String criaInformacaoProduto(Produto produto){
        String codigo = produto.getCodigo();
        String nome = produto.getNome();
        String descricao = produto.getDescricao();
        String quantidade = produto.getQuantidade();
        String valorPorUnidade = produto.getValorUnidade();
        String resultado = (codigo + "/"  + nome + "/" + descricao + "/" + quantidade + "/" + valorPorUnidade);
        return resultado;
    }

    /**
     * Cria informacao produto alimentar string.
     *
     * @param produtoAlimentar the produto alimentar
     * @return the string
     */
    protected String criaInformacaoProdutoAlimentar(ProdutoAlimentar produtoAlimentar) {
        String informacaoProduto = criaInformacaoProduto(produtoAlimentar);
        String biologico = produtoAlimentar.getBiologico();
        String taxa = produtoAlimentar.getTipoTaxa();
        String resultado = (informacaoProduto + "/" + biologico);
        return resultado;
    }

    /**
     * Cria informacao produto alimentar taxa reduzida string.
     *
     * @param produtoAlimentarTaxaReduzida the produto alimentar taxa reduzida
     * @return the string
     */
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

    /**
     * Cria informacao produto alimentar taxa intermedia string.
     *
     * @param produtoAlimentarTaxaIntermedia the produto alimentar taxa intermedia
     * @return the string
     */
    protected String criaInformacaoProdutoAlimentarTaxaIntermedia(ProdutoAlimentarTaxaIntermedia produtoAlimentarTaxaIntermedia) {
        String informacaoProduto = criaInformacaoProdutoAlimentar(produtoAlimentarTaxaIntermedia);
        String categoria = produtoAlimentarTaxaIntermedia.getCategoria();
        String resultado = ("I#" + informacaoProduto + "/" + categoria);
        return resultado;
    }

    /**
     * Cria informacao produto alimentar taxa normal string.
     *
     * @param produtoAlimentarTaxaNormal the produto alimentar taxa normal
     * @return the string
     */
    protected String criaInformacaoProdutoAlimentarTaxaNormal(ProdutoAlimentarTaxaNormal produtoAlimentarTaxaNormal) {
        String informacaoProduto = criaInformacaoProdutoAlimentar(produtoAlimentarTaxaNormal);
        String resultado = ("N#" + informacaoProduto);
        return resultado;
    }

    /**
     * Cria informacao produto farmacia string.
     *
     * @param produtoFarmacia the produto farmacia
     * @return the string
     */
    protected String criaInformacaoProdutoFarmacia(ProdutoFarmacia produtoFarmacia) {
        String informacaoProduto = criaInformacaoProduto(produtoFarmacia);
        String prescricao = produtoFarmacia.getPrescricao();
        String resultado = (informacaoProduto + "/" + prescricao);
        return resultado;
    }

    /**
     * Cria informacao produto farmacia com prescricao string.
     *
     * @param produtoFarmaciaComPrescricao the produto farmacia com prescricao
     * @return the string
     */
    protected String criaInformacaoProdutoFarmaciaComPrescricao(ProdutoFarmaciaComPrescricao produtoFarmaciaComPrescricao) {
        String informacaoProduto = criaInformacaoProdutoFarmacia(produtoFarmaciaComPrescricao);
        String medico = produtoFarmaciaComPrescricao.getMedico();
        String resultado = ("P#" + informacaoProduto + "/" + medico);
        return resultado;
    }

    /**
     * Cria informacao produto farmacia sem prescricao string.
     *
     * @param produtoFarmaciaSemPrescricao the produto farmacia sem prescricao
     * @return the string
     */
    protected String criaInformacaoProdutoFarmaciaSemPrescricao(ProdutoFarmaciaSemPrescricao produtoFarmaciaSemPrescricao) {
        String informacaoProduto = criaInformacaoProdutoFarmacia(produtoFarmaciaSemPrescricao);
        String categoria = produtoFarmaciaSemPrescricao.getCategoria();
        String resultado = ("S#" + informacaoProduto + "/" + categoria);
        return resultado;
    }

    /**
     * Cria informacao fatura string.
     *
     * @param fatura the fatura
     * @return the string
     */
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

    /**
     * Verifica nif ficheiros cliente.
     *
     * @param nif           the nif
     * @param arrayClientes the array clientes
     * @return the cliente
     */
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

    /**
     * Le ficheiro texto.
     *
     * @param arrayClientes the array clientes
     * @param arrayFaturas  the array faturas
     * @param arrayProdutos the array produtos
     * @param nomeFicheiro  the nome ficheiro
     */
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
                            trataInformacoesClientes(arrayClientes, informacoesLinha[1]);
                            break;

                        case "N":
                            trataInformacoesTaxaNormal(arrayProdutos, informacoesLinha[1], "Normal");
                            break;

                        case "I":
                            trataInformacoesTaxaIntermedia(arrayProdutos, informacoesLinha[1], "Intermedia");
                            break;

                        case "R":
                            trataInformacoesTaxaReduzida(arrayProdutos, informacoesLinha[1], "Reduzida");
                            break;

                        case "P":
                            trataInformacoesComPrescricao(arrayProdutos, informacoesLinha[1]);
                            break;

                        case "S":
                            trataInformacoesSemPrescricao(arrayProdutos, informacoesLinha[1]);
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
    //--------------------------------------------------------------------------------------------------

    /**
     * Escreve para ficheiro objeto.
     *
     * @param nomeFicheiroObjetos the nome ficheiro objetos
     * @param arrayClientes       the array clientes
     * @param arrayProdutos       the array produtos
     * @param arrayFaturas        the array faturas
     */
    protected void escreveParaFicheiroObjeto(String nomeFicheiroObjetos, ArrayList<Cliente> arrayClientes, ArrayList<Produto> arrayProdutos, ArrayList<Fatura> arrayFaturas){
        File ficheiroObjetos = new File(nomeFicheiroObjetos);
        try {
            FileOutputStream fos = new FileOutputStream(ficheiroObjetos);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.reset();

            oos.writeObject(arrayClientes);
            oos.writeObject(arrayProdutos);
            oos.writeObject(arrayFaturas);

            oos.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Erro ao criar ficheiro objeto");
        } catch (IOException exception) {
            System.out.println("Erro ao escrever para o ficheiro objeto");
        }
    }

    /**
     * Le ficheiro objeto.
     *
     * @param nomeFicheiroObjetos the nome ficheiro objetos
     * @param arrayClientes       the array clientes
     * @param arrayProdutos       the array produtos
     * @param arrayFaturas        the array faturas
     */
    protected void leFicheiroObjeto(String nomeFicheiroObjetos, ArrayList<Cliente> arrayClientes, ArrayList<Produto> arrayProdutos, ArrayList<Fatura> arrayFaturas){
        File ficheiroObjetos = new File(nomeFicheiroObjetos);
        try {
            FileInputStream fis = new FileInputStream(ficheiroObjetos);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Object objeto = ois.readObject();
            ArrayList<Cliente> arrayClientesObtido = (ArrayList<Cliente>) objeto;
            for (Cliente clienteObtido: arrayClientesObtido) {
                arrayClientes.add(clienteObtido);
            }

            objeto = ois.readObject();
            ArrayList<Produto> arrayProdutosObtido = (ArrayList<Produto>) objeto;
            for(Produto produto: arrayProdutosObtido) {
                arrayProdutos.add(produto);
            }

            objeto = ois.readObject();
            ArrayList<Fatura> arrayFaturasObtido = (ArrayList<Fatura>) objeto;
            for(Fatura fatura: arrayFaturasObtido) {
                arrayFaturas.add(fatura);
            }

            ois.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Erro ao abrir ficheiro objeto");
        } catch (IOException exception) {
            System.out.println("Erro ao ler ficheiro objeto");
        } catch (ClassNotFoundException exception) {
            System.out.println("Erro ao converter objeto");
        }
    }
}
