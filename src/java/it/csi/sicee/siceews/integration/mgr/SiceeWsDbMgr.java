/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceews.integration.mgr;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import it.csi.sicee.siceews.integration.db.SiceeDStatoTrans2018;
import it.csi.sicee.siceews.integration.db.SiceeTCertificatore;
import it.csi.sicee.siceews.integration.db.SiceeTCredito2018;
import it.csi.sicee.siceews.integration.db.SiceeTParametriMdp;
import it.csi.sicee.siceews.integration.db.SiceeTTransazione2018;
import it.csi.sicee.siceews.util.GenericUtil;
import it.csi.sicee.siceews.util.Constants;

//@WebService(name = "MdpnewMgrWS", targetNamespace = "http://it/csi/sicee/siceews/integration/mdpnew", serviceName = "MdpnewMgr")
//@SOAPBinding(style = SOAPBinding.Style.RPC)
@Remote(ISiceeWsDbMgr.class)
@Stateless(name = "MdpnewMgrSL")


public class SiceeWsDbMgr implements ISiceeWsDbMgr {

	private static final Logger log = Logger.getLogger(Constants.LOGGER_PREFIX);

	@PersistenceContext(unitName = "SICEEWS")
    private EntityManager entityManager;

	@Override
	public String provaMdpNew() {
		// TODO Auto-generated method stub
		
		String prova = "Prova di Beppe NEW";
		
		findTransazione2018ByNumTrans("TST000000000042580");
		
		return prova;
	}

	@Override
	public String findSiceeTParametriMdpByCodice(String codice) {
		// TODO Auto-generated method stub
		String q = "SELECT p from " + SiceeTParametriMdp.class.getName() + " p where codice=:codice";
        TypedQuery<SiceeTParametriMdp> query = entityManager.createQuery(q, SiceeTParametriMdp.class);
        query.setParameter("codice", codice);
        List<SiceeTParametriMdp> result=query.getResultList();
        if(result.size()>0) return result.get(0).getValore();
        return null;
        
	}
	
	
	public String findSiceeDStatoTrans2018(int idStatoTrans2018) {
		// TODO Auto-generated method stub
		
		
//		StringBuffer qB = new StringBuffer();
//		qB.append("SELECT c FROM ");
//		qB.append(SiceeTCertificato.class.getName());
//		qB.append(" WHERE AND c.id.idCertificatore = "+siapePk.getIdCertificatore());
//		qB.append(" AND c.id.progrCertificato = "+siapePk.getProgrCertificato());
//		qB.append(" AND c.id.anno = "+siapePk.getAnno());
//		
//		
//		System.out.println("Stampo la query findCertificatiDaGestire: "+qB);
//		
//		TypedQuery<SiceeTCertificato> query = entityManager.createQuery(qB.toString(), SiceeTCertificato.class);
        
		
		
		
		SiceeDStatoTrans2018 siceeDStatoTrans2018 = entityManager.find(SiceeDStatoTrans2018.class, idStatoTrans2018);

		
		
		log.debug("Stampo il risultato (siceeDStatoTrans2018): "+siceeDStatoTrans2018);

		  if (log.isDebugEnabled())
			  GenericUtil.stampa(siceeDStatoTrans2018, true, 3);
		
    	/*
        if (siceeTCertificato != null) {
        	
        	logger.debug("#################");
        	logger.debug("Stampo il siceeTCertificato: "+siceeTCertificato);
        	logger.debug("Stampo getIdCertificatore: "+siceeTCertificato.getId().getIdCertificatore());
        	logger.debug("Stampo getProgrCertificato: "+siceeTCertificato.getId().getProgrCertificato());
        	logger.debug("Stampo getAnno: "+siceeTCertificato.getId().getAnno());
        	logger.debug("Stampo getNumCertificatore: "+siceeTCertificato.getSiceeTCertificatore().getNumCertificatore());

        	logger.debug("#################");
			
        }
        */
        
        return siceeDStatoTrans2018.getDescrStatoTrans();
	}
	
