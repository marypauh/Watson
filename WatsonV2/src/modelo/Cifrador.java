package modelo;

import java.util.ArrayList;

import util.Utilitario;

public abstract class Cifrador {
	public Utilitario util = new Utilitario();
	public boolean necesitaParams = false;
	
	public abstract String codificar(String pTexto);
	public abstract String decodificar(String pTexto);
	public abstract ArrayList<String> getParams();
	public final ArrayList<String> getListParams(){
		if (necesitaParams == true) {
			return getParams();
		}else {
			return null;
		}
	}
	public abstract void setListParams(ArrayList<String> pParams);
}
