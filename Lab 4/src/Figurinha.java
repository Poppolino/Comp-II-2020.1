public class Figurinha {
    
    private int posicao;
    private String urlDaImagem;

    public Figurinha(int posicao) {
        this.posicao = posicao;
    }

    
    /**
     * @return A posição que esta figurinha deve ocupar no álbum
     */
    public int getPosicao() {
       return this.posicao;
    }
    
    
    /**
     * @return A url da imagem da figurinha
     */
    public String getUrlDaImagem() {
       return this.urlDaImagem;
    }
    
    
    /**
     * Atualiza a url da imagem da figurinha
     */
    public void setUrlDaImagem(String novaUrl)  {
        this.urlDaImagem = novaUrl;
    }
    
    @Override
    public boolean equals(Object o){
        if(o == null || this.getClass() != o.getClass()){
            return false;
        }
        
        Figurinha figurinha = (Figurinha) o;
        
        return (this.posicao == figurinha.posicao);
    }
}
