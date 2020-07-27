package decorador;

import java.util.ArrayList;
import Archivos.CSV;
import Archivos.TXT;
import Archivos.XML;
import modelo.Bitacora;
import modelo.Cifrador;
import worshop.chat.ServiciosChat;

public class DecoradorArchivos {
	
	
	public static String agregar(ArrayList<Bitacora> bitacoras, String pFecha, String pHora, String pAccion,
	String pTexto, Cifrador pCifrador) throws Exception {
		String accion = ServiciosChat.realizarAccion(pCifrador);
		CSV.agregarBitacora(bitacoras,pFecha,pHora,pAccion,pTexto);
		TXT.agregarBitacora(bitacoras, pFecha, pHora, pAccion, pTexto);
		XML.agregarBitacoras(pFecha, pHora, pAccion, pTexto);
		return accion;
		
	}
	

	
	

}
