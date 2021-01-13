import java.util.ArrayList;
import java.util.Random;

public class Pacotinho extends ArrayList<Figurinha> {

    private Album album;

    public Pacotinho(Album album) {
        this.album = album;
        adicionarFigurinhasAleatorias();
    }

    // sobrecarga no costrutor, passando aqui as posi��es desejadas
    public Pacotinho(Album album, int[] posicoes) {
        this.album = album;
        int maxPosicao = album.getTamanho();
        int quantFigurinhasPorPacotinho = album.getQuantFigurinhasPorPacotinho();
        
        if(posicoes.length != quantFigurinhasPorPacotinho){             // verifica se o tamanho do array est� correto
           throw new RuntimeException("Pacotinho no tamanho errado!");
        }
        
        for(int i=0; i<posicoes.length; i++){
            if(posicoes[i] < 1 || posicoes[i] > maxPosicao){           // verifica se a figurinha tem numera��o v�lida
                throw new RuntimeException("O n�mero " + posicoes[i] + " � inv�lido, n�o pode ser figurinha nesse �lbum!");
            }
            
            Figurinha figurinha = new Figurinha(posicoes[i]);
            this.add(figurinha);
        }
    }

    
    /**
     * Gera figurinhas aleat�rias que v�o compor o novo pacotinho
     */
    private void adicionarFigurinhasAleatorias() {
        int maxPosicao = this.album.getTamanho();
        int quantFigurinhasPorPacotinho = this.album.getQuantFigurinhasPorPacotinho();
        Random sorteia = new Random();
        
        for (int i = 1; i <= quantFigurinhasPorPacotinho; i++) {
            int posicao = sorteia.nextInt(maxPosicao) + 1;            //sorteia uma posi��o entre 1 e o tamanho do �lbum

            Figurinha figurinha = new Figurinha(posicao);
            this.add(figurinha);                                      //Cria figurinha e adiciona ao pacotinho
        }
    }

    
    /**
     * @return O �lbum ao qual o pacotinho est� associado
     */
    public Album getAlbum() {
        return this.album;
    }
}
