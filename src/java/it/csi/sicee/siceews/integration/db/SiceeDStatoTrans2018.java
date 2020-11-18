/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceews.integration.db;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the SICEE_D_STATO_TRANS_2018 database table.
 * 
 */
@Entity
@Table(name="SICEE_D_STATO_TRANS_2018")
public class SiceeDStatoTrans2018 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_STATO_TRANS_2018")
	private Integer idStatoTrans2018;

	@Column(name="DESCR_STATO_TRANS")
	private String descrStatoTrans;

	
	
	public SiceeDStatoTrans2018() {
	}

	
	
	public Integer getIdStatoTrans2018() {
		return idStatoTrans2018;
	}



	public void setIdStatoTrans2018(Integer idStatoTrans2018) {
		this.idStatoTrans2018 = idStatoTrans2018;
	}



	public String getDescrStatoTrans() {
		return descrStatoTrans;
	}



	public void setDescrStatoTrans(String descrStatoTrans) {
		this.descrStatoTrans = descrStatoTrans;
	}

}