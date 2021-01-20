import java.util.ArrayList;

public abstract class JogoDeDoisJogadores {
    private String nomeJogo;
    private String nomeJogador1;
    private String nomeJogador2;
    private int numeroDeRodadas;
    private ArrayList<Integer> historicoResultados;
    
    public JogoDeDoisJogadores(String nomeJogo, String nomeJogador1, String nomeJogador2, int numeroDeRodadas) {
        if(numeroDeRodadas < 1){
            throw new RuntimeException("Quantidade inválida de rodadas! O número de rodadas deve ser maior que zero.");
        }
        
        this.nomeJogo = nomeJogo;
        this.nomeJogador1 = nomeJogador1;
        this.nomeJogador2 = nomeJogador2;
        this.numeroDeRodadas = numeroDeRodadas;
        this.historicoResultados = new ArrayList<>();
    }
    
    
    /**
     * @return status de empate ou de vitória para um jogador depois de ocorrer uma rodada do jogo
     */
    protected abstract int executarRodadaDoJogo();
    
    
    /**
     * Todas as rodadas do jogo são disputadas, e o total de rodadas ganhas por cada jogador é contabilizado. 
     * 
     * @return o resultado do jogo: se houve empate ou se um jogador venceu mais rodadas que o outro.
     */
    public String jogar() {
        int vitoriaRodada, vitoriaJog1 = 0, vitoriaJog2 = 0;
        
        for(int rodada=0; rodada < this.numeroDeRodadas; rodada++){
            vitoriaRodada = executarRodadaDoJogo();
            
            if(vitoriaRodada == 1){
                vitoriaJog1++;
            }
            else if(vitoriaRodada == 2){
                vitoriaJog2++;
            }
        }
        
        if(vitoriaJog1 > vitoriaJog2){
            return String.format("O jogador %s venceu o jogo por %d a %d.", 
                                 this.nomeJogador1, vitoriaJog1, vitoriaJog2);
        }
        
        if(vitoriaJog2 > vitoriaJog1){
            return String.format("O jogador %s venceu o jogo por %d a %d.", 
                                 this.nomeJogador2, vitoriaJog2, vitoriaJog1);
        }
        
        return String.format("O jogo terminou em empate após %d rodadas.", this.numeroDeRodadas);
    }
    
    
    /**
     * @return o nome do jogo
     */
    public String getNomeJogo() {
        return this.nomeJogo;
    }
    
    /**
     * @return o nome do Jogador 1
     */
    public String getNomeJogador1() {
        return this.nomeJogador1;
    }
    
    /**
     * @return o nome do Jogador 2
     */
    public String getNomeJogador2() {
        return this.nomeJogador2;
    }
    
    /**
     * @return o número de rodadas do jogo
     */
    public int getNumeroDeRodadas() {
        return this.numeroDeRodadas;
    }
}
