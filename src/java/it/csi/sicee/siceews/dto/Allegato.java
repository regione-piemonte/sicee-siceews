/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceews.dto;

public class Allegato implements java.io.Serializable {

	private java.lang.String nomeFile = null;

	private byte[] file;
	
	private java.lang.String contentType = null;

	
	
	public java.lang.String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(java.lang.String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public java.lang.String getContentType() {
		return contentType;
	}

	public void setContentType(java.lang.String contentType) {
		this.contentType = contentType;
	}
	
}
