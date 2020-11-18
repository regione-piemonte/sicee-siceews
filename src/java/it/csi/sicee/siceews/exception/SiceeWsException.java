/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceews.exception;

/**
 * Eccezione rilanciata in caso di eccezione nell'invocazione dei servizi.
 * 
 * 
 *
 */
public class SiceeWsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * @see Exception#Exception()
	 */
	public SiceeWsException() {
		super();
	}

	/**
	 * @see Exception#Exception(String, Throwable)
	 */
	public SiceeWsException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @see Exception#Exception(String)
	 */
	public SiceeWsException(String message) {
		super(message);
	}

	/**
	 * @see Exception#Exception(Throwable)
	 */
	public SiceeWsException(Throwable cause) {
		super(cause);
	}

}
