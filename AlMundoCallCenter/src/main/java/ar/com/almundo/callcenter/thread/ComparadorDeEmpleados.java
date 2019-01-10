package ar.com.almundo.callcenter.thread;

import java.util.Comparator;

import ar.com.almundo.callcenter.entities.Director;
import ar.com.almundo.callcenter.entities.Empleado;
import ar.com.almundo.callcenter.entities.Operador;
import ar.com.almundo.callcenter.entities.Supervisor;

public class ComparadorDeEmpleados implements Comparator<Empleado> {

	@Override
	public int compare(Empleado empleado1, Empleado empleado2) {

		if (empleado1 instanceof Operador) {
			return -1;
		} else if (empleado2 instanceof Operador) {
			return 1;
		} else if (empleado1 instanceof Supervisor) {
			return -1;
		} else if (empleado2 instanceof Supervisor) {
			return 1;
		} else if (empleado1 instanceof Director) {
			return -1;
		} else if (empleado2 instanceof Director) {
			return 1;
		}
		return 0;
	}

}
