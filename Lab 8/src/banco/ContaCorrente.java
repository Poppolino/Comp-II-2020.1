package banco;

import excecoes.*;
import java.util.ArrayList;
import java.util.Date;

public class ContaCorrente {

    // o banco já te dá algo como estímulo :-)
    public static final float SALDO_INICIAL_DE_NOVAS_CONTAS = 10.0f;

    //limite comum a todas as contas
    public static final float LIMITE_CHEQUE_ESPECIAL = 200.0f;

    private final long numeroDaConta;

    private final Agencia agencia;

    private float saldoEmReais;

    private Date dataDeCriacao;

    private Pessoa correntista;

    private Pessoa gerenteDaConta;

    private ArrayList<String> historicoDeOperacoes;

    public static int numeroDeContasCriadas = 0;

    public ContaCorrente(Pessoa correntista, Agencia agencia) {
        this.historicoDeOperacoes = new ArrayList<>();
        this.dataDeCriacao = new Date();  // data corrente
        this.saldoEmReais = SALDO_INICIAL_DE_NOVAS_CONTAS;

        this.numeroDaConta = ++numeroDeContasCriadas;

        this.correntista = correntista;
        this.agencia = agencia;
    }

    public long getNumeroDaConta() {
        return numeroDaConta;
    }

    public float getSaldoEmReais() {  // getter (métodos de acesso para leitura)
        return saldoEmReais;
    }

    private void setSaldoEmReais(float novoSaldo) {
        this.saldoEmReais = novoSaldo;
        if (novoSaldo < 0) {
            this.agencia.getBanco().registrarCorrentistaComSaldoNegativo(
                    this.correntista);
        }
    }

    
    /**
     * Deposita um valor na conta, se o valor for válido.
     * 
     * @param valor o valor a ser depositado.
     * 
     * @throws DepositoDeValorNaoPositivoException se o valor do depósito for menor ou 
     *         igual a zero.
     */
    public void depositar(float valor) throws DepositoDeValorNaoPositivoException{
        // valida o parâmetro
        if (valor <= 0) {
            throw new DepositoDeValorNaoPositivoException();
        }

        // altera o saldo
        setSaldoEmReais(this.saldoEmReais + valor);

        historicoDeOperacoes.add(String.format(
                "Deposito em dinheiro: R$%.2f na data %s",
                valor, new Date()));
    }

    
    /**
     * Saca um valor da conta, se o valor for válido e tiver saldo suficiente.
     * 
     * @param valor o valor a ser sacado. 
     * 
     * @throws SaqueDeValorNaoPositivoException se o valor do saque for menor ou 
     *         igual a zero.
     * @throws SaldoInsuficienteException se o saldo da conta não for suficiente para 
     *         realizar o saque desejado.
     */
    public void sacar(float valor)
            throws SaqueDeValorNaoPositivoException, SaldoInsuficienteException {

        // valida o parâmetro
        if (valor <= 0) {
            throw new SaqueDeValorNaoPositivoException();
        }

        // verifica se há fundos na conta
        float valorAlemDoLimite = valor - (saldoEmReais + LIMITE_CHEQUE_ESPECIAL);
        if (valorAlemDoLimite > 0) {
            throw new SaldoInsuficienteException(valorAlemDoLimite);
        }

        setSaldoEmReais(this.saldoEmReais - valor);

        historicoDeOperacoes.add(String.format(
                "Saque em dinheiro: R$%.2f na data %s",
                valor, new Date()));
    }

    
    /**
     * Transfere um valor desta conta para a conta destino informada, se houver saldo suficiente
     * nesta conta.
     *
     * @param valor o valor desejado
     * @param contaDestino a conta de destino
     * 
     * @throws TransferenciaDeValorNaoPositivoException se o valor da transferência for menor ou 
     *         igual a zero.
     * @throws SaldoInsuficienteException se o saldo da conta não for suficiente para fazer a 
     *         transferência.
     */
    public void transferir(float valor, ContaCorrente contaDestino) 
            throws TransferenciaDeValorNaoPositivoException, SaldoInsuficienteException{
        
        // valida o parâmetro
        if (valor <= 0) {
            throw new TransferenciaDeValorNaoPositivoException();
        }

        // verifica se há fundos na conta
        float valorAlemDoLimite = valor - (saldoEmReais + LIMITE_CHEQUE_ESPECIAL);
        if (valorAlemDoLimite > 0) {
            throw new SaldoInsuficienteException(valorAlemDoLimite);
        }

        setSaldoEmReais(this.saldoEmReais - valor);
        contaDestino.saldoEmReais += valor;

        historicoDeOperacoes.add(String.format(
                "Transferência efetuada para a conta %d: R$%.2f na data %s",
                contaDestino.numeroDaConta, valor, new Date()));

        contaDestino.historicoDeOperacoes.add(String.format(
                "Transferência recebida da conta %d: R$%.2f na data %s",
                this.numeroDaConta, valor, new Date()));
    }

    
    public String getUltimoItemHistorico() {
        return historicoDeOperacoes.size() > 0 ?
                historicoDeOperacoes.get(historicoDeOperacoes.size() - 1) :
                null;
    }

    public Agencia getAgencia() {
        return agencia;
    }
}
