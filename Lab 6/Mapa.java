public interface Mapa<C, V> {

    /**
     * Armazena um valor no mapa, associado �chave informada.
     * Caso j� exista um valor associado a essa chave,
     * o valor ser� substitu�do pelo novo valor informado.
     *
     * @param chave A chave desejada
     * @param valor O valor associado �chave informada
     */
    void put(C chave, V valor);

    /**
     * Retorna o valor associado � chave informada.
     *
     * @param chave A chave de busca
     * @return O valor associado � chave, se existir;
     *         null, caso contr�rio
     */
    V get(C chave);
}
