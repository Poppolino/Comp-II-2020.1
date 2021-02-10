import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


public class Loja {

    private static final int NUMERO_DE_PEDIDOS_POR_RELATORIO = 1;

    private String nomeDaLoja;

    private ArrayList<Vendavel> catalogo;

    private Map<Vendavel, Integer> quantidadeEmEstoquePorItem;

    private Transportadora frete;

    private Impressora impressora;

    private long quantPedidosRecebidos;

    public Loja(Transportadora transportadora,
                Impressora impressora) {

        catalogo = new ArrayList<>();  // COMPOSIÇÃO
        quantidadeEmEstoquePorItem = new HashMap<>();      // COMPOSIÇÃO
        setFrete(transportadora);      // AGREGAÇÃO
        this.impressora = impressora;  // AGREGAÇÃO
        this.quantPedidosRecebidos = 0;
    }

    public void setFrete(Transportadora transportadora) {
        frete = transportadora;
    }

    public String receberPedido(Vendavel item, int quantidade, Usuario usuario) {

        // verifica se existe no catálogo da loja
        if (buscarItem(item.getId()) == null) {
            // ToDo lançar uma exceção específica
            return null;
        }
        
        // verifica se a quantidade pedida é consistente, impedindo valores negativos
        if(quantidade < 0){
            // ToDo lançar uma exceção específica
            return null;
        }
        
        // verifica se existe aquela quantidade do produto desejado
        // no estoque da loja
        if (quantidadeEmEstoquePorItem.get(item) < quantidade) {
            // ToDo lançar uma exceção específica
            return null;
        }

        // verifica se o usuário tem um endereço de entrega válido
        if (usuario.getEndereco() == null) {
            // Todo lançar uma exceção específica
            return null;
        }

        float precoTotal = quantidade * item.getPrecoEmReais();

        if (!processarPagamento(precoTotal)) {
            // ToDo lançar uma exceção específica
            return null;
        }
        
        // Abate a quantidade vendida do item pedido
        int quantidadeAnterior = this.quantidadeEmEstoquePorItem.get(item);
        this.quantidadeEmEstoquePorItem.put(item, quantidadeAnterior - quantidade);

        if (item instanceof Transportavel) {  // é transportável?
            // cria um array com todos os itens que precisarão ser entregues
            // (possivelmente várias unidades do mesmo item)
            ArrayList<Transportavel> pedido = new ArrayList<>();
            for (int i = 0; i < quantidade; i++) {
                pedido.add((Transportavel) item);
            }
            frete.transportar(pedido, usuario.getEndereco());
        }

        if (++this.quantPedidosRecebidos % NUMERO_DE_PEDIDOS_POR_RELATORIO == 0) {
            imprimirRelatorioUltimasVendas();
        }

        String recibo = String.format("Recibo no valor de R$%.2f referente à " +
                "compra de %d unidades do item: %s",
                precoTotal, quantidade, item);

        return recibo;
    }

    private void imprimirRelatorioUltimasVendas() {
        String relatorio = ".................To Do...............";
        this.impressora.imprimir(relatorio);
    }
    
    
    /**
     * Inclui um novo item no catálogo da loja, assim como inicia seu estoque (com nenhum produto).
     * 
     * @param vendavel
     */
    public void incluirItem(Vendavel vendavel) {
        if (buscarItem(vendavel.getId()) != null) {
            // produto já existe no catálogo -- nada a fazer
            return;
        }
        
        this.catalogo.add(vendavel);
        this.quantidadeEmEstoquePorItem.put(vendavel, 0);
    }

    /**
     * Adiciona mais produtos no estoque de determinado item.
     * 
     * @param item
     * @param quantidade
     */
    public void chegadaDeMaisProdutosDoItem(Vendavel item, int quantidade){
        int quantidadeEmEstoque = this.quantidadeEmEstoquePorItem.getOrDefault(item, -1);
        
        if(quantidadeEmEstoque == -1){
            // produto nunca foi estocado, não existe na loja
            // ToDo lançar uma exceção específica
            return;
        }
        
        if(quantidade <= 0){
            // não chega uma quantidade válida para o estoque 
            // ToDo lançar uma exceção específica
            return;
        }
        
        this.quantidadeEmEstoquePorItem.put(item, quantidadeEmEstoque + quantidade);
    }
    
    
    /**
     * Retorna a quantidade de unidades do item desejado.
     * 
     * @param item
     * @return a quantidade de itens em estoque. Se não for um item do catálogo, retorna -1.
     */
    public int getQuantidadeEmEstoqueDoItem(Vendavel item){
        return this.quantidadeEmEstoquePorItem.getOrDefault(item, -1);
    }
    
    
    /**
     * Busca um ítem no catálogo da loja a partir de sua descrição.
     *
     * @param descricao a descrição do ítem desejado (ou parte dela)
     * @return o primeiro Vendavel que case com a descrição fornecida, caso encontre;
     *         ou null, caso contrário
     */
    public Vendavel buscarItem(String descricao) {
        for (Vendavel item : catalogo) {
            if (item.getDescricao().contains(descricao)) {
                return item;
            }
        }
        return null;
    }

    
    /**
     * Busca um ítem no catálogo da loja a partir de seu código.
     *
     * @param id o código de identificação do ítem desejado
     * @return o Vendavel cujo código seja igual ao código fornecido, caso encontre;
     *         ou null, caso contrário
     */
    public Vendavel buscarItem(long id) {
        for (Vendavel item : catalogo) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    private boolean processarPagamento(float valor) {
        // ToDo passar o cartão de crédito, ou emitir boleto, etc.
        System.out.println(String.format(
                "Processando pagamento no valor de R$%.2f...",
                valor));
        return true;  // ToDo retornar false caso o pagamento falhe
    }
}
