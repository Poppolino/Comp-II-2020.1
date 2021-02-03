public interface Mapa<C, V> {

    /**
     * Armazena um valor no mapa, associado à chave informada.
     * Caso já exista um valor associado a essa chave,
     * o valor será substituído pelo novo valor informado.
     *
     * @param chave A chave desejada
     * @param valor O valor associado à chave informada
     */
    void put(C chave, V valor);

    /**
     * Retorna o valor associado à chave informada.
     *
     * @param chave A chave de busca
     * @return O valor associado à chave, se existir;
     *         null, caso contrário
     */
    V get(C chave);
}
