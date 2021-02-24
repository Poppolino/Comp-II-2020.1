public interface Vendavel {

    /**
     * @return um identificador único para este Vendável.
     */
    long getId();

    /**
     * @return a descrição textual deste Vendável.
     */
    String getDescricao();

    /**
     * @return o preço unitário, em reais, do Vendável.
     */
    float getPrecoEmReais();
}
