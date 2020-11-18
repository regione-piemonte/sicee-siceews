/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceews.integration.mgr;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

//import javax.swing.text.AbstractDocument.Content;

import org.apache.log4j.Logger;

import it.csi.mdp.core.business.dto.RicevutaTelematicaNodo;
import it.csi.mdp.core.business.dto.Transazione;
import it.csi.mdp.core.interfacecxf.IMdpCoreCxf;
import it.csi.nodospc.mdp.interfacews.serviziofruitore.EsitoRiceviRT;
import it.csi.nodospc.mdp.interfacews.serviziofruitore.ParametriRiceviRT;
import it.csi.sicee.siceews.dto.Allegato;
import it.csi.sicee.siceews.dto.Mail;
import it.csi.sicee.siceews.exception.SiceeWsException;
import it.csi.sicee.siceews.integration.db.SiceeTCertificatore;
import it.csi.sicee.siceews.integration.db.SiceeTTransazione2018;
import it.csi.sicee.siceews.util.GenericUtil;
import it.csi.sicee.siceews.util.Constants;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//import it.doqui.index.ecmengine.client.webservices.dto.Node;
//import it.doqui.index.ecmengine.client.webservices.dto.OperationContext;
//import it.doqui.index.ecmengine.client.webservices.dto.engine.management.Content;
//import it.doqui.index.ecmengine.client.webservices.engine.EcmEngineWebServiceDelegate;
//import it.doqui.index.ecmengine.client.webservices.engine.EcmEngineWebServiceDelegateServiceLocator;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.ejb.Stateless;
import javax.jws.soap.SOAPBinding;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.xml.bind.DataBindingException;

public class SiceeWsServiceMgr {

	private static final Logger log = Logger.getLogger(Constants.LOGGER_PREFIX);
	//final static   SiceeorchSrv srvSiceeorch;

   public static SiceeWsServiceMgrFactory factory = new SiceeWsServiceMgrFactory();

	//final static String SERVER = factory.getActaHost();
	//final static int PORT = factory.getActaPort();
	//final static String CONTEXT = "/"+factory.getActaContext();
	
	/** The Constant PD_SORCH_RES. */
	//public final static String PD_SORCH_RES = "/META-INF/defpd_siceeorch.xml";

   /*
	public EsitoRiceviRT gestisciRicezioneMdpRT(ParametriRiceviRT parametriRT) throws Exception {
		log.debug("[SiceeWsService::gestisciRicezioneMdpRT] BEGIN");
		//GenericUtil.stampaVO(emailVO);	
		// Create a mail session
		
		EsitoRiceviRT esito = new EsitoRiceviRT();
		try {
			
			String codEsito = parametriRT.getCodEsitoPagamento();
			DataHandler rt = parametriRT.getRtData();
			//byte[] rtByte = parametriRT.getRtData();

			String timestamp = parametriRT.getTimestamp();
			String mac = parametriRT.getMac();
			
			String macCalcolato = GenericUtil.getMacRiceviRT(parametriRT);
			
			if (!mac.equals(macCalcolato))
			{
				esito.setEsito(Constants.COD_KO);
				//esito.setCodiceErrore("");
				esito.setMessaggioErrore("MAC non corrispondenti");
			}
			
			// Devo fare il salvataggio
			
			// Ricevo L'oggetto
			//SiceeTTransazione2018 transazioneDb = findTransazione2018ByNumTrans(parametriRT.getTransactionId());
			SiceeTTransazione2018 transazioneDb = null;
			
			Mail mail = new Mail();
			mail.setMittente("assistenza.energia@csi.it");
			mail.setDestinatario("giuseppe.todaro@csi.it");
			
			mail.setOggetto("SIPEEWS: Prova invio");

			StringBuffer messaggio = new StringBuffer();
			messaggio.append("Con la presente si informa che l'acquisto....  ");
			messaggio.append("\n\nIn allegato si trasmette ricevuta aggiornata.");

			mail.setTesto(messaggio.toString());

			byte[] doc = null;
//			byte[] doc = recuperaFileRicevuta(certificato.getNumCertificatore(), certificato.getProgrCertificato(), certificato.getAnno());

			sendMail(mail, "ricevuta_trasmissione.pdf", doc);
			
		}
//		catch (ACTAInvocationException e) {
//			log.debug("[SiceeWsService::sendMailProva] Errore nell'invio della mail (ACTAInvocationException)");
//
//			throw e;
//		}
		catch (Exception e) {
			log.debug("[SiceeWsService::gestisciRicezioneMdpRT] Errore nell'invio della mail (Exception)");
			throw e;
		} finally {
			log.debug("[SiceeWsService::gestisciRicezioneMdpRT] END");
			return esito;

		}

	}
	*/
   
