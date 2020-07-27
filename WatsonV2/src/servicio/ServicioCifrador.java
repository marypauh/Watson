package servicio;

import factory.CifradorFactory;
import modelo.BitacoraXML;
import modelo.Cifrador;

public class ServicioCifrador {
	private CifradorFactory factory;
	
	public Cifrador crearCifrado(String pTipo) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return factory.crearCifrador(pTipo);
	}

	/**
	   * Lee la informaci�n existente en la bit�cora
	   * y la muestra.
	   * @param pBitacora
	   * @param pTipoSeparador
	   * @return
	   */
	
	
	  /**
	   * Determina el criterio de la bit�cora
	   * a buscar y realiza la operaci�n respectiva.
	   * @param pCriterioBitacora
	   * @param pBitacora
	   * @return
	   */
	public static BitacoraXML filtrarBitacora(String pCriterioBitacora,BitacoraXML pBitacora,String pFechaHoy){
		System.out.println("Bitacora que manda");
		if(pCriterioBitacora.equals("registros de hoy")) {
			System.out.println("Si entro a hoy ");
		   pBitacora = pBitacora.getRegistrosHoy(pFechaHoy);	
		  
		}
		else if(pCriterioBitacora.equals("registros codificacion")) {
			System.out.println("Si entro a cdificar ");
			pBitacora = pBitacora.getRegistrosCodificar();	
		}
		else if(pCriterioBitacora.equals("registros decodificacion")) {
			System.out.println("Si entro a deco ");
			pBitacora = pBitacora.getRegistrosDecodificar();
	    }
		System.out.println("no ENTRO ");
	    return pBitacora; 
	  }

}
