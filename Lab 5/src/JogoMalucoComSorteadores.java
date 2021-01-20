
public class JogoMalucoComSorteadores extends JogoDeDoisJogadores {
    
    private final Sorteador sorteador1;
    private final Sorteador sorteador2;
    
    public JogoMalucoComSorteadores(String nomeJogo, String nomeJogador1, String nomeJogador2, int numeroDeRodadas, Sorteador sorteador1, Sorteador sorteador2) {       
        super(nomeJogo, nomeJogador1, nomeJogador2, numeroDeRodadas);
        
        this.sorteador1 = sorteador1;
        this.sorteador2 = sorteador2;
    }
    
    
    @Override
    protected int executarRodadaDoJogo(){
        int valorJog1 = this.sorteador1.sortear();
        int valorJog2 = this.sorteador2.sortear();
        
        if(valorJog1 > valorJog2){
            return 1;
        }
        
        if(valorJog2 > valorJog1){
            return 2;
        }
        
        return 0;
    }
}