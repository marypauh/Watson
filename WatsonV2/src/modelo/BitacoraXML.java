package modelo;


import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "bitacora")
@XmlAccessorType(XmlAccessType.FIELD)
public class BitacoraXML {

	@XmlElement (name = "registro")
	public ArrayList<Registro> registros;
	

	public BitacoraXML() {
		registros = new  ArrayList<Registro>();
	}
	
	public void agregarRegistro(Registro pRegistro) {
			if (existeRegistro(pRegistro) == false) {
				registros.add(pRegistro);
			}
	}
		
	private boolean existeRegistro(Registro pRegistro) {
		boolean existe = false;
		for(int j = 0; j < registros.size();j++) {
			if (registros.get(j).equals(pRegistro)) {
				existe = true;
				return existe;
			}
		}
		return existe; 
	}
	
	public BitacoraXML getRegistrosHoy(String pFecha){
		ArrayList<Registro> resultado = new  ArrayList<Registro>();
		
		for(Registro registro : registros) {
			if (registro.fecha.equals(pFecha)) {
				resultado.add(registro);
			}
		}
		BitacoraXML bitacora = new BitacoraXML();
		bitacora.registros = resultado;
		return bitacora;
	}
	
	public BitacoraXML getRegistrosCodificar(){
		BitacoraXML bitacora = new BitacoraXML();

		for(int i = 0; i < registros.size();i++) {
			if (registros.get(i).getAccion().equals("codificar")) {
				bitacora.registros.add(registros.get(i));
			}
		}
		return bitacora;
	}
	
	public BitacoraXML getRegistrosDecodificar(){
		BitacoraXML bitacora = new BitacoraXML();
		for(int i = 0; i < registros.size();i++) {
			if (registros.get(i).getAccion().equals("decodificar")) {
				bitacora.registros.add(registros.get(i));
			}
		}
		return bitacora;
	}
	
	
}

	

