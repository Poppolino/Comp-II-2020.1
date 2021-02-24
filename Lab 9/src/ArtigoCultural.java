import java.util.Objects;

public class ArtigoCultural implements Vendavel {

    private final long id;  // código único de identificação do produto

    private String descricao;

    private float precoEmReais;

    private String urlDaImagem;

    private int pesoEmGramas;

    public ArtigoCultural(long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPrecoEmReais() {
        return precoEmReais;
    }

    public void setPrecoEmReais(float precoEmReais) {
        this.precoEmReais = precoEmReais;
    }

    public String getUrlDaImagem() {
        return urlDaImagem;
    }

    public void setUrlDaImagem(String urlDaImagem) {
        this.urlDaImagem = urlDaImagem;
    }

    public int getPesoEmGramas() {
        return pesoEmGramas;
    }

    public void setPesoEmGramas(int pesoEmGramas) {
        this.pesoEmGramas = pesoEmGramas;
    }

    public int getVolumeEmCm3() {
        return 0;  // ToDo IMPLEMENT ME!
    }

    @Override
    public String toString() {
        return descricao;
    }
    
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        
        if(o == null || this.getClass() != o.getClass()){
            return false;
        }
        
        ArtigoCultural outroArtigo = (ArtigoCultural) o;
        
        return this.id == outroArtigo.id;
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(this.id);
    }
}
