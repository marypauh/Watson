package modelo;

public class TransPalabra extends Transposicion {

	@Override
	protected String invertir(String pTexto) {
		  String textoInvertido = "";
		    String[] palabras = pTexto.split("\\s+");
		      
		    for (String palabra : palabras) {
		      textoInvertido += invertirUnTexto(palabra) + " ";
		    }
		    return textoInvertido;

	  }    
}
