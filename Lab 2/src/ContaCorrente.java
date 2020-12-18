import java.util.ArrayList;
import java.util.Date;

public class ContaCorrente {

    private final long numeroDaConta;

    private final Agencia agencia;

    private float saldoEmReais;

    private Date dataDeCriacao;

    private Pessoa correntista;

    private Pessoa gerenteDaConta;

    private ArrayList<String> historicoDeOperacoes;

    public ContaCorrente(long numeroDaConta, Pessoa correntista, Agencia agencia) {
        this.historicoDeOperacoes = new ArrayList<>();
        this.dataDeCriacao = new Date();  // data corrente
        this.saldoEmReais = 10;  // o banco dá 10 reais de estímulo para a abertura de conta
        this.numeroDaConta = numeroDaConta;
        this.correntista = correntista;
        this.agencia = agencia;
    }

    
    public float getSaldoEmReais() {  // getter (métodos de acesso para leitura)
        return this.saldoEmReais;
    }
    
    public Pessoa getCorrentista() {
        return this.correntista;
    }
    
    public ArrayList<String> getHistoricoDeOperacoes() {
        return this.historicoDeOperacoes;
    }
    

    
    /**
     * Realiza o depósito de um determinado valor na conta corrente  
     *
     * @param o valor desejado
     */
    public void depositar(float valor) {
        // valida o parâmetro
        if (valor <= 0) {
            return;  // ToDo lançar uma exceção!!!!
        }

        // altera o saldo
        this.saldoEmReais += valor;

        this.historicoDeOperacoes.add("Deposito em dinheiro: " + valor +
                                      " na data " + new Date());
    }
    
    
    /**
     * Realiza o saque de um determinado valor na conta corrente  
     *
     * @param o valor desejado
     */
    public void sacar(float valor){
    	if(valor <= 0 || this.saldoEmReais < valor){    //impede inconsistências no saque
    	    return;
    	}
    	
    	this.saldoEmReais -= valor;    //altera o saldo
    	
    	this.historicoDeOperacoes.add("Saque em dinheiro: " + valor + 
                                      " na data " + new Date());
    }
    
    
    /**
     * Transfere um valor desta conta para a conta destino informada, se houver saldo suficiente
     * nesta conta.
     *
     * @param o valor desejado
     * @param contaDestino a conta de destino
     */
    public void transferir(float valor, ContaCorrente contaDestino) {
    	if(valor <= 0 || this.saldoEmReais < valor){    //impede inconsistências na transferência
            return;
    	}
    	
    	this.saldoEmReais -= valor; 
    	
    	String destinatario = contaDestino.getCorrentista().getNome();
    	contaDestino.depositar(valor);
    	
    	this.historicoDeOperacoes.add("Tranferência para " + destinatario + " no valor de "  
                                      + valor + " reais na data " + new Date());
    }
}
