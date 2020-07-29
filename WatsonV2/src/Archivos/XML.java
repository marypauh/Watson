package Archivos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import modelo.BitacoraXML;
import modelo.Registro;
import worshop.chat.ServiciosChat;

public class XML {
	public static String filepath = "C:\\Users\\raque\\OneDrive\\Desktop\\bitacora.xml";

	public static void crearBitacoraXML(BitacoraXML pBitacora) throws JAXBException {
		File file = new File(filepath);
		JAXBContext jaxbContext = JAXBContext.newInstance(BitacoraXML.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(pBitacora, file);
	}

	  public static BitacoraXML leerXML() throws ParserConfigurationException, SAXException, IOException, JAXBException{
		    File file = new File(filepath);
		    JAXBContext jaxbContext = JAXBContext.newInstance(BitacoraXML.class);
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		    BitacoraXML bitacora = (BitacoraXML) jaxbUnmarshaller.unmarshal(file);
		    return bitacora;
		  }
	  /**
	   * Permite validar si existen los archivos de la
	   * bitácora, de lo contrario los crea.
	   * @throws JAXBException
	   * @throws IOException
	   */
	  public static void validarArchivos() throws JAXBException, IOException {
	    File xml = new File(filepath);
	    BitacoraXML bitacora = new BitacoraXML();
	    if(!xml.exists()) {
	      crearBitacoraXML(bitacora);
	    }  
	  }
	  /**
	   * Lee el archivo y muestra el árbol de
	   * información que contiene,
	   * @param pBitacoraXML
	   * @return
	   * @throws JAXBException
	   */
	  public static String bitacoraToStringXML(BitacoraXML pBitacoraXML) throws JAXBException {
	    JAXBContext jaxbContext = JAXBContext.newInstance(BitacoraXML.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    StringWriter writer = new StringWriter();
	    jaxbMarshaller.marshal(pBitacoraXML, writer);
	    String resultadoXML = writer.toString();
	    return resultadoXML;
	  }
	  /**
	   * Lee la información existente en la bitácora
	   * y la muestra.
	   * @param pBitacora
	   * @param pTipoSeparador
	   * @return
	   */
	  
	  
	  public static void agregarBitacoras(String pFecha, String pHora, String pAccion, String pTexto) throws ParserConfigurationException, SAXException, IOException, JAXBException {
		XML.validarArchivos();
		BitacoraXML bitacora = XML.leerXML();
		Registro registro = new Registro();
		registro.registrarDatos(pFecha, pHora, pAccion, pTexto);
		bitacora.agregarRegistro(registro);
		XML.crearBitacoraXML(bitacora);
	}
		
}

