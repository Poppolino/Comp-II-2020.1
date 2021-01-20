
public class DadosDeGamao implements Sorteador {
    
    private static final Dado DADO = new Dado();  
    
    /**
     * @return a soma dos valores sorteados por 2 dados, ou 2 vezes o valor dessa soma, caso os dados tenham o mesmo valor
     */
    public int sortear() {
        int valor1 = DADO.sortear();
        int valor2 = DADO.sortear();
        
        if(valor1 == valor2){
            return 4 * valor1;
        }
        
        return valor1 + valor2;
    }
}
