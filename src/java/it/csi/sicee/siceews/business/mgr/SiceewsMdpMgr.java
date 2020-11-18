/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceews.business.mgr;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.SynchronizationType;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.Log4jEntityResolver;

import it.csi.nodospc.mdp.interfacews.serviziofruitore.EsitoChiediDatiPagamento;
import it.csi.nodospc.mdp.interfacews.serviziofruitore.EsitoRiceviEsito;
import it.csi.nodospc.mdp.interfacews.serviziofruitore.EsitoRiceviRT;
import it.csi.nodospc.mdp.interfacews.serviziofruitore.EsitoVerificaDatiPagamento;
import it.csi.nodospc.mdp.interfacews.serviziofruitore.ParametriChiediDatiPagamento;
import it.csi.nodospc.mdp.interfacews.serviziofruitore.ParametriRiceviEsito;
import it.csi.nodospc.mdp.interfacews.serviziofruitore.ParametriRiceviRT;
import it.csi.nodospc.mdp.interfacews.serviziofruitore.ParametriVerificaDatiPagamento;
import it.csi.sicee.siceews.dto.Mail;
import it.csi.sicee.siceews.exception.SiceeWsException;
import it.csi.sicee.siceews.integration.db.SiceeTCertificatore;
import it.csi.sicee.siceews.integration.db.SiceeTCredito2018;
import it.csi.sicee.siceews.integration.db.SiceeTTransazione2018;
import it.csi.sicee.siceews.integration.mgr.ISiceeWsDbMgr;
import it.csi.sicee.siceews.integration.mgr.SiceeWsServiceMgr;
import it.csi.sicee.siceews.integration.mgr.SiceeWsServiceMgrFactory;
import it.csi.sicee.siceews.util.GenericUtil;
import it.csi.sicee.siceews.util.MapDto;
import it.csi.sicee.siceews.util.Constants;


//@WebService(name = "SiceewsMgrWS", targetNamespace = "http://it/csi/sicee/siceews/integration/mdpnew", serviceName = "SiceewsMgr")
//@WebService(name = "SiceewsMgrWS", serviceName = "SiceewsMgr")
@WebService(name = "SiceewsMdpMgrWS", targetNamespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/", serviceName = "SiceewsMgr")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@Remote(ISiceewsMgr.class)
@Stateless(name = "SiceewsMdpMgrSL")


public class SiceewsMdpMgr implements ISiceewsMdpMgr {

	private static final Logger log = Logger.getLogger(Constants.LOGGER_PREFIX);

	@PersistenceContext(unitName = "SICEEWS")
  private EntityManager entityManager;

	  @EJB
	  private ISiceeWsDbMgr dbMgr;

	  private SiceeWsServiceMgr serviceMgr = new SiceeWsServiceMgr();

	//MdpnewMgr serviceSrv = new MdpnewMgr();

	/*
	@Override
	public void servizioEsposto() {
		// TODO Auto-generated method stub
		
		String prova = dbMgr.provaMdpNew();
		System.out.println("Sto chiamando l'interfaccia");
		System.out.println(prova);
		
	}

	@Override
	public void servizioEspostoNew() {
		// TODO Auto-generated method stub
		
		try
		{
			System.out.println("Sto chiamando il Factory (invio mail)");
			String prova = serviceMgr.sendMailProva("assistenza.energia@csi.it", "giuseppe.todaro@csi.it");
			System.out.println("ho mandato la mail ");
			System.out.println(prova);
		}
		catch (Exception ex)
		{
			System.err.println(ex);
		}
	}

	@Override
	public EsitoRiceviRT RiceviRTSimulato() {
		ParametriRiceviRT parametriRT = new ParametriRiceviRT();
		
		parametriRT.setTransactionId("TST000000000042580");

		parametriRT.setApplicationId("SICEE_NODO");
		parametriRT.setCodEsitoPagamento("1");
		//parametriRT.setRtData();
		parametriRT.setTimestamp("02/03/2018");
		parametriRT.setIuv("RF73180590003TEST00000006");
		parametriRT.setIdMsgRicevuta("3");
		String macCalcolato = GenericUtil.getMacRiceviRT(parametriRT);
		parametriRT.setMac(macCalcolato);
		
		return RiceviRT(parametriRT);
	}
	*/
	  
	  @Override
	  public boolean isAlive() {
		  // TODO Auto-generated method stub
		  log.debug("[SiceewsMgr::isAlive] BEGIN");

		  boolean isAlive = false;
		  try
		  {
			 
			  isAlive = true;
		  }
		  catch (Exception e) {
			  log.error("[SiceewsMgr::isAlive] Errore ",e);
			  //System.out.println("[SiceewsMgr::isAlive] Errore ");
			  //e.printStackTrace();

			  throw e;
		  } finally {
			  log.debug("[SiceewsMgr::isAlive] END");
			  return isAlive;

		  }

	  }
	  
	  @Override
	  @WebResult(name="result")
	  public EsitoRiceviRT riceviRT(@WebParam(name="parametri") ParametriRiceviRT parametri) {
		  // TODO Auto-generated method stub
		  log.debug("[SiceewsMgr::riceviRT] BEGIN");

		  EsitoRiceviRT esito = new EsitoRiceviRT();
		  try
		  {

			  String servizioAttivo = dbMgr.findSiceeTParametriMdpByCodice(Constants.SERVIZIO_ATTIVO);

			  String passphrase = dbMgr.findSiceeTParametriMdpByCodice(Constants.PASS_PHRASE);

			  boolean isServizioAttivo = Constants.COD_S.equalsIgnoreCase(servizioAttivo);

			  log.debug("isServizioAttivo: "+isServizioAttivo);

			  if (isServizioAttivo)
			  {
				  //factoryWs.settaEntityManager(entityManager);

				  String mittenteMail = dbMgr.findSiceeTParametriMdpByCodice(Constants.MITTENTE_MAIL);

				  if (log.isDebugEnabled())
					  GenericUtil.stampa(parametri, true, 3);

				  SiceeTTransazione2018 transazioneDb = dbMgr.findTransazione2018ByNumTrans(parametri.getTransactionId());

				  
				  log.debug("Stampo transazioneDb: "+transazioneDb);

				  log.debug("Stampo transazioneDb.getXmlRt(): "+transazioneDb.getXmlRt());

				  log.debug("Stampo GenericUtil.isNullOrEmpty(transazioneDb.getXmlRt()): "+GenericUtil.isNullOrEmpty(transazioneDb.getXmlRt()));
				  
				  if (transazioneDb == null)
				  {
					  throw new SiceeWsException("Transazione non trovata sul sistema");
				  }
				  else if (GenericUtil.isNullOrEmpty(transazioneDb.getXmlRt()))
				  {
					  
					  // Se non e' presente l'RT, potrebbe essere presente se si e' richiamato (a mano) il metodo recuperaRT

					  if (log.isDebugEnabled())
						  GenericUtil.stampa(transazioneDb, false, 3);

					  SiceeTCertificatore certificatoreDb = null;
					  /*
			if (transazioneDb == null)
			{
				throw new SiceeWsException("Transazione "+parametri.getTransactionId() + " non trovata sul DB");
			}

			SiceeTCertificatore certificatoreDb = dbMgr.findSiceeTCertificatore(transazioneDb.getFkCertificatore());

			GenericUtil.stampa(certificatoreDb, false, 3);
					   */

					  String codEsitoPag = parametri.getCodEsitoPagamento();
					  String timestamp = parametri.getTimestamp();
					  String mac = parametri.getMac();

					  DataHandler rt = parametri.getRtData();
					  byte[] rtByte = GenericUtil.readBytesFromInputStream(rt.getInputStream());
					  // byte[] rtByte = parametri.getRtData();

					  String rtString = GenericUtil.readByteArray(rtByte);

					  log.debug("rtString: "+rtString);

					  String macCalcolato = GenericUtil.getMacRiceviRT(parametri, passphrase);

					  log.debug("mac (orig): "+mac);

					  log.debug("mac (calc): "+macCalcolato);

					  if (!mac.equals(macCalcolato))
					  {
						  throw new SiceeWsException("MAC non corrispondenti");
					  }

					  int codEsito = GenericUtil.convertToInt(codEsitoPag);

					  transazioneDb.setDtChiusuraTransazione(new Date());
					  transazioneDb.setXmlRt(rtString);
					  
					  SiceeTCredito2018 credito2018 = null;

					  switch (codEsito) {
					  case Constants.COD_ESITO_PAGAMENTO_AGID_ESEGUITO:
						  transazioneDb.setFkStatoTrans2018(Constants.ID_STATO_TRANS_MDP_NEW_PAGAMENTO_OK);
						  
						  // Solo nel caso di esito positivo andiamo a controllare che l valore dell'RT sia uguale al valore salvato sul DB
						  
						  log.debug("Prima della conversione");

						  it.gov.digitpa.schemas.x2011.pagamenti.RTDocument rtObject = GenericUtil.mapToMODDocumentRTNew(rtString, true);

						  log.debug("Dopo la conversione");

						  //GenericUtil.stampa(rtObject, false, 3);

						  BigDecimal importoPagatoRt = rtObject.getRT().getDatiPagamento().getImportoTotalePagato();

						  if (importoPagatoRt.doubleValue() != transazioneDb.getS1ValoreTransazione().doubleValue())
						  {

							  String destinatarioMail = dbMgr.findSiceeTParametriMdpByCodice(Constants.DESTINATARIO_MAIL_ERRORE);
							  serviceMgr.sendMailAssistenzaErrore(transazioneDb, mittenteMail, destinatarioMail, rtByte);

							  throw new SiceeWsException("Il valore dell'RT non corrisponde al valore a sistema");
						  }
						  else
						  {
							  // Recupero l'ultimo credito (se presente)
							  SiceeTCredito2018 creditoDb = dbMgr.findSiceeTCredito2018(transazioneDb.getFkCertificatore());

							  BigDecimal saldoCredito = null;
							  if (creditoDb != null)
							  {
								  saldoCredito = creditoDb.getValoreCreditoFinale();
							  }
							  
							  credito2018 = MapDto.mapToSiceeTCredito2018(transazioneDb.getFkCertificatore(), transazioneDb, saldoCredito);
						  }

						  break;
					  case Constants.COD_ESITO_PAGAMENTO_AGID_NON_ESEGUITO:
						  transazioneDb.setFkStatoTrans2018(Constants.ID_STATO_TRANS_MDP_NEW_PAGAMENTO_KO);

						  break;
					  case Constants.COD_ESITO_PAGAMENTO_AGID_PARZ_ESEGUITO:
						  // non dovrebbe mai capitare perche' non facciamo pagamenti multipli
						  break;
					  case Constants.COD_ESITO_PAGAMENTO_AGID_DEC_TERMINI:
						  transazioneDb.setFkStatoTrans2018(Constants.ID_STATO_TRANS_MDP_NEW_PAGAMENTO_KO_AUTO);

						  break;
					  case Constants.COD_ESITO_PAGAMENTO_AGID_DEC_PARZ_TERMINI:
						  // non dovrebbe mai capitare perche' non facciamo pagamenti multipli
						  break;
					  default:
						  break;
					  }
					  
					  dbMgr.aggiornaTransazione2018(transazioneDb, credito2018);

					  //dbMgr.insertSiceeTCredito2018(MapDto.mapToSiceeTCredito2018(transazioneDb.getFkCertificatore(), transazioneDb, saldoCredito));

					  certificatoreDb = dbMgr.findSiceeTCertificatore(transazioneDb.getFkCertificatore());

					  if (certificatoreDb != null)
					  {

						  log.debug("Stampo il getFkStatoTrans2018: "+transazioneDb.getFkStatoTrans2018());
						  String descEsito = dbMgr.findSiceeDStatoTrans2018(transazioneDb.getFkStatoTrans2018());

						  serviceMgr.sendMailEsitoRT(transazioneDb, mittenteMail, certificatoreDb.getEmail(), descEsito);
						  log.debug("ho mandato la mail ");
					  }


					  //findTransazione2018ByNumTrans("TST000000000042580");
					  //System.out.println(prova);
				  }

				  esito.setEsito(Constants.COD_OK);
			  }
			  else
			  {
				  // Il servizio non e' attivo
				  throw new SiceeWsException("Il servizio non e' attivo");
			  }
		  }
		  catch (Exception e) {
			  log.error("[SiceewsMgr::riceviRT] Errore ",e);

			  esito.setEsito(Constants.COD_KO);
			  esito.setMessaggioErrore(e.getMessage());

			  throw e;
		  } finally {
			  log.debug("[SiceewsMgr::riceviRT] END");
			  return esito;

		  }

	  }

	  /*
	  @Override
	  public ArrayList<String> recuperaRT(ArrayList<String> elencoTransactionId) {
		  // TODO Auto-generated method stub
		  log.debug("[SiceewsMgr::recuperaRT] BEGIN");

		  ArrayList<String> esito = new ArrayList<String>();

		  String idIuv = null;
		  String idTransMdp = null;

		  try
		  {

			  String servizioAttivo = dbMgr.findSiceeTParametriMdpByCodice(Constants.SERVIZIO_ATTIVO);
			  boolean isServizioAttivo = Constants.COD_S.equalsIgnoreCase(servizioAttivo);

			  if (isServizioAttivo)
			  {
				  //factoryWs.settaEntityManager(entityManager);

				  String mittenteMail = dbMgr.findSiceeTParametriMdpByCodice(Constants.MITTENTE_MAIL);


				  for (String transactionId : elencoTransactionId) {

					  idTransMdp = transactionId;
					  try
					  {
						  
						  System.out.println("Prima dello sleep");
						  TimeUnit.SECONDS.sleep(2);
						  System.out.println("Dopo lo sleep");

						  GenericUtil.stampa(transactionId, false, 3);

						  SiceeTTransazione2018 transazioneDb = dbMgr.findTransazione2018ByNumTrans(transactionId);


						  if (transazioneDb == null)
						  {
							  throw new SiceeWsException("Transazione non trovata sul sistema (DB)");
						  }

						  idIuv = transazioneDb.getS3IdIuv();

						  if (idIuv != null)
						  {

							  GenericUtil.stampa(transazioneDb, false, 3);

							  SiceeTCertificatore certificatoreDb = null;

							  //					  String codEsitoPag = parametri.getCodEsitoPagamento();
							  //					  DataHandler rt = parametri.getRtData();
							  //					  String timestamp = parametri.getTimestamp();
							  //					  String mac = parametri.getMac();
							  //
							  //					  


							  //byte[] rtByte = GenericUtil.readBytesFromInputStream(rt.getInputStream());
							  byte[] rtByte = serviceMgr.recuperaMdpRT(idIuv);

							  String rtString = GenericUtil.readByteArray(rtByte);

							  System.out.println("rtString: "+rtString);

							  //					  String macCalcolato = GenericUtil.getMacRiceviRT(parametri);
							  //
							  //					  System.out.println("mac (orig): "+mac);
							  //
							  //					  System.out.println("mac (calc): "+macCalcolato);
							  //
							  //					  if (!mac.equals(macCalcolato))
							  //					  {
							  //						  throw new SiceeWsException("MAC non corrispondenti");
							  //					  }

							  System.out.println("Prima della conversione");

							  it.gov.digitpa.schemas.x2011.pagamenti.RTDocument rtObject = GenericUtil.mapToMODDocumentRT(GenericUtil
									  .readString(rtString), false);


							  System.out.println("Dopo la conversione");

							  //GenericUtil.stampa(rtObject, false, 3);

							  BigDecimal importoPagatoRt = rtObject.getRT().getDatiPagamento().getImportoTotalePagato();
							  String codEsitoPag = rtObject.getRT().getDatiPagamento().getCodiceEsitoPagamento().toString();

							  if (importoPagatoRt.doubleValue() != transazioneDb.getS1ValoreTransazione().doubleValue())
							  {

								  String destinatarioMail = dbMgr.findSiceeTParametriMdpByCodice(Constants.DESTINATARIO_MAIL_ERRORE);
								  serviceMgr.sendMailAssistenzaErrore(transazioneDb, mittenteMail, destinatarioMail, rtByte);

								  throw new SiceeWsException("Il valore dell'RT non corrisponde al valore a sistema");

							  }

							  int codEsito = GenericUtil.convertToInt(codEsitoPag);

							  transazioneDb.setDtChiusuraTransazione(new Date());
							  transazioneDb.setXmlRt(rtString);

							  switch (codEsito) {
							  case Constants.COD_ESITO_PAGAMENTO_AGID_ESEGUITO:
								  transazioneDb.setFkStatoTrans2018(Constants.ID_STATO_TRANS_MDP_NEW_PAGAMENTO_OK);

								  break;
							  case Constants.COD_ESITO_PAGAMENTO_AGID_NON_ESEGUITO:
								  transazioneDb.setFkStatoTrans2018(Constants.ID_STATO_TRANS_MDP_NEW_PAGAMENTO_KO);

								  break;
							  case Constants.COD_ESITO_PAGAMENTO_AGID_PARZ_ESEGUITO:
								  // non dovrebbe mai capitare perche' non facciamo pagamenti multipli
								  break;
							  case Constants.COD_ESITO_PAGAMENTO_AGID_DEC_TERMINI:
								  transazioneDb.setFkStatoTrans2018(Constants.ID_STATO_TRANS_MDP_NEW_PAGAMENTO_KO_AUTO);

								  break;
							  case Constants.COD_ESITO_PAGAMENTO_AGID_DEC_PARZ_TERMINI:
								  // non dovrebbe mai capitare perche' non facciamo pagamenti multipli
								  break;
							  default:
								  break;
							  }


							  dbMgr.aggiornaTransazione2018(transazioneDb);

							  // Recupero l'ultimo credito (se presente)
							  SiceeTCredito2018 creditoDb = dbMgr.findSiceeTCredito2018(transazioneDb.getFkCertificatore());

							  BigDecimal saldoCredito = null;
							  if (creditoDb != null)
							  {
								  saldoCredito = creditoDb.getValoreCreditoFinale();
							  }

							  dbMgr.insertSiceeTCredito2018(MapDto.mapToSiceeTCredito2018(transazioneDb.getFkCertificatore(), transazioneDb, saldoCredito));

							  certificatoreDb = dbMgr.findSiceeTCertificatore(transazioneDb.getFkCertificatore());

							  if (certificatoreDb != null)
							  {

								  System.out.println("Stampo il getFkStatoTrans2018: "+transazioneDb.getFkStatoTrans2018());
								  String descEsito = dbMgr.findSiceeDStatoTrans2018(transazioneDb.getFkStatoTrans2018());

								  serviceMgr.sendMailEsitoRT(transazioneDb, mittenteMail, certificatoreDb.getEmail(), descEsito);
								  System.out.println("ho mandato la mail ");
							  }

							  //esito.setEsito(Constants.COD_OK);
						  }
					  }
					  catch (SiceeWsException e) {
						  esito.add(idTransMdp + " - " + e.getMessage());
					  }
				  }

			  }
			  else
			  {
				  // Il servizio non e' attivo
				  //throw new SiceeWsException("Il servizio non e' attivo");

				  esito.add("Il servizio non e' attivo");
			  }
		  }
		  catch (Exception e) {
			  System.out.println("[SiceewsMgr::recuperaRT] Errore ");
			  e.printStackTrace();

//			  esito.setEsito(Constants.COD_KO);
//			  esito.setMessaggioErrore(e.getMessage());
			  esito.add("Si e' verificata un'eccezione non prevista: "+e.getMessage());

			  throw e;
		  } finally {
			  log.debug("[SiceewsMgr::recuperaRT] END");
			  return esito;

		  }

	  }
	*/
	  
	  /*
	  @Override
	  public ArrayList<String> recuperaStatoTransazione(ArrayList<String> elencoTransactionId) {
		  // TODO Auto-generated method stub
		  log.debug("[SiceewsMgr::recuperaStatoTransazione] BEGIN");

		  ArrayList<String> esito = new ArrayList<String>();

		  String idTransMdp = null;
		  try
		  {

			  String servizioAttivo = dbMgr.findSiceeTParametriMdpByCodice(Constants.SERVIZIO_ATTIVO);
			  boolean isServizioAttivo = Constants.COD_S.equalsIgnoreCase(servizioAttivo);

			  if (isServizioAttivo)
			  {
				  //factoryWs.settaEntityManager(entityManager);

				  for (String transactionId : elencoTransactionId) {

					  idTransMdp = transactionId;
					  try
					  {

						  System.out.println("Prima dello sleep");
						  TimeUnit.SECONDS.sleep(2);
						  System.out.println("Dopo lo sleep");

						  GenericUtil.stampa(transactionId, false, 3);

						  SiceeTTransazione2018 transazioneDb = dbMgr.findTransazione2018ByNumTrans(transactionId);


						  if (transazioneDb == null)
						  {
							  throw new SiceeWsException("Transazione non trovata sul sistema (DB)");
						  }

						  //idTransMdp = transazioneDb.getS1IdTransazioneMdp();

						  if (idTransMdp != null)
						  {

							  GenericUtil.stampa(transazioneDb, false, 3);

							  //SiceeTCertificatore certificatoreDb = null;

							  //					  String codEsitoPag = parametri.getCodEsitoPagamento();
							  //					  DataHandler rt = parametri.getRtData();
							  //					  String timestamp = parametri.getTimestamp();
							  //					  String mac = parametri.getMac();
							  //
							  //					  


							  //byte[] rtByte = GenericUtil.readBytesFromInputStream(rt.getInputStream());
							 
							  System.out.println("Prima del serviceMgr.recuperaMdpStatoTransazione(idTransMdp): "+(idTransMdp));
							  Integer statoTransMdp = serviceMgr.recuperaMdpStatoTransazione(idTransMdp);

							  System.out.println("Dopo del serviceMgr.... statoTransMdp: "+statoTransMdp);
							 transazioneDb.setOpGetstatotransFkStato(statoTransMdp);
							 transazioneDb.setOpGetstatotransDt(new Date());
							 
							  dbMgr.aggiornaStatoTransazione2018(transazioneDb);

						  }
					  }
					  catch (SiceeWsException e) {
						  
						  e.printStackTrace();
						  esito.add(idTransMdp + " - " + e.getMessage());
					  }
				  }

			  }
			  else
			  {
				  // Il servizio non e' attivo
				  //throw new SiceeWsException("Il servizio non e' attivo");

				  esito.add("Il servizio non e' attivo");
			  }
		  }
		  catch (Exception e) {
			  System.out.println("[SiceewsMgr::recuperaStatoTransazione] Errore ");
			  e.printStackTrace();

//			  esito.setEsito(Constants.COD_KO);
//			  esito.setMessaggioErrore(e.getMessage());
			  esito.add("Si e' verificata un'eccezione non prevista: "+e.getMessage());

			  throw e;
		  } finally {
			  log.debug("[SiceewsMgr::recuperaStatoTransazione] END");
			  return esito;

		  }

	  }
	  */
	  
	/*
	private SiceeTTransazione2018 findTransazione2018ByNumTrans(String numTrans) {
		// TODO Auto-generated method stub
		
		System.out.println("Prima di fare la query: "+numTrans);
		System.out.println("Stampo entityManager: "+entityManager);
		
		String q = "SELECT p from " + SiceeTTransazione2018.class.getName() + " p where S1_ID_TRANSAZIONE_MDP=:idTransMdp";
      TypedQuery<SiceeTTransazione2018> query = entityManager.createQuery(q, SiceeTTransazione2018.class);
      query.setParameter("idTransMdp", numTrans);
      
      System.out.println("Stampo la query: "+q);
      
      List<SiceeTTransazione2018> result=query.getResultList();
      
      System.out.println("Stampo il result: "+result);
      
      if(result.size()>0) return result.get(0);
      return null;
      
	}
	*/

	// Questi metodi li espongo per completezza, ma noi non li usiamo, quindi non li implemento
	public EsitoRiceviEsito riceviEsito(ParametriRiceviEsito parametriRiceviEsito)
	{
		return null;
	}
	public EsitoVerificaDatiPagamento verificaDatiPagamento(ParametriVerificaDatiPagamento parametriVerificaDatiPagamento)
	{
		return null;
	}
	
	public EsitoChiediDatiPagamento chiediDatiPagamento(ParametriChiediDatiPagamento parametriChiediDatiPagamento)
	{
		return null;
	}

}
