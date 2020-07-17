package util;

public class Utilitario {
	  
	  public int convertirLetraASCII(char letra){
	    return (int)letra; //devuelve el aascii de la letra
	  }
	  
	  public char convertirASCIILetra(int codigo){
	    return (char)codigo; //devuelve la letra correspondiente
	  }
	  
	  public char validarCodigoASCIICodificar(int codigo){
	    if(codigo > 122){
	      int diferencia = codigo - 122;
	      codigo = 96 + diferencia;
	    }
	    return convertirASCIILetra(codigo);    
	  }
	  
	  public char validarCodigoASCIIDecodificar(int codigo){
	    if(codigo < 97){
	      int diferencia = codigo - 97;
	      codigo = 123 - diferencia;
	    }    
	    return convertirASCIILetra(codigo);
	  }
	  
}
