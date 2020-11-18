/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceews.util;

public interface Constants {

  String LOGGER_PREFIX = "siceews";

	public static final String COD_S = "S";
	public static final String COD_N = "N";


	public static final String COD_OK = "OK";
	public static final String COD_KO = "KO";

	public static final int ID_TIPO_OP_STORNO = 1;
	public static final int ID_TIPO_OP_RICARICA = 2;
	public static final int ID_TIPO_OP_GENERA_APE = 3;
	public static final int ID_TIPO_OP_ISCRIZIONE_ANN = 4;

	public static final int ID_STATO_TRANS_MDP_NEW_CREA_TRANS = 1;
	public static final int ID_STATO_TRANS_MDP_NEW_URL_WIS = 2;
	public static final int ID_STATO_TRANS_MDP_NEW_AVVIO_TRANS = 3;
	public static final int ID_STATO_TRANS_MDP_NEW_ATTESA_RT = 4;
	public static final int ID_STATO_TRANS_MDP_NEW_PAGAMENTO_OK = 5;
	public static final int ID_STATO_TRANS_MDP_NEW_FALLITO = 6;
	public static final int ID_STATO_TRANS_MDP_NEW_ANNULLATO = 7;
	public static final int ID_STATO_TRANS_MDP_NEW_PAGAMENTO_KO = 8;
	public static final int ID_STATO_TRANS_MDP_NEW_PAGAMENTO_KO_AUTO = 9;

	public static final int COD_ESITO_PAGAMENTO_AGID_ESEGUITO = 0;
	public static final int COD_ESITO_PAGAMENTO_AGID_NON_ESEGUITO = 1;
	public static final int COD_ESITO_PAGAMENTO_AGID_PARZ_ESEGUITO = 2;
	public static final int COD_ESITO_PAGAMENTO_AGID_DEC_TERMINI = 3;
	public static final int COD_ESITO_PAGAMENTO_AGID_DEC_PARZ_TERMINI = 4;
	
	
	public static final String SERVIZIO_ATTIVO = "SERVIZIO_ATTIVO";
	public static final String MITTENTE_MAIL = "MITTENTE_MAIL";
	public static final String DESTINATARIO_MAIL_ERRORE = "DESTINATARIO_MAIL_ERRORE";
	public static final String PASS_PHRASE = "PASS_PHRASE";
	
	
}