	public SiceeTCredito2018 findSiceeTCredito2018(String idCertificatore) {
		// TODO Auto-generated method stub
		
		SiceeTCredito2018 creditoDb = null;
		
		StringBuffer qB = new StringBuffer();
		qB.append("SELECT c FROM ");
		qB.append(SiceeTCredito2018.class.getName() + " c ");
		qB.append(" WHERE FK_CERTIFICATORE = :idCertificatore");
		qB.append(" ORDER BY ID_CREDITO DESC");

		log.debug("[SiceeWsDbMgr::findSiceeTCredito2018] Stampo la query findSiceeTCredito2018: "+qB);
		
		Query query = entityManager.createQuery(qB.toString()).setParameter("idCertificatore", idCertificatore);
		
		// Prendo il primo elemento
		query = query.setMaxResults(1);

				
		List<SiceeTCredito2018> list =query.getResultList();
		
		// Sicuramente c'e' solo un elemento
		if (list != null && list.size() > 0)
		{
			creditoDb = list.get(0);
		}
		
        return creditoDb;
	}
	
	public SiceeTCredito2018 findSiceeTCredito2018ByIdTrans(Integer idTransazione) {
		// TODO Auto-generated method stub
		
		SiceeTCredito2018 creditoDb = null;
		
		StringBuffer qB = new StringBuffer();
		qB.append("SELECT c FROM ");
		qB.append(SiceeTCredito2018.class.getName() + " c ");
		qB.append(" WHERE FK_TRANSAZIONE_2018 = :idTransazione");

		log.debug("[SiceeWsDbMgr::findSiceeTCredito2018ByIdTrans] Stampo la query findSiceeTCredito2018ByIdTrans: "+qB);
		
		Query query = entityManager.createQuery(qB.toString()).setParameter("idTransazione", idTransazione);
		
		// Prendo il primo elemento
		query = query.setMaxResults(1);

				
		List<SiceeTCredito2018> list =query.getResultList();
		
		// Sicuramente c'e' solo un elemento
		if (list != null && list.size() > 0)
		{
			creditoDb = list.get(0);
		}
		
        return creditoDb;
	}
	
