import java.util.ArrayList;

public class Album {

    public static final int PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR = 90;  // 90%
    
    private int tamanhoDoAlbum;
    private int quantFigurinhasColadas;
    private int quantFigurinhasPorPacotinho;
    private Figurinha[] figurinhasColadas; 
    private ArrayList<Figurinha> figurinhasRepetidas;
    

    public Album(int tamanhoDoAlbum, int quantFigurinhasPorPacotinho) {
        if(tamanhoDoAlbum <= 0){                                        // verifica se o tamanho é consistente
            throw new RuntimeException("O álbum deve ter pelo menos uma figurinha!");
        }
        
        if(quantFigurinhasPorPacotinho <= 0){                           // verifica se a quantidade de figurinhas faz sentido
            throw new RuntimeException("O pacote deve ter no mínimo uma figurinha!");
        }
        
        this.tamanhoDoAlbum = tamanhoDoAlbum;
        this.quantFigurinhasColadas = 0;
        this.quantFigurinhasPorPacotinho = quantFigurinhasPorPacotinho;
        this.figurinhasColadas = new Figurinha[tamanhoDoAlbum+1];
        this.figurinhasRepetidas = new ArrayList<>();
    }

    
    /**
     * Recebe novo pacote de figurinhas e cola elas no álbum ou bota no monte de repetidas 
     * @param pacotinho
     */
    public void receberNovoPacotinho(Pacotinho pacotinho) {
        for (Figurinha fig : pacotinho) {
            int posicao = fig.getPosicao();
            
            if(possuiFigurinhaColada(posicao)){              // Se já foi colada, vai para o monte das repetidas
                this.figurinhasRepetidas.add(fig);
                continue;
            }
            
            this.figurinhasColadas[posicao] = fig;
            this.quantFigurinhasColadas++;
        }
    }
    

    /**
     * Preenche o álbum com as figurinhas que faltam para completá-lo
     */
    public void autoCompletar() {
        int completo = (this.quantFigurinhasColadas*100) / this.tamanhoDoAlbum; 
        
        if(completo < PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR){           //Se não atingiu o percentual mínimo, não pode autocompletar
            return;
        }

        for(int pos=1; pos<=this.tamanhoDoAlbum; pos++){                //Adiciona todas as figurinhas faltantes
            if(this.figurinhasColadas[pos] == null){
                this.figurinhasColadas[pos] = new Figurinha(pos); 
            }
        }
    
        this.quantFigurinhasColadas = this.tamanhoDoAlbum;
    }
    

    /**
     * Verifica se uma figurinha já foi colada no álbum
     * @param posicao
     * @return verdadeiro se a figurinha está colada, ou falso caso contrário
     */
    public boolean possuiFigurinhaColada(int posicao) {
        if(posicao < 1 || posicao > this.tamanhoDoAlbum){
            return false;
        }
        
        return (this.figurinhasColadas[posicao] != null);
    }

    
    /**
     * Verifica se possui uma certa figurinha repetida
     * @param posicao
     * @return verdadeiro se tem uma repetida, ou falso caso contrário
     */
    public boolean possuiFigurinhaRepetida(int posicao) {
        if(posicao < 1 || posicao > this.tamanhoDoAlbum){
            return false;
        }
        
        return figurinhasRepetidas.contains(new Figurinha(posicao));
    }

    
    /**
     * @return tamanho do álbum
     */
    public int getTamanho() {
        return this.tamanhoDoAlbum;
    }

    /**
     * @return quantidade de figurinhas por pacote do álbum  
     */
    public int getQuantFigurinhasPorPacotinho() {
        return this.quantFigurinhasPorPacotinho;
    }
    
    /**
     * @return quantidade de figurinhas coladas no álbum
     */
    public int getQuantFigurinhasColadas() {
        return this.quantFigurinhasColadas;
    }

    /**
     * @return a quantidade de figurinhas que são repetidas
     */
    public int getQuantFigurinhasRepetidas() {
        return this.figurinhasRepetidas.size();
    }

    /**
     * @return a quantidade de figurinhas que faltam para completar o álbum
     */
    public int getQuantFigurinhasFaltantes() {
        return this.tamanhoDoAlbum - this.quantFigurinhasColadas;
    }
}
