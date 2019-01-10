package ar.com.almundo.callcenter.entities;

/**
 * 
 * @author Pablo_Cumpe
 *
 */
public class Llamada {
	/** id de la llamada */
	private String id;
	/** numero origen llamada */
	private long numeroOrigen;
	/** numero destino llamada */
	private long numeroDestino;

	/**
	 * @param id
	 * @param numeroOrigen
	 * @param numeroDestino
	 */
	public Llamada(String id, long numeroOrigen, long numeroDestino) {
		super();
		this.id = id;
		this.numeroOrigen = numeroOrigen;
		this.numeroDestino = numeroDestino;
	}

	/**
	 * @return the numeroOrigen
	 */
	public long getNumeroOrigen() {
		return numeroOrigen;
	}

	/**
	 * @param numeroOrigen
	 *            the numeroOrigen to set
	 */
	public void setNumeroOrigen(long numeroOrigen) {
		this.numeroOrigen = numeroOrigen;
	}

	/**
	 * @return the numeroDestino
	 */
	public long getNumeroDestino() {
		return numeroDestino;
	}

	/**
	 * @param numeroDestino
	 *            the numeroDestino to set
	 */
	public void setNumeroDestino(long numeroDestino) {
		this.numeroDestino = numeroDestino;
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

}