	public byte[] recuperaMdpRT(String iuv) throws SiceeWsException {
		log.debug("[SiceeWsService::recuperaMdpRT] BEGIN");
		//GenericUtil.stampaVO(emailVO);	
		// Create a mail session
		
		byte[] rt = null;
		try {
			
			JaxWsProxyFactoryBean fact = new JaxWsProxyFactoryBean();
			fact.setServiceClass(IMdpCoreCxf.class);
			//String mdpcoreAddress = "http://tst-applogic.reteunitaria.piemonte.it/mdpcoreservices/MdpCoreCxf/MdpCoreCxf";
			fact.setAddress(factory.getMdpEndpoint());
			fact.getInInterceptors().add(new LoggingInInterceptor());
			fact.getOutInterceptors().add(new LoggingOutInterceptor());
			
			IMdpCoreCxf clientcxfmdp = (IMdpCoreCxf)fact.create();

			log.debug("Ricerco lo iuv: "+iuv);
			
			// BEPPE - TEST DA CANCELLARE
			//iuv = "RF50181590003TEST00000007";
			
			RicevutaTelematicaNodo ric = clientcxfmdp.getRTperIUV(iuv);
			
			log.debug("Stampo il RicevutaTelematicaNodo: "+ric);
			
			rt = ric.getRtData();
			
			log.debug("Stampo l'RT: "+rt);
			
			return rt;

		}
		catch (it.csi.mdp.core.business.exceptions.DaoException ex) {
			log.debug("[SiceeWsService::recuperaMdpRT] Errore nell'invocazione del servizio MDP");

			  throw new SiceeWsException(ex.getMessage());
		}
		catch (Exception e) {
			log.debug("[SiceeWsService::recuperaMdpRT] Errore nel recupero dell'RT (Exception)");
			  throw new SiceeWsException("Errore nel recupero dell'RT (Exception)");
		}
		catch (Throwable t)
		{
			t.printStackTrace();
			
			log.error("[SiceeWsService::recuperaMdpRT] Errore nel recupero dell'RT (Throwable)");
			  throw new SiceeWsException("Errore nel recupero dell'RT (Throwable)");
			
		} finally {
			
			log.debug("[SiceeWsService::recuperaMdpRT] END");

		}

	}
	
