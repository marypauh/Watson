package servicio;

import factory.CifradorFactory;
import modelo.Cifrador;

public class ServicioCifrador {
	private CifradorFactory factory;
	
	public Cifrador crearCifrado(String pTipo) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return factory.crearCifrador(pTipo);
	}

}
