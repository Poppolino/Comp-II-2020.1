import java.util.Random;

public class Dado implements Sorteador {
    public static final int FACES = 6;
    private static final Random SORTEIA = new Random();
    
    /**
     * @return um n�mero qualquer de 1 ao n�mero total de FACES
     */
    public int sortear() {
        int numero = SORTEIA.nextInt(FACES) + 1;
        
        return numero;
    }
}
