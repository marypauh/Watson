package modelo;

import java.util.ArrayList;

public class Telefonico extends Cifrador {
	private final char[][] teclado= {{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','o'},{'p','q','r','s'}
    ,{'t','u','v'},{'w','x','y','z'}};
	

	@Override
	public ArrayList<String> getParams() {
		return null;
	}

	@Override
	public void setListParams(ArrayList<String> pParams) {		
	}
	
	@Override
	public String codificar(String ptexto) {
		String fraseCifrada = "";
	    for(int i = 0; i <= ptexto.length()-1; i++){
	      fraseCifrada += verificarLetra(ptexto.charAt(i));
	    }
	    return fraseCifrada;	  }

	  @Override
	  public String decodificar(String ptexto) {
	        String fraseCifrada = "";
	       int indice = 0;
	       while(indice<ptexto.length()){
			 if(ptexto.charAt(indice) == '*'){
			   fraseCifrada += " ";
			   indice++;
			 } else {
				if (ptexto.charAt(indice) != ' ') {
				fraseCifrada += numeroALetra(ptexto.charAt(indice),ptexto.charAt(indice+1));
				indice +=2;
				}else {
				indice+=1;
				}
			  }
			}
			 return fraseCifrada;	  
		   }
	  
	  private String verificarLetra(char pLetra){
	    if(pLetra == ' '){
	      return "*";
	    } else {
	      return letraANumero(pLetra);
	    }
	  }
	  
	  private String letraANumero(char pLetra){ 
	    String sustiticion = "";
	    for (int fila = 0; fila < teclado.length; fila++){
	      for (int columna = 0; columna < teclado[fila].length; columna++){
		if(pLetra == teclado[fila][columna]) {
		  sustiticion = String.valueOf((((fila+2)*10)+(columna+1)));
	          break;
		}
	      }
	    }
	    return sustiticion;
	  }
	  
	  private char numeroALetra(char pNumUno, char pNumDos) {
	    return teclado[Character.getNumericValue(pNumUno)-2]
	        [Character.getNumericValue(pNumDos)-1];
	  }
	  
}
