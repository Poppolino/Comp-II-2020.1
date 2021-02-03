import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MapaUsandoDoisArraysParalelos<C, V> implements Map<C, V> {

    private ArrayList<C> minhaListaDeChaves;
    private ArrayList<V> minhaListaDeValores;

    public MapaUsandoDoisArraysParalelos() {
        this.minhaListaDeChaves = new ArrayList<>();
        this.minhaListaDeValores = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.minhaListaDeChaves.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object chave) {
        return obterPosicaoChave(chave) != -1;
    }

    @Override
    public boolean containsValue(Object valorBuscado) {
        for (int i = 0; i < this.minhaListaDeValores.size(); i++) {
            if (this.minhaListaDeValores.get(i).equals(valorBuscado)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V put(C chave, V valor) {
        V valorAnterior = null;
        int posicaoDaChavePreExistente = obterPosicaoChave(chave);

        if (posicaoDaChavePreExistente == -1) {  // chave inédita!!
            this.minhaListaDeChaves.add(chave);
            this.minhaListaDeValores.add(valor);

        } else {  // chave pré-existente
            valorAnterior = this.minhaListaDeValores.get(posicaoDaChavePreExistente);
            this.minhaListaDeValores.set(
                    posicaoDaChavePreExistente, valor);
        }
        return valorAnterior;
    }

    @Override
    public V remove(Object key) {
        throw new RuntimeException("Operação não suportada!");
    }

    @Override
    public void putAll(Map<? extends C, ? extends V> m) {
        throw new RuntimeException("Operação não suportada!");
    }

    @Override
    public void clear() {
        this.minhaListaDeChaves.clear();
        this.minhaListaDeValores.clear();
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

    private int obterPosicaoChave(Object chave) {
        for (int i = 0; i < this.minhaListaDeChaves.size(); i++) {
            if (this.minhaListaDeChaves.get(i).equals(chave)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public V get(Object chaveDeBusca) {
        int posicaoDaChave = obterPosicaoChave(chaveDeBusca);
        return posicaoDaChave == -1
                ? null
                : this.minhaListaDeValores.get(posicaoDaChave);
    }
}
