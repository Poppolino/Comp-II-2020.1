import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;

public class MapaTest {

    private Random random = new Random();

    private Map<Long, String> mapaUsandoDoisArraysParalelos;
    private Map<Long, String> mapaUsandoArrayUnico;
    private Map<Long, String> mapaUsandoArrayOrdenado;
    private Map<Long, String> hashMap;

    @Before
    public void setUp() {
        mapaUsandoDoisArraysParalelos = new MapaUsandoDoisArraysParalelos<>();
        mapaUsandoArrayUnico = new MapaUsandoArrayUnico<>();
        mapaUsandoArrayOrdenado = new MapaUsandoArrayOrdenado<>();
        hashMap = new HashMap<>();
    }

    @Test
    public void testeFuncionalidadeBasica() {
        rodarTesteDaFuncionalidadeBasica(mapaUsandoArrayUnico);
        rodarTesteDaFuncionalidadeBasica(mapaUsandoArrayOrdenado);
        rodarTesteDaFuncionalidadeBasica(mapaUsandoDoisArraysParalelos);
        rodarTesteDaFuncionalidadeBasica(hashMap);
    }

    private void rodarTesteDaFuncionalidadeBasica(Map<Long, String> mapa) {
        mapa.put(1234L, "Qualquer Coisa");
        mapa.put(2222L, "Outra Coisa Qualquer");

        assertEquals("Outra Coisa Qualquer", mapa.get(2222L));
        assertEquals("Qualquer Coisa", mapa.get(1234L));
        assertNull(mapa.get(8798798L));
    }

    @Test
    public void testeAtualizacaoParaChaveExistente() {
        rodarTesteAtualizacaoParaChaveExistente(mapaUsandoArrayUnico);
        rodarTesteAtualizacaoParaChaveExistente(mapaUsandoArrayOrdenado);
        rodarTesteAtualizacaoParaChaveExistente(mapaUsandoDoisArraysParalelos);
        rodarTesteAtualizacaoParaChaveExistente(hashMap);
    }

    private void rodarTesteAtualizacaoParaChaveExistente(Map<Long, String> mapa) {
        mapa.put(1234L, "Qualquer Coisa");
        mapa.put(1234L, "Qualquer Coisa Modificada");

        assertEquals("Qualquer Coisa Modificada", mapa.get(1234L));
    }

    @Test
    public void testarPerformance() {
        rodarTesteDePerformance(mapaUsandoArrayUnico);
        rodarTesteDePerformance(mapaUsandoArrayOrdenado);
        rodarTesteDePerformance(mapaUsandoDoisArraysParalelos);
        rodarTesteDePerformance(hashMap);
    }

    private void rodarTesteDePerformance(Map<Long, String> mapa) {
        System.out.println("\nRodando teste de performance para a classe " +
                mapa.getClass().getName() + "...");

        final int TAMANHO = 40_000;

        System.out.println("Vou fazer as inserções...");

        long inicio = System.currentTimeMillis();
        for (long i = 0; i < TAMANHO; i++) {
            long x = random.nextInt(1_000_000);
            mapa.put(x, String.format("%d^2 = %d", x, x*x));
        }
        long duracao = System.currentTimeMillis() - inicio;
        System.out.printf("tamanho = %d --- duracao = %.3f segundos\n",
                TAMANHO, duracao / 1000f);

        System.out.println("Vou fazer as buscas...");
        inicio = System.currentTimeMillis();
        for (long i = 0; i < TAMANHO; i++) {
            mapa.get(random.nextLong());
        }
        duracao = System.currentTimeMillis() - inicio;
        System.out.printf("tamanho = %d --- duracao = %.3f segundos\n",
                TAMANHO, duracao / 1000f);
    }
    
    
    @Test
    public void testarTamanhoDoMapa(){
        rodarTesteTamanhoDoMapa(mapaUsandoArrayUnico);
        rodarTesteTamanhoDoMapa(mapaUsandoArrayOrdenado);
    }
    
