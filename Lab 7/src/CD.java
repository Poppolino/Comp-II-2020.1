public class CD extends ArtigoCultural {

    private String nomeDoDisco;

    private String artista;

    private int anoGravacao;

    public CD(long numeroIdentificacao, String nomeDoDisco, String artista, int anoGravacao) {

        super(numeroIdentificacao, String.format("CD: %s (%s, %d)",
                nomeDoDisco, artista, anoGravacao));

        this.nomeDoDisco = nomeDoDisco;
        this.artista = artista;
    }

    public String getNomeDoDisco() {
        return nomeDoDisco;
    }

    public void setNomeDoDisco(String nomeDoDisco) {
        this.nomeDoDisco = nomeDoDisco;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public int getAnoGravacao() {
        return anoGravacao;
    }

    public void setAnoGravacao(int anoGravacao) {
        this.anoGravacao = anoGravacao;
    }
}
