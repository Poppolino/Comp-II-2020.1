package banco;

public class Gerente extends Funcionario {

    private int nivel;  // 1: gerente de conta; 2: gerente geral

    public Gerente(String nome, long cpf, int matricula) {
        super(nome, cpf, matricula);
        nivel = 1;
    }

    public void setNivel(int nivel) {
        if (nivel < 1 && nivel > 2) {
            throw new RuntimeException("Nivel invalido!");
        }

        this.nivel = nivel;
    }
}
