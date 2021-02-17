package banco;
import java.util.Date;
import java.util.Objects;

public class Pessoa {

    protected String nome;  // protected é "package private" + subclasses

    protected final long cpf;  // final indica que o campo JAMAIS poderá ser atualizado

    private Date dataDeNascimento;

    private String endereco;

    // overload do construtor
    public Pessoa(String nomeDaPessoa, long cpfDaPessoa) {
        this.nome = nomeDaPessoa;
        this.cpf = cpfDaPessoa;
        this.endereco = "Endereço desconhecido";
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        if (endereco.length() > 40) {  // tamanho máximo permitido para endereços
            return;  // ToDo lançar exceção!
        }
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return String.format("%s (CPF: %d)",
                nome,
                cpf);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return cpf == pessoa.cpf &&
                Objects.equals(dataDeNascimento, pessoa.dataDeNascimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, dataDeNascimento);
    }


    /*
         ***** O quê? Por que isso me importa?

         Sempre que um objeto será tratado como chave de uma estrutura baseada
         em hashing (por exemplo, faremos meuHashSet.add(meuObjeto) ou
         meuHashMap.put(meuObjeto, valorAssociadoAoMeuObjeto), devemos nos preocupar
         em fazer o override de seus métodos equals() e hashCode().

         ***** Como faço isso?

         Você pode criar na mão esses dois métodos, garantindo que eles usem
         os mesmos atributos do seu objeto (vide o exemplo acima, onde os atributos
         cpf e dataDeNascimento foram usados nos dois métodos).
         Ou você pode pedir para a sua IDE criar para você, via Generate...,
         e aí ela própria garantirá que os mesmos atributos serão utilizados
         (você só precisará selecionar os atributos que constituem a
         "igualdade semântica" dos seus objetos).

         ***** Por quê?

         Porque, na INSERÇÃO, o esquema é

         meuObjeto.hashCode() --> FUNÇÃO HASH DA ESTRUTURA (map, set)  --> pos

         O objeto será inserido na posição "pos" de um array que fica dentro
         da estrutura (map, set), invisível pro programador
         (na verdade, o objeto ficará numa LISTA que fica na posição "pos"
         do array, lista essa que conterá TODOS os objetos que cairem naquela
         mesma posição "pos" do array).

         E, na BUSCA, ele vai usar esse mesmo esquema

         meuObjeto.hashCode() --> FUNÇÃO HASH DA ESTRUTURA (map, set)  --> pos

         pra determinar a posição onde irá buscar.

     */
}
