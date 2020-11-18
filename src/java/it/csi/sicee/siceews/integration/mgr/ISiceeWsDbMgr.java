/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceews.integration.mgr;

import it.csi.sicee.siceews.integration.db.SiceeTCertificatore;
import it.csi.sicee.siceews.integration.db.SiceeTCredito2018;
import it.csi.sicee.siceews.integration.db.SiceeTTransazione2018;

//import it.csi.csi.wrapper.CSIException

public interface ISiceeWsDbMgr {

	public String provaMdpNew() ;
	public String findSiceeTParametriMdpByCodice(String codice) ;
	public String findSiceeDStatoTrans2018(int idStatoTrans2018) ;
	public SiceeTTransazione2018 findTransazione2018ByNumTrans(String numTrans);
	public SiceeTCertificatore findSiceeTCertificatore(String idCertificatore);
	//public void aggiornaTransazione2018(SiceeTTransazione2018 transazioneDb);
	public void aggiornaTransazione2018(SiceeTTransazione2018 transazioneDb, SiceeTCredito2018 creditoDb);
	
	public void aggiornaStatoTransazione2018(SiceeTTransazione2018 transazioneDb);
	public SiceeTCredito2018 findSiceeTCredito2018(String idCertificatore);
	public SiceeTCredito2018 insertSiceeTCredito2018(SiceeTCredito2018 creditoDb);

}
