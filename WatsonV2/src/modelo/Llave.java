package modelo;

import java.util.ArrayList;

public class Llave extends Sustitucion {
	private String llave;

	@Override
	public ArrayList<String> getParams() {
		ArrayList<String> params = new ArrayList<String>();
		params.add("Llave");
		return params;
	}
	
	@Override
	public void setListParams(ArrayList<String> pParams) {
		if (pParams != null) {
			this.llave = pParams.get(0).toString();		
		}
	}
		  
		  @Override
		  protected String intercambiarPalabra(String pPalabra){
		    String palabraCifrada = "";   
		    String nuevaLlave = generarLlave(pPalabra.length()-1);
		    
		    for(int i = 0; i <= pPalabra.length()-1; i++){
		      palabraCifrada += sustituirLetra(pPalabra.charAt(i),nuevaLlave.charAt(i));
		    }
		    
		    return palabraCifrada;
		  }
		  
		  private char sustituirLetra(char pLetra,char pLlave){
		    int codigoLetra = util.convertirLetraASCII(pLetra)-96;
		    int codigoLlave = util.convertirLetraASCII(pLlave)-96;
		    
		    if(this.esCodificar){
		      codigoLetra += codigoLlave;    
		    }else{
		      codigoLetra -= codigoLlave;        
		    }
		    codigoLetra = validarTamanoCodigo(codigoLetra);
		    return util.convertirASCIILetra(codigoLetra + 96);
		    
		  }
		  
		  private int validarTamanoCodigo(int pCodigo){ 
			if (validarMayor(pCodigo)) {
				return pCodigo -=26;
			}
		    if(validarNegativo(pCodigo)){
		      return pCodigo += 26;    
		    } 
		    
		    return pCodigo;
		  }
		  
		  
		  private boolean validarMayor(int pCodigo) {
			  if (this.esCodificar && pCodigo > 25) {
				  return true;
			  }
			  return false;
		  }
		  
		   
		  private boolean validarNegativo(int pCodigo) {
			  if (!this.esCodificar && pCodigo < 0) {
				  return true;
			  }
			  return false;
		  }
		  private String generarLlave(int pTamano){
		    String nuevaLlave = "";
		    int contador = 0;
		    for(int i = 0; i <= pTamano;i++){
		      if(contador > this.llave.length()-1){
		        contador = 0;    
		      }    
		      nuevaLlave += this.llave.charAt(contador);
		      contador++;
		    }
		    return nuevaLlave;
		  }
}
