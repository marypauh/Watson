package modelo;

import java.util.ArrayList;

import util.Utilitario;

public class Binario extends Cifrador{
	private Utilitario util = new Utilitario();

	@Override
	public ArrayList<String> getParams() {
		return null;
	}

	@Override
	public void setListParams(ArrayList<String> pParams) {		
	}
	
	  @Override
	  public String codificar(String pTexto) {
	    return aBinario(pTexto);
	  }

	  @Override
	  public String decodificar(String pTexto) {
	    return aTexto(pTexto);  
	  }
	  
	  private String aBinario(String pTexto){
	    byte[] bytes = pTexto.getBytes();
	    String textoEnBinario = "";
	    for (byte b : bytes){
	    	textoEnBinario += letraABinario(b)+" ";
	    }
	    return textoEnBinario;
	  }
	  
	  private String aTexto(String pTexto) {
	    String binarioEnTexto = "";
	    String[] textoSeparado = pTexto.split(" ");
	    for (String separado : textoSeparado) {
	    	binarioEnTexto += esEspacio(separado);//verificar si es palabra o espacio
	    }
	    return binarioEnTexto;
	  }
	  
	  private char esEspacio(String pTexto) {
	    if(pTexto.equals("*")){
	      return ' ';
	    } else {
	      return binarioALetra(pTexto);
	    }
	  }
	  
	  private String letraABinario(int pValorByte) {
	    String letraBinario;
	    if(pValorByte == 32){
	      return "*";
	    } else {
	      letraBinario = Long.toBinaryString(pValorByte-97);
	      return verificarCantBinario(letraBinario);
	    }
	  }
	  
	  private char binarioALetra(String pTexto){
	    int valorByte = 16;
	    int valorNumerico = 0;
	    for (int i = 0; i < pTexto.length();i++){
	      valorNumerico += Character.getNumericValue((pTexto.charAt(i)))*valorByte;
	      valorByte = valorByte /2;
	    }
	    return util.convertirASCIILetra(valorNumerico+97); 
	  }
	  
	  private String verificarCantBinario(String pCambio){
	    if(pCambio.length()==5){
	      return pCambio;
	    }else{
	      return cantBinario(pCambio);
	    }
	  }
	  
	  private String cantBinario(String pCambio) {
		  while(pCambio.length()<5){
	    	  pCambio = "0" + pCambio;
	      }
	      return pCambio; 
	}
}



