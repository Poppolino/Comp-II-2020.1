import java.util.Date;

public abstract class Impressora {

    private final String marca;

    public final int anoDeFabricacao;

    private int quantidadeDeCaracteresImpressos;

    private Date dataUltimaRecarga;

    private int quantidadeDeFolhasDePapelNaBandeja;

    public Impressora(String marca, int anoDeFabricacao) {
        this.marca = marca;
        this.anoDeFabricacao = anoDeFabricacao;
        this.quantidadeDeFolhasDePapelNaBandeja = 0;
    }

    public abstract void imprimir(String texto);

    public void recarregar() {
        System.out.println("Recarregando...");
        this.dataUltimaRecarga = new Date();  // hora atual
    }

    public void receberPapel(int quantidadeDeFolhas) {
        abrirBandeja();
        colocarPapelNaBandeja();
        this.quantidadeDeFolhasDePapelNaBandeja += quantidadeDeFolhas;
    }

    private void abrirBandeja() {
        System.out.println("Abrindo bandeja...");
    }

    private void colocarPapelNaBandeja() {
        System.out.println("Colocando papel na bandeja...");
    }

    public int getQuantidadeDeCaracteresImpressos() {
        return this.quantidadeDeCaracteresImpressos;
    }

    public int getAnoDeFabricacao() {
        return this.anoDeFabricacao;
    }

    public String getMarca() {
        return this.marca;
    }
}
