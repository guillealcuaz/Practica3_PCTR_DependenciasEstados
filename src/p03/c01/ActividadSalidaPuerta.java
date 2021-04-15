package src.p03.c01;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Guillermo Alcuaz Temiño
 * @author Pablo Marcos Bravo
 * @since JDK 11
 * @version 2.0
 */

public class ActividadSalidaPuerta implements Runnable{

	private static final int N_SALIDAS = 20;
	private IParque parque;
	private String puerta;

	/**
	* Constructor
	* 
	* @param puerta de salida del parque
	* @param parque sobre el que nos encontramos
	*/
	public ActividadSalidaPuerta(String puerta, IParque parque) {
		this.puerta = puerta;
		this.parque = parque;
	}

	@Override
	public void run() {
		for (int i = 0; i < N_SALIDAS; i++) {		
			try {
				parque.salirDelParque(puerta);
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5)*1000);
			} catch (InterruptedException e) {
				Logger.getGlobal().log(Level.INFO, "Salida interrumpida");
				Logger.getGlobal().log(Level.INFO, e.toString());
				return;
			}
		}
	}
}

