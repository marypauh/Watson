package modelo;

import java.util.ArrayList;

public class Vigenere extends Sustitucion {
	private int cifraUno;
	private int cifraDos;
		  
private char sustituirLetra(char pLetra, int pPosicion){
		    int codigo = util.convertirLetraASCII(pLetra);
		    int cifra = determinarDesplazamiento(pPosicion);
		    
		    if(this.esCodificar){
		      codigo += cifra;    
		    }else{
		      codigo -= cifra;    
		    }
		    return util.validarCodigoASCIICodificar(codigo);
		  }
		  
private int determinarDesplazamiento(int pPosicion){
		    if(pPosicion%2 == 0){
		      return this.cifraUno;  //Si la posicion es par corresponde la primera cifra   
		    }else{
		      return this.cifraDos;    
		    }    
		  }

@Override
public ArrayList<String> getParams() {
	ArrayList<String> params = new ArrayList<String>();
	params.add("Cifra de dos digitos");
	return params;
}

@Override
public void setListParams(ArrayList<String> pParams) {
	if (pParams != null) {
		int cifra = Integer.parseInt(pParams.get(0).toString());		
		this.cifraDos = cifra%10;
	    cifra /= 10;
	    this.cifraUno = cifra%10;	
	 }
}

@Override
protected String intercambiarPalabra(String pPalabra) {
    String palabraCifrada = "";
    
    for(int i = 0; i <= pPalabra.length()-1; i++){
      palabraCifrada += sustituirLetra(pPalabra.charAt(i),i);
    }
    return palabraCifrada;    
}
}
