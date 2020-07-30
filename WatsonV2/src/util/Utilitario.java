package util;

public class Utilitario {
	  
	  public int convertirLetraASCII(char pLetra){
	    return (int)pLetra; //devuelve el aascii de la letra
	  }
	  
	  public char convertirASCIILetra(int pCodigo){
	    return (char)pCodigo; //devuelve la letra correspondiente
	  }
	  
	  public char validarCodigoASCIICodificar(int pCodigo){
	    if(pCodigo > 122){
	      int diferencia = pCodigo - 122;
	      pCodigo = 96 + diferencia;
	    }
	    return convertirASCIILetra(pCodigo);    
	  }
	  
	  public char validarCodigoASCIIDecodificar(int pCodigo){
	    if(pCodigo < 97){
	      int diferencia = pCodigo - 97;
	      pCodigo = 123 - diferencia;
	    }    
	    return convertirASCIILetra(pCodigo);
	  }
	  
}
