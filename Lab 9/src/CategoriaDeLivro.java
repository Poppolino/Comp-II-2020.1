
public enum CategoriaDeLivro {
    ENCICLOPEDIA("ENC"), 
    LIVRO_DIDATICO("LD"), 
    FICCAO("FIC"),
    BIOGRAFIA("BIO"),
    ARTE("AR"), 
    DICIONARIO("DIC"), 
    NAO_FICCAO("NF");
    
    private final String codigo;
    
    CategoriaDeLivro(String codigo){
        this.codigo = codigo;
    }
    
    public String getCodigo(){
        return this.codigo;
    }
}
