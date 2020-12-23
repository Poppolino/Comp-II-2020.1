import java.util.ArrayList;
import java.util.Date;

public class ContaCorrente {

    // o banco já te dá algo como estímulo :-)
    public static final float SALDO_INICIAL_DE_NOVAS_CONTAS = 10.0f;
    
    private static long totalDeContas; 
    
    private final long numeroDaConta;
    
    private final Agencia agencia;

    private float saldoEmReais;

    private Date dataDeCriacao;

    private Pessoa correntista;

    private Pessoa gerenteDaConta;

    private ArrayList<String> historicoDeOperacoes;

    public ContaCorrente(Pessoa correntista, Agencia agencia) {
        this.historicoDeOperacoes = new ArrayList<>();
        this.dataDeCriacao = new Date();  // data corrente
        this.saldoEmReais = SALDO_INICIAL_DE_NOVAS_CONTAS;

        totalDeContas++;
        this.numeroDaConta = totalDeContas;
        historicoDeOperacoes.add("" + totalDeContas);
        
        this.correntista = correntista;
        this.agencia = agencia;
    }

    public long getNumeroDaConta() {
        return numeroDaConta;
    }

    public float getSaldoEmReais() {  // getter (métodos de acesso para leitura)
        return saldoEmReais;
    }
    
    public String getUltimaOperacaoDoHistorico(){
    	return this.historicoDeOperacoes.get(this.historicoDeOperacoes.size()-1);
    }

    
    /**
     * Realiza o depósito de um determinado valor na conta corrente  
     *
     * @param valor, o valor desejado
     * @return true se consegue depositar, falso caso contrário
     */
    public boolean depositar(float valor) {
        // valida o parâmetro
        if (valor <= 0) {
            return false;  // ToDo lançar uma exceção!!!!
        }

        // altera o saldo
        saldoEmReais += valor;

        historicoDeOperacoes.add("Deposito em dinheiro: R$" + valor +
                " na data " + new Date());
        
        return true;
    }

    
    /**
     * Realiza o saque de um determinado valor na conta corrente  
     *
     * @param valor, o valor desejado
     * @return true se consegue sacar, falso caso contrário
     */
    public boolean sacar(float valor) {
    	// valida o parâmetro
        if (valor <= 0) {
            return false;  // ToDo lançar uma exceção!!!!
        }

        // verifica se há fundos na conta
        if (valor > saldoEmReais) {
            return false;  // ToDo lançar uma exceção!!!!
        }

        saldoEmReais -= valor;

        historicoDeOperacoes.add(String.format(
                "Saque em dinheiro: R$%.2f na data %s",
                valor, new Date()));
        
        return true;
    }



    /**
     * Transfere um valor desta conta para a conta destino informada, se houver saldo suficiente
     * nesta conta.
     *
     * @param valor, o valor desejado
     * @param contaDestino, a conta de destino
     * @return true se consegue transferir, falso caso contrário
     */
    public boolean transferir(float valor, ContaCorrente contaDestino) {
    	// valida o parâmetro
        if (valor <= 0) {
            return false;  // ToDo lançar uma exceção!!!!
        }

        // verifica se há fundos na conta
        if (valor > saldoEmReais) {
            return false;  // ToDo lançar uma exceção!!!!
        }

        saldoEmReais -= valor;
        contaDestino.saldoEmReais += valor;

        historicoDeOperacoes.add(String.format(
                "Transferência efetuada para a conta %d: R$%.2f na data %s",
                contaDestino.numeroDaConta, valor, new Date()));

        contaDestino.historicoDeOperacoes.add(String.format(
                "Transferência recebida da conta %d: R$%.2f na data %s",
                this.numeroDaConta, valor, new Date()));
    
        return true;
    }
}
