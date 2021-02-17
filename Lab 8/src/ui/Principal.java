package ui; 

import banco.*;
import excecoes.*;
import java.util.Scanner;

public class Principal {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Banco meuBanco = new Banco();

        boolean terminar = false;

        while (!terminar) {

            apresentarMenu();
            String opcao = lerOpcao();

            switch (opcao.toUpperCase()) {
                case "D":
                    System.out.print("Número da conta: ");
                    long numeroDaConta = Long.parseLong(scanner.nextLine());
                    System.out.print("Valor desejado: ");
                    float valor = Float.parseFloat(scanner.nextLine());

                    ContaCorrente contaCorrente = meuBanco.localizarConta(numeroDaConta);
                    if (contaCorrente != null) {
                        
                        try{
                            contaCorrente.depositar(valor);
                            System.out.println(contaCorrente.getUltimoItemHistorico());
                        } 
                        catch (DepositoDeValorNaoPositivoException e){
                            System.out.println("Valor inválido!");
                        }
                        
                    } else {
                        System.out.println("Conta inexistente!");
                    }
                    break;  // switch

                case "S":
                    System.out.print("Número da conta: ");
                    numeroDaConta = Long.parseLong(scanner.nextLine());
                    System.out.print("Valor desejado: ");
                    valor = Float.parseFloat(scanner.nextLine());

                    contaCorrente = meuBanco.localizarConta(numeroDaConta);
                    if (contaCorrente != null) {


                        try {
                            contaCorrente.sacar(valor);
                            System.out.println(contaCorrente.getUltimoItemHistorico());
                        } catch (SaqueDeValorNaoPositivoException e) {

                            // qual o tratamento que eu quero dar?
                            System.out.println("Valor inválido!");

                        } catch (SaldoInsuficienteException e) {

                            // qual o tratamento que eu quero dar para esse outro caso?

                            // 1) enviarEmail(contaCorrente.getAgencia().getGerenteGeral(),
                            //        "Usuario precisando de emprestimo!!!");   ???

                            // 2) resgate de aplicação automática  ???
                            //
                            //  etc. etc.

                            System.out.println("Saldo insuficiente!!!");
                        }


                    } else {
                        System.out.println("Conta inexistente!");
                    }
                    break;  // switch

                case "T":
                    System.out.print("Número da conta de origem: ");
                    numeroDaConta = Long.parseLong(scanner.nextLine());
                    System.out.print("Número da conta de destino: ");
                    long numeroDaContaDestino = Long.parseLong(scanner.nextLine());
                    
                    System.out.print("Valor desejado: ");
                    valor = Float.parseFloat(scanner.nextLine());

                    contaCorrente = meuBanco.localizarConta(numeroDaConta);
                    ContaCorrente contaCorrenteDestino = meuBanco.localizarConta(numeroDaContaDestino);
                    
                    if (contaCorrente == null) {
                        System.out.println("Conta de origem inexistente!");
                    } else if(contaCorrenteDestino == null){
                        System.out.println("Conta de destino inexistente!");
                    } else{
                        
                        try{
                            contaCorrente.transferir(valor, contaCorrenteDestino);
                            System.out.println(contaCorrente.getUltimoItemHistorico()); 
                        } 
                        catch (TransferenciaDeValorNaoPositivoException e){
                            System.out.println("Valor inválido!");
                        } 
                        catch (SaldoInsuficienteException e){
                            System.out.println("Saldo da conta de origem é insuficiente!!!");
                        }
                        
                    }
                    
                    break;

                case "C":
                    System.out.print("Número da conta: ");
                    long numero = Long.parseLong(scanner.nextLine());
                    ContaCorrente conta = meuBanco.localizarConta(numero);
                    if (conta != null) {
                        System.out.println(String.format("Saldo = %.2f",
                                conta.getSaldoEmReais()));
                    } else {
                        System.out.println("Conta inexistente!");
                    }
                    break;  // switch

                case "P":
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    long cpf = Long.parseLong(scanner.nextLine());

                    meuBanco.cadastrarCorrentista(nome, cpf);
                    System.out.println("Cadastro realizado!");

                    break;  // switch

                case "N":
                    System.out.print("CPF do correntista: ");
                    cpf = Long.parseLong(scanner.nextLine());
                    Pessoa correntista = meuBanco.localizarCorrentista(cpf);
                    if (correntista != null) {
                        ContaCorrente novaConta = meuBanco.cadastrarConta(correntista);
                        System.out.println("Conta criada com o número " + novaConta.getNumeroDaConta());
                    } else {
                        System.out.println("Correntista não existe!");
                    }
                    break;  // switch

                case "X":
                    terminar = true;
                    break;

                default:
                    System.out.println("Opção inválida");
            }
        }

        System.out.println("Tchau!");
    }

    private static void apresentarMenu() {
        System.out.println(
                "\n------\n" +
                "(D)epositar\n" +
                "(S)acar\n" +
                "(T)ransferir\n" +
                "(C)onsultar saldo\n" +
                "Cadastrar (P)essoa como correntista\n" +
                "Criar (N)ova conta\n" +
                "(X) para sair\n");
    }

    private static String lerOpcao() {
        System.out.print("\nAção desejada: ");
        return scanner.nextLine();
    }



}
