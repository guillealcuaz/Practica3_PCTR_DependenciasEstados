package src.p03.c01;

/**
 * Parque.Comportamiento común para todos los parques
 * 
 * @author Guillermo Alcuaz Temiño
 * @author Pablo Marcos Bravo
 * @since JDK 11
 * @version 2.0
 */

public interface IParque {
	/**
	 * 
	 * Método que analiza las personas que entran por puerta
	 * @param puerta del parque por la que entra al parque
	 */
	public abstract void entrarAlParque(String puerta);
	
	/**
	 * 
	 * Método que analiza las personas que salen por puerta
	 * @param puerta por la que sale al parque
	 */
	public abstract void salirDelParque(String puerta);
}
