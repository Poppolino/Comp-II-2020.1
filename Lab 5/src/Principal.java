
public class Principal {
    
    /* 8) O melhor sorteador, indiscutivelmente, � o que se utiliza de Dados Triplos. 
     *    J� daria para ter uma no��o disso antes de rodar o programa, j� que Dados Triplos somam um dado a mais ao seu valor em rela��o aos de Gam�o (no geral). 
     *    Em alguns casos (1/36 de chance), Dados de Gam�o utilizam "4 dados" de mesmo valor, o que d� uma vantagem nesses casos. 
     *    Ainda assim, esses casos de vantagem dos Dados de Gam�o tendem a ocorrer bem menos que o caso geral.
     *    
     *    Por fim, ainda analisando os resultados, podemos perceber que quanto maior o n�mero de rodadas, menor � a chance do jogador mais fraco vencer. 
     * */
    
    public static void main(String args[]){
        JogoMalucoComSorteadores jogo;
        
        String nomeJogo = "Jogo Tendencioso de Dados";
        String jogador1 = "Poppolino";
        String jogador2 = "Gusm�o";
        
        DadosDeGamao sorteador1 = new DadosDeGamao();
        DadosTriplos sorteador2 =  new DadosTriplos();
        
        for(int numeroRodadas=1; numeroRodadas<=100; numeroRodadas++){
            jogo = new JogoMalucoComSorteadores(nomeJogo, jogador1, jogador2, numeroRodadas, sorteador1, sorteador2);
            
            System.out.println(jogo.jogar());
        }
    }
}
