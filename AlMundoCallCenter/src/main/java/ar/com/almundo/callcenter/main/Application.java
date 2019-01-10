package ar.com.almundo.callcenter.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

import ar.com.almundo.callcenter.dispatcher.Dispatcher;
import ar.com.almundo.callcenter.entities.Empleado;
import ar.com.almundo.callcenter.entities.Llamada;
import ar.com.almundo.callcenter.thread.CustomRunnable;
import ar.com.almundo.callcenter.utils.GeneradorEmpleados;
import ar.com.almundo.callcenter.utils.GeneradorLlamadas;

/**
 * @author Pablo_Cumpe
 *
 */

public class Application {

	private final static Logger LOGGER = Logger.getLogger(Application.class);

	public static void main(String[] args) {
		try {
			LOGGER.info("Empieza aplicacion");
			validarParametrosEntrada(args);
			int cantidadDeHilos = Integer.parseInt(args[0]);
			int cantidadDeEmpleados = Integer.parseInt(args[1]);
			int cantidadDeLlamadas = Integer.parseInt(args[2]);

			Dispatcher dispatcher = new Dispatcher();

			mapearEmpleados(dispatcher, cantidadDeEmpleados);
			mapearLlamadas(dispatcher, cantidadDeLlamadas);
			List<CustomRunnable> listaHilos = mapearHilos(dispatcher, cantidadDeHilos);
			startearHilos(listaHilos);
			esperarHilos(listaHilos);

		} catch (InterruptedException e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("Termina aplicacion");

	}

	/**
	 * 
	 * @param args
	 */
	private static void validarParametrosEntrada(String[] args) {
		if (args.length != 3) {
			LOGGER.error("faltan parametros de entrada");
			System.exit(0);
		}
		try {
			int cantidadDeHilos = Integer.parseInt(args[0]);
			int cantidadDeEmpleados = Integer.parseInt(args[1]);
			int cantidadDeLlamadas = Integer.parseInt(args[2]);

			if (!(cantidadDeHilos > 0) || !(cantidadDeEmpleados > 0) || !(cantidadDeLlamadas > 0)) {
				LOGGER.error("Los parametros de entrada tienen q ser mayores a 0");
				System.exit(0);

			}
		} catch (NumberFormatException e) {
			LOGGER.error("Los parametros de entrada tienen q ser numeros");
			System.exit(0);
		}

	}

	/**
	 * 
	 * @param dispatcher
	 * @param cantidadDeEmpleados
	 */
	private static void mapearEmpleados(Dispatcher dispatcher, int cantidadDeEmpleados) {

		List<Empleado> empleados = GeneradorEmpleados.crearListaEmpleadosRandom(cantidadDeEmpleados);
		for (Empleado empleado : empleados) {
			dispatcher.getListaEmpleadosDisponibles().add(empleado);
		}
	}

	/**
	 * 
	 * @param dispatcher
	 * @param cantidadLlamadas
	 */
	private static void mapearLlamadas(Dispatcher dispatcher, int cantidadLlamadas) {
		ArrayBlockingQueue<Llamada> llamadasGeneradas = GeneradorLlamadas.crearListaLlamadas(cantidadLlamadas);

		for (Llamada llamada : llamadasGeneradas) {
			dispatcher.getListaLlamadas().add(llamada);
		}

	}

	/**
	 * 
	 * @param dispatcher
	 * @param cantidadHilos
	 * @return
	 */
	private static List<CustomRunnable> mapearHilos(Dispatcher dispatcher, int cantidadHilos) {
		List<CustomRunnable> listaHilos = new ArrayList<>();
		for (int i = 0; i < cantidadHilos; i++) {
			listaHilos.add(new CustomRunnable(dispatcher, Integer.toString(i + 1)));

		}
		return listaHilos;

	}

	/**
	 * 
	 * @param listaHilos
	 */
	private static void startearHilos(List<CustomRunnable> listaHilos) {
		for (int i = 0; i < listaHilos.size(); i++) {
			listaHilos.get(i).start();

		}

	}

	/**
	 * 
	 * @param listaHilos
	 * @throws InterruptedException
	 */
	private static void esperarHilos(List<CustomRunnable> listaHilos) throws InterruptedException {
		for (int i = 0; i < listaHilos.size(); i++) {
			listaHilos.get(i).join();

		}

	}
}