	public Integer recuperaMdpStatoTransazione(String iuv) throws Exception {
		log.debug("[SiceeWsService::recuperaMdpStatoTransazione] BEGIN");
		//GenericUtil.stampaVO(emailVO);	
		// Create a mail session
		log.debug("SiceeWsService - PASSO1"); 
		Integer esito = null;
		try {
			
			JaxWsProxyFactoryBean fact = new JaxWsProxyFactoryBean();

			log.debug("SiceeWsService - PASSO2"); 

			fact.setServiceClass(IMdpCoreCxf.class);
			
			log.debug("SiceeWsService - PASSO3"); 

			//String mdpcoreAddress = "http://tst-applogic.reteunitaria.piemonte.it/mdpcoreservices/MdpCoreCxf/MdpCoreCxf";
			fact.setAddress(factory.getMdpEndpoint());
			
			log.debug("SiceeWsService - PASSO4: "+factory.getMdpEndpoint()); 

			fact.getInInterceptors().add(new LoggingInInterceptor());
			
			log.debug("SiceeWsService - PASSO5"); 

			fact.getOutInterceptors().add(new LoggingOutInterceptor());
			
			log.debug("SiceeWsService - PASSO6: "+(fact!=null)); 

			log.debug("SiceeWsService - PASSO6.1: "+fact.isLoadHandlers()); 
			log.debug("SiceeWsService - PASSO6.2: "+fact.getWsdlURL()); 
			log.debug("SiceeWsService - PASSO6.3: "+fact.getWsdlLocation()); 
			log.debug("SiceeWsService - PASSO6.4: "+fact.getAddress()); 
			log.debug("SiceeWsService - PASSO6.5: "+fact.getEndpointName()); 
			log.debug("SiceeWsService - PASSO6.6: "+fact.getServiceClass()); 
			log.debug("SiceeWsService - PASSO6.7: "+fact.getTransportId()); 
			log.debug("SiceeWsService - PASSO6.8: "+fact.getBindingId()); 

			log.debug("[SiceeWsService::recuperaMdpStatoTransazione] fact.getAddress(): "+fact.getAddress()); 
			log.debug("[SiceeWsService::recuperaMdpStatoTransazione] fact.getServiceClass(): "+fact.getServiceClass()); 

			log.debug("[SiceeWsService::recuperaMdpStatoTransazione] Prima del create");

			IMdpCoreCxf clientcxfmdp = (IMdpCoreCxf)fact.create();

			log.debug("[SiceeWsService::recuperaMdpStatoTransazione] Dopo il create");

			log.debug("SiceeWsService - PASSO7"); 

			log.debug("[SiceeWsService::recuperaMdpStatoTransazione] iuv: "+iuv);

			log.debug("Sono recuperaMdpStatoTransazione - iuv: "+iuv);
			Transazione ric = clientcxfmdp.getStatoTransazione(iuv);
			
			log.debug("Stampo il ric: "+ric);
			log.debug("Stampo il ric.getCodStato(): "+ric.getCodStato());
			
			log.debug("[SiceeWsService::recuperaMdpStatoTransazione] ric: "+ric);
			log.debug("[SiceeWsService::recuperaMdpStatoTransazione] ric.getCodStato(): "+ric.getCodStato());

			
			  if (log.isDebugEnabled())
				  GenericUtil.stampa(ric, true, 3);
			
			
			
			esito = GenericUtil.convertToInteger(ric.getCodStato());
			
			return esito;

		}
		catch (it.csi.mdp.core.business.exceptions.DaoException ex) {
			
			ex.printStackTrace();
			
			log.error("[SiceeWsService::recuperaMdpStatoTransazione] Errore nell'invocazione del servizio MDP");

			  throw new SiceeWsException(ex.getMessage());
		}
		catch (Exception e) {
			
			e.printStackTrace();
			
			log.error("[SiceeWsService::recuperaMdpStatoTransazione] Errore nel recupero dell'RT (Exception)");
			  throw new SiceeWsException("Errore nel recupero dell'RT (Exception)");
		}
		catch (Throwable t)
		{
			t.printStackTrace();
			
			log.error("[SiceeWsService::recuperaMdpStatoTransazione] Errore nel recupero dell'RT (Throwable)");
			  throw new SiceeWsException("Errore nel recupero dell'RT (Throwable)");
			
		}
		finally {
			log.debug("[SiceeWsService::recuperaMdpStatoTransazione] END");

		}

	}
	
	public EsitoRiceviRT provaRichiamoDB() throws Exception {
		log.debug("[SiceeWsService::provaRichiamoDB] BEGIN");
		//GenericUtil.stampaVO(emailVO);	
		// Create a mail session
		
		EsitoRiceviRT esito = new EsitoRiceviRT();
		try {
			
			ParametriRiceviRT parametriRT = new ParametriRiceviRT();
			parametriRT.setTransactionId("TST000000000042580");
			
			
			
			// Ricevo L'oggetto
			//SiceeTTransazione2018 transazioneDb = findTransazione2018ByNumTrans(parametriRT.getTransactionId());
			SiceeTTransazione2018 transazioneDb = null;
			
			  if (log.isDebugEnabled())
				  GenericUtil.stampa(transazioneDb, true, 3);
			
			//SiceeTCertificatore cert = findSiceeTCertificatore(transazioneDb.getFkCertificatore());
			SiceeTCertificatore cert = null;

			  if (log.isDebugEnabled())
				  GenericUtil.stampa(cert, true, 3);

			Mail mail = new Mail();
			mail.setMittente("assistenza.energia@csi.it");
			mail.setDestinatario("giuseppe.todaro@csi.it");
			
			mail.setOggetto("SIPEEWS: Prova invio");

			StringBuffer messaggio = new StringBuffer();
			messaggio.append("Con la presente si informa che l'acquisto....  ");
			messaggio.append("\n\nIn allegato si trasmette ricevuta aggiornata.");

			mail.setTesto(messaggio.toString());

			byte[] doc = null;
//			byte[] doc = recuperaFileRicevuta(certificato.getNumCertificatore(), certificato.getProgrCertificato(), certificato.getAnno());

			sendMail(mail, "ricevuta_trasmissione.pdf", doc);
			
			
		}
//		catch (ACTAInvocationException e) {
//			log.debug("[SiceeWsService::sendMailProva] Errore nell'invio della mail (ACTAInvocationException)");
//
//			throw e;
//		}
		catch (Exception e) {
			log.error("[SiceeWsService::provaRichiamoDB] Errore nell'invio della mail (Exception)",e);
			throw e;
		} finally {
			log.debug("[SiceeWsService::provaRichiamoDB] END");
			return esito;

		}

	}

	public String sendMailEsitoRT(SiceeTTransazione2018 transazioneDb, String mailMittente, String mailDestinatario, String descEsito)  throws Exception
	{
		log.debug("[SiceeWsService::sendMailEsitoRT] BEGIN");

		try {
			Mail mail = new Mail();

			mail.setDestinatario(mailDestinatario);
			mail.setMittente(mailMittente);

			mail.setOggetto("Pagamento SIPEE " + transazioneDb.getS1IdTransazioneMdp());

			StringBuffer messaggio = new StringBuffer();
			messaggio.append("La presente per informare che il pagamento di ");
			messaggio.append(GenericUtil.convertToString(transazioneDb.getS1ValoreTransazione()));
			messaggio.append(" euro in oggetto ");
			messaggio.append(transazioneDb.getS1IdTransazioneMdp());
			messaggio.append(" richiesto in data ");
			messaggio.append(GenericUtil.convertToString(transazioneDb.getS1DtCreazioneTransazione()));
			messaggio.append(" ha avuto esito ");
			messaggio.append(descEsito);
			messaggio.append("\n\n ");
			messaggio.append("La ricevuta puo' essere scaricata direttamente dal SIPEE ");
			messaggio.append(" ");


			mail.setTesto(messaggio.toString());

			sendMail(mail, null, null);

			return "OK";
		}
		//		catch (ACTAInvocationException e) {
		//			log.debug("[SiceeWsService::sendMailProva] Errore nell'invio della mail (ACTAInvocationException)");
		//
		//			throw e;
		//		}
		catch (Exception e) {
			log.debug("[SiceeWsService::sendMailEsitoRT] Errore nell'invio della mail (Exception)");
			throw e;
		} finally {
			log.debug("[SiceeWsService::sendMailEsitoRT] END");
		}

	}

	public String sendMailAssistenzaErrore(SiceeTTransazione2018 transazioneDb, String mailMittente, String mailDestinatario, byte[] doc)  throws Exception
	{
		log.debug("[SiceeWsService::sendMailEsitoRT] BEGIN");

		try {
			Mail mail = new Mail();

			mail.setDestinatario(mailDestinatario);
			mail.setMittente(mailMittente);

			mail.setOggetto("Pagamento SIPEE " + transazioneDb.getS1IdTransazioneMdp());

			StringBuffer messaggio = new StringBuffer();
			messaggio.append("La presente per informare che il pagamento di ");
			messaggio.append(GenericUtil.convertToString(transazioneDb.getS1ValoreTransazione()));
			messaggio.append(" euro in oggetto ");
			messaggio.append(transazioneDb.getS1IdTransazioneMdp());
			messaggio.append(" richiesto in data ");
			messaggio.append(GenericUtil.convertToString(transazioneDb.getS1DtCreazioneTransazione()));
			messaggio.append(" presenta delle incongruenze tra il valore richiesto dall'utente ");
			messaggio.append(GenericUtil.convertToString(transazioneDb.getS1ValoreTransazione()));
			messaggio.append(" euro e il valore ricevuto da MDP attraverso la ricevuta telematica (RT) allegata ");
			
			mail.setTesto(messaggio.toString());

			sendMail(mail, "RT_"+transazioneDb.getS1IdTransazioneMdp()+".xml", doc);

			return "OK";
		}
		//		catch (ACTAInvocationException e) {
		//			log.debug("[SiceeWsService::sendMailProva] Errore nell'invio della mail (ACTAInvocationException)");
		//
		//			throw e;
		//		}
		catch (Exception e) {
			log.debug("[SiceeWsService::sendMailEsitoRT] Errore nell'invio della mail (Exception)");
			throw e;
		} finally {
			log.debug("[SiceeWsService::sendMailEsitoRT] END");
		}

	}
	
	public void sendMail(Mail mail) throws Exception {
		log.debug("[SiceeWsService::sendMail] BEGIN");
		//GenericUtil.stampaVO(emailVO);	
		// Create a mail session
		
		sendMail(mail, null, null);
		
		log.debug("[SiceeWsService::sendMail] END");

	}
	
	public void sendMail(Mail mail, String nomeFile, byte[] doc) throws Exception {
		log.debug("[SiceeWsService::sendMail] BEGIN");
		//GenericUtil.stampaVO(emailVO);	
		// Create a mail session
		File allegato = null;
		
		try {
			java.util.Properties props = new java.util.Properties();        
			props.put("mail.smtp.host", factory.getMailHost());
			props.put("mail.smtp.port", factory.getMailPort());
			Session session = Session.getDefaultInstance(props, null);


			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(mail.getMittente()));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getDestinatario()));
			//        if(StringUtils.isNotEmpty(emailVo.getDestinatarioCC())){
			//        	msg.setRecipient(Message.RecipientType.CC, new InternetAddress(emailVo.getDestinatarioCC()));
			//        }
			msg.setSubject(mail.getOggetto());
			MimeMultipart  mp = new MimeMultipart();



			MimeBodyPart html = new MimeBodyPart();
			html.setText(mail.getTesto(), "text/plain");                
			html.setContent(mail.getTesto().replace("\n", "</br>"),"text/html");



			// create and fill the second message part
			if (doc != null) {
				
				MimeBodyPart attachmentPart = new MimeBodyPart();

//				JIRA SICEE-515
				allegato = createFileWithData(nomeFile, doc);
				FileDataSource fileDataSource = new FileDataSource(allegato) {
					@Override
					public String getContentType() {
						return "text/xml";
					}
				};
				attachmentPart.setDataHandler(new DataHandler(fileDataSource));
				attachmentPart.setFileName(nomeFile);        	            
				mp.addBodyPart(attachmentPart);
				
			}
			// create the Multipart and its parts to it

			//mp.addBodyPart(text);
			mp.addBodyPart(html);

			// add the Multipart to the message
			msg.setContent(mp);        

			// Aggiunto questo comando per risolvere il problema di invio mail
			//Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());

			log.debug("[SiceeWsService::sendMail] - stampo il ContextClassLoader: "+Thread.currentThread().getContextClassLoader());
			if (Thread.currentThread().getContextClassLoader() == null)
			{
				log.debug("[SiceeWsService::sendMail] - stampo il this.getClass().getClassLoader(): "+this.getClass().getClassLoader());

				Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
				
				log.debug("[SiceeWsService::sendMail] - stampo il ContextClassLoader - dopo: "+Thread.currentThread().getContextClassLoader());
			}
			
			// Send the message
			Transport.send(msg);
		} catch (Exception e) {
			log.error("Errore nell'invio della mail");
			throw e;
		} finally {
//			JIRA SICEE-515
			if (allegato != null && allegato.exists()) {
				boolean isDelete = allegato.delete();
				
				if (log.isDebugEnabled())
        			log.debug("[MailSender::sendMail] "+allegato+": file.delete(): "+isDelete);
			}
			log.debug("[SiceeWsService::sendMail] END");
		}

	}
	
	public void sendMailNew(Mail mail) throws Exception {
		log.info("[SiceeWsService::sendMail] BEGIN");
		//GenericUtil.stampaVO(emailVO);	
		// Create a mail session
		
		// Questa collezione serve per tener traccia dei files da cancellare
        ArrayList<File> elencoFileAllegati = new ArrayList<File>();

        try {

			java.util.Properties props = new java.util.Properties();        
			props.put("mail.smtp.host", factory.getMailHost());
			props.put("mail.smtp.port", factory.getMailPort());
			Session session = Session.getDefaultInstance(props, null);


			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(mail.getMittente()));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getDestinatario()));
			if(mail.getDestinatarioCC() != null){
				msg.setRecipient(Message.RecipientType.CC, new InternetAddress(mail.getDestinatarioCC()));
			}
			msg.setSubject(mail.getOggetto());
			MimeMultipart  mp = new MimeMultipart();


			
			MimeBodyPart html = new MimeBodyPart();
			
			/*
			log.debug("#####");
			log.debug("Stampo il corpo testo: "+mail.getTesto());
			log.debug("Stampo il corpo html: "+mail.getHtml());
			log.debug("#####");
			*/
			
			if (mail.getTesto() != null)
				html.setText(mail.getTesto(), "utf-8");               
			
			if (mail.getHtml() != null)
				html.setContent(mail.getHtml(),"text/html; charset=utf-8");
			
			mp.addBodyPart(html);
			

			
			if (mail.getElencoAllegati().size() > 0)
			{
				java.util.ArrayList<Allegato> elencoAllegati = mail.getElencoAllegati();
				
				for (Allegato allegato : elencoAllegati) {
					
				
				// create and fill the second message part
				if (allegato != null) {
					
					MimeBodyPart attachmentPart = new MimeBodyPart();
					final String contentType = allegato.getContentType();
					
					File allegatoFile = createFileWithData(allegato.getNomeFile(), allegato.getFile());
					
					elencoFileAllegati.add(allegatoFile);
					
					FileDataSource fileDataSource = new FileDataSource(allegatoFile) {
						@Override
						public String getContentType() {
							//return allegato.getContentType();
							//return "application/pdf";
							return contentType;

						}
					};
					attachmentPart.setDataHandler(new DataHandler(fileDataSource));
					attachmentPart.setFileName(allegato.getNomeFile());        	            
					mp.addBodyPart(attachmentPart);
					
				}
				// create the Multipart and its parts to it	
				}
			}
			
			

			// add the Multipart to the message
			msg.setContent(mp);        

			// Aggiunto questo comando per risolvere il problema di invio mail
			//Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());

			log.debug("[SiceeWsService::sendMail] - stampo il ContextClassLoader: "+Thread.currentThread().getContextClassLoader());
			if (Thread.currentThread().getContextClassLoader() == null)
			{
				log.debug("[SiceeWsService::sendMail] - stampo il this.getClass().getClassLoader(): "+this.getClass().getClassLoader());

				Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
				
				log.debug("[SiceeWsService::sendMail] - stampo il ContextClassLoader - dopo: "+Thread.currentThread().getContextClassLoader());
			}
			
			// Send the message
			Transport.send(msg);
		} catch (Exception e) {
			log.error("Errore nell'invio della mail");
			throw e;
		} finally {
			
			log.debug("elencoFileAllegati: "+elencoFileAllegati);
	        if (elencoFileAllegati != null && elencoFileAllegati.size() > 0)
	        {
	        	for (File fileDel : elencoFileAllegati) {

	        		if (fileDel != null && fileDel.exists()) {
	        			
	        			log.debug("Prima di cancellare");
	        			boolean isDelete = fileDel.delete();
	        			
	        			if (log.isDebugEnabled()) {	        				
	        				log.debug("[MailSender::sendMail] "+fileDel+": file.delete(): "+isDelete);
	        			}
	        		}
	        	}
	        }
			
			log.info("[SiceeWsService::sendMail] END");
		}

	}
	
	private static File createFileWithData(String nome, byte[] doc) throws IOException
	{		
		int pos = nome.lastIndexOf(".");
		File file = File.createTempFile(nome, nome);
//		file.deleteOnExit();
		OutputStream src = new FileOutputStream(file);
		src.write(doc);
		src.close();
		return file;
	}
	
	/*
	public SiceeTTransazione2018 findTransazione2018ByNumTrans(String numTrans) {
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
        
		
		
		
		SiceeTCertificatore siceeTCertificatore = entityManager.find(SiceeTCertificatore.class, idCertificatore);

		
		
		log.debug("Stampo il risultato: "+siceeTCertificatore);

		GenericUtil.stampa(siceeTCertificatore, false, 3);
		
    	
        return siceeTCertificatore;
	}
	*/
	
	/*
	public SiceeorchSrv getSiceeorchSrv() {
		logger.debug("[ACTAService::getSiceeorchSrv] BEGIN");
		SiceeorchSrv srvSiceeorch = null;
		if (srvSiceeorch == null )
		{
			InputStream is = getClass().getResourceAsStream(PD_SORCH_RES);
			
			if (is != null) {
				try {

					InfoPortaDelegata info = PDConfigReader.read(is);
						
					srvSiceeorch = (SiceeorchSrv) PDProxy.newInstance(info);

					return srvSiceeorch;

				} 
				catch (Exception e) {
					logger.error("[ACTAService::getSiceeorchSrv] errore nella parsificazione della configurazione di SICEEORCH:"+ e, e);
					throw new IllegalArgumentException("errore nella parsificazione della configurazione di SICEEORCH");
				}
			} else{
				logger.error("[ACTAService::getSiceeorchSrv] configurazione di SICEEORCH non trovata");
			}

			throw new IllegalArgumentException("configurazione di SICEEORCH non trovata");
		}

		logger.debug("[ACTAService::getSiceeorchSrv] END");

		return srvSiceeorch;

	}
		
	
	private static AcarisContentStreamType creaContentStream(byte[] fileByte, final String fileName,
			final String estensioneFile, EnumMimeTypeType mimeType) throws IOException {
		AcarisContentStreamType contentStream = new AcarisContentStreamType();
		//byte[] stream = getBytesFromFile(filePath + fileName);
		final InputStream iS = new ByteArrayInputStream(fileByte);
		final OutputStream oS = new ByteArrayOutputStream(fileByte.length);

		javax.activation.DataSource a = new javax.activation.DataSource() {

			public OutputStream getOutputStream() throws IOException {
				return oS;
			}

			public String getName() {
				return fileName;
			}

			public InputStream getInputStream() throws IOException {
				return iS;
			}

			public String getContentType() {
				return estensioneFile;
			}
		};
		contentStream.setStreamMTOM(new DataHandler(a));
		// contentStream.setStream(stream);
		contentStream.setFilename(a.getName());
		contentStream.setMimeType(mimeType);
		return contentStream;
	}
	

	private static AcarisContentStreamType creaContentStreamOld(String filePath, final String fileName,
			final String estensioneFile, EnumMimeTypeType mimeType) throws IOException {
		AcarisContentStreamType contentStream = new AcarisContentStreamType();
		byte[] stream = getBytesFromFileOld(filePath + fileName);
		final InputStream iS = new ByteArrayInputStream(stream);
		final OutputStream oS = new ByteArrayOutputStream(stream.length);

		javax.activation.DataSource a = new javax.activation.DataSource() {

			public OutputStream getOutputStream() throws IOException {
				return oS;
			}

			public String getName() {
				return fileName;
			}

			public InputStream getInputStream() throws IOException {
				return iS;
			}

			public String getContentType() {
				return estensioneFile;
			}
		};
		contentStream.setStreamMTOM(new DataHandler(a));
		// contentStream.setStream(stream);
		contentStream.setFilename(a.getName());
		contentStream.setMimeType(mimeType);
		return contentStream;
	}
	
	
	public void sendMailProtocollazione(String mittente, Certificato certificato, String numProtocollo) throws ACTAInvocationException, Exception {
		logger.debug("[ACTAService::sendMailProtocollazione] BEGIN");
		//GenericUtil.stampaVO(emailVO);	
		// Create a mail session
		try {

			Mail mail = new Mail();

			mail.setMittente(mittente);
			mail.setDestinatario(certificato.getEmail());
			
			mail.setOggetto("SIPEE: avvenuta protocollazione  APE " + GenericUtil.getOggettoCertificato(certificato));

			StringBuffer messaggio = new StringBuffer();
			messaggio.append("Con la presente si informa che l'attestato n. "+GenericUtil.getOggettoCertificato(certificato)+" trasmesso in data "+ GenericUtil.convertToString(certificato.getDtUpload()) +" e' stato protocollato con Rif. Protocollo: ");
			messaggio.append(numProtocollo);
			messaggio.append("\n\nIn allegato si trasmette ricevuta aggiornata.");

			mail.setTesto(messaggio.toString());


			byte[] doc = recuperaFileRicevuta(certificato.getNumCertificatore(), certificato.getProgrCertificato(), certificato.getAnno());

			sendMail(mail, "ricevuta_trasmissione.pdf", doc);

		}
		catch (ACTAInvocationException e) {
			logger.debug("[ACTAService::sendMailProtocollazione] Errore nell'invio della mail (ACTAInvocationException)");

			throw e;
		}
		catch (Exception e) {
			logger.debug("[ACTAService::sendMailProtocollazione] Errore nell'invio della mail (Exception)");
			throw e;
		} finally {
			logger.debug("[ACTAService::sendMailProtocollazione] END");
		}

	}
	
	public void sendMailRiepilogo(Mail mail) throws Exception {
		logger.debug("[ACTAService::sendMailRiepilogo] BEGIN");
		//GenericUtil.stampaVO(emailVO);	
		// Create a mail session
		try {
			//mail.setDestinatario("giuseppe.todaro@csi.it");
			
			mail.setOggetto("SIPEE Archiviazione e Protocollazione ACTA "+GenericUtil.convertToString(mail.getInizioProcesso()));

			StringBuffer messaggio = new StringBuffer();
			messaggio.append("Il Timer Service di Archiviazione e Protocollazione certificati APE su ACTA avviato alle ");
			messaggio.append(GenericUtil.convertToString(mail.getInizioProcesso()));
			messaggio.append(" ha eseguito le seguenti operazioni:");


			messaggio.append("\n\n");
			messaggio.append("Numero APE elaborati: ");
			messaggio.append(mail.getApeGestiti());

			messaggio.append("\n");
			messaggio.append("Numero APE archiviati su ACTA: ");
			messaggio.append(mail.getApeArchiviati());

			messaggio.append("\n");
			messaggio.append("Numero APE protocollati su ACTA: ");
			messaggio.append(mail.getApeProtocollati());


			ArrayList<String> listError = mail.getElencoErrori();

			if (listError != null && listError.size() > 0)
			{
				messaggio.append("\n\n\n");
				messaggio.append("Sono stati rilevati i seguenti errori in fase di Archiviazione/Protocollazione:");

				for (String errore : listError) {
					messaggio.append("\n");
					messaggio.append(errore);
				}
			}

			mail.setTesto(messaggio.toString());

			// Send the message
			sendMail(mail, null, null);

		} catch (Exception e) {
			logger.error("Errore nell'invio della mail");
			throw e;
		} finally {
			logger.debug("[ACTAService::sendMailRiepilogo] END");
		}

	}

	public void sendMail(Mail mail, String nomeFile, byte[] doc) throws Exception {
		logger.debug("[ACTAService::sendMail] BEGIN");
		//GenericUtil.stampaVO(emailVO);	
		// Create a mail session
		try {
			java.util.Properties props = new java.util.Properties();        
			props.put("mail.smtp.host", factory.getMailHost());
			props.put("mail.smtp.port", factory.getMailPort());
			Session session = Session.getDefaultInstance(props, null);


			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(mail.getMittente()));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getDestinatario()));
			//        if(StringUtils.isNotEmpty(emailVo.getDestinatarioCC())){
			//        	msg.setRecipient(Message.RecipientType.CC, new InternetAddress(emailVo.getDestinatarioCC()));
			//        }
			msg.setSubject(mail.getOggetto());
			MimeMultipart  mp = new MimeMultipart();



			MimeBodyPart html = new MimeBodyPart();
			html.setText(mail.getTesto(), "text/plain");                
			html.setContent(mail.getTesto().replace("\n", "</br>"),"text/html");



			// create and fill the second message part
			if (doc != null) {
				MimeBodyPart attachmentPart = new MimeBodyPart();
				FileDataSource fileDataSource = new FileDataSource(createFileWithData(nomeFile, doc)) {
					@Override
					public String getContentType() {
						return "application/pdf";
					}
				};
				attachmentPart.setDataHandler(new DataHandler(fileDataSource));
				attachmentPart.setFileName(nomeFile);        	            
				mp.addBodyPart(attachmentPart);
			}
			// create the Multipart and its parts to it

			//mp.addBodyPart(text);
			mp.addBodyPart(html);

			// add the Multipart to the message
			msg.setContent(mp);        

			// Aggiunto questo comando per risolvere il problema di invio mail
			//Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
			
			// Send the message
			Transport.send(msg);
		} catch (Exception e) {
			logger.error("Errore nell'invio della mail");
			throw e;
		} finally {
			logger.debug("[ACTAService::sendMail] END");
		}

	}
	
	
	

	public String getMailHost() throws Exception {
		Properties properties = new Properties();
		InputStream stream = this.getClass().getResourceAsStream("/contants.properties");
		properties.load(stream);
		return properties.getProperty("mail.host");
	}
	*/
}
