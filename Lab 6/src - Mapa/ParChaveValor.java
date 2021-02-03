public class ParChaveValor<C, V> {

    private C chave;
    private V valor;

    public ParChaveValor(C chave, V valor) {
        this.chave = chave;
        this.valor = valor;
    }

    public C getChave() {
        return chave;
    }

    public void setChave(C chave) {
        this.chave = chave;
    }

    public V getValor() {
        return valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "ParChaveValor{" +
                "chave=" + chave +
                ", valor=" + valor +
                '}';
    }
}
