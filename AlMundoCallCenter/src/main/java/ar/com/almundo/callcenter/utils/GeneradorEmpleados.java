package ar.com.almundo.callcenter.utils;

import java.util.ArrayList;
import java.util.List;

import ar.com.almundo.callcenter.entities.Director;
import ar.com.almundo.callcenter.entities.Empleado;
import ar.com.almundo.callcenter.entities.Operador;
import ar.com.almundo.callcenter.entities.Supervisor;

/**
 * 
 * @author Pablo_Cumpe
 *
 */
public class GeneradorEmpleados {
	/** OPERADOR */
	public static final String EMPLEADO_OPERADOR = "OPERADOR";
	/** SUPERVISOR */
	public static final String EMPLEADO_SUPERVISOR = "SUPERVISOR";
	/** DIRECTOR */
	public static final String EMPLEADO_DIRECTOR = "DIRECTOR";

	/**
	 * 
	 * @param tipoEmpleado
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @return
	 */
	public static Empleado crearEmpleado(String tipoEmpleado, String id, String nombre, String apellido) {

		switch (tipoEmpleado) {
		case EMPLEADO_OPERADOR:
			return new Operador(id, nombre, apellido);
		case EMPLEADO_SUPERVISOR:
			return new Supervisor(id, nombre, apellido);
		case EMPLEADO_DIRECTOR:
			return new Director(id, nombre, apellido);
		}
		return null;

	}

	/**
	 * 
	 * @param cantidadDeEmpleados
	 * @return
	 */
	public static List<Empleado> crearListaEmpleadosRandom(int cantidadDeEmpleados) {
		List<Empleado> listaEmpleados = new ArrayList<>();

		for (int i = 0; i < cantidadDeEmpleados; i++) {
			int numeroRandom = RandomNumberGenerator.generarNumeroRandom(1, 3);
			switch (numeroRandom) {
			case 1:
				listaEmpleados.add(new Operador(Integer.toString(i), "", ""));
				break;
			case 2:
				listaEmpleados.add(new Supervisor(Integer.toString(i), "", ""));
				break;
			case 3:
				listaEmpleados.add(new Director(Integer.toString(i), "", ""));
				break;

			}

		}
		return listaEmpleados;
	}
}
