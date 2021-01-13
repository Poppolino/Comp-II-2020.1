import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlbumTest {

    Album album;

    private static final int TAMANHO_DO_ALBUM = 200;
    private static final int FIGURINHAS_POR_PACOTE = 3;

    @Before  // roda antes de cada teste
    public void setUp() {
        this.album = new Album(TAMANHO_DO_ALBUM, FIGURINHAS_POR_PACOTE);
    }

    private void popularAlbum(int[] posicoesDesejadas) {
        Pacotinho pacote = new Pacotinho(this.album, posicoesDesejadas);
        this.album.receberNovoPacotinho(pacote);
    }

    @Test
    public void testarPossuiFigurinhaParaFigurinhasExistentes() {
        popularAlbum(new int[] {1, 2, 3});

        for (int i = 1; i <= FIGURINHAS_POR_PACOTE; i++) {
            assertTrue("Figurinhas já inseridas devem ser encontradas",
                    this.album.possuiFigurinhaColada(i));
        }
    }

    @Test
    public void testarPossuiFigurinhaParaFigurinhasAusentes() {
        popularAlbum(new int[] {1, 2, 3});

        assertFalse("Não devemos encontrar no álbum figurinhas nunca inseridas",
                this.album.possuiFigurinhaColada(4));
        assertFalse("Não devemos encontrar figurinhas de posições não-positivas",
                this.album.possuiFigurinhaColada(-390));
        assertFalse("Não devemos encontrar figurinhas maiores do que o tamanho",
                this.album.possuiFigurinhaColada(TAMANHO_DO_ALBUM + 1));
    }

    @Test
    public void testarPossuiRepetidaParaFigurinhaRepetida() {
        popularAlbum(new int[] {1, 2, 3});

        assertFalse(this.album.possuiFigurinhaRepetida(2));  // sanity check

        popularAlbum(new int[] {2, 8, 9});
        assertTrue(this.album.possuiFigurinhaRepetida(2));
    }

    @Test
    public void testarGetTamanhoAlbum() {
        assertEquals(TAMANHO_DO_ALBUM, this.album.getTamanho());
    }

    @Test
    public void testarGetContFigurinhas() {
        popularAlbum(new int[] {1, 2, 3});
        assertEquals(FIGURINHAS_POR_PACOTE, this.album.getQuantFigurinhasColadas());

        // vou agora abrir outro pacotinho com as mesmas figurinhas
        // e verificar que o álbum terá ainda 3 figurinhas

        popularAlbum(new int[] {1, 2, 3});
        assertEquals(FIGURINHAS_POR_PACOTE, this.album.getQuantFigurinhasColadas());
    }

    @Test
    public void testarGetQuantasFaltam() {
        popularAlbum(new int[] {1, 2, 3});
        assertEquals(TAMANHO_DO_ALBUM - FIGURINHAS_POR_PACOTE,
                this.album.getQuantFigurinhasFaltantes());
    }

    @Test
    public void testarAutoCompletar() {
        album.autoCompletar();
        assertEquals("Não deve ser possível auto-completar um álbum que esteja vazio",
                TAMANHO_DO_ALBUM, album.getQuantFigurinhasFaltantes());

        // agora vamos adicionar pacotinhos aleatórios até o álbum ficar quase cheio

        int minimoFigurinhasColadasParaAutoCompletar =
                (int) (TAMANHO_DO_ALBUM * Album.PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR / 100f);

        while (album.getQuantFigurinhasColadas() < minimoFigurinhasColadasParaAutoCompletar) {
            Pacotinho novoPacotinho = new Pacotinho(this.album);  // aleatório
            album.receberNovoPacotinho(novoPacotinho);
        }

        // sanity check
        assertTrue(album.getQuantFigurinhasFaltantes() > 0);

        album.autoCompletar();

        assertEquals(0, album.getQuantFigurinhasFaltantes());  // álbum completo!

    }

}
