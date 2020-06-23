package worshop.chat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import com.ibm.watson.developer_cloud.assistant.v1.model.Context;

import factory.CifradorFactory;
import modelo.Cifrador;
import util.Traductor;

public class ServiciosChat {
	
	static Context cont;
	static ArrayList<String> parametros = new ArrayList<String>();
	static String valorTipo;
	static String parametro;
	static String accion;
	static String textoRespuesta;
	static String textoLISTO;
	static String completo;
	
	public static String determinarHora() {
		int hora = obtenerHora();
			    
		if(hora <= 12) {
		  return "Buenos días";
		}    
		if(hora < 17 && hora > 12) {
		  return "Buenas tardes";
		}    
		if (hora <=24 && hora >= 17) {
		  return "Buenas noches";
		}	    
		return "Buenas";
	  }
		
	  private static int obtenerHora() {
	    TimeZone tz = TimeZone.getTimeZone("GMT-6");
		Calendar c = Calendar.getInstance(tz);
		return c.get(Calendar.HOUR_OF_DAY);
	  }
	
	  public static String pedirParams(Cifrador cifrador) {
			String parametro = "";
			if (cifrador.getListParams()== null) {
				parametro = "Escriba el texto"; 	
			}else {
				for(int i = 0; cifrador.getListParams().size() > i; i++) {
					parametro = "Digite el " +cifrador.getListParams().get(i).toString();
				System.out.println(parametro);	
				}  
			}
			return parametro;
	}

		public static void agregarParam(Cifrador cifrador, String respuesta) {
			//if (validarParametro() == true) {
			parametros.add(respuesta);
			System.out.println("Se ha agregado el parametro: " + parametros.toString());
			cifrador.setListParams(parametros); 
			//}
		}
		
		
		public static void setContext(Context contexto) {
			cont = contexto;	
		}
		
		public static void imprimirContexto() {
			System.out.println("Contexto: " +cont);
		}
		
		public static void agregarTipo(String pTexto) {
			valorTipo = pTexto;
		}
		
		public static boolean validarParametro() {
			if (parametros.size() > 1 ) {
			return false;
			} else {
			return true;
			}
			
		}
		
		 public static String realizarAccion(Cifrador pCifrador) {
			 System.out.println(accion);
		    if(accion.equals("codificar")) {
		    	return pCifrador.codificar(ServiciosChat.textoRespuesta);
		    } else {
		    	return pCifrador.decodificar(ServiciosChat.textoRespuesta);
		    }
		    	
		  }
		 
		 public static String traduccirTexto(String pTexto) throws Exception {
			 return Traductor.translate("es", "eng", pTexto);
			 
		 }
}


