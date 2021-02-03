package Banco;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BancoTest {
    static final int TAMANHO = 50_000;
    
    private Banco banco;
    private BancoUsandoMapa bancoUsandoMapa;

    @Before
    public void setUp() {
        banco = new Banco();
        bancoUsandoMapa = new BancoUsandoMapa(); 
    }

    @Test
    public void rodarTesteDePerformanceBanco() {
        System.out.println("\nRodando teste de performance para a classe Banco...");

        System.out.println("Vou fazer os cadastros de correntistas...");

        long inicio = System.currentTimeMillis();
        for (long i = 0; i < TAMANHO; i++) {
            banco.cadastrarCorrentista("Correntista " + i, i);
        }
        long duracao = System.currentTimeMillis() - inicio;
        System.out.printf("tamanho = %d --- duracao = %.3f segundos\n",
                TAMANHO, duracao / 1000f);

        
        System.out.println("Vou fazer as buscas por correntistas...");
        
        inicio = System.currentTimeMillis();
        for (long i = 0; i < TAMANHO; i++) {
            banco.localizarCorrentista(i);
        }
        duracao = System.currentTimeMillis() - inicio;
        System.out.printf("tamanho = %d --- duracao = %.3f segundos\n",
                TAMANHO, duracao / 1000f);
    }
    
    
    @Test
    public void rodarTesteDePerformanceBancoUsandoMapa() {
        System.out.println("\nRodando teste de performance para a classe BancoUsandoMapa...");

        System.out.println("Vou fazer os cadastros de correntistas...");

        long inicio = System.currentTimeMillis();
        for (long i = 0; i < TAMANHO; i++) {
            bancoUsandoMapa.cadastrarCorrentista("Correntista " + i, i);
        }
        long duracao = System.currentTimeMillis() - inicio;
        System.out.printf("tamanho = %d --- duracao = %.3f segundos\n",
                TAMANHO, duracao / 1000f);

        
        System.out.println("Vou fazer as buscas por correntistas...");
        
        inicio = System.currentTimeMillis();
        for (long i = 0; i < TAMANHO; i++) {
            bancoUsandoMapa.localizarCorrentista(i);
        }
        duracao = System.currentTimeMillis() - inicio;
        System.out.printf("tamanho = %d --- duracao = %.3f segundos\n",
                TAMANHO, duracao / 1000f);
    }

}
