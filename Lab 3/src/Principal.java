import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


public class Principal {
	
	private static Agencia agenciaGeral = new Agencia();
	private static ArrayList<ContaCorrente> contas = new ArrayList<>();
	private static ArrayList<Pessoa> pessoas = new ArrayList<>();
	
	
	public static void depositar(Scanner leitura){ 
		System.out.println("\n- DEPÓSITO -");
		System.out.println("Por favor, entre com o número da conta que deseja depositar, seguido do valor do depósito em reais:");
		
		long numeroConta = leitura.nextLong();
		float valor = leitura.nextFloat();
		
		if(numeroConta <= 0 || numeroConta > contas.size()){
			System.out.println("Número da conta inválido.\n");
			return;
		}
		
		ContaCorrente aux = (ContaCorrente)contas.get((int) numeroConta-1);
		
		if(!aux.depositar(valor)){
			System.out.println("Não foi possível depositar o valor especificado.\n");
			return;
		}
		
		System.out.println(aux.getUltimaOperacaoDoHistorico() + "\n");
	}
	
	
	public static void sacar(Scanner leitura){
		System.out.println("\n- SAQUE -");
		System.out.println("Por favor, entre com o número da conta que deseja sacar, seguido do valor do saque em reais:");
		
		long numeroConta = leitura.nextLong();
		float valor = leitura.nextFloat();
		
		if(numeroConta <= 0 || numeroConta > contas.size()){
			System.out.println("Número da conta inválido.\n");
			return;
		}
		
		ContaCorrente aux = (ContaCorrente)contas.get((int) numeroConta-1);
		
		if(!aux.sacar(valor)){
			System.out.println("Não foi possível sacar o valor especificado.\n");
			return;
		}
		
		System.out.println(aux.getUltimaOperacaoDoHistorico() + "\n");
	}
	
	
	public static void transferir(Scanner leitura){
		System.out.println("\n- Transferência -");
		System.out.println("Por favor, entre com o número da conta de origem, número da conta de destino e o valor da transferência em reais:");
		
		long numeroContaOrigem = leitura.nextLong();
		long numeroContaDestino = leitura.nextLong();
		float valor = leitura.nextFloat();
		
		if(numeroContaOrigem <= 0 || numeroContaOrigem > contas.size()){
			System.out.println("Número da conta de origem é inválido.\n");
			return;
		}
		
		if(numeroContaDestino <= 0 || numeroContaDestino > contas.size()){
			System.out.println("Número da conta de destino é inválido.\n");
			return;
		}
		
		ContaCorrente origem = (ContaCorrente)contas.get((int) numeroContaOrigem-1);
		ContaCorrente destino = (ContaCorrente)contas.get((int) numeroContaDestino-1);
		
		if(!origem.transferir(valor, destino)){
			System.out.println("Não foi possível realizar a transferência.\n");
			return;
		}
		
		System.out.println(origem.getUltimaOperacaoDoHistorico());
		System.out.println(destino.getUltimaOperacaoDoHistorico() + "\n");
	}
	
	
	public static void consultarSaldo(Scanner leitura){
		System.out.println("\n- SALDO -");
		System.out.println("Por favor, entre com o número da conta que deseja consultar o saldo:");
		
		long numeroConta = leitura.nextLong();
		
		if(numeroConta <= 0 || numeroConta > contas.size()){
			System.out.println("O número da conta é inválido.\n");
			return;
		}
		
		ContaCorrente aux = (ContaCorrente)contas.get((int) numeroConta-1);
		
		System.out.printf("O saldo da conta escolhida é: R$%.2f\n\n", aux.getSaldoEmReais());
	}
	
	
	public static void cadastrarPessoa(Scanner leitura){
		System.out.println("\n- CADASTRAR CORRENTISTA -");
		
		System.out.println("Por favor, entre com o nome da pessoa que deseja cadastrar:");
		String nome = leitura.nextLine();
		
		System.out.println("Por favor, entre com o CPF da pessoa que deseja cadastrar:");
		long cpf = leitura.nextLong();
		
		Pessoa correntista = new Pessoa(nome, cpf);
		
		if(pessoas.contains(correntista)){
			System.out.println("A pessoa escolhida já está cadastrada no sistema.\n");
			return;
		}
		
		pessoas.add(correntista);
		System.out.println("Pessoa cadastrada com sucesso!!\n");
	}
	
	
	public static void criarNovaConta(Scanner leitura){
		System.out.println("\n- CRIAR CONTA -");
		System.out.println("Por favor, digite o CPF do titular da conta que deseja criar:");
		
		long cpf = leitura.nextLong();
		
		if(!pessoas.contains(new Pessoa("checa", cpf))){
			System.out.println("A pessoa escolhida ainda não está cadastrada no sistema.\n");
			return;
		}
		
		Pessoa correntista = pessoas.get( pessoas.indexOf(new Pessoa("checa", cpf)) );
		
		ContaCorrente novaConta = new ContaCorrente(correntista, agenciaGeral);
		
		contas.add(novaConta);
		
		System.out.println("Conta criada com sucesso!! Número da conta: " + novaConta.getNumeroDaConta() + "\n");
	}
	
	
	
    public static void main(String[] args) {
    	Scanner leitura = new Scanner(System.in);
    	leitura.useLocale(Locale.US);            //Definindo o padrão de casa decimal como americano para ler com '.' e não com ','
    	String opcao;
    	
    	System.out.println("Bem-vindo ao sistema da Agência Geral!!");
    	
    	while(true){
    		System.out.println("\nPara realizar uma das operações a seguir, digite a letra correspondente:");
    		System.out.println("(D)epositar");
    		System.out.println("(S)acar");
    		System.out.println("(T)ransferir");
    		System.out.println("(C)onsultar saldo");
    		System.out.println("Cadastrar (P)essoa como correntista");
    		System.out.println("Criar (N)ova conta");
    		System.out.println("(X) para sair");
    		System.out.println("\nOpção desejada:");
    		
    		opcao = leitura.nextLine();
    		
    		if(opcao.equals("D") || opcao.equals("d")){
    			depositar(leitura);
    			leitura.nextLine();
    			continue;
    		}
    		
    		if(opcao.equals("S") || opcao.equals("s")){
    			sacar(leitura);
    			leitura.nextLine();
    			continue;
    		}
    		
    		if(opcao.equals("T") || opcao.equals("t")){
    			transferir(leitura);
    			leitura.nextLine();
    			continue;
    		}
    		
    		if(opcao.equals("C") || opcao.equals("c")){
    			consultarSaldo(leitura);
    			leitura.nextLine();
    			continue;
    		}
    		
    		if(opcao.equals("P") || opcao.equals("p")){
    			cadastrarPessoa(leitura);
    			leitura.nextLine();
    			continue;
    		}
    		
    		if(opcao.equals("N") || opcao.equals("n")){
    			criarNovaConta(leitura);
    			leitura.nextLine();
    			continue;
    		}
    		    		
    		if(opcao.equals("X") || opcao.equals("x")){
    			break;
    		}
    		
    		System.out.println("Opção inválida! Tente novamente.\n");
    	}
    	
    	System.out.println("Agradecemos a preferência. Volte sempre!");
    	leitura.close();
    }
}