    private void rodarTesteTamanhoDoMapa(Map<Long, String> mapa){
        //Sanity Check
        assertEquals(0, mapa.size());
        
        mapa.put(123L, "Exemplo");
        assertEquals(1, mapa.size());
        
        mapa.put(345L, "Exemplo");
        assertEquals(2, mapa.size());
        
        mapa.put(345L, "Exemplo");
        assertEquals(2, mapa.size());       
    }
    
    
    @Test
    public void testarMapaVazio(){
        rodarTesteMapaVazio(mapaUsandoArrayUnico);
        rodarTesteMapaVazio(mapaUsandoArrayOrdenado);
    }
    
    private void rodarTesteMapaVazio(Map<Long, String> mapa){
        assertTrue(mapa.isEmpty());
        
        mapa.put(123L, "Preenchi");
        
        assertFalse(mapa.isEmpty());
    }
    
    
    @Test
    public void testarSeContemChave(){
        rodarTesteSeContemChave(mapaUsandoArrayUnico);
        rodarTesteSeContemChave(mapaUsandoArrayOrdenado);
    }
    
    private void rodarTesteSeContemChave(Map<Long, String> mapa){
        //Sanity Check
        assertFalse(mapa.containsKey(0L));
        
        mapa.put(123L, "Tem chave agora");
        
        assertFalse(mapa.containsKey("Quebra?"));    //Testa se passar um objeto de tipo diferente da chave 
        
        assertFalse(mapa.containsKey(678L));
        assertTrue(mapa.containsKey(123L));
    }
    
    
    @Test
    public void testarSeContemValor(){
        rodarTesteSeContemValor(mapaUsandoArrayUnico);
        rodarTesteSeContemValor(mapaUsandoArrayOrdenado);
    }
    
    private void rodarTesteSeContemValor(Map<Long, String> mapa){
        //Sanity Check
        assertFalse(mapa.containsValue("Teste"));
        
        mapa.put(123L, "Tem valor agora");
        
        assertFalse(mapa.containsValue(1000L));    //Testa se passar um objeto de tipo diferente do valor 
        
        assertFalse(mapa.containsValue("Algo inexistente"));
        assertTrue(mapa.containsValue("Tem valor agora"));
    }
    
    
    @Test
    public void testarRetornoDoPut(){
        rodarTesteRetornoDoPut(mapaUsandoArrayUnico);
        rodarTesteRetornoDoPut(mapaUsandoArrayOrdenado);
    }
    
    private void rodarTesteRetornoDoPut(Map<Long, String> mapa){
        assertNull(mapa.put(123L, "Primeiro Valor"));
        
        assertEquals("Primeiro Valor", mapa.put(123L, "Novo Valor"));
    }
    
    
    @Test
    public void testarRemoverElemento(){
        rodarTesteRemoverElemento(mapaUsandoArrayUnico);
        rodarTesteRemoverElemento(mapaUsandoArrayOrdenado);
    }
    
    private void rodarTesteRemoverElemento(Map<Long, String> mapa){
        assertNull(mapa.remove(123L));    //Tenta remover algo que não existe
        
        mapa.put(247L, "Qualquer coisa");
        
        assertEquals("Qualquer coisa", mapa.remove(247L));
        assertTrue(mapa.isEmpty());
    }
    
    
    @Test
    public void testarLimparMapa(){
        rodarTesteLimparMapa(mapaUsandoArrayUnico);
        rodarTesteLimparMapa(mapaUsandoArrayOrdenado);
    }
    
    private void rodarTesteLimparMapa(Map<Long, String> mapa){
        final int TAMANHO = 1_000;
        
        for(long i = 1; i<=TAMANHO; i++){
            mapa.put(i, "Elemento " + i);
        }
        
        assertEquals(TAMANHO, mapa.size());
        mapa.clear();
        assertEquals(0, mapa.size());
    }
}