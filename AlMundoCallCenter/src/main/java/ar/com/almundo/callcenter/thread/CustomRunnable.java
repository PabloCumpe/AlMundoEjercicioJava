package ar.com.almundo.callcenter.thread;

import org.apache.log4j.Logger;

import ar.com.almundo.callcenter.dispatcher.Dispatcher;
import ar.com.almundo.callcenter.exceptions.LlamadaException;

/**
 * 
 * @author Pablo_Cumpe
 *
 */
public class CustomRunnable extends Thread {
	
	/** variable log4j */
	private final static Logger LOGGER = Logger.getLogger(Dispatcher.class);
	/** dispatcher de llamadas */
	private Dispatcher dispatcher;
	/** identificador hilo */
	private String identificador;

	/**
	 * @param dispatcher
	 * @param identificador
	 */
	public CustomRunnable(Dispatcher dispatcher, String identificador) {
		super();
		this.dispatcher = dispatcher;
		this.identificador = identificador;
	}

	@Override
	public void run() {
		try {
			this.dispatcher.dispatchCall(this.identificador);
		} catch (LlamadaException e) {
			LOGGER.error(e.getMessage(), e);
		}

	}

	/**
	 * @return the dispatcher
	 */
	public Dispatcher getDispatcher() {
		return dispatcher;
	}

	/**
	 * @param dispatcher
	 *            the dispatcher to set
	 */
	public void setDispatcher(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	/**
	 * @return the identificador
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * @param identificador
	 *            the identificador to set
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

}
