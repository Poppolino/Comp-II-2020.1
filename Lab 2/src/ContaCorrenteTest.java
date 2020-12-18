import org.junit.Test;

import static org.junit.Assert.*;

public class ContaCorrenteTest {

    private final float ERRO_ACEITAVEL_NOS_FLOATS = 0.000001f;

    @Test
    public void testarDeposito() {
        Pessoa maria = new Pessoa("Maria", 12345678);
        Agencia minhaAgencia = new Agencia();
        ContaCorrente conta = new ContaCorrente(1, maria, minhaAgencia);

        // sanity check: a conta já começa com saldo 10 (regra de negócio)
        assertEquals(10f, conta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        conta.depositar(1000);
        assertEquals(1010f, conta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        conta.depositar(500);
        assertEquals(1510f, conta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        conta.depositar(-100);
        assertEquals(1510f, conta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);  // nada mudou, porque o depósito não foi feito
   }

    
    @Test
    public void testarSaque() {
        Pessoa pedro = new Pessoa("Pedro", 99999999);
        Agencia agenciaInventada = new Agencia();
        ContaCorrente conta = new ContaCorrente(123, pedro, agenciaInventada);

        // sanity check: a conta já começa com saldo 10 (regra de negócio)
        assertEquals(10f, conta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        
        conta.depositar(1000);    //Um depósito amigável, nossa 1ª operação do histórico da conta   
        
        conta.sacar(-42);   
        assertEquals(1010f, conta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);    //checa valor negativo

        conta.sacar(0);
        assertEquals(1010f, conta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);    //checa valor nulo

        conta.sacar(2000);
        assertEquals(1010f, conta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);    //checa saldo insuficiente para o saque 
        
        conta.sacar(500);    
        assertEquals(510f, conta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);    //checa saque legítimo 
        assertEquals(2, conta.getHistoricoDeOperacoes().size());    //checa se houve uma nova entrada no histórico    
    }
    
   
    @Test
    public void testarTransferecia() {
        Pessoa maria = new Pessoa("Maria", 12345678);
        Pessoa joao = new Pessoa("Joao", 23222);
        Agencia minhaAgencia = new Agencia();

        ContaCorrente contaMaria = new ContaCorrente(1, maria, minhaAgencia);
        ContaCorrente contaJoao = new ContaCorrente(2, joao, minhaAgencia);

        // sanity check: as contas já começam com saldo 10 (regra de negócio)
        assertEquals(10f, contaMaria.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        assertEquals(10f, contaJoao.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        contaMaria.transferir(7, contaJoao);

        assertEquals(3f, contaMaria.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        assertEquals(17f, contaJoao.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
    }

    @Test
    public void testarTransfereciaSemFundosNaContaDeOrigem() {
        Pessoa maria = new Pessoa("Maria", 12345678);
        Pessoa joao = new Pessoa("Joao", 23222);
        Agencia minhaAgencia = new Agencia();

        ContaCorrente contaMaria = new ContaCorrente(1, maria, minhaAgencia);
        ContaCorrente contaJoao = new ContaCorrente(2, joao, minhaAgencia);

        // sanity check: as contas já começam com saldo 10 (regra de negócio)
        assertEquals(10f, contaMaria.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        assertEquals(10f, contaJoao.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        contaMaria.transferir(200, contaJoao);

        assertEquals(10f, contaMaria.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        assertEquals(10f, contaJoao.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        // a transferência NÃO DEVE SER REALIZADA, porque não há fundos na conta de origem (Maria).
    }
}