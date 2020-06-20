package modelo;

import java.util.ArrayList;

public class Cesar extends Sustitucion{
	private int desplazamiento;

	@Override
	public ArrayList<String> getParams() {
		ArrayList<String> params = new ArrayList<String>();
		params.add("Desplazamiento");
		return params;
	}

	@Override
	public void setListParams(ArrayList<String> pParams) {
		if (pParams != null) {
			this.desplazamiento = Integer.parseInt(pParams.get(0).toString());		
		}
	}

	@Override
	protected String intercambiarPalabra(String palabra) {
		String nuevaPalabra = "";
		char ch;
	    for(int i = 0; i <= palabra.length()-1; i++){
	    	nuevaPalabra += codificarLetra(Character.toLowerCase(palabra.charAt(i))); // sustitur cada letra de la palabra segun desplazamiento
	    }
	    return nuevaPalabra;
	  }
	
	private char codificarLetra(char pLetra){
	    int cod = util.convertirLetraASCII(pLetra);
	    if(esCodificar){
	    	cod += desplazamiento;   
	    	return util.validarCodigoASCIIDecodificar(cod);
	    }else{
	    	cod -= this.desplazamiento;
	      return util.validarCodigoASCIIDecodificar(cod);
	    }
	  }
	
		
}
