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
    
    /**
     * Além dos métodos acima, todo vendável precisa sobrescrever
     * o método equals e o hashCode de Object para que a busca por 
     * um objeto vendável numa estrutura de dados hash possa ocorrer. 
     */ 
 }
