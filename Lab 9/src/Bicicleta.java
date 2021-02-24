import java.util.Objects;

public class Bicicleta extends Veiculo implements Vendavel {

    private static float PRECO_DEFAULT = 500;

    private long codigoModelo;

    private int aro;

    private String marca;

    private float precoEmReais;

    public int getAro() {
        return aro;
    }

    public Bicicleta(long codigoModelo, int aro, String marca) {
        this.codigoModelo = codigoModelo;
        this.aro = aro;
        this.marca = marca;
        this.precoEmReais = PRECO_DEFAULT;
    }

    @Override
    public long getId() {
        return codigoModelo;
    }

    @Override
    public String getDescricao() {
        return String.format("Bicicleta %s aro %d", marca, aro);
    }

    @Override
    public float getPrecoEmReais() {
        return precoEmReais;
    }

    public void setPrecoEmReais(float precoEmReais) {
        this.precoEmReais = precoEmReais;
    }

    @Override
    public String toString() {
        return getDescricao();
    }
    
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        
        if(o == null || this.getClass() != o.getClass()){
            return false;
        }
        
        Bicicleta outraBicicleta = (Bicicleta) o;
        
        return this.getId() == outraBicicleta.getId();
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(this.getId());
    }
}
