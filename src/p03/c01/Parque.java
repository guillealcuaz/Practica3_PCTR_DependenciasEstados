package src.p03.c01;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * 
 * @author Guillermo Alcuaz Temiño
 * @author Pablo Marcos Bravo
 * @since JDK 11
 * @version 2.0
 */

public class Parque implements IParque{

	private static final int NUMENTRADAS = 20;
	// TODO 
	private int contadorPersonasTotales;
	private Hashtable<String, Integer> contadoresPersonasPuerta;
	
	
	public Parque() {
		contadorPersonasTotales = 0;
		contadoresPersonasPuerta = new Hashtable<String, Integer>();
	}


	@Override
	public void entrarAlParque(String puerta){		
		try {
			comprobarAntesDeEntrar();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
		}
		
		// Aumentamos el contador total y el individual
		contadorPersonasTotales++;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)+1);
		
		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Entrada");		
		checkInvariante();
		notify();		
	}
	
	public synchronized void salirDelParque(String puerta) {

		try {
			comprobarAntesDeEntrar();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (contadoresPersonasPuerta.get(puerta) == null) {
			contadoresPersonasPuerta.put(puerta, 0);
		}
		
		contadorPersonasTotales--;
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta) - 1);

		imprimirInfo(puerta, "Salida");		
		checkInvariante();		
		notify();
	}
	
	
	private void imprimirInfo (String puerta, String movimiento){
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("--> Personas en el parque " + contadorPersonasTotales); //+ " tiempo medio de estancia: "  + tmedio);
		
		// Iteramos por todas las puertas e imprimimos sus entradas
		for(String p: contadoresPersonasPuerta.keySet()){
			System.out.println("----> Por puerta " + p + " " + contadoresPersonasPuerta.get(p));
		}
		System.out.println(" ");
	}
	
	private int sumarContadoresPuerta() {
		int sumaContadoresPuerta = 0;
			Enumeration<Integer> iterPuertas = contadoresPersonasPuerta.elements();
			while (iterPuertas.hasMoreElements()) {
				sumaContadoresPuerta += iterPuertas.nextElement();
			}
		return sumaContadoresPuerta;
	}
	
	protected void checkInvariante() {
		assert sumarContadoresPuerta() == contadorPersonasTotales : "INV: La suma de contadores de las puertas debe ser igual al valor del contador del parte";
		// TODO 
		// TODO
	}

	protected void comprobarAntesDeEntrar() throws InterruptedException{	// TODO
		while(contadorPersonasTotales == NUMENTRADAS) {
			wait();
		}
	}

	protected void comprobarAntesDeSalir() throws InterruptedException{		// TODO
		while(contadorPersonasTotales == 0) {
			wait();
		}
	}
}
