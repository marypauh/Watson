package worshop.chat;

import java.io.FileWriter;
import java.io.InputStream;
import factory.CifradorFactory;
import modelo.Cifrador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.Context;
import com.ibm.watson.developer_cloud.assistant.v1.model.DialogNode;
import com.ibm.watson.developer_cloud.assistant.v1.model.DialogNodeOutput;
import com.ibm.watson.developer_cloud.assistant.v1.model.GetDialogNodeOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.assistant.v1.model.RuntimeEntity;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.opencsv.CSVWriter;

import modelo.Bitacora;
import modelo.BitacoraXML;
import Archivos.CSV;
import Archivos.TXT;
import Archivos.XML;
import decorador.DecoradorArchivos;


@Path("/chatservice")
public class ChatService {

private String tipoFinal;
	
	private String urlDB;
	private String userDB;
	private String passwordDB;
	private String apiKey = "Qso2rLcZLbpPhJhCXmH4Tfw9fgEyoGjmuCgNoSazZRHS";
	private String assistantURL = "https://api.us-south.assistant.watson.cloud.ibm.com/instances/b9d3ad33-7e3e-46af-b799-54828842c510";
	private static String workspaceId = "9f06737a-5357-4846-b257-53ea2c62ee67";
	
	
	
	public ChatService(){
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	  

	@GET
	@Produces("application/json")
	public Response getResponse(@QueryParam("conversationMsg") String conversationMsg, @QueryParam("conversationCtx") String conversationCtx) throws Exception {
		Assistant service = serviceBuild();
		Context context = setContext(conversationCtx);	
		InputData input = new InputData.Builder(conversationMsg).build();
		MessageOptions options = new MessageOptions.Builder(workspaceId).input(input).context(context).build();
		MessageResponse assistantResponse = getAssistant(service,conversationMsg,context);
		context.put("saludo", ServiciosChat.determinarHora());
		
		//String tipoAccion = (String) context.get("accion");	
		//System.out.println("ACCION: " +tipoAccion);
		
		CifradorFactory factory = new CifradorFactory();
		Cifrador cifrador;
		String respuestaParam;
		
		
			
		try {
			Context contexto = assistantResponse.getContext();
			ServiciosChat.setContext(contexto);
			ServiciosChat.imprimirContexto();
			
			String tipoAccion = (String) ServiciosChat.cont.get("accion");
			ServiciosChat.accion = tipoAccion;
			
			String tipoB = (String) ServiciosChat.cont.get("tipoCifrador");
			System.out.println("VALOR: " +tipoB);
			ServiciosChat.valorTipo = tipoB;
			
			respuestaParam = (String) ServiciosChat.cont.get("parametro");
			ServiciosChat.parametro = respuestaParam;
			
			String respuestaTexto = (String) ServiciosChat.cont.get("respuestaTexto");
			ServiciosChat.textoRespuesta = respuestaTexto;
			
			String textoCompleto = (String) ServiciosChat.cont.get("completo");
			ServiciosChat.completo = textoCompleto;
			
			String tipoBitacora = (String) ServiciosChat.cont.get("tipoBitacora");
			ServiciosChat.tipoBitacora = tipoBitacora;
			
			String opcionBitacora = (String) ServiciosChat.cont.get("opcionBitacora");
			ServiciosChat.opcionBitacora = opcionBitacora;
			
			String textoBitacora = (String) ServiciosChat.cont.get("textoBitacora");
			ServiciosChat.textoBitacora = textoBitacora;
			
			String realizaAccion = (String) ServiciosChat.cont.get("cifrar");
			ServiciosChat.realizoAccion = realizaAccion;
	
		} catch (Exception e) {
			
		}
		
		 
		if(ServiciosChat.tipoBitacora != null){
			
			if(ServiciosChat.tipoBitacora.equals("csv")) {
				
				if(ServiciosChat.opcionBitacora != null) {
					
					if(ServiciosChat.opcionBitacora.equals("1")) {
						ServiciosChat.textoBitacora = CSV.leerFecha();
						context.put("textoBitacora", ServiciosChat.textoBitacora);
						
						} else if (ServiciosChat.opcionBitacora.equals("2")){
							ServiciosChat.textoBitacora = CSV.leerAcciones("codificar");
							context.put("textoBitacora", ServiciosChat.textoBitacora);
						
							} else if (ServiciosChat.opcionBitacora.equals("3")){
								ServiciosChat.textoBitacora = CSV.leerAcciones("decodificar");
								context.put("textoBitacora", ServiciosChat.textoBitacora);
						
								} else { 
									ServiciosChat.textoBitacora = CSV.leerCsv();
									context.put("textoBitacora", ServiciosChat.textoBitacora);
									
					}
				}
			} else if(ServiciosChat.tipoBitacora.equals("txt")) {
			
			if(ServiciosChat.opcionBitacora != null) {
				
				if(ServiciosChat.opcionBitacora.equals("1")) {
					ServiciosChat.textoBitacora = TXT.leerFechaTxt();
					context.put("textoBitacora", ServiciosChat.textoBitacora);
					
					} else if (ServiciosChat.opcionBitacora.equals("2")){
						ServiciosChat.textoBitacora = TXT.leerAccionesTxt("codificar");
						context.put("textoBitacora", ServiciosChat.textoBitacora);
					
						} else if (ServiciosChat.opcionBitacora.equals("3")){
							ServiciosChat.textoBitacora = TXT.leerAccionesTxt("decodificar");
							context.put("textoBitacora", ServiciosChat.textoBitacora);
					
							} else {
								ServiciosChat.textoBitacora = TXT.leerTxt();
								context.put("textoBitacora", ServiciosChat.textoBitacora);
								
				}
			}
		} else {
			if(ServiciosChat.opcionBitacora != null) {
				
				BitacoraXML bit = new BitacoraXML();
				bit = XML.leerXML();
				if(ServiciosChat.opcionBitacora.equals("1")) {
					BitacoraXML bitFiltrado = bit.getRegistrosHoy(ServiciosChat.fijarFecha());
					ServiciosChat.textoBitacora = XML.bitacoraToStringXML(bitFiltrado);
					context.put("textoBitacora", ServiciosChat.textoBitacora);
					
					} else if (ServiciosChat.opcionBitacora.equals("2")){
						BitacoraXML bitFiltrado = bit.getRegistrosCodificar();
						ServiciosChat.textoBitacora = XML.bitacoraToStringXML(bitFiltrado);
						context.put("textoBitacora", ServiciosChat.textoBitacora);
					
						} else if (ServiciosChat.opcionBitacora.equals("3")){
							BitacoraXML bitFiltrado = bit.getRegistrosDecodificar();
							ServiciosChat.textoBitacora = XML.bitacoraToStringXML(bitFiltrado);
							context.put("textoBitacora", ServiciosChat.textoBitacora);
					
							} else {
								ServiciosChat.textoBitacora = XML.bitacoraToStringXML(bit);
								context.put("textoBitacora", ServiciosChat.textoBitacora);
								
				}
			}
		}
	}
		
		if(ServiciosChat.valorTipo != null) {
			try {
				
				
				cifrador = factory.crearCifrador(ServiciosChat.valorTipo);
				String texto = ServiciosChat.pedirParams(cifrador);
				System.out.println(texto);
				context.put("pedirParametros", texto);
				System.out.println("hola");
				
				if(ServiciosChat.parametro != null) {
					System.out.println(ServiciosChat.parametro);
					ServiciosChat.agregarParam(cifrador,ServiciosChat.parametro);
				
				
					if (ServiciosChat.textoRespuesta != null) {
						System.out.println("TEXTO: " + ServiciosChat.textoRespuesta + "Cifrador: " + cifrador);
						ServiciosChat.textoLISTO = ServiciosChat.realizarAccion(cifrador);
						System.out.println(ServiciosChat.textoLISTO);
						System.out.println(ServiciosChat.bitacoras.toString());
						context.put("textoFinal", ServiciosChat.textoLISTO);
						ServiciosChat.parametros.clear();
							CSV.agregarBitacora(CSV.bitacoras, ServiciosChat.fijarFecha(), ServiciosChat.fijarHora(), ServiciosChat.accion, ServiciosChat.textoLISTO);
							TXT.agregarBitacora(TXT.bitacoras, ServiciosChat.fijarFecha(), ServiciosChat.fijarHora(), ServiciosChat.accion, ServiciosChat.textoLISTO);
							XML.agregarBitacoras(ServiciosChat.fijarFecha(), ServiciosChat.fijarHora(), ServiciosChat.accion, ServiciosChat.textoLISTO);
						
					} 
						
				}
					else if (ServiciosChat.completo != null) {
						System.out.println("Todo el texto: " + ServiciosChat.completo);
						
						if (ServiciosChat.valorTipo.equals("Cesar") || ServiciosChat.valorTipo.equals("Llave") || ServiciosChat.valorTipo.equals("Vigenere")) {
							ServiciosChat.parametro = ServiciosChat.obtenerTexto(ServiciosChat.completo);
							
							System.out.println("Param: " +ServiciosChat.parametro);
							ServiciosChat.agregarParam(cifrador,ServiciosChat.parametro);
							
							ServiciosChat.textoRespuesta = ServiciosChat.MatcherSimple(ServiciosChat.completo);
							System.out.println("TEXTO LISTO: " +ServiciosChat.textoRespuesta);
							
						} else {
						ServiciosChat.textoRespuesta = ServiciosChat.obtenerTexto(ServiciosChat.completo);
						System.out.println("TEXTO CORTADO: " +ServiciosChat.parametro);
						}
						ServiciosChat.textoLISTO = ServiciosChat.realizarAccion(cifrador);
						context.put("textoCompleto", ServiciosChat.textoLISTO);
						ServiciosChat.parametros.clear();
						System.out.println("2do");
					//	CSV.agregarBitacora(CSV.bitacoras, ServiciosChat.fijarFecha(), ServiciosChat.fijarHora(), ServiciosChat.accion, ServiciosChat.textoLISTO);
						TXT.agregarBitacora(TXT.bitacoras, ServiciosChat.fijarFecha(), ServiciosChat.fijarHora(), ServiciosChat.accion, ServiciosChat.textoLISTO);
					}
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
		
		//RepeticiOn innecesaria (mete nuevo contexto a la conversaciOn)
		
		input = new InputData.Builder(conversationMsg).build();
		options = new MessageOptions.Builder(workspaceId).input(input).context(context).build();
		assistantResponse = service.message(options).execute();
		JSONObject object = Output(assistantResponse);
		return Response.status(Status.OK).entity(object.toString()).build();
			}	
	
	
	//Metodos de conexion
	private Assistant serviceBuild() {
		IamOptions iAmOptions = new IamOptions.Builder()
			.apiKey(apiKey)
		    .build();
		Assistant service = new Assistant("2018-09-20", iAmOptions);
		service.setEndPoint(assistantURL);
	return service;
	}
	
	private Context setContext(String conversationCtx) {
		JSONObject ctxJsonObj = new JSONObject(conversationCtx);
		Context context = new Context();
		context.putAll(ctxJsonObj.toMap());	
		return context;
	}
	
	private MessageResponse getAssistant(Assistant service, String conversationMsg, Context context) {
		InputData input = new InputData.Builder(conversationMsg).build();
		MessageOptions options = new MessageOptions.Builder(workspaceId).input(input).context(context).build();
		MessageResponse assistantResponse = service.message(options).execute();
	return assistantResponse;
	}
	
	
    private JSONObject Output(MessageResponse assistantResponse) {
			List<String> assistantResponseList = assistantResponse.getOutput().getText();
			JSONObject object = new JSONObject();
			
			String assistantResponseText = "";
			for (String tmpMsg : assistantResponseList)
				assistantResponseText = assistantResponseText + System.lineSeparator() + tmpMsg;
				
			object.put("response", assistantResponseText);
			object.put("context", assistantResponse.getContext());
			return object;
	     }
    
    
   
	
}
