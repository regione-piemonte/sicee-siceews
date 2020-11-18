/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceews.business.mgr;

import java.util.ArrayList;

import it.csi.sicee.siceews.dto.Mail;

public interface ISiceewsMgr {


	// Questo metodo viene usato da siceeBO
	public ArrayList<String> recuperaRT(ArrayList<String> elencoIUV);
	public ArrayList<String> recuperaStatoTransazione(ArrayList<String> elencoTransactionId);
	public boolean invioMail(Mail mail);

	public boolean isAlive();

}
