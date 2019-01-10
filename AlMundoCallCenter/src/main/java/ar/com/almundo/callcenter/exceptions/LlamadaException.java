package ar.com.almundo.callcenter.exceptions;

/**
 * 
 * @author Pablo_Cumpe
 *
 */
public class LlamadaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 * @param e
	 */
	public LlamadaException(String message, Throwable e) {
		super(message, e);
	}

}
