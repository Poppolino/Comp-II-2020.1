package banco;

public class Funcionario extends Pessoa {

    private int matricula;

    private float salario;

    private String cargo;

    public Funcionario(String nome, long cpf, int matricula) {
        /* A primeira linha de qualquer construtor PRECISA SER
           uma chamada ao construtor da superclasse, via super(.....).
           Se nós não fizermos explicitamente essa chamada,
           o compilador vai acrescentar automaticamente
           a chamada

               super();
        */

        super(nome, cpf);

        this.matricula = matricula;
        this.cargo = "sem cargo";
        this.salario = 1000f;
    }

    public int getMatricula() {
        return matricula;
    }

    public void receberAumento(float percentual) {
        salario *= (1 + percentual/100);
    }

    @Override
    public String toString() {

//        return String.format("%d --- %s (CPF: %d)",
//                matricula, nome, cpf);

        // assim é mais elegante, acrescentando campos ao toString() da superclasse
        return String.format("%d --- %s",
                matricula, super.toString());
    }
}
