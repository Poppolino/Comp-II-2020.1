import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MapaUsandoArrayUnico<C, V> implements Map<C, V> {

    private ArrayList<ParChaveValor<C, V>> minhaListaDePares;

    public MapaUsandoArrayUnico() {
        this.minhaListaDePares = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.minhaListaDePares.size();
    }

    @Override
    public boolean isEmpty() {
        return this.minhaListaDePares.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.obterParChaveValor(key) != null; 
    }

    @Override
    public boolean containsValue(Object value) {
        for (ParChaveValor<C, V> par : this.minhaListaDePares) {
            if (par.getValor().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V put(C chave, V valor) {
        V valorPreExistente = null; 
        ParChaveValor<C, V> parPreExistente = obterParChaveValor(chave);

        if (parPreExistente == null) {  // chave inédita!!
            this.minhaListaDePares.add(new ParChaveValor<>(chave, valor));
            
        } else {  // chave pré-existente
            valorPreExistente = parPreExistente.getValor();
            parPreExistente.setValor(valor);
        }

        return valorPreExistente;
    }

    @Override
    public V remove(Object key) {
        int indice = -1, tamanhoMapa = this.size();
        ParChaveValor<C, V> par;
        
        for(int i = 0; i<tamanhoMapa; i++){
            par = this.minhaListaDePares.get(i);
            
            if(par.getChave().equals(key)){
                indice = i;
                break;
            }
        }
        
        if(indice == -1){
            return null;
        }
            
        return this.minhaListaDePares.remove(indice).getValor();
    }

    @Override
    public void putAll(Map<? extends C, ? extends V> m) {
        throw new RuntimeException("Operação não suportada!");
    }

    @Override
    public void clear() {
        this.minhaListaDePares.clear();
    }

    @Override
    public Set<C> keySet() {
        throw new RuntimeException("Operação não suportada!");
    }

    @Override
    public Collection<V> values() {
        throw new RuntimeException("Operação não suportada!");
    }

    @Override
    public Set<Entry<C, V>> entrySet() {
        throw new RuntimeException("Operação não suportada!");
    }

    @Override
    public V get(Object chaveDeBusca) {
        ParChaveValor<C, V> par = obterParChaveValor(chaveDeBusca);
        return par == null
                ? null
                : par.getValor();
    }

    private ParChaveValor<C, V> obterParChaveValor(Object chave) {
        for (ParChaveValor<C, V> par : this.minhaListaDePares) {
            if (par.getChave().equals(chave)) {
                return par;
            }
        }
        return null;
    }
}
