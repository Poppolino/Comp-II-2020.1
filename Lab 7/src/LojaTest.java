import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LojaTest {

    Loja loja;
    Livro livro1, novoLivro1;
    Livro livro2, novoLivro2;
    CD cd1, novoCD;
    Bicicleta bicicleta1, novaBicicleta;
    Usuario comprador;
    Transportadora gatoPreto;
    ImpressoraJatoDeTinta impressoraJatoDeTinta1;
    Grafica grafica1;

    @Before
    public void setUp() {
        gatoPreto = new Transportadora();
        impressoraJatoDeTinta1 = new ImpressoraJatoDeTinta("HP", 2018);
        grafica1 = new Grafica();

        loja = new Loja(
                gatoPreto,  // informamos à loja qual a transportadora que ela vai usar (agregação)
                impressoraJatoDeTinta1);  // ...e o serviço de impressão que ela vai usar (agregação tb)


        livro1 = new Livro(12345, "Da Terra à Lua", "Julio Verne", null, 1865);
        livro1.setPrecoEmReais(25);

        livro2 = new Livro(12446, "Dom Quixote", "Miguel de Cervantes", null, 1605);
        livro2.setPrecoEmReais(42.30f);

        cd1 = new CD(121223, "Ride The Lightning", "Metallica", 1985);
        cd1.setPrecoEmReais(18.50f);

        bicicleta1 = new Bicicleta(9999, 700, "Pinarello");
        bicicleta1.setPrecoEmReais(580);

        loja.incluirItem(livro1);
        loja.incluirItem(livro2);
        loja.incluirItem(cd1);
        loja.incluirItem(bicicleta1);

        comprador = new Usuario(111111, "Maria");
        comprador.setEndereco("Rua Tal, Numero Tal");
    }
    
    private void chegadaDoEstoque(){        
        this.loja.chegadaDeMaisProdutosDoItem(cd1, 50);
        this.loja.chegadaDeMaisProdutosDoItem(livro1, 20);
        this.loja.chegadaDeMaisProdutosDoItem(livro2, 100);
        this.loja.chegadaDeMaisProdutosDoItem(bicicleta1, 10);
        
        this.loja.chegadaDeMaisProdutosDoItem(novoCD, 20);
        this.loja.chegadaDeMaisProdutosDoItem(novoLivro1, 80);
        this.loja.chegadaDeMaisProdutosDoItem(novoLivro2, 50);
        this.loja.chegadaDeMaisProdutosDoItem(novaBicicleta, 10);
    }
    
    
    @Test
    public void testarVendaParaProdutoExistente() {
        this.novoCD = new CD(121223, "Ride The Lightning", "Metaaaaallica", 1984);
        this.novoLivro1 = new Livro(12345, "Da Terra à Lua: atualizado", "Julio Verne", "Boa editora", 1865);
        this.novoLivro2 = new Livro(12446, "Dom Quixote: o inimigo agora e outro", "Miguel de Cervantes", "Editora Espetacular", 1605);
        this.novaBicicleta = new Bicicleta(9999, 700, "Poppolino");
        
        chegadaDoEstoque();
        
        String recibo = loja.receberPedido(this.novoLivro1, 10, comprador);
        assertNotNull(recibo);
        System.out.println(recibo);
        
        recibo = loja.receberPedido(novoLivro2, 5, comprador);
        assertNotNull(recibo);
        System.out.println(recibo);

        recibo = loja.receberPedido(novoCD, 1, comprador);
        assertNotNull(recibo);
        System.out.println(recibo);

        recibo = loja.receberPedido(novaBicicleta, 3, comprador);
        assertNotNull(recibo);
        System.out.println(recibo);
    }

    
    @Test
    public void testarConsultaDaQuantidadeEmEstoque(){
        this.novoCD = new CD(121223, "Ride The Lightning", "Metaaaaallica", 1984);
        this.novoLivro1 = new Livro(12345, "Da Terra à Lua: atualizado", "Julio Verne", "Boa editora", 1865);
        this.novoLivro2 = new Livro(12446, "Dom Quixote: o inimigo agora e outro", "Miguel de Cervantes", "Editora Espetacular", 1605);
        this.novaBicicleta = new Bicicleta(9999, 700, "Poppolino");
        
        // Sanity Checks
        assertEquals(0, loja.getQuantidadeEmEstoqueDoItem(novoCD));
        assertEquals(0, loja.getQuantidadeEmEstoqueDoItem(novoLivro1));
        assertEquals(0, loja.getQuantidadeEmEstoqueDoItem(novoLivro2));
        assertEquals(0, loja.getQuantidadeEmEstoqueDoItem(novaBicicleta));
        
        chegadaDoEstoque();
        
        // Checa se os estoques foram feitos de forma adequada
        assertEquals(70, loja.getQuantidadeEmEstoqueDoItem(novoCD));
        assertEquals(100, loja.getQuantidadeEmEstoqueDoItem(novoLivro1));
        assertEquals(150, loja.getQuantidadeEmEstoqueDoItem(novoLivro2));
        assertEquals(20, loja.getQuantidadeEmEstoqueDoItem(novaBicicleta));
    }
    
    
    @Test
    public void testarControleDoEstoqueNosPedidos(){
        this.novoCD = new CD(121223, "Ride The Lightning", "Metaaaaallica", 1984);
        this.novoLivro1 = new Livro(12345, "Da Terra à Lua: atualizado", "Julio Verne", "Boa editora", 1865);
        this.novoLivro2 = new Livro(12446, "Dom Quixote: o inimigo agora e outro", "Miguel de Cervantes", "Editora Espetacular", 1605);
        this.novaBicicleta = new Bicicleta(9999, 700, "Poppolino");
        
        chegadaDoEstoque();
        
        // Checa tentativa de pedir valor inconsistente (menor que zero)
        assertNull(loja.receberPedido(novoLivro2, -1, comprador));
        
        // Testa pedir quantidades superiores às que existem no estoque
        assertNull(loja.receberPedido(novoCD, 80, comprador));
        assertNull(loja.receberPedido(novoLivro1, 101, comprador));
        assertNull(loja.receberPedido(novoLivro2, 300, comprador));
        assertNull(loja.receberPedido(novaBicicleta, 27, comprador));
    
        
        CD novoCD2 = new CD(121223, "Ride The Lightning", "Metal na veia", 1984);
        Bicicleta novaBicicleta2 = new Bicicleta(9999, 700, "Lettieri");
        
        // Testa se os itens são descontados corretamente do estoque após a compra
        assertNotNull(loja.receberPedido(novoCD2, 20, comprador));
        assertNotNull(loja.receberPedido(novaBicicleta2, 20, comprador));
        
        assertEquals(50, loja.getQuantidadeEmEstoqueDoItem(novoCD2));
        assertEquals(0, loja.getQuantidadeEmEstoqueDoItem(novaBicicleta2));
    }
    
    
    @Test
    public void testarVendaParaProdutoNaoExistente() {
        Livro livroNaoExistente = new Livro(1010101, "Blah", "Qualquer coisa", null, 2000);
        String recibo = loja.receberPedido(livroNaoExistente, 5, comprador);
        assertNull(recibo);
    }

}