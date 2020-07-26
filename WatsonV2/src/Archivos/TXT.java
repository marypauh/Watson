package Archivos;

import java.io.Writer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;

import modelo.Bitacora;
import worshop.chat.ServiciosChat;


public class TXT {
	
	private static int contador =0;
	private static File archivo = new File("C:\\Users\\personal\\Documents\\GitHub\\Watson\\WatsonV2\\src\\Bitacora.txt");
	public static ArrayList<Bitacora> bitacoras = new ArrayList<Bitacora>();
	
	public static void agregarBitacora(ArrayList<Bitacora> bitacoras, String pFecha, String pHora, String pAccion, String pTexto) {
		if (contador == 0) {
			Bitacora bit = new Bitacora(pFecha,pHora,pAccion,pTexto); 
			bitacoras.add(bit);
			crearTxt(bitacoras);
			contador +=1;
		}
	}
	
	private static void crearTxt(ArrayList<Bitacora> bitacoras) { 
			FileWriter fileWriter = null; 
			
		try {
			  
			fileWriter =  new FileWriter(archivo,true);
		   for(Bitacora b: bitacoras) {
		    fileWriter.append(b.getFecha());
		    fileWriter.append(",");
		    fileWriter.append(b.getHora());
		    fileWriter.append(",");
		    fileWriter.append(b.getAccion());
		    fileWriter.append(",");
		    fileWriter.append(b.getTexto());
		    fileWriter.append("\n");
		   }
		  } catch (Exception ex) {
		   ex.printStackTrace();
		  } finally {
		   try {
		    fileWriter.flush();
		    fileWriter.close();
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		  }
		 }
	
	
	public static String leerTxt() throws IOException {
		CSVReader csvReader = new CSVReader(new FileReader(archivo));
		String[] fila = null;
		String texto = "";
		
		while((fila = csvReader.readNext()) != null) {
			Bitacora bit = new Bitacora(fila[0],fila[1],fila[2],fila[3]);
			texto += bit.getFecha() + " " + bit.getHora() + " " + bit.getAccion() + " " + bit.getTexto()  + "\n";
			System.out.println(bit.getFecha() + " " + bit.getHora() + " " + bit.getAccion() + " " + bit.getTexto());
			bitacoras.clear();
			bitacoras.add(bit);
		}
		csvReader.close();
		return texto;
	}
	
	public static String leerAccionesTxt(String tipo) throws IOException {
		CSVReader csvReader = new CSVReader(new FileReader(archivo));
		String[] fila = null;
		String texto = "";
		
		while((fila = csvReader.readNext()) != null) {
			Bitacora b = new Bitacora(fila[0],fila[1],fila[2],fila[3]);
			if(b.getAccion().equals(tipo)) {
				texto += b.getFecha() + " " + b.getHora() + " " + b.getAccion() + " " + b.getTexto() + "\n";
				System.out.println(b.getFecha() + " " + b.getHora() + " " + b.getAccion() + " " + b.getTexto());
			}
		bitacoras.clear();
		bitacoras.add(b);
		}
		csvReader.close();
		return texto;
	}
	
	public static String leerFechaTxt() throws IOException {
		CSVReader csvReader = new CSVReader(new FileReader(archivo));
		String[] fila = null;
		String texto = "";
		
		while((fila = csvReader.readNext()) != null) {
			Bitacora b = new Bitacora(fila[0],fila[1],fila[2],fila[3]);
			System.out.println("Fecha");
			if(compararFechas(b.getFecha())) {
				texto += b.getFecha() + " " + b.getHora() + " " + b.getAccion() + " " + b.getTexto() + "\n";
				System.out.println(b.getFecha() + " " + b.getHora() + " " + b.getAccion() + " " + b.getTexto());
			}
		bitacoras.clear();
		bitacoras.add(b);
		}
		csvReader.close();
		return texto;
	}
	
	private static boolean compararFechas(String pFecha) {
		if (ServiciosChat.fijarFecha().equals(pFecha)) {
			return true;
		} else {
			return false;
			
		}
	}
	
	
	


	
}