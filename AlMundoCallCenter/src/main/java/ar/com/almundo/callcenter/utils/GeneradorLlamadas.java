package ar.com.almundo.callcenter.utils;

import java.util.concurrent.ArrayBlockingQueue;

import ar.com.almundo.callcenter.entities.Llamada;

/**
 * 
 * @author Pablo_Cumpe
 *
 */
public class GeneradorLlamadas {
	/** id llamada */
	private static int id;

	/**
	 * 
	 * @return
	 */
	public static Llamada crearLlamada() {
		GeneradorLlamadas.id++;
		return new Llamada(Integer.toString(id), 000, 000);
	}

	/**
	 * 
	 * @param cantidadDeLlamadas
	 * @return
	 */
	public static ArrayBlockingQueue<Llamada> crearListaLlamadas(int cantidadDeLlamadas) {
		ArrayBlockingQueue<Llamada> llamadas = new ArrayBlockingQueue<Llamada>(cantidadDeLlamadas);
		for (int i = 0; i < cantidadDeLlamadas; i++) {
			GeneradorLlamadas.id++;
			llamadas.add(new Llamada(Integer.toString(id), 000, 000));
		}
		return llamadas;
	}
}
