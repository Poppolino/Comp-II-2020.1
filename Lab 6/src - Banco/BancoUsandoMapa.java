package Banco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BancoUsandoMapa {

    private Map<Long, ContaCorrente> contas;                               
    private Map<Long, Pessoa> correntistas;
    private Agencia agenciaUnica;

    public BancoUsandoMapa() {
        contas = new HashMap<>();
        correntistas = new HashMap<>();
        agenciaUnica = new Agencia();
    }

    public Pessoa cadastrarCorrentista(String nome, long cpf) {
        Pessoa p = new Pessoa(nome, cpf);
        this.correntistas.put(cpf, p);    //Se existir, atualiza. Sen�o, cria um novo.
        return p;
    }

    public ContaCorrente cadastrarConta(Pessoa correntista) {
        // verifica correntista
        if (localizarCorrentista(correntista.getCpf()) == null) {
            // correntista n�o existe!!
            // ToDo lan�ar uma exce��o!
            return null;  // n�o vou criar conta cois�ssima nenhuma!
        }

        // aceitamos mais de uma conta para o mesmo correntista

        ContaCorrente novaConta = new ContaCorrente(correntista, this.agenciaUnica);
        this.contas.put(novaConta.getNumeroDaConta(), novaConta);

        return novaConta;
    }

    public Pessoa localizarCorrentista(long cpf) {
        return this.correntistas.get(cpf);
    }

    public ContaCorrente localizarConta(long numeroDaConta) {
        return this.contas.get(numeroDaConta);
    }
}
