import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class k_media{
	public static void main(String[] args) throws IOException{
		int k;
		Random numger = new Random();
		ArrayList<Centroide> cent = new ArrayList<>();
    	String nome = "c2ds1-2sp.txt";
    	FileWriter arq2 = new FileWriter("resultado.txt");
		PrintWriter gravarArq = new PrintWriter(arq2);
    	try {
	      	FileReader arq = new FileReader(nome);
	 	    BufferedReader lerArq = new BufferedReader(arq);
	 	    
	 
		    String linha = lerArq.readLine(); // lê a primeira linha
		// a variável "linha" recebe o valor "null" quando o processo
		// de repetição atingir o final do arquivo texto
		    linha = lerArq.readLine();
		    while (linha != null) {
		    	String [] s = linha.split("\t");
		    	/*for(int i=0;i<3;i++){
		    		System.out.println(i+ " " +s[i]+" ");
		    	}*/
		    	cent.add(new Centroide(s[0], Double.parseDouble(s[1]), Double.parseDouble(s[2])));
		        linha = lerArq.readLine(); // lê da segunda até a última linha
		    }
	    	arq.close();
	    }catch (IOException e) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	          e.getMessage());
	    
		}	    
	    System.out.println();
	    System.out.println("Defina K");
	    Scanner ler = new Scanner(System.in);
	    ArrayList<Cluster> clst = new ArrayList<>();
	    k = ler.nextInt();
	    for(int i=0;i<k;i++){
	    	int alt = numger.nextInt(cent.size());
	    	clst.add(new Cluster(cent.get(alt).getx() ,cent.get(alt).gety()));
	    }
	    int flag = 1;
	    int cont = 0;
	    while(flag != 0){
	    	flag = 0;
		    Double menor = -1.0;
		    Double resultx;
		    Double resulty;
		   	Double resultado;
		    String nomenor = null;
		    int aux = 0;
		    for(int i=0;i<cent.size();i++){
		    	for(int j=0;j<k;j++){
		    		resultx = Math.pow((clst.get(j).getx() - cent.get(i).getx()),2);
		    		resulty = Math.pow((clst.get(j).gety() - cent.get(i).gety()),2);
		    		resultado = Math.sqrt(resultx+resulty);
		    		if(menor == -1.0){
		    			menor = resultado;
		    			aux = j;
		    		}else if(resultado < menor){
		    			menor = resultado;
		    			aux = j;
		    		}
		    	}
		    	clst.get(aux).addArray(cent.get(i));
		    	menor = -1.0;
		    }
		    for(int i = 0;i<k;i++){
		    	Double mediax=0.0;
		    	Double mediay=0.0;
		    	for(int j=0;j<clst.get(i).getSize();j++){
		    		mediax = mediax + clst.get(i).getCentx(j);
		    		mediay = mediax + clst.get(i).getCenty(j);
		    	}
		    	mediax = mediax/clst.get(i).getSize();
		    	mediay = mediay/clst.get(i).getSize();
		    	if(clst.get(i).getx() != mediax && clst.get(i).gety() != mediay){
			    	clst.get(i).setx(mediax);
			    	clst.get(i).setx(mediay);
			    	if(cont != 10000){
			    		clst.get(i).recalc();
			    	}
			    	flag = 1;
		    	}
				if(cont == 10000){
					flag = 0;
				}
		    }
		    cont++;
		}
        for(int j=0;j<k;j++){
	    	for(int i=0;i<clst.get(j).getSize();i++){
			 	gravarArq.printf(clst.get(j).getNomeCentroide(i) + ", " + j +"\r\n");
	    	}
	    }    
	    arq2.close();
	}
}