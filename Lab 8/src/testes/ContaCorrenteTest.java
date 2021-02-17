package testes;

import banco.*;
import excecoes.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContaCorrenteTest {

    private static final float ERRO_ACEITAVEL_NOS_FLOATS = 0.000001f;

    private Pessoa maria;
    private Pessoa joao;

    private Banco banco;

    private Agencia minhaAgencia;

    private ContaCorrente contaDaMaria;
    private ContaCorrente contaDoJoao;

    @Before
    public void setUp() {
        banco = new Banco();

        // cria algumas pessoas
        maria = new Pessoa("Maria", 12345678);
        joao = new Pessoa("Joao", 23222);

        // cria uma agencia
        minhaAgencia = new Agencia(banco, 1, "Agência Principal");

        ContaCorrente.numeroDeContasCriadas = 0;  // reseta o static da classe

        // cria algumas contas
        contaDaMaria = new ContaCorrente(maria, minhaAgencia);
        contaDoJoao = new ContaCorrente(joao, minhaAgencia);
    }

    
    @Test
    public void testarNumerosAutomaticosDeContas() {
        assertEquals(1, contaDaMaria.getNumeroDaConta());
        assertEquals(2, contaDoJoao.getNumeroDaConta());
//        ContaCorrente novaConta = new ContaCorrente(maria, minhaAgencia);
//        long numeroDaConta = novaConta.getNumeroDaConta();
//
//        assertEquals(numeroDaConta + 1,
//                (new ContaCorrente(joao, minhaAgencia).getNumeroDaConta()));
//        assertEquals(numeroDaConta + 2,
//                (new ContaCorrente(joao, minhaAgencia).getNumeroDaConta());
    }

    
    @Test
    public void testarDeposito() throws DepositoDeValorNaoPositivoException {
        checarSaldoInicial(contaDaMaria);

        contaDaMaria.depositar(1000);
        assertFloatEquals(1010f, contaDaMaria.getSaldoEmReais());

        contaDaMaria.depositar(500);
        assertFloatEquals(1510f, contaDaMaria.getSaldoEmReais());
    }
    
   
    @Test
    public void testarDepositoNaoPositivo() {
        checarSaldoInicial(contaDaMaria);
        
        for(int valor = 0; valor > -11; valor-=10){
            try{
                contaDaMaria.depositar(valor);
                fail("Uma DepositoDeValorNaoPositivoException deve ser produzida ao tentar depositar um valor não positivo.");
            }
            catch (DepositoDeValorNaoPositivoException e){
                // A exceção foi lançada corretamente!
            }
        }
        
        checarSaldoInicial(contaDaMaria); // nada mudou, porque o depósito não foi feito
   }

    
    @Test
    public void testarSaque() 
            throws SaqueDeValorNaoPositivoException, SaldoInsuficienteException {
       
        checarSaldoInicial(contaDaMaria);
        contaDaMaria.sacar(7);
        assertEquals(3f, contaDaMaria.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
    }
    
    
    @Test
    public void testarSaqueNaoPositivo() throws SaldoInsuficienteException {
        checarSaldoInicial(contaDaMaria);
        
        for(int valor = 0; valor > -11; valor-=10){
            try{
                contaDaMaria.sacar(valor);
                fail("Uma SaqueDeValorNaoPositivoException deve ser produzida ao tentar sacar um valor não positivo.");
            }
            catch (SaqueDeValorNaoPositivoException e){
                // A exceção foi lançada corretamente!
            }
        }
        
        checarSaldoInicial(contaDaMaria); // nada mudou, porque o saque não foi feito
    }
    
    
    @Test
    public void testarSaqueSemFundos() throws SaqueDeValorNaoPositivoException {
        checarSaldoInicial(contaDaMaria);
        
        try{
            contaDaMaria.sacar(300);
            fail("Uma SaldoInsuficienteException deve ser produzida se o saldo é insuficiente para o saque.");
        } 
        catch(SaldoInsuficienteException e){
            // A exceção foi lançada corretamente!
        }
        
        assertFloatEquals(10f, contaDaMaria.getSaldoEmReais());  // nada mudou, porque o saque não foi feito
    }

    
    @Test
    public void testarTransferecia() 
            throws TransferenciaDeValorNaoPositivoException, SaldoInsuficienteException {
        
        // sanity check: as contas já começam com saldo 10 (regra de negócio)
        checarSaldoInicial(contaDaMaria);
        checarSaldoInicial(contaDoJoao);

        contaDaMaria.transferir(7, contaDoJoao);

        assertFloatEquals(3f, contaDaMaria.getSaldoEmReais());
        assertFloatEquals(17f, contaDoJoao.getSaldoEmReais());
    }
    
    
    @Test
    public void testarTransfereciaDeValorNaoPositivo() throws SaldoInsuficienteException {
        // sanity check: as contas já começam com saldo 10 (regra de negócio)
        checarSaldoInicial(contaDaMaria);
        checarSaldoInicial(contaDoJoao);
        
        for(int valor = 0; valor > -11; valor-=10){
            try{
                contaDaMaria.transferir(valor, contaDoJoao);
                fail("Uma TransferenciaDeValorNaoPositivoException deve ser produzida ao tentar transferir um valor não positivo.");
            } 
            catch (TransferenciaDeValorNaoPositivoException e){
                // A exceção foi lançada corretamente!
            }
        }
        
        // nada mudou, porque a transferência não foi feita
        assertFloatEquals(10f, contaDaMaria.getSaldoEmReais()); 
        assertFloatEquals(10f, contaDoJoao.getSaldoEmReais());
    }
    
    
    @Test
    public void testarTransfereciaSemFundosNaContaDeOrigem() throws TransferenciaDeValorNaoPositivoException {
        // sanity check: as contas já começam com saldo 10 (regra de negócio)
        assertFloatEquals(10f, contaDaMaria.getSaldoEmReais());
        assertFloatEquals(10f, contaDoJoao.getSaldoEmReais());
        
        try{
            contaDaMaria.transferir(300, contaDoJoao);
            fail("Uma SaldoInsuficienteException deve ser lançada se o saldo da conta de origem é insuficiente para realizar a transferência.");
        }
        catch (SaldoInsuficienteException e){
            // A exceção foi lançada corretamente!
        }
        
        // a transferência NÃO DEVE SER REALIZADA, porque não há fundos na conta de origem (Maria).
        assertFloatEquals(10f, contaDaMaria.getSaldoEmReais());
        assertFloatEquals(10f, contaDoJoao.getSaldoEmReais());
    }

    
    private void checarSaldoInicial(ContaCorrente conta) {
        // sanity check: as contas já começam com saldo 10 (regra de negócio)
        assertFloatEquals(
                ContaCorrente.SALDO_INICIAL_DE_NOVAS_CONTAS,
                conta.getSaldoEmReais());
    }

    private static void assertFloatEquals(float expected, float actual) {
        assertEquals(expected, actual, ERRO_ACEITAVEL_NOS_FLOATS);
    }
}