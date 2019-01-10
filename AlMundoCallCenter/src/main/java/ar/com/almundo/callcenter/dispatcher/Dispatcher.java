package ar.com.almundo.callcenter.dispatcher;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import org.apache.log4j.Logger;
import ar.com.almundo.callcenter.entities.Empleado;
import ar.com.almundo.callcenter.entities.Llamada;
import ar.com.almundo.callcenter.exceptions.LlamadaException;
import ar.com.almundo.callcenter.thread.ComparadorDeEmpleados;
import ar.com.almundo.callcenter.utils.ErrorUtils;

/**
 * 
 * @author Pablo_Cumpe
 *
 */

public class Dispatcher {
	/** variable log4j */
	private final static Logger LOGGER = Logger.getLogger(Dispatcher.class);
	/** tiempo de espera para fetchear empleado */
	private static final long TIEMPO_ESPERA_EMPLEADO = 5000;
	/** cola de prioridad de empleados */
	private final Queue<Empleado> listaEmpleadosDisponibles = new PriorityBlockingQueue<>(100, new ComparadorDeEmpleados());
	/** cola de llamadas */
	private final ArrayBlockingQueue<Llamada> listaLlamadas = new ArrayBlockingQueue<>(100);

	/**
	 * 
	 * @param llamada
	 * @return
	 * @throws LlamadaException
	 * @throws EmpleadosNoDisponiblesException
	 */
	public void dispatchCall(String identificadorHilo) throws LlamadaException {

		String resultado = null;
		Empleado empleado = null;

		try {
			Llamada llamada = listaLlamadas.take();
			empleado = obtenerEmpleado();
			resultado = empleado.procesarLlamada(llamada);
			LOGGER.info("HILO " + identificadorHilo + " [" + resultado + "]");

		} catch (LlamadaException | InterruptedException e) {
			throw new LlamadaException(ErrorUtils.DESCRIPCION_ERROR_LLAMADA, e);
		} finally {
			this.listaEmpleadosDisponibles.add(empleado);

		}

	}

	/**
	 * Metodo recursivo para obtener empleado
	 * 
	 * Si no hay empleados disponibles se espera 5000 ms y se vuelve a rellamar,
	 * hasta que pueda obtener uno
	 * 
	 * @param reintentos
	 * @return
	 * @throws EmpleadosNoDisponiblesException
	 */
	private Empleado obtenerEmpleado() {
		Empleado empleado = this.listaEmpleadosDisponibles.poll();

		if (empleado == null) {
			esperarEmpleadosDisponibles();
			return obtenerEmpleado();
		}

		return empleado;

	}

	/**
	 * 
	 */
	private void esperarEmpleadosDisponibles() {
		try {

			Thread.sleep(TIEMPO_ESPERA_EMPLEADO);
		} catch (InterruptedException e) {
			LOGGER.error(e.getMessage());
		}

	}

	/**
	 * @return the listaEmpleadosDisponibles
	 */
	public Queue<Empleado> getListaEmpleadosDisponibles() {
		return listaEmpleadosDisponibles;
	}

	/**
	 * @return the listaLlamadas
	 */
	public ArrayBlockingQueue<Llamada> getListaLlamadas() {
		return listaLlamadas;
	}

}
