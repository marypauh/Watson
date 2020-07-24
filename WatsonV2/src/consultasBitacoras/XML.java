package consultasBitacoras;

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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XML {
	public static String filepath = "C:\\Users\\raque\\OneDrive\\Desktop\\bitacora.xml";
	
	public static void registrarAccion(String pFecha,String pHora, String pTexto, String pAccion) {
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
	
	public static int verificarExistenciaXML() throws FileNotFoundException {
		File yourFile = new File(filepath);
		if (yourFile.exists() == true) {
			return 2;
		}else {
			FileOutputStream oFile = new FileOutputStream(yourFile, false); 
			return 1;
		}

	}
	
	public static Document agregarPrimerRegistro(String pHora, String pFecha, String pTexto, String pAccion, Document pDoc) {
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
	
	public static Document agregarRegistro(String pHora, String pFecha, String pTexto, String pAccion, Document pDoc) {
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
	
public static String filtrarPorAccion(String pAccion) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true); // never forget this!
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(filepath);
    
    XPathFactory xpathfactory = XPathFactory.newInstance();
    XPath xpath = xpathfactory.newXPath();

    //Create XPath
    System.out.println("//11) Get book titles written by Neal Stephenson");
    
    XPathExpression expr = xpath.compile("//registro[accion = '"+ pAccion +"']");
    Object result = expr.evaluate(doc, XPathConstants.NODESET);
    System.out.println("ihi");
    NodeList nodes = (NodeList) result;
    System.out.println(nodes.getLength());
    return getRegistros(nodes);

    
}

public static String filtrarPorHoy() throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
	String date = "29/08/2020";
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true); // never forget this!
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(filepath);
    
    XPathFactory xpathfactory = XPathFactory.newInstance();
    XPath xpath = xpathfactory.newXPath();

    XPathExpression expr = xpath.compile("//registro[@fecha = '"+ date +"']");
    Object result = expr.evaluate(doc, XPathConstants.NODESET);
    NodeList nodes = (NodeList) result;
    System.out.println(nodes.getLength());
    return getRegistros(nodes);
}

public static String getTodos() throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true); // never forget this!
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(filepath);
    
    Element root = doc.getDocumentElement();
    NodeList nodes = doc.getElementsByTagName("registro");

    return getRegistros(nodes);
}


public static String getRegistros(NodeList pNodos) {
	String resultado = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +  
			" <bitacora>";
	for (int i = 0; i < pNodos.getLength(); i++) {
        resultado += "  <registro fecha=\""+ pNodos.item(i).getAttributes().item(0).toString()+"\">\r\n" + 
        		"   <texto>"+ pNodos.item(i).getChildNodes().item(0).getTextContent()+"</texto>\r\n" +  
        		"   <accion>"+pNodos.item(i).getChildNodes().item(1).getTextContent()+"</accion>\r\n" + 
        		"   <hora>"+pNodos.item(i).getChildNodes().item(2).getTextContent()+"</hora>\r\n" + 
        		"  </registro>\r\n";
    }
	resultado += " </ bitacora>";
	
	return resultado;
	
}

}
