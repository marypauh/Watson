package modelo;
import factory.CifradorFactory;
import modelo.Cifrador;

public class servicioCifrador {
	private CifradorFactory factory;
	
	public Cifrador crearCifrado(String pTipo) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return factory.crearCifrador(pTipo);
	}
	
}
