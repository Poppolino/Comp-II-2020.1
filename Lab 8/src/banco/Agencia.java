package banco;

public class Agencia {

    private String nome;

    private String endereco;

    private Pessoa gerenteGeral;

    private int codigo;

    private Banco banco;

    public Agencia(Banco banco, int codigo, String nome) {
        this.banco = banco;
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public Pessoa getGerenteGeral() {
        return gerenteGeral;
    }

    public int getCodigo() {
        return codigo;
    }

    public Banco getBanco() {
        return banco;
    }
}
