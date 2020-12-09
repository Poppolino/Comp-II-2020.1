import java.util.*;

public class Principal{
    
	public static void main(String args[]) { 
		long dre, maiorDRE=0, quant=0;
	    float media, maiorMedia=-1, total=0; 
	    
	    Scanner leitura = new Scanner(System.in);
	    leitura.useLocale(Locale.US);            //Definindo o padrão de casa decimal como americano para ler com '.' e não com ','
	    
	    System.out.println("Por favor, entre com o DRE e a média de cada aluno da disciplina.");
	    System.out.println("Caso queira parar de inserir, digite uma média negativa.");
	    
	    dre = leitura.nextLong();
	    media = leitura.nextFloat();
	    
	    while(media >= 0){
	        if(maiorMedia < media){
	            maiorMedia = media;
	            maiorDRE = dre;
	        }
	        
	        quant++;
	        total += media;
	        
	        dre = leitura.nextLong();
	        media = leitura.nextFloat();
	    }
	    
	    System.out.printf("\n%d nota(s) digitada(s)\n", quant);
	    
	    if(quant > 0){
	        media = total/quant;
	        
	        System.out.println("Média da turma: " + media);
	        System.out.println("DRE com maior média: " + maiorDRE);
	    }
	    
	    leitura.close();
	} 
}