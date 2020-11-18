/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceews.business.mgr;

import java.util.ArrayList;

import it.csi.nodospc.mdp.interfacews.serviziofruitore.EsitoChiediDatiPagamento;
import it.csi.nodospc.mdp.interfacews.serviziofruitore.EsitoRiceviEsito;
import it.csi.nodospc.mdp.interfacews.serviziofruitore.EsitoRiceviRT;
import it.csi.nodospc.mdp.interfacews.serviziofruitore.EsitoVerificaDatiPagamento;
import it.csi.nodospc.mdp.interfacews.serviziofruitore.ParametriChiediDatiPagamento;
import it.csi.nodospc.mdp.interfacews.serviziofruitore.ParametriRiceviEsito;
import it.csi.nodospc.mdp.interfacews.serviziofruitore.ParametriRiceviRT;
import it.csi.nodospc.mdp.interfacews.serviziofruitore.ParametriVerificaDatiPagamento;

public interface ISiceewsMdpMgr {

	public EsitoRiceviRT riceviRT(ParametriRiceviRT parametriRiceviRT);

	// Questi metodi li espongo per completezza, ma noi non li usiamo, quindi non li implemento
	public EsitoRiceviEsito riceviEsito(ParametriRiceviEsito parametriRiceviEsito);
	public EsitoVerificaDatiPagamento verificaDatiPagamento(ParametriVerificaDatiPagamento parametriVerificaDatiPagamento);
	public EsitoChiediDatiPagamento chiediDatiPagamento(ParametriChiediDatiPagamento parametriChiediDatiPagamento);

	public boolean isAlive();

}
