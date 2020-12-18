import java.util.Date;

public class Pessoa {

    private String nome;

    private final long cpf;  // final indica que o campo JAMAIS poder� ser atualizado

    private Date dataDeNascimento;

    private String endereco;

    public Pessoa(String nomeDaPessoa, long cpfDaPessoa) {
        nome = nomeDaPessoa;
        cpf = cpfDaPessoa;
        endereco = "Endereço desconhecido";
    }
    
    public void setEndereco(String endereco) {
        if (endereco.length() > 40) {  // tamanho m�ximo permitido para endere�os
            return;  // ToDo lan�ar exce��o!
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
