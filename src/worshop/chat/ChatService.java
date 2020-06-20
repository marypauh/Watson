package worshop.chat;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

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
import factory.CifradorFactory;

import modelo.Cifrador;

@Path("/chatservice")
public class ChatService {

	
	private String urlDB;
	private String userDB;
	private String passwordDB;
	private String apiKey = "Qso2rLcZLbpPhJhCXmH4Tfw9fgEyoGjmuCgNoSazZRHS";
	private String assistantURL = "https://api.us-south.assistant.watson.cloud.ibm.com/instances/b9d3ad33-7e3e-46af-b799-54828842c510";
	private static String workspaceId = "96ed91ec-779c-476d-8c1c-ca8a508a32fd";
	
	public ChatService(){
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@GET
	@Produces("application/json")
	public void getResponse(@QueryParam("conversationMsg") String conversationMsg, @QueryParam("conversationCtx") String conversationCtx) {
		
		IamOptions iAmOptions = new IamOptions.Builder()
			.apiKey(apiKey)
		    .build();

		Assistant service = new Assistant("2018-09-20", iAmOptions);
		service.setEndPoint(assistantURL);
		
		// Initialize with empty value to start the conversation.
		JSONObject ctxJsonObj = new JSONObject(conversationCtx);
		Context context = new Context();
		context.putAll(ctxJsonObj.toMap());		
		
		InputData input = new InputData.Builder(conversationMsg).build();
		MessageOptions options = new MessageOptions.Builder(workspaceId).input(input).context(context).build();
		
		MessageResponse assistantResponse = service.message(options).execute();
		System.out.println(assistantResponse);
		
		
		//DespuEs del assistant Response manipulamos el contexto

		String tipo = (String) context.get("tipo");
		
		CifradorFactory factory = new CifradorFactory();
		Cifrador cifrador;
		try {
			cifrador = factory.crearCifrador(tipo);
			String tipoAccion = (String) context.get("accion");
			pedirParams(cifrador);	
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

		// Print the output from dialog, if any.
		 List<String> assistantResponseList = assistantResponse.getOutput().getText();
		JSONObject object = new JSONObject();
		
		String assistantResponseText = "";
		for (String tmpMsg : assistantResponseList)
			assistantResponseText = assistantResponseText + System.lineSeparator() + tmpMsg;
			
		object.put("response", assistantResponseText);
		object.put("context", assistantResponse.getContext());
		System.out.println();
		
		return Response.status(Status.OK).entity(object.toString()).build();
	}
	
	private void pedirParams(Cifrador cifrador) {
		Context context = new Context();
		
		if (cifrador.getListParams()== null) {
			context.put("pedirParametro", "Escriba el texto");
			 String parametro = (String) context.get("respuesta");
			
		}else {
			ArrayList<String> parametros = new ArrayList<String>();
			for(int i = 0; cifrador.getListParams().size() > i; i++) {
			context.put("pediParametro","Digite el " +cifrador.getListParams().get(i).toString());
			 String parametro = (String) context.get("respuesta");
		     parametro.toLowerCase();
		     parametros.add(parametro);
			}		
			cifrador.setListParams(parametros);  
		}
}
}
