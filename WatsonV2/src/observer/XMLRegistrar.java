package observer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class XMLRegistrar {
public static String filepath = "C:\\Users\\raque\\OneDrive\\Desktop\\bitacora.xml";
	
	
	private static int verificarExistenciaXML() throws FileNotFoundException {
		File yourFile = new File(filepath);
		if (yourFile.exists() == true) {
			return 2;
		}else {
			FileOutputStream oFile = new FileOutputStream(yourFile, false); 
			return 1;
		}

	}
	
	private static Document agregarPrimerRegistro(String pHora, String pFecha, String pTexto, String pAccion, Document pDoc) {
		Element root = pDoc.createElement("bitacora");
		pDoc.appendChild(root);

        Element registro = pDoc.createElement("registro");

        root.appendChild(registro);

        //Fecha
        Attr attr = pDoc.createAttribute("fecha");
        attr.setValue(pFecha);
        registro.setAttributeNode(attr);
                
        // Texto que se codifico o decodifico
        Element texto = pDoc.createElement("texto");
        texto.appendChild(pDoc.createTextNode(pTexto));
        registro.appendChild(texto);

        // Accion (codificacion o decodificacio)
        Element accion = pDoc.createElement("accion");
        accion.appendChild(pDoc.createTextNode(pAccion));
        registro.appendChild(accion);
        
        Element hora = pDoc.createElement("hora");
        hora.appendChild(pDoc.createTextNode(pHora));
        registro.appendChild(hora);
        
        return pDoc;
	}
	
	private static Document agregarRegistro(String pHora, String pFecha, String pTexto, String pAccion, Document pDoc) {
		Node root = pDoc.getFirstChild();
		
		 Element registro = pDoc.createElement("registro");

	        root.appendChild(registro);

	        //Fecha
	        Attr attr = pDoc.createAttribute("fecha");
	        attr.setValue(pFecha);
	        registro.setAttributeNode(attr);
	                
	        // Texto que se codifico o decodifico
	        Element texto = pDoc.createElement("texto");
	        texto.appendChild(pDoc.createTextNode(pTexto));
	        registro.appendChild(texto);

	        // Accion (codificacion o decodificacio)
	        Element accion = pDoc.createElement("accion");
	        accion.appendChild(pDoc.createTextNode(pAccion));
	        registro.appendChild(accion);
	        
	        Element hora = pDoc.createElement("hora");
	        hora.appendChild(pDoc.createTextNode(pHora));
	        registro.appendChild(hora);
	        
	        return pDoc;
		
	}

	public static void registrarAccion(String pFecha, String pHora, String pTexto, String pAccion) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc;
			if (verificarExistenciaXML() == 1) {
	             doc = docBuilder.newDocument();
			}else {
				 doc = docBuilder.parse(filepath);
			}
			if (doc.getFirstChild() == null) {
					doc = agregarPrimerRegistro(pHora, pFecha,pTexto,pAccion, doc);
					System.out.print("1");

			}else {
				System.out.print("2");

					doc = agregarRegistro(pHora, pFecha,pTexto,pAccion,doc);
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			System.out.print(doc == null);
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
