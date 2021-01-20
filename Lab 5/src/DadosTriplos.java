
public class DadosTriplos implements Sorteador {
    
    private static final Dado DADO = new Dado();  
    
    /**
     * @return a soma dos valores sorteados por 3 dados
     */
    public int sortear() {
        int valor1 = DADO.sortear();
        int valor2 = DADO.sortear();
        int valor3 = DADO.sortear();
        
        return valor1 + valor2 + valor3;
    }
}
