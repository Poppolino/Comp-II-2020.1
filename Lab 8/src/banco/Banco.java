package banco;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Banco {

    Map<Long, ContaCorrente> contaPorNumero;
    Map<Long, Pessoa> correntistaPorCpf;

    Set<Pessoa> correntistasComSaldoNegativo;

    private Agencia agenciaMatriz;

    public Banco() {
        contaPorNumero = new HashMap<>();
        correntistaPorCpf = new HashMap<>();
        correntistasComSaldoNegativo = new HashSet<>();
        agenciaMatriz = new Agencia(this, 1, "Agência Um");
    }

    public Pessoa cadastrarCorrentista(String nome, long cpf) {
        Pessoa p = localizarCorrentista(cpf);
        if (p != null) {
            // Localizei o correntista! Não é preciso recriá-lo! Podemos atualizar o nome, se for o caso.
            p.setNome(nome);
        } else {
            // Correntista novo
            p = new Pessoa(nome, cpf);
            correntistaPorCpf.put(cpf, p);
        }
        return p;
    }

    public ContaCorrente cadastrarConta(Pessoa correntista) {
        // verifica correntista
        if (localizarCorrentista(correntista.getCpf()) == null) {
            // correntista não existe!!
            // ToDo lançar uma exceção!
            return null;  // não vou criar conta coisíssima nenhuma!
        }

        // aceitamos mais de uma conta para o mesmo correntista

        ContaCorrente novaConta = new ContaCorrente(correntista, this.agenciaMatriz);
        this.contaPorNumero.put(novaConta.getNumeroDaConta(), novaConta);

        return novaConta;
    }

    public Pessoa localizarCorrentista(long cpf) {
        return this.correntistaPorCpf.get(cpf);
    }

    public ContaCorrente localizarConta(long numeroDaConta) {
        return this.contaPorNumero.get(numeroDaConta);
    }

    void registrarCorrentistaComSaldoNegativo(Pessoa correntista) {
        this.correntistasComSaldoNegativo.add(correntista);
    }

    public Set<Pessoa> getCorrentistasComSaldoNegativo() {
        return this.correntistasComSaldoNegativo;
    }
}
