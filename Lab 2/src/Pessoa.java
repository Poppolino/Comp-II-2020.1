import java.util.Date;

public class Pessoa {

    private String nome;

    private final long cpf;  // final indica que o campo JAMAIS poderá ser atualizado

    private Date dataDeNascimento;

    private String endereco;

    public Pessoa(String nomeDaPessoa, long cpfDaPessoa) {
        nome = nomeDaPessoa;
        cpf = cpfDaPessoa;
        endereco = "EndereÃ§o desconhecido";
    }
    
    public void setEndereco(String endereco) {
        if (endereco.length() > 40) {  // tamanho máximo permitido para endereços
            return;  // ToDo lançar exceção!
        }
        this.endereco = endereco;
    }

    public long getCpf() {
        return cpf;
    }
    
    public String getNome(){
    	return this.nome;
    }
}
