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
				System.out.println("Es igual " + registros.get(j).getTexto() +" === " + pRegistro.getTexto());
				existe = true;
				return existe;
			}
		}
		return existe; 
	}
	
	public BitacoraXML getRegistrosHoy(String pFecha){
		ArrayList<Registro> resultado = new  ArrayList<Registro>();
		
		for(int j = 0; j < registros.size();j++) {
			System.out.println("La accion en" + j +"es " + registros.get(j).getFecha());
			}
		
		/*for(Registro registro : registros) {
			if (registro.fecha.equals(pFecha) && registro.fecha != null) {
				resultado.add(registro);
			}
		}*/
		BitacoraXML bitacora = new BitacoraXML();
		bitacora.registros = resultado;
		return bitacora;
	}
	
	public BitacoraXML getRegistrosCodificar(){
		BitacoraXML bitacora = new BitacoraXML();

		for(int j = 0; j < registros.size();j++) {
			System.out.println("La accion en" + j +"es " + registros.get(j).getAccion());
			}

		for(int i = 0; i < registros.size();i++) {
			if (registros.get(i).getAccion().equals("codificar")) {
				bitacora.registros.add(registros.get(i));
			}
		}
		return bitacora;
	}
	
	public BitacoraXML getRegistrosDecodificar(){
		BitacoraXML bitacora = new BitacoraXML();

		for(int j = 0; j < registros.size();j++) {
			System.out.println("La accion en" + j +"es " + registros.get(j).getAccion());
			}

		for(int i = 0; i < registros.size();i++) {
			System.out.println("Aqui entro a la" + registros.get(i).getAccion().equals("decodificar"));
			if (registros.get(i).getAccion().equals("decodificat")) {
				System.out.println("Aqui entro a la compacaracion de condificar");
				bitacora.registros.add(registros.get(i));
			}
		}
		return bitacora;
	}
	
	
}

	

