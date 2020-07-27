package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import modelo.Registro;
import modelo.Bitacora;


public class utilBitacora {
	public static String filepath = "C:\\Users\\raque\\OneDrive\\Desktop\\bitacora";
	private int contador = 0;
	
	public static void crearBitacoraXML(Bitacora pBitacora) throws JAXBException {
		File file = new File(filepath + ".xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Bitacora.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(pBitacora, file);
	}

	  public static Bitacora leerXML() throws ParserConfigurationException, SAXException, IOException, JAXBException{
		    File file = new File(filepath+".xml");
		    JAXBContext jaxbContext = JAXBContext.newInstance(Bitacora.class);
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		    Bitacora bitacora = (Bitacora) jaxbUnmarshaller.unmarshal(file);
		    return bitacora;
		  }
	  /**
	   * Permite validar si existen los archivos de la
	   * bitácora, de lo contrario los crea.
	   * @throws JAXBException
	   * @throws IOException
	   */
	  public static void validarArchivos() throws JAXBException, IOException {
	    File xml = new File(filepath+".xml");
	    File csv = new File(filepath+".csv");
	    File txt = new File(filepath+".txt");
	    Bitacora bitacora = new Bitacora();
	    if(!xml.exists()) {
	      crearBitacoraXML(bitacora);
	    }
	    if(!csv.exists()) {
	      crearBitacora(bitacora, "csv");	
	    }
	    if(!txt.exists()) {
		  crearBitacora(bitacora, "txt");		
	    }
	    
		  
	  }
	  
	  
	  private static void crearBitacora(Bitacora pBitacora,String pTipoSeparador, String pTipoArchivo) throws IOException {
		FileWriter file= new FileWriter(filepath+"."+pTipoArchivo,true);  
		for(Registro registro: pBitacora.registros ) {
		  file.append(registro.getFecha());
		  file.append(pTipoSeparador);
		  file.append(registro.getHora());
		  file.append(pTipoSeparador);
		  file.append(registro.getAccion());
		  file.append(pTipoSeparador);
	      file.append(registro.getTexto());
	      file.append(pTipoSeparador);
	      file.append("\n");
		}
		file.flush();
		file.close();
	  }
	  
	  /**
	   * Crea la bitácora dependiendo del 
	   * tipo de archivo que sea.
	   * @param pBitacora
	   * @param pTipoArchivo
	   * @throws IOException
	   */
	  public static void crearBitacora(Bitacora pBitacora, String pTipoArchivo) throws IOException {
		if(pTipoArchivo.equals("txt")) {
	  	crearBitacora(pBitacora, "\t", pTipoArchivo);
	  	return;
	    }
	    crearBitacora(pBitacora, ",", pTipoArchivo);
	  }
	  
	  /**
	   * Lee el archivo y muestra el árbol de
	   * información que contiene,
	   * @param pBitacoraXML
	   * @return
	   * @throws JAXBException
	   */
	  public static String bitacoraToStringXML(Bitacora pBitacoraXML) throws JAXBException {
	    JAXBContext jaxbContext = JAXBContext.newInstance(Bitacora.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    StringWriter writer = new StringWriter();
	    jaxbMarshaller.marshal(pBitacoraXML, writer);
	    String resultadoXML = writer.toString();
	    return resultadoXML;
	  }
	  
	  /**
	   * Lee la bitácora dependiendo del tipo
	   * que sea, csv o txt.
	   */
	  public static String leerBitacora(Bitacora pBitacora,String pTipoArchivo) {
		if(pTipoArchivo.equals("txt")) {
	      return bitacoraToString(pBitacora,"\t");
	    }
	    return bitacoraToString(pBitacora, ",");  
	  }
	  
	  /**
	   * Lee la información existente en la bitácora
	   * y la muestra.
	   * @param pBitacora
	   * @param pTipoSeparador
	   * @return
	   */
	  public static String bitacoraToString(Bitacora pBitacora, String pTipoSeparador) {
		String resultado = "";
		for(Registro registro: pBitacora.registros){
		System.out.println("HOLAAA" + pBitacora.registros.get(0).fecha);
		  resultado += registro.getFecha();
		  resultado += pTipoSeparador;
		  resultado += registro.getHora();
		  resultado += pTipoSeparador;
		  resultado += registro.getAccion();
		  resultado += pTipoSeparador;
		  resultado += registro.getTexto();
		  resultado += pTipoSeparador;
		  resultado += "\n";		
		}
		return resultado;
	  }
	  
	  /**
	   * Permite leer la bitácora dependiendo
	   * del tipo que sea, csv o txt.
	   * @param pTipoArchivo
	   * @return
	   * @throws IOException
	   */
	  public static Bitacora leerBitacora(String pTipoArchivo) throws IOException {
		if(pTipoArchivo.equals("csv")) {
			return leerInformacionBitacora("csv", ",");
		}
		return leerInformacionBitacora("txt", "\t"); 
		  
	  }
	  
	  /**
	   * Lee la información de la bitácora.
	   * @param pTipoArchivo
	   * @param pTipoSeparador
	   * @return
	   * @throws IOException
	   */
	  public static Bitacora leerInformacionBitacora(String pTipoArchivo,String pTipoSeparador) throws IOException {
	    BufferedReader buffered = new BufferedReader(new FileReader(filepath+"."+pTipoArchivo));
	    Bitacora bitacora = new Bitacora();
	    String linea = "";
	     while((linea = buffered.readLine())!= null) {
	       String[] resultado = linea.split(pTipoSeparador) ;
	       Registro registro = new Registro();
	       registro.registrarDatos(resultado[0], resultado[1], resultado[2], resultado[3]);
	       bitacora.agregarRegistro(registro);
	     }
	     buffered.close();
	     return bitacora;
	    
	  }
	  
	  /**
	   * Determina el criterio de la bitácora
	   * a buscar y realiza la operación respectiva.
	   * @param pCriterioBitacora
	   * @param pBitacora
	   * @return
	   */
	public static Bitacora filtrarBitacora(String pCriterioBitacora,Bitacora pBitacora,String pFechaHoy){
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
	  
	  
	  /**
	   * Determina la fuente de la bitácora
	   * a buscar y realiza la operación respectiva.
	   * @param pCriterioBitacora
	   * @param pBitacora
	   * @return
	   */
	  public static Bitacora determinarFuenteBitacora(String pFuenteBitacora) throws IOException, ParserConfigurationException, SAXException, JAXBException{
		if(pFuenteBitacora.equals("csv")) {
	      return utilBitacora.leerBitacora("csv");
	    } 
		else if(pFuenteBitacora.equals("txt")) {
	      return utilBitacora.leerBitacora("txt");
	    }
		return utilBitacora.leerXML();
	    
	  }  
	  
	  
}
