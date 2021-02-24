import java.util.Scanner;

/**
 * Interface de texto com o usuário
 */
public class Principal {

    public static Impressora instanciarImpressora() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Qual o tipo de impressora? ");
        String tipo = sc.nextLine();

        if (tipo.equals("jato")) {
            return new ImpressoraJatoDeTinta("Epson", 2005);
        } else if (tipo.equals("laser")) {
            return new ImpressoraLaser("HP", 2000);
        } else {
            return new ImpressoraMatricial();
        }
    }

    public static void main(String[] args) {

//        Impressora impressora;
//        impressora = instanciarImpressora();
//        int ano = impressora.getAnoDeFabricacao();
//        impressora.imprimir("blah");

        Scanner sc = new Scanner(System.in);

        Transportadora transportadora = new Transportadora();
        Impressora impressora = new ImpressoraJatoDeTinta("Epson", 2018);


        Loja minhaLoja = new Loja(transportadora, impressora);
        
        Livro livro1 = new Livro(11111, "O Pequeno Principe", "Saint Exupery",
                                 CategoriaDeLivro.FICCAO, "Abril", 1920);
        minhaLoja.incluirItem(livro1);

        Usuario joao = new Usuario(123456, "Joao");

        int quantidadeDesejada = 20;
        int contTentativasDePagamento = 1;

        while (true) {
            try {
                minhaLoja.receberPedido(livro1, quantidadeDesejada, joao);

            } catch (ItemNaoExisteNoCatalogoException e) {
                System.out.println("Item inexistente");

            } catch (QuantidadeInconsistenteException e){ 
                System.out.println("Quantidade Inválida, peça pelo menos um item.");
            
            }catch (EstoqueInsuficienteException e) {
                int quantidadeEmEstoque = e.getQuantidadeEmEstoque();

                if (quantidadeEmEstoque == 0) {
                    System.out.println("Estoque esgotado!");
                }

                // tratamento possível: compra menos unidades
                int quantidadesFaltantes = quantidadeDesejada - quantidadeEmEstoque;
                System.out.println("Estoque insuficiente. Serão compradas apenas " +
                        quantidadeEmEstoque + " unidades. Ficarão faltando " +
                        quantidadesFaltantes +  " unidades.");

                quantidadeDesejada = quantidadeEmEstoque;

                // vai repetir a tentativa de compra naturalmente (próxima iteração do while)
                continue;


            } catch (EnderecoInvalidoException e) {

                System.out.println("Endereco Inválido. Digite o endereço de entrega por favor: ");
                String enderecoDigitado = sc.nextLine();
                joao.setEndereco(enderecoDigitado);

                // vai repetir a tentativa de compra naturalmente (próxima iteração do while)
                continue;


            } catch (ErroNoPagamentoException e) {

                System.out.println("Problema no pagamento");

                if (contTentativasDePagamento <= 3) {
                    System.out.println("Tentativa #" + (++contTentativasDePagamento) + " em curso...");
                    continue;

                } else {
                    System.out.println("Operação abortada.");
                    // break;
                }
            }

            break;  // se chegar até aqui, interrompe o while...
        }
    }

//        Scanner sc = new Scanner(System.in);
//
//        Loja loja = null;
//        Transportadora transportadora = new Transportadora();
//
//        while (loja == null) {
//            System.out.println("O que usar como serviço de impressão? " +
//                    "\n Impressora (J)ato de Tinta\n Impressora (M)atricial");
//            String resposta = sc.nextLine();
//
//            switch (resposta.toUpperCase().charAt(0)) {
////                case 'G':
////                    Grafica grafica = new Grafica();
////                    loja = new Loja(transportadora, grafica);
////                    break;
//
//                case 'M':
//                    ImpressoraMatricial impressoraMatricial = new ImpressoraMatricial();
//                    loja = new Loja(transportadora, impressoraMatricial);
//                    break;
//
//                case 'J':
//                    ImpressoraJatoDeTinta impressoraJato = new ImpressoraJatoDeTinta("Epson", 2020);
//                    loja = new Loja(transportadora, impressoraJato);
//                    break;
//
//                case 'S':
//                    return;  // sai do main()
//
//                default:
//                    System.out.println("\nOpção inválida!");
//            }
//        }
//
//        Usuario usuario = new Usuario(123456, "Vinícius Gusmão");
//        usuario.setEndereco("Rua Taltaltal, 10000 apto 501");
//
//        CD cd = new CD(1234, "Fragile", "Yes", 1974);
//        cd.setPrecoEmReais(35);
//
//        loja.incluirItem(cd);
//        loja.receberPedido(cd, 5, usuario);
//    }
}
