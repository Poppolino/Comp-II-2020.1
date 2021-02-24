public class ImpressoraMatricial extends Impressora {

    public ImpressoraMatricial() {
        super("Qualquer marca de impressora matricial", 2000);
    }

    public void trocarFita() {

    }

    @Override
    public void imprimir(String texto) {
        System.out.println("Imprimindo do jeito de uma impressora matricial...");
        // aqui seria a implementação de verdade do método imprimir para uma jato de tinta
    }
}
