/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceews.integration.db;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SICEE_T_PARAMETRI_MDP database table.
 * 
 */
@Entity
@Table(name="SICEE_T_PARAMETRI_MDP")
@NamedQuery(name="SiceeTParametriMdp.findAll", query="SELECT s FROM SiceeTParametriMdp s")
public class SiceeTParametriMdp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PARAMETRI_MDP", insertable=false, updatable=false, unique=true, nullable=false, precision=22)
	private Integer idParametriMdp;

	private String codice;

	private String valore;

	public SiceeTParametriMdp() {
	}

	public Integer getIdParametriMdp() {
		return this.idParametriMdp;
	}

	public void setIdParametriMdp(Integer idParametriMdp) {
		this.idParametriMdp = idParametriMdp;
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getValore() {
		return this.valore;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}

}