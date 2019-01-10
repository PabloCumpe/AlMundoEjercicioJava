package ar.com.almundo.callcenter.entities;

import ar.com.almundo.callcenter.exceptions.LlamadaException;
import ar.com.almundo.callcenter.utils.ErrorUtils;
import ar.com.almundo.callcenter.utils.RandomNumberGenerator;

/**
 * 
 * @author Pablo_Cumpe
 *
 */
public abstract class Empleado {
	/** id empleado */
	private String id;
	/** nombre empleado */
	private String nombre;
	/** apellido empleado */
	private String apellido;

	/**
	 * 
	 * @param id
	 * @param nombre
	 * @param apellido
	 */
	public Empleado(String id, String nombre, String apellido) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	/**se genera un sleep random del thread que va entre 5 y 10 segundos
	 * 
	 * @param llamada
	 * @throws LlamadaException
	 */
	public String procesarLlamada(Llamada llamada) throws LlamadaException {

		try {
			int tiempoDeDuracionDeLlamada = RandomNumberGenerator.generarNumeroRandom(5, 10);
			Thread.sleep(tiempoDeDuracionDeLlamada * 1000);

			return "La llamada con id: " + llamada.getId() + " fue procesada por un empleado tipo '"
					+ this.getClass().getSimpleName() + "' y tardo " + tiempoDeDuracionDeLlamada
					+ " segundos en finalizar";

		} catch (InterruptedException e) {
			throw new LlamadaException(ErrorUtils.DESCRIPCION_ERROR_LLAMADA, e);
		}

	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido
	 *            the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

}
