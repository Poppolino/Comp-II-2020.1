public class EstoqueInsuficienteException extends Exception {

    private int quantidadeEmEstoque;

    public EstoqueInsuficienteException(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }
}
