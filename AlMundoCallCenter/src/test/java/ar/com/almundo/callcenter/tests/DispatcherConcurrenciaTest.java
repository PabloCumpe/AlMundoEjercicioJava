package ar.com.almundo.callcenter.tests;

import org.junit.Assert;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import org.apache.log4j.Logger;
import org.junit.Test;

import ar.com.almundo.callcenter.dispatcher.Dispatcher;
import ar.com.almundo.callcenter.entities.Director;
import ar.com.almundo.callcenter.entities.Empleado;
import ar.com.almundo.callcenter.entities.Llamada;
import ar.com.almundo.callcenter.entities.Operador;
import ar.com.almundo.callcenter.entities.Supervisor;
import ar.com.almundo.callcenter.thread.ComparadorDeEmpleados;
import ar.com.almundo.callcenter.thread.CustomRunnable;
import ar.com.almundo.callcenter.utils.GeneradorLlamadas;

/**
 * @author Pablo_Cumpe
 *
 */
public class DispatcherConcurrenciaTest {

	/** variable log4j */
	private final static Logger LOGGER = Logger.getLogger(DispatcherConcurrenciaTest.class);



	/**
	 * Se crean 10 hilos, 10 empleados, 10 llamadas
	 * 
	 * cada hilo toma un empleado y procesa una llamada
	 */
	@Test
	public void testCon10Hilos() {

		try {
			LOGGER.info("*****************************testCon10Hilos*****************************");
			Dispatcher dispatcher = new Dispatcher();
			Queue<Empleado> empleados = obtenerColaEmpleados();

			for (Empleado empleado : empleados) {
				dispatcher.getListaEmpleadosDisponibles().add(empleado);
			}

			ArrayBlockingQueue<Llamada> llamadasGeneradas = GeneradorLlamadas.crearListaLlamadas(10);

			for (Llamada llamada : llamadasGeneradas) {
				dispatcher.getListaLlamadas().add(llamada);
			}
			CustomRunnable c1 = new CustomRunnable(dispatcher, "1");
			CustomRunnable c2 = new CustomRunnable(dispatcher, "2");
			CustomRunnable c3 = new CustomRunnable(dispatcher, "3");
			CustomRunnable c4 = new CustomRunnable(dispatcher, "4");
			CustomRunnable c5 = new CustomRunnable(dispatcher, "5");
			CustomRunnable c6 = new CustomRunnable(dispatcher, "6");
			CustomRunnable c7 = new CustomRunnable(dispatcher, "7");
			CustomRunnable c8 = new CustomRunnable(dispatcher, "8");
			CustomRunnable c9 = new CustomRunnable(dispatcher, "9");
			CustomRunnable c10 = new CustomRunnable(dispatcher, "10");

			c1.start();
			c2.start();
			c3.start();
			c4.start();
			c5.start();
			c6.start();
			c7.start();
			c8.start();
			c9.start();
			c10.start();

			c1.join();
			c2.join();
			c3.join();
			c4.join();
			c5.join();
			c6.join();
			c7.join();
			c8.join();
			c9.join();
			c10.join();
		} catch (InterruptedException e) {
			LOGGER.error(e.getMessage(), e);
			Assert.fail("error en los threads");
		}

		LOGGER.info("**********************************************************");

	}

	/**
	 * 
	 * 
	 * En este metodo se crea una instancia de la clase Dispatcher con solo una
	 * lista de empleados, pero sin ninguna llamada que atender. Los thread quedan
	 * esperando a que aparezca una llamada en el metodo "listaLlamadas.take()"
	 * 
	 * Una vez esperado un tiempo (5 seg), se agregan dos llamadas, el metodo
	 * '.take' se libera y se atienden las llamadas , una por cada hilo
	 * 
	 * Se verifica al final que no hayan quedado llamadas sin ser atendidas
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void dispatcherConDosHilosYsinLLamadas() {

		try {

			LOGGER.info("*************************dispatcherConDosHilosYsinLLamadas*********************************");

			Dispatcher dispatcher = new Dispatcher();
			LOGGER.info("Se crea un dispatcher sin llamadas");

			Queue<Empleado> empleados = obtenerColaEmpleados();

			for (Empleado empleado : empleados) {
				dispatcher.getListaEmpleadosDisponibles().add(empleado);
			}

			CustomRunnable c1 = new CustomRunnable(dispatcher, "1");
			CustomRunnable c2 = new CustomRunnable(dispatcher, "2");

			c1.start();
			c2.start();

			LOGGER.info("Se esperan 5 segundos");
			Thread.sleep(5000);
			ArrayBlockingQueue<Llamada> llamadasGeneradas = GeneradorLlamadas.crearListaLlamadas(2);

			LOGGER.info("Se agregan dos llamadas");
			for (Llamada llamada : llamadasGeneradas) {
				dispatcher.getListaLlamadas().add(llamada);
			}

			c1.join();
			c2.join();
			
			Assert.assertTrue(dispatcher.getListaLlamadas().isEmpty());

		} catch (InterruptedException e) {
			LOGGER.error(e.getMessage(), e);
			Assert.fail("error en los threads");
		}

		LOGGER.info("**********************************************************");

	}

	/**
	 * 
	 * @return
	 */
	private Queue<Empleado> obtenerColaEmpleados() {
		Queue<Empleado> listaEmpleadosDisponibles = new PriorityBlockingQueue<>(10, new ComparadorDeEmpleados());
		Operador operador1 = new Operador("001", "", "");
		Operador operador2 = new Operador("002", "", "");
		Operador operador3 = new Operador("006", "", "");
		Operador operador4 = new Operador("007", "", "");

		Director director = new Director("003", "", "");
		Director director2 = new Director("003", "", "");

		Supervisor supervisor = new Supervisor("004", "", "");
		Supervisor supervisor2 = new Supervisor("005", "", "");
		Supervisor supervisor3 = new Supervisor("008", "", "");
		Supervisor supervisor4 = new Supervisor("008", "", "");

		listaEmpleadosDisponibles.add(supervisor);
		listaEmpleadosDisponibles.add(operador1);
		listaEmpleadosDisponibles.add(operador2);
		listaEmpleadosDisponibles.add(supervisor2);
		listaEmpleadosDisponibles.add(operador3);
		listaEmpleadosDisponibles.add(director);
		listaEmpleadosDisponibles.add(operador4);
		listaEmpleadosDisponibles.add(supervisor3);
		listaEmpleadosDisponibles.add(supervisor4);
		listaEmpleadosDisponibles.add(director2);

		return listaEmpleadosDisponibles;

	}

}
