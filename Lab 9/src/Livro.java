import java.util.Objects;

public class Livro extends ArtigoCultural {

    private final int codigoISBN;

    private String titulo;

    private String autor;

    private String editora;

    private int anoPublicacao;

    private int numeroDePaginas;
    
    private CategoriaDeLivro categoria;
    
    
    public Livro(int codigoISBN, String titulo, String autor, 
            CategoriaDeLivro categoria, String editora, int anoPublicacao) {

        super(codigoISBN,
                String.format("Livro: %s (%s, %d)",
                titulo, autor, anoPublicacao));

        this.codigoISBN = codigoISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
    }

    
    @Override 
    public String toString(){
        return super.toString() + " [" + this.categoria.getCodigo() + "]";
    }
    
    
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        
        if(o == null || this.getClass() != o.getClass()){
            return false;
        }
        
        Livro outroLivro = (Livro) o;
        
        return (this.codigoISBN == outroLivro.codigoISBN) 
                && (this.categoria == outroLivro.categoria);
    }
    
    
    @Override
    public int hashCode(){
        return Objects.hash(this.codigoISBN, this.categoria);
    }
    
    
    public int getCodigoISBN() {
        return codigoISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public CategoriaDeLivro getCategoria(){
        return this.categoria;
    }
    
    public void setCategoria(CategoriaDeLivro categoria) {
        this.categoria = categoria;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public void setNumeroDePaginas(int numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }
}
