public class ImpressoraJatoDeTinta extends Impressora {

    public ImpressoraJatoDeTinta(String marca, int anoDeFabricacao) {
        super(marca, anoDeFabricacao);
        efetuarLimpezaBicos();
    }

    public int getNivelTinta(int codigoDaCor) {
        // ToDo
        return 0;
    }

    public void efetuarLimpezaBicos() {

    }

    @Override
    public void imprimir(String texto) {
        System.out.println("Imprimindo do jeito de uma impressora jato de tinta...");
    }
}
