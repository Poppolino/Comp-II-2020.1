public class ImpressoraLaser extends Impressora {

    public ImpressoraLaser(String marca, int anoDeFabricacao) {
        super(marca, anoDeFabricacao);
    }

    public void trocarTonner() {
        // ToDo
    }

    @Override
    public void imprimir(String texto) {
        System.out.println("Imprimindo do jeito de uma impressora laser ...");
        // implementa do jeito dela...
    }


}
