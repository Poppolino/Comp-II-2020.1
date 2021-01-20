
public class Principal {
    
    /* 8) O melhor sorteador, indiscutivelmente, é o que se utiliza de Dados Triplos. 
     *    Já daria para ter uma noção disso antes de rodar o programa, já que Dados Triplos somam um dado a mais ao seu valor em relação aos de Gamão (no geral). 
     *    Em alguns casos (1/36 de chance), Dados de Gamão utilizam "4 dados" de mesmo valor, o que dá uma vantagem nesses casos. 
     *    Ainda assim, esses casos de vantagem dos Dados de Gamão tendem a ocorrer bem menos que o caso geral.
     *    
     *    Por fim, ainda analisando os resultados, podemos perceber que quanto maior o número de rodadas, menor é a chance do jogador mais fraco vencer. 
     * */
    
    public static void main(String args[]){
        JogoMalucoComSorteadores jogo;
        
        String nomeJogo = "Jogo Tendencioso de Dados";
        String jogador1 = "Poppolino";
        String jogador2 = "Gusmão";
        
        DadosDeGamao sorteador1 = new DadosDeGamao();
        DadosTriplos sorteador2 =  new DadosTriplos();
        
        for(int numeroRodadas=1; numeroRodadas<=100; numeroRodadas++){
            jogo = new JogoMalucoComSorteadores(nomeJogo, jogador1, jogador2, numeroRodadas, sorteador1, sorteador2);
            
            System.out.println(jogo.jogar());
        }
    }
}
