package modelo;

public class Bitacora {

	private String fecha;
	private String accion;
	private String texto;
	private String hora;
	
	
	public Bitacora(String pFecha, String pHora, String pAccion, String pTexto) {
		this.fecha = pFecha;
		this.hora = pHora;
		this.accion = pAccion;
		this.texto = pTexto;
		
	}


	public String getFecha() {
		return fecha;
	}


	public String getAccion() {
		return accion;
	}


	public String getTexto() {
		return texto;
	}
	
	public String getHora() {
		return hora;
	}
	
	public String toString() {
		String msg = "";
		msg += this.getFecha() + " ";
		msg += this.getHora() + " ";
		msg += this.getAccion() + " ";
		msg += this.getTexto();
		
	return msg;
	}
	
	
	
	
	
}

	

