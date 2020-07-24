package observer;

public abstract class RegistrarObservador {
	private String pathfile;
	public  abstract  void registrarAccion(String pFecha,String pHora, String pTexto, String pAccion);

}
