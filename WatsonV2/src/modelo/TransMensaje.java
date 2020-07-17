package modelo;

import java.util.ArrayList;

public class TransMensaje extends Transposicion{

	@Override
	public String invertir(String pTexto) {
		String frasesInvertida = "";
	    String[] palabras = pTexto.split("\\s+");
	      
	    for(int i = palabras.length-1; i >= 0; i--){ //recorre el arreglo de Strings para invertir las palabras
	      frasesInvertida += invertirUnTexto(palabras[i])+" "; 
	    }
	    return frasesInvertida;
	  }
}

