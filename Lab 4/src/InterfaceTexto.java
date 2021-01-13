public class InterfaceTexto {


    /**
     * Main apenas para testes muito rápidos. Melhor seria escrever unit tests de verdade.
     * Na prática, o seu "main()" abriria uma interface de texto de verdade,
     * apresentando um menu, etc.
     *
     * @param args
     */
    public static void main(String[] args) {

        Album album = new Album(200, 3);

        Pacotinho p1 = new Pacotinho(album, new int[] {1, 6, 18});

        album.receberNovoPacotinho(p1);

        System.out.println("Possui figurinha 1? " + album.possuiFigurinhaColada(1));
        System.out.println("Possui figurinha 6? " + album.possuiFigurinhaColada(6));
        System.out.println("Possui figurinha 18? " + album.possuiFigurinhaColada(18));
        System.out.println("Possui figurinha 60? " + album.possuiFigurinhaColada(60));

        System.out.println("Quant figurinhas coladas? " + album.getQuantFigurinhasColadas());

        Pacotinho p2 = new Pacotinho(album, new int[] {3, 6, 44});
        album.receberNovoPacotinho(p2);

        System.out.println("Tem figurinha repetida 6? " + album.possuiFigurinhaRepetida(6));
        System.out.println("Quant figurinhas coladas? " + album.getQuantFigurinhasColadas());
    }
}
