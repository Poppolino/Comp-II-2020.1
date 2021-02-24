import java.util.ArrayList;

public class Transportadora {

    private String nome;

    public void transportar(ArrayList<Transportavel> itens, String endereco) {

        int pesoTotalEmGramas = 0;
        for (Transportavel item : itens) {
            pesoTotalEmGramas += item.getPesoEmGramas();
        }

        System.out.println(
                String.format("Transportando %d itens (peso total: %d gramas) para o endereço: %s",
                        pesoTotalEmGramas, itens.size(), endereco));

        // ToDo chamar de fato o serviço da transportadora física, emitindo OS
    }
}
