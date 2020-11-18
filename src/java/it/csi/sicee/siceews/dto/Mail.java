/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
/*
 * 
 */
package it.csi.sicee.siceews.dto;

import java.sql.Timestamp;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Mail.
 */
public class Mail implements java.io.Serializable {

	
	/// Field [mittente]
	/** The _mittente. */
	private java.lang.String _mittente = null;

	/**
	 * Sets the mittente.
	 *
	 * @param val the new mittente
	 */
	public void setMittente(java.lang.String val) {
		_mittente = val;
	}

	/**
	 * Gets the mittente.
	 *
	 * @return the mittente
	 */
	public java.lang.String getMittente() {
		return _mittente;
	}

	/// Field [oggetto]
	/** The _oggetto. */
	private java.lang.String _oggetto = null;

	/**
	 * Sets the oggetto.
	 *
	 * @param val the new oggetto
	 */
	public void setOggetto(java.lang.String val) {
		_oggetto = val;
	}

	/**
	 * Gets the oggetto.
	 *
	 * @return the oggetto
	 */
	public java.lang.String getOggetto() {
		return _oggetto;
	}

	/// Field [testo]
	/** The _testo. */
	private java.lang.String _testo = null;

	/**
	 * Sets the testo.
	 *
	 * @param val the new testo
	 */
	public void setTesto(java.lang.String val) {
		_testo = val;
	}

	/**
	 * Gets the testo.
	 *
	 * @return the testo
	 */
	public java.lang.String getTesto() {
		return _testo;
	}
	
	/// Field [html]
	/** The _html. */
	private java.lang.String _html = null;

	/**
	 * Sets the html.
	 *
	 * @param val the new html
	 */
	public void setHtml(java.lang.String val) {
		_html = val;
	}

	/**
	 * Gets the html.
	 *
	 * @return the html
	 */
	public java.lang.String getHtml() {
		return _html;
	}

	/// Field [destinatario]
	/** The _destinatario. */
	private java.lang.String _destinatario = null;

	/**
	 * Sets the destinatario.
	 *
	 * @param val the new destinatario
	 */
	public void setDestinatario(java.lang.String val) {
		_destinatario = val;
	}

	/**
	 * Gets the destinatario.
	 *
	 * @return the destinatario
	 */
	public java.lang.String getDestinatario() {
		return _destinatario;
	}

	/// Field [host]
	/** The _host. */
	private java.lang.String _host = null;

	/**
	 * Sets the host.
	 *
	 * @param val the new host
	 */
	public void setHost(java.lang.String val) {
		_host = val;
	}

	/**
	 * Gets the host.
	 *
	 * @return the host
	 */
	public java.lang.String getHost() {
		return _host;
	}

	/// Field [port]
	/** The _port. */
	private java.lang.String _port = null;

	/**
	 * Sets the port.
	 *
	 * @param val the new port
	 */
	public void setPort(java.lang.String val) {
		_port = val;
	}

	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public java.lang.String getPort() {
		return _port;
	}

	

	/// Field [protocol]
	/** The _protocol. */
	private java.lang.String _protocol = null;

	/**
	 * Sets the protocol.
	 *
	 * @param val the new protocol
	 */
	public void setProtocol(java.lang.String val) {
		_protocol = val;
	}

	/**
	 * Gets the protocol.
	 *
	 * @return the protocol
	 */
	public java.lang.String getProtocol() {
		return _protocol;
	}

		/// Field [nomeAllegato]
	/** The _nome allegato. */
	private java.lang.String _nomeAllegato = null;

	/**
	 * Sets the nome allegato.
	 *
	 * @param val the new nome allegato
	 */
	public void setNomeAllegato(java.lang.String val) {
		_nomeAllegato = val;
	}

	/**
	 * Gets the nome allegato.
	 *
	 * @return the nome allegato
	 */
	public java.lang.String getNomeAllegato() {
		return _nomeAllegato;
	}

	/// Field [destinatarioCC]
	/** The _destinatario cc. */
	private java.lang.String _destinatarioCC = null;

	/**
	 * Sets the destinatario cc.
	 *
	 * @param val the new destinatario cc
	 */
	public void setDestinatarioCC(java.lang.String val) {
		_destinatarioCC = val;
	}

	/**
	 * Gets the destinatario cc.
	 *
	 * @return the destinatario cc
	 */
	public java.lang.String getDestinatarioCC() {
		return _destinatarioCC;
	}
	
	
	private java.util.ArrayList<Allegato> _elencoAllegati = new ArrayList<Allegato>();
	
	public void setElencoAllegati(java.util.ArrayList<Allegato> val) {
		_elencoAllegati = val;
	}
	
	public java.util.ArrayList<Allegato> getElencoAllegati() {
		return _elencoAllegati;
	}
	
	
	
	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public Mail() {
		super();

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		/*PROTECTED REGION ID(R271254851) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