	/*
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SiceeTCredito2018 insertSiceeTCredito2018(SiceeTCredito2018 creditoDb)
	{
		
		System.out.println("Stampo insertSiceeTCredito2018");
		GenericUtil.stampa(creditoDb, false, 3);
		entityManager.persist(creditoDb);
		
		System.out.println("Stampo l'ID del siceeTCredito2018: "+creditoDb.getIdCredito());
		log.debug("Stampo l'ID del siceeTCredito2018: "+creditoDb.getIdCredito());
		
		return creditoDb;
	}
	*/
	
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SiceeTCredito2018 insertSiceeTCredito2018(SiceeTCredito2018 creditoDb)
	{
		
		
		
		log.debug("Stampo insertSiceeTCredito2018 prima");

		  if (log.isDebugEnabled())
			  GenericUtil.stampa(creditoDb, true, 3);
		
		//Query query = entityManager.createNativeQuery("INSERT INTO  " + SiceeTCredito2018.class.getName() + " (ID_CREDITO,FK_CERTIFICATORE,FK_TRANSAZIONE_2018,FK_TIPO_OP_2018,VALORE_CREDITO_INIZIALE,VALORE_CREDITO_FINALE,VALORE_OPERAZIONE,DATA_OPERAZIONE) VALUES (SEQ_SICEE_T_CREDITO_2018.nextval,:fkCertificatore,:fkTransazione2018,:fkTipoOp2018,:valoreCreditoIniziale,:valoreCreditoFinale,:valoreOperazione, SYSDATE)");
		//Query query = entityManager.createNativeQuery("INSERT INTO  SICEE_T_CREDITO_2018 (ID_CREDITO,FK_CERTIFICATORE,FK_TRANSAZIONE_2018,FK_TIPO_OP_2018,VALORE_CREDITO_INIZIALE,VALORE_CREDITO_FINALE,VALORE_OPERAZIONE,DATA_OPERAZIONE) VALUES (SEQ_SICEE_T_CREDITO_2018.nextval,1441,4253,2,5,16.0,11,SYSDATE)");
		Query query = entityManager.createNativeQuery("INSERT INTO SICEE_T_CREDITO_2018 (ID_CREDITO,FK_CERTIFICATORE,FK_TRANSAZIONE_2018,FK_TIPO_OP_2018,VALORE_CREDITO_INIZIALE,VALORE_CREDITO_FINALE,VALORE_OPERAZIONE,DATA_OPERAZIONE) VALUES (SEQ_SICEE_T_CREDITO_2018.nextval,?,?,?,?,?,?,?)");
		
		
//		query.setParameter("fkCertificatore", creditoDb.getFkCertificatore());
//		query.setParameter("fkTransazione2018", creditoDb.getFkTransazione2018());
//		query.setParameter("fkTipoOp2018", creditoDb.getFkTipoOp2018());
//		query.setParameter("valoreCreditoIniziale", creditoDb.getValoreCreditoIniziale());
//		query.setParameter("valoreCreditoFinale", creditoDb.getValoreCreditoFinale());
//		query.setParameter("valoreOperazione", creditoDb.getValoreOperazione());

		query.setParameter(1, creditoDb.getFkCertificatore());
		query.setParameter(2, creditoDb.getFkTransazione2018());
		query.setParameter(3, creditoDb.getFkTipoOp2018());
		query.setParameter(4, creditoDb.getValoreCreditoIniziale());
		query.setParameter(5, creditoDb.getValoreCreditoFinale());
		query.setParameter(6, creditoDb.getValoreOperazione());
		query.setParameter(7, creditoDb.getDataOperazione(), TemporalType.DATE);

//		System.out.println("Stampo la query: "+query);
//		System.out.println("Stampo la query: "+query.toString());
		
		query.executeUpdate();

		
//		System.out.println("Stampo insertSiceeTCredito2018 dopo la insert");
//		GenericUtil.stampa(creditoDb, false, 3);
		
		
//		System.out.println("Stampo l'ID del siceeTCredito2018: "+creditoDb.getIdCredito());
		log.debug("Stampo l'ID del siceeTCredito2018: "+creditoDb.getIdCredito());
		
		return creditoDb;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void aggiornaTransazione2018(SiceeTTransazione2018 transazioneDb, SiceeTCredito2018 creditoDb)
	{
		// Non funziona perche' c'e' l'oggetto CLOB
		//entityManager.merge(transazioneDb);

		Query query = entityManager.createQuery("UPDATE " + SiceeTTransazione2018.class.getName() + " SET DT_CHIUSURA_TRANSAZIONE = :dataChiusura, XML_RT = :xml, FK_STATO_TRANS_2018 = :statoTran WHERE ID_TRANSAZIONE_2018 = :idTransazione");

		query.setParameter("dataChiusura", transazioneDb.getDtChiusuraTransazione());
		query.setParameter("xml", transazioneDb.getXmlRt());
		query.setParameter("statoTran", transazioneDb.getFkStatoTrans2018());
		query.setParameter("idTransazione", transazioneDb.getIdTransazione2018());
		query.executeUpdate();

		// NEW

		if (creditoDb != null)
		{
			// Cerco su tCredito2018 se esiste gia' l'assegnazione x l'idTransazione (in prod si sono verificati casi di doppia assegnazione)
			// Se e' gia' presente non do errore ma non inserisco un nuovo credito
			// E' un errore che bisognerebbe capire bene, il metodo dovrebbe essere transazionale
			SiceeTCredito2018 creditoDbOld = findSiceeTCredito2018ByIdTrans(creditoDb.getFkTransazione2018());

			if (creditoDbOld == null)
			{
				log.debug("Stampo insertSiceeTCredito2018 prima");

				  if (log.isDebugEnabled())
					  GenericUtil.stampa(creditoDb, true, 3);

				/*
		//Query query = entityManager.createNativeQuery("INSERT INTO  " + SiceeTCredito2018.class.getName() + " (ID_CREDITO,FK_CERTIFICATORE,FK_TRANSAZIONE_2018,FK_TIPO_OP_2018,VALORE_CREDITO_INIZIALE,VALORE_CREDITO_FINALE,VALORE_OPERAZIONE,DATA_OPERAZIONE) VALUES (SEQ_SICEE_T_CREDITO_2018.nextval,:fkCertificatore,:fkTransazione2018,:fkTipoOp2018,:valoreCreditoIniziale,:valoreCreditoFinale,:valoreOperazione, SYSDATE)");
		//Query query = entityManager.createNativeQuery("INSERT INTO  SICEE_T_CREDITO_2018 (ID_CREDITO,FK_CERTIFICATORE,FK_TRANSAZIONE_2018,FK_TIPO_OP_2018,VALORE_CREDITO_INIZIALE,VALORE_CREDITO_FINALE,VALORE_OPERAZIONE,DATA_OPERAZIONE) VALUES (SEQ_SICEE_T_CREDITO_2018.nextval,1441,4253,2,5,16.0,11,SYSDATE)");
		query = entityManager.createNativeQuery("INSERT INTO SICEE_T_CREDITO_2018 (ID_CREDITO,FK_CERTIFICATORE,FK_TRANSAZIONE_2018,FK_TIPO_OP_2018,VALORE_CREDITO_INIZIALE,VALORE_CREDITO_FINALE,VALORE_OPERAZIONE,DATA_OPERAZIONE) VALUES (SEQ_SICEE_T_CREDITO_2018.nextval,?,?,?,?,?,?,?)");


//		query.setParameter("fkCertificatore", creditoDb.getFkCertificatore());
//		query.setParameter("fkTransazione2018", creditoDb.getFkTransazione2018());
//		query.setParameter("fkTipoOp2018", creditoDb.getFkTipoOp2018());
//		query.setParameter("valoreCreditoIniziale", creditoDb.getValoreCreditoIniziale());
//		query.setParameter("valoreCreditoFinale", creditoDb.getValoreCreditoFinale());
//		query.setParameter("valoreOperazione", creditoDb.getValoreOperazione());

		query.setParameter(1, creditoDb.getFkCertificatore());
		query.setParameter(2, creditoDb.getFkTransazione2018());
		query.setParameter(3, creditoDb.getFkTipoOp2018());
		query.setParameter(4, creditoDb.getValoreCreditoIniziale());
		query.setParameter(5, creditoDb.getValoreCreditoFinale());
		query.setParameter(6, creditoDb.getValoreOperazione());
		query.setParameter(7, creditoDb.getDataOperazione());



		System.out.println("Stampo la query: "+query);
		System.out.println("Stampo la query: "+query.toString());

		query.executeUpdate();
				 */
				entityManager.persist(creditoDb);
				log.debug("Stampo insertSiceeTCredito2018 dopo la insert");
				  
				if (log.isDebugEnabled())
					  GenericUtil.stampa(creditoDb, false, 3);
				  
				log.debug("Stampo l'ID del siceeTCredito2018: "+creditoDb.getIdCredito());
			}
			else
			{
				log.error("Si sta cercando di inserire un credito gia' presente su siceeTCredito2018, id credito: "+creditoDbOld.getIdCredito());
			}
		}
		else
		{
			log.debug("NON devo inserire il credito perche' e' KO");

		}

		//entityManager.persist(creditoDb);


	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SiceeTTransazione2018 findTransazione2018ByNumTrans(String numTrans) {
		// TODO Auto-generated method stub
		
		log.debug("Prima di fare la query: "+numTrans);
		log.debug("Stampo entityManager: "+entityManager);
		
		String q = "SELECT p from " + SiceeTTransazione2018.class.getName() + " p where S1_ID_TRANSAZIONE_MDP=:idTransMdp";
        
		TypedQuery<SiceeTTransazione2018> query = entityManager.createQuery(q, SiceeTTransazione2018.class);
        query.setParameter("idTransMdp", numTrans);
        
        log.debug("Stampo la query: "+q);
        
        List<SiceeTTransazione2018> result=query.getResultList();
        
        log.debug("Stampo il result: "+result);
        
        if(result.size()>0) return result.get(0);
        return null;
        
	}
	
	/*
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void aggiornaTransazione2018(SiceeTTransazione2018 transazioneDb)
	{
		// Non funziona perche' c'e' l'oggetto CLOB
		//entityManager.merge(transazioneDb);
		
		Query query = entityManager.createQuery("UPDATE " + SiceeTTransazione2018.class.getName() + " SET DT_CHIUSURA_TRANSAZIONE = :dataChiusura, XML_RT = :xml, FK_STATO_TRANS_2018 = :statoTran WHERE ID_TRANSAZIONE_2018 = :idTransazione");
		
		query.setParameter("dataChiusura", transazioneDb.getDtChiusuraTransazione());
		query.setParameter("xml", transazioneDb.getXmlRt());
		query.setParameter("statoTran", transazioneDb.getFkStatoTrans2018());
		query.setParameter("idTransazione", transazioneDb.getIdTransazione2018());
		query.executeUpdate();

		
		
	}
	*/
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void aggiornaStatoTransazione2018(SiceeTTransazione2018 transazioneDb)
	{
		// Non funziona perche' c'e' l'oggetto CLOB
		//entityManager.merge(transazioneDb);
		
		Query query = entityManager.createQuery("UPDATE " + SiceeTTransazione2018.class.getName() + " SET OP_GETSTATOTRANS_FK_STATO = :opGetstatotransFkStato, OP_GETSTATOTRANS_DT = :opGetstatotransDt WHERE ID_TRANSAZIONE_2018 = :idTransazione");
		
		log.debug("opGetstatotransFkStato: " + transazioneDb.getOpGetstatotransFkStato());
		log.debug("getOpGetstatotransDt: " + transazioneDb.getOpGetstatotransDt());
		log.debug("getIdTransazione2018: " + transazioneDb.getIdTransazione2018());
		
		query.setParameter("opGetstatotransFkStato", transazioneDb.getOpGetstatotransFkStato());
		query.setParameter("opGetstatotransDt", transazioneDb.getOpGetstatotransDt());
		query.setParameter("idTransazione", transazioneDb.getIdTransazione2018());
		
		query.executeUpdate();

	}
	
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
	
	public SiceeTCertificatore findSiceeTCertificatore(String idCertificatore) {
		// TODO Auto-generated method stub
		
		
//		StringBuffer qB = new StringBuffer();
//		qB.append("SELECT c FROM ");
//		qB.append(SiceeTCertificato.class.getName());
//		qB.append(" WHERE AND c.id.idCertificatore = "+siapePk.getIdCertificatore());
//		qB.append(" AND c.id.progrCertificato = "+siapePk.getProgrCertificato());
//		qB.append(" AND c.id.anno = "+siapePk.getAnno());
//		
//		
//		System.out.println("Stampo la query findCertificatiDaGestire: "+qB);
//		
//		TypedQuery<SiceeTCertificato> query = entityManager.createQuery(qB.toString(), SiceeTCertificato.class);
        
		
		log.debug("Stampo l'idCertificatore: "+idCertificatore);
		
		SiceeTCertificatore siceeTCertificatore = entityManager.find(SiceeTCertificatore.class, idCertificatore);

		
		
		log.debug("Stampo il risultato (siceeTCertificatore): "+siceeTCertificatore);

		  if (log.isDebugEnabled())
			  GenericUtil.stampa(siceeTCertificatore, true, 3);
		
    	/*
        if (siceeTCertificato != null) {
        	
        	logger.debug("#################");
        	logger.debug("Stampo il siceeTCertificato: "+siceeTCertificato);
        	logger.debug("Stampo getIdCertificatore: "+siceeTCertificato.getId().getIdCertificatore());
        	logger.debug("Stampo getProgrCertificato: "+siceeTCertificato.getId().getProgrCertificato());
        	logger.debug("Stampo getAnno: "+siceeTCertificato.getId().getAnno());
        	logger.debug("Stampo getNumCertificatore: "+siceeTCertificato.getSiceeTCertificatore().getNumCertificatore());

        	logger.debug("#################");
			
        }
        */
        
        return siceeTCertificatore;
	}
}
