import java.util.ArrayList;
import java.util.Random;

public class Pacotinho extends ArrayList<Figurinha> {

    private Album album;

    public Pacotinho(Album album) {
        this.album = album;
        adicionarFigurinhasAleatorias();
    }

    // sobrecarga no costrutor, passando aqui as posições desejadas
    public Pacotinho(Album album, int[] posicoes) {
        this.album = album;
        int maxPosicao = album.getTamanho();
        int quantFigurinhasPorPacotinho = album.getQuantFigurinhasPorPacotinho();
        
        if(posicoes.length != quantFigurinhasPorPacotinho){             // verifica se o tamanho do array está correto
            throw new RuntimeException("Pacotinho no tamanho errado!");
        }
        
        for(int i=0; i<posicoes.length; i++){
            if(posicoes[i] < 1 || posicoes[i] > maxPosicao){           // verifica se a figurinha tem numeração válida
                throw new RuntimeException("O número " + posicoes[i] + " é inválido, não pode ser figurinha nesse álbum!");
            }
            
            Figurinha figurinha = new Figurinha(posicoes[i]);
            this.add(figurinha);
        }
    }

    
    /**
     * Gera figurinhas aleatórias que vão compor o novo pacotinho
     */
    private void adicionarFigurinhasAleatorias() {
        int maxPosicao = this.album.getTamanho();
        int quantFigurinhasPorPacotinho = this.album.getQuantFigurinhasPorPacotinho();
        Random sorteia = new Random();
        
        for (int i = 1; i <= quantFigurinhasPorPacotinho; i++) {
            int posicao = sorteia.nextInt(maxPosicao) + 1;            //sorteia uma posição entre 1 e o tamanho do álbum

            Figurinha figurinha = new Figurinha(posicao);
            this.add(figurinha);                                      //Cria figurinha e adiciona ao pacotinho
        }
    }

    
    /**
     * @return O álbum ao qual o pacotinho está associado
     */
    public Album getAlbum() {
        return this.album;
    }
}
