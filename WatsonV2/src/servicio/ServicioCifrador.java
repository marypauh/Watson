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
	   * Lee la información existente en la bitácora
	   * y la muestra.
	   * @param pBitacora
	   * @param pTipoSeparador
	   * @return
	   */
	
	
	  /**
	   * Determina el criterio de la bitácora
	   * a buscar y realiza la operación respectiva.
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
