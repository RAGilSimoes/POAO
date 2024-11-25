import java.io.*;
import java.util.ArrayList;

public class TrataInformacoesFicheiros {
    final String nomeFicheiroObjetos = "projeto.obj";
    final String nomeFicheiroTextoFaturas = "faturas.txt";
    final String nomeFicheiroTextoClientes = "clientes.txt";
    final String nomeFicheiroTextoProdutos = "produtos.txt";
    final String diretorio = System.getProperty("user.dir");

    protected boolean verificaExistenciaFicheiroObjeto() {
        File ficheiroObjetos = new File(nomeFicheiroObjetos);
        boolean existe = (ficheiroObjetos.exists() && ficheiroObjetos.isFile());

        return existe;
    }

    protected void trataInformacoesClientes(ArrayList<Cliente> arrayClientes) {
        File ficheiroClientes = new File(nomeFicheiroTextoClientes);
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        if(ficheiroClientes.exists() && ficheiroClientes.isFile()) {
            try {
                FileReader fileReader = new FileReader(ficheiroClientes);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String linha;
                while ((linha = bufferedReader.readLine()) != null) {
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
            } catch (FileNotFoundException exception) {
                System.out.println("Erro ao abrir ficheiro");
            } catch (IOException exception) {
                System.out.println("Erro ao ler");
            }
        }
    }

    protected void escreveFicheiroTextoClientes(ArrayList<Cliente> arrayClientes){
        File ficheiroClientes = new File(nomeFicheiroTextoClientes);
        try {
            FileWriter fileWriter = new FileWriter(ficheiroClientes);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Cliente cliente: arrayClientes) {
                String nome = cliente.getNome();
                String NIF = cliente.getNif();
                String localizacao = cliente.getLocalizacao();
                bufferedWriter.write(nome + "/" + NIF + "/" + localizacao);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException exception) {
            System.out.println("Erro ao escrever");
        }
    }

    protected void trataInformacoesFaturas(ArrayList<Fatura> arrayFaturas, ArrayList<Cliente> arrayClientes, ArrayList<Produto> arrayProdutos) {
        File ficheiroFaturas = new File(nomeFicheiroTextoFaturas);
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        if(ficheiroFaturas.exists() && ficheiroFaturas.isFile()) {
            try {
                FileReader fileReader = new FileReader(ficheiroFaturas);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String linha;
                while ((linha = bufferedReader.readLine()) != null) {
                    String[] informacoesLinha = linha.split(";");
                    switch (informacoesLinha[0]) {
                        case "F":
                            Fatura fatura = new Fatura(null, null, null, null, 0, 0);
                            Cliente cliente = new Cliente(null, null, null);
                            boolean verificaNumeroFatura = fatura.verificaNumeroFatura(informacoesLinha[1], arrayFaturas);
                            if(verificaNumeroFatura) {
                                fatura.setnFatura(informacoesLinha[1]);
                            } else {
                                throw new IOException();
                            }
                            boolean verificaNIF = cliente.verificaNif(informacoesLinha[2], arrayClientes);
                            if(!verificaNIF) {
                                for(Cliente clienteProcurar : arrayClientes) {
                                    String nif = clienteProcurar.getNif();
                                    if(nif.equalsIgnoreCase(informacoesLinha[2])) {
                                        fatura.setCliente(clienteProcurar);
                                        break;
                                    }
                                }
                            } else {
                                throw new IOException();
                            }

                            Data data = new Data(0,0,0);
                            boolean verificaData = data.verificaData(informacoesLinha[3]);
                            if(verificaData) {
                                data = data.passaParaObjetoData(informacoesLinha[3]);
                                fatura.setDataFatura(data);
                            } else {
                                throw new IOException();
                            }

                            String stringCodigosProdutos = informacoesLinha[4];
                            String[] codigosProdutos = stringCodigosProdutos.split("/");
                            ArrayList<Produto> arrayProdutosFatura = new ArrayList<Produto>();
                            if(codigosProdutos.length != 0) {
                                for(String codigoProduto: codigosProdutos) {
                                    boolean verificaInteiro = funcoesUteis.verificaInt(codigoProduto);
                                    if(!verificaInteiro) {
                                        throw new IOException();
                                    } else {
                                        for(Produto produto: arrayProdutos) {
                                            String codigoProdutoAtual = produto.getCodigo();
                                            if(codigoProdutoAtual.equalsIgnoreCase(codigoProduto)) {
                                                arrayProdutosFatura.add(produto);
                                            }
                                        }
                                        fatura.setListaProdutos(arrayProdutosFatura);
                                    }
                                }
                            } else {
                                throw new IOException();
                            }

                            String valorSemIVAFicheiro = informacoesLinha[5];
                            boolean verificaValorSemIVA = funcoesUteis.verificaDouble(valorSemIVAFicheiro);
                            if(verificaValorSemIVA) {
                                fatura.setValorTotalSemIVA(Double.parseDouble(valorSemIVAFicheiro));
                            } else {
                                throw new IOException();
                            }

                            String valorComIVAFicheiro = informacoesLinha[6];
                            boolean verificaValorComIVA = funcoesUteis.verificaDouble(valorComIVAFicheiro);
                            if(verificaValorComIVA) {
                                fatura.setValorTotalComIVA(Double.parseDouble(valorComIVAFicheiro));
                            } else {
                                throw new IOException();
                            }

                            arrayFaturas.add(fatura);

                        default:
                            throw new IOException();
                    }
                }
            } catch (FileNotFoundException exception) {
                System.out.println("Erro ao abrir ficheiro");
            } catch (IOException exception) {
                System.out.println("Erro ao ler");
            }
        }
    }


    protected void escreveFicheiroTextoFaturas(ArrayList<Fatura> arrayFaturas){
        File ficheiroFaturas = new File(nomeFicheiroTextoFaturas);
        try {
            FileWriter fileWriter = new FileWriter(ficheiroFaturas);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Fatura fatura : arrayFaturas) {
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
                bufferedWriter.write(numeroFatura+ ";" + NIF + ";" + data.getDia() + "/" + data.getMes() + "/" + data.getAno() + ";" + codigosProdutos + ";" + valorSemIVA + ";" + valorComIVA);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException exception) {
            System.out.println("Erro ao escrever");
        }
    }

    private void trataInformacoesProdutoTaxaReduzida(ArrayList<Produto> arrayProdutos, String[] informacoesLinha, FuncoesUteis funcoesUteis) throws Exception{
        String codigoProduto = informacoesLinha[1];
        boolean verificaInteiro = funcoesUteis.verificaInt(codigoProduto);
        ProdutoAlimentarTaxaReduzida produtoAlimentarTaxaReduzida = new ProdutoAlimentarTaxaReduzida(null, null, null, null, null, null, null, 0, null);
        if(verificaInteiro) {
            boolean verificaCodigo = produtoAlimentarTaxaReduzida.verificaCodigo(codigoProduto, arrayProdutos);
            if(verificaCodigo) {
                produtoAlimentarTaxaReduzida.setCodigo(codigoProduto);
            } else {
                throw new Exception();
            }
        } else {
            throw new Exception();
        }

        String nomeProduto = informacoesLinha[2];
        boolean verificaNome = funcoesUteis.verificaNome(nomeProduto);
        if(verificaNome) {
            produtoAlimentarTaxaReduzida.setNome(nomeProduto);
        } else {
            throw new Exception();
        }

        String descricaoProduto = informacoesLinha[3];
        produtoAlimentarTaxaReduzida.setDescricao(descricaoProduto);

        String quantidadeProduto = informacoesLinha[4];
        boolean verificaQuantidade = funcoesUteis.verificaInt(quantidadeProduto);
        if (verificaQuantidade) {
            produtoAlimentarTaxaReduzida.setQuantidade(quantidadeProduto);
        } else {
            throw new Exception();
        }

        String valorSemIVA = informacoesLinha[5];
        boolean verificaValorSemIVA = funcoesUteis.verificaDouble(valorSemIVA);
        if (verificaValorSemIVA) {
            produtoAlimentarTaxaReduzida.setValorSemIVA(valorSemIVA);
        } else {
            throw new Exception();
        }

        String biologico = informacoesLinha[6];
        if (biologico.equalsIgnoreCase("Sim") || biologico.equalsIgnoreCase("Nao")) {
            produtoAlimentarTaxaReduzida.setBiologico(biologico);
        } else {
            throw new Exception();
        }

        //continuar e meter isto tudo comum em funcoes
    }

    protected void trataInformacoesProdutos(ArrayList<Produto> arrayProdutos) {
        File ficheiroProdutos = new File(nomeFicheiroTextoProdutos);
        FuncoesUteis funcoesUteis = new FuncoesUteis();
        if(ficheiroProdutos.exists() && ficheiroProdutos.isFile()) {
            try {
                FileReader fileReader = new FileReader(ficheiroProdutos);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String linha;
                while ((linha = bufferedReader.readLine()) != null) {
                    String[] informacoesLinha = linha.split("/");
                    String caracterControlo = informacoesLinha[0];
                    switch (caracterControlo) {
                        case "R":

                    }
                }
            } catch (FileNotFoundException exception) {
                System.out.println("Erro ao abrir ficheiro");
            } catch (IOException exception) {
                System.out.println("Erro ao ler");
            }
        }
    }


    protected void escreveFicheiroTexto(){
        File ficheiroObjetos = new File(nomeFicheiroTextoFaturas);
        try {
            FileWriter fw = new FileWriter(ficheiroObjetos);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Olá");
            bw.newLine();
            bw.write("Adeus");
            bw.close();
        } catch (IOException exception) {
            System.out.println("Erro ao escrever");
        }
    }

    protected void leFicheiroTexto(){
        File ficheiroObjetos = new File(nomeFicheiroTextoFaturas);
        if(ficheiroObjetos.exists() && ficheiroObjetos.isFile()){
            try {
                FileReader fr = new FileReader(ficheiroObjetos);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while((line = br.readLine()) != null){
                    String[] informacoesLinha = line.split(";");
                    String caracterControlo = informacoesLinha[0];
                    switch (caracterControlo) {
                        case "F":
                            System.out.println("Fatura");
                            break;

                        case "C":
                            System.out.println("Cliente");
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

                        default:
                            System.out.println("Tipo não existe");
                            break;
                    }
                }
                br.close();
            } catch (FileNotFoundException exception) {
                System.out.println("Erro ao abrir ficheiro");
            } catch (IOException exception) {
                System.out.println("Erro ao ler");
            }
        } else {
            System.out.println("Ficheiro não existe");
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
