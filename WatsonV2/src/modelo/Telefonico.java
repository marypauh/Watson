package modelo;

import java.util.ArrayList;

public class Telefonico extends Cifrador {
	private final char[][] teclado= {{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','o'},{'p','q','r','s'}
    ,{'t','u','v'},{'w','x','y','z'}};
	

	private static int bandera;
	
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
			 if (ptexto.charAt(indice) == ' ') {
				 indice++;
			 }else {
				fraseCifrada +=  decoNumALetra(ptexto,indice);
				indice += this.bandera;
			 }
	       }
		  return fraseCifrada;	  
	 }
	   
	  private String decoNumALetra(String pTexto, int indice) {
		  if (pTexto.charAt(indice) == '*') {
			  this.bandera=1;
			  return " ";
			  
		  }else {
			  char letra = numeroALetra(pTexto.charAt(indice),pTexto.charAt(indice+1));
			  this.bandera=2;
			  return Character.toString(letra);
		  }
		  
	  }
	  
	  private String verificarLetra(char pLetra){
	    if(pLetra == ' '){
	      return "*";
	    } else {
	      return letraANumero(pLetra);
	    }
	  }
	  
	  private String letraANumero(char pLetra){ 
	    String sustitucion = "";
	    for (int fila = 0; fila < teclado.length; fila++){
	      for (int columna = 0; columna < teclado[fila].length; columna++){
	    	  sustitucion += compararLetra(fila,columna,pLetra);   	  
		}
	    }
	    return sustitucion;
	  }
	 
	  private String compararLetra(int pFila, int pColumna, char pLetra) {
		  String sustitucion = "";
		  if (pLetra == teclado[pFila][pColumna]) {
			  sustitucion = String.valueOf((((pFila+2)*10)+(pColumna+1)));
			  sustitucion += " ";        
		  }
		  return sustitucion;
	  }
	  
	  private char numeroALetra(char pNumUno, char pNumDos) {
	    return teclado[Character.getNumericValue(pNumUno)-2]
	        [Character.getNumericValue(pNumDos)-1];
	  }
	  
}
