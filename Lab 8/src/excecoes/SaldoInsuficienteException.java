package excecoes;

public class SaldoInsuficienteException extends Exception {

    private float valorAlemDoLimite;

    public SaldoInsuficienteException(float valorAlemDoLimite) {
        this.valorAlemDoLimite = valorAlemDoLimite;
    }

    public float getValorAlemDoLimite() {
        return valorAlemDoLimite;
    }
}
