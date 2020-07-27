package modelo;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Registro implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String accion;
	public String fecha;
	public String hora;
	public String texto;
	

	public void setAccion (String pAccion) {
		accion = pAccion;
	}
	

	public String getAccion() {
		return accion;
	}
	

	public void setFecha (String pFecha) {
		fecha = pFecha;
	}
	

	public String getFecha() {
		return fecha;
	}

	public void setHora (String pHora) {
		hora = pHora;
	}
	

	public String getHora() {
		return hora;
	}
	
	public void setTexto (String pTexto) {
		texto = pTexto;
	}

	public String getTexto() {
		return texto;
	}
	
	public void registrarDatos(String pFecha, String pHora, String pAccion, String pTexto) {
			fecha = pFecha;
			hora = pHora;
			accion = pAccion;
			texto = pTexto;	
	}
}
