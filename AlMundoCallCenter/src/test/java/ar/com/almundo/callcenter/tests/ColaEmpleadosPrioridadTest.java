package ar.com.almundo.callcenter.tests;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import ar.com.almundo.callcenter.entities.Director;
import ar.com.almundo.callcenter.entities.Empleado;
import ar.com.almundo.callcenter.entities.Operador;
import ar.com.almundo.callcenter.entities.Supervisor;
import ar.com.almundo.callcenter.thread.ComparadorDeEmpleados;



/**
 * @author Pablo_Cumpe
 *
 */
public class ColaEmpleadosPrioridadTest {
	
	/** variable log4j */
	private final static Logger LOGGER = Logger.getLogger(ColaEmpleadosPrioridadTest.class);

	/**
	 * Metodo que verifica que el orden de prioridad de una cola con distintos
	 * tipos de empleados mezclados, sea el correcto
	 * 
	 * Los primeros 4 elementos de la cola tienen q ser operador, los tres
	 * siguientes supervisor y el ultimo director
	 */
	@Test
	public void evaluarPrioridadEmpleadosTest1() {
		LOGGER.info("*****************************evaluarPrioridadEmpleadosTest1*****************************");

		
		Queue<Empleado> colaEmpleados = new PriorityBlockingQueue<>(100, new ComparadorDeEmpleados());

		Operador operador1 = new Operador("001", "pedro", "");
		Operador operador2 = new Operador("002", "pablo", "");
		Operador operador3 = new Operador("003", "jorge", "");
		Operador operador4 = new Operador("004", "nestor", "");

		Supervisor supervisor = new Supervisor("005", "carlos", null);
		Supervisor supervisor2 = new Supervisor("006", "pedro", "");
		Supervisor supervisor3 = new Supervisor("007", "jorge", "");

		Director director = new Director("008", "esteban", "");

		colaEmpleados.add(director);
		colaEmpleados.add(operador1);
		colaEmpleados.add(operador2);
		colaEmpleados.add(supervisor);
		colaEmpleados.add(operador4);
		colaEmpleados.add(supervisor2);
		colaEmpleados.add(supervisor3);
		colaEmpleados.add(operador3);

		int size = colaEmpleados.size();
		for (int i = 1; i <= size; i++) {
			Empleado empleado = colaEmpleados.poll();

			if (i <= 4) {
				Assert.assertTrue(empleado instanceof Operador);
			} else if (i <= 7) {
				Assert.assertTrue(empleado instanceof Supervisor);
			} else if (i == 8) {
				Assert.assertTrue(empleado instanceof Director);
			}
		}

	}

}
