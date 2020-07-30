package modelo;

import java.util.ArrayList;

public abstract class Transposicion extends Cifrador {

	@Override
	public final void setListParams(ArrayList<String> pParams) {		
	}
	
	@Override
	public final String codificar(String pTexto) {
		return invertir(pTexto);
	}

	@Override
	public final String decodificar(String pTexto) {
		return invertir(pTexto);
	}
	
	protected abstract String invertir(String pTexto);
	
	public final String invertirUnTexto(String pTexto){
		String textoInvertido = "";
		for (int i = pTexto.length()-1; i >= 0; i--){
		  textoInvertido += pTexto.charAt(i);
		}
		 return textoInvertido;
		}
	@Override
	public ArrayList<String> getParams() {
		// TODO Auto-generated method stub
		return null;
	}
}
