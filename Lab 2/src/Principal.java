public class Principal {

    public static void main(String[] args) {
        Pessoa fulano = new Pessoa("Fulano de Tal", 12345678);
        fulano.setEndereco("Rua BlaBLaBla, numero tal");

        Agencia agencia = new Agencia();

        ContaCorrente minhaConta = new ContaCorrente(1, fulano, agencia);

        System.out.println(minhaConta.getSaldoEmReais());

        minhaConta.depositar(1000);
        System.out.println(minhaConta.getSaldoEmReais());

        minhaConta.depositar(1000);
        System.out.println(minhaConta.getSaldoEmReais());
        
        System.out.println(fulano);
    }
}
