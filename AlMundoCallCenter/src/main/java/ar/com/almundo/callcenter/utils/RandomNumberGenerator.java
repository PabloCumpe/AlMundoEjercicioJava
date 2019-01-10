package ar.com.almundo.callcenter.utils;

import java.util.Random;

/**
 * 
 * @author Pablo_Cumpe
 *
 */
public class RandomNumberGenerator {

	/**
	 * Generar numero random entre dos parametros inclusive
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int generarNumeroRandom(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
