/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.lancio;

import it.csi.nodospc.mdp.interfacews.serviziofruitore.ParametriRiceviEsito;
import it.csi.nodospc.mdp.interfacews.serviziofruitore.ParametriRiceviRT;
import it.csi.sicee.siceews.stubs.Allegato;
import it.csi.sicee.siceews.stubs.Mail;
import it.csi.sicee.siceews.stubs.SiceewsMgrLocator;
import it.csi.sicee.siceews.stubs.SiceewsMgrSoapBindingStub;
import it.csi.sicee.siceews.stubs.SiceewsMgrWS;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

//import javax.xml.ws.BindingProvider
//import javax.xml.bind.annotation.XmlSchema
//import javax.xml.rpc.ServiceException
//import javax.xml.soap.SOAPException
public class LancioSiceewsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Stampo la prova");
//		provaUna();
		//provaDue(); // Funziona
		//provaTre(); // Funziona
		
		//provaInvioMail();
		sendMailTxtHtml();
	}
	
	static void provaTre()
	{
		try
		{
			SiceewsMgrLocator loc = new SiceewsMgrLocator();
			loc.setSiceewsMgrWSPortEndpointAddress("http://10.102.40.182:25210/siceews/SiceewsMgr/SiceewsMgrWS");
			
			SiceewsMgrSoapBindingStub  binding = (SiceewsMgrSoapBindingStub) loc.getSiceewsMgrWSPort();
			
			boolean isAlive = binding.isAlive();
			
			System.out.println("isAlive: "+isAlive);

			String[] elencoId = new String[]{"TST000000000043516", "TST000000000043514", "TST000000000043445", "TST000000000043443", "TST000000000043438", "TST000000000043434", "TST000000000043386", "TST000000000043385", "TST000000000043377", "TST000000000043376", "TST000000000043367", "TST000000000043248", "TST000000000043245", "TST000000000043244", "TST000000000043232", "TST000000000043231", "TST000000000043230", "TST000000000042922", "TST000000000042917", "TST000000000042707", "TST000000000042635", "TST000000000042634", "TST000000000042627", "TST000000000042595", "TST000000000042593", "TST000000000042589", "TST000000000042585", "TST000000000042584", "TST000000000042577"};
			
			/*
			String[] elencoId = new String[1];
			elencoId[0] = "TST000000000043432";
			//elencoId[0] = "TST000000000043215";
			//elencoId[1] = "TST000000000043216";
			*/
			
			String[] elencoIdError = binding.recuperaStatoTransazione(elencoId);
			
			System.out.println("elencoIdError: "+elencoIdError);
			
			for (String error : elencoIdError) {
				System.out.println("error: "+error);
			}
			
			System.out.println("Dopo della chiamta MDP");

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	
	static void provaDue()
	{
		try
		{
			it.csi.sicee.siceews.stubs.SiceewsMgrSoapBindingStub  binding = (it.csi.sicee.siceews.stubs.SiceewsMgrSoapBindingStub)
                    new it.csi.sicee.siceews.stubs.SiceewsMgrLocator().getSiceewsMgrWSPort();
			
			boolean isAlive = binding.isAlive();
			
			System.out.println("isAlive: "+isAlive);

			System.out.println("Dopo della chiamta MDP");

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	
	static void provaUna()
	{
		try
		{
			JaxWsProxyFactoryBean fact = new JaxWsProxyFactoryBean();
			fact.setServiceClass(SiceewsMgrWS.class);
			String mdpcoreAddress = "http://localhost:8080/siceews/SiceewsMgr/SiceewsMgrWS?wsdl";
			fact.setAddress(mdpcoreAddress);
			fact.getInInterceptors().add(new LoggingInInterceptor());
			fact.getOutInterceptors().add(new LoggingOutInterceptor());
	
			System.out.println("Prima della create");
			
			SiceewsMgrWS clientcxfmdp = (SiceewsMgrWS) fact.create();

			System.out.println("Dopo della create");


			boolean isAlive = clientcxfmdp.isAlive();
			
			System.out.println("isAlive: "+isAlive);

			System.out.println("Dopo della chiamta MDP");

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	static void provaInvioMail()
	{
		System.out.println("[LancioSiceewsTest::provaInvioMail] BEGIN");

		try
		{
			it.csi.sicee.siceews.stubs.SiceewsMgrSoapBindingStub  binding = (it.csi.sicee.siceews.stubs.SiceewsMgrSoapBindingStub)
                    new it.csi.sicee.siceews.stubs.SiceewsMgrLocator().getSiceewsMgrWSPort();
			
			boolean isAlive = binding.isAlive();
			
			System.out.println("isAlive: "+isAlive);
			
			Mail mail = new Mail();
			
			mail.setMittente("assistenza.energia@csi.it");
			mail.setDestinatario("giuseppe.todaro@csi.it");
			
			mail.setOggetto("provo invio mail siceews");
			
			mail.setTesto("Prova corpo (testo)...");
			mail.setHtml("Prova corpo (html)...");
			
			//mail.

			ArrayList<Allegato> elencoAllegati = new ArrayList<Allegato>();
			
			
			it.csi.sicee.siceews.stubs.Allegato allegato1 = new Allegato();
			byte[] allegato1Array = Files.readAllBytes(Paths.get("D:\\progetti\\eclipse oxygen\\sicee\\sicee_siceews\\docs\\2014_301441_0029.pdf.p7m"));
			allegato1.setNomeFile("2014_301441_0029.pdf.p7m");
			allegato1.setContentType("application/pkcs7-mime");
			allegato1.setFile(allegato1Array);
			elencoAllegati.add(allegato1);

			it.csi.sicee.siceews.stubs.Allegato allegato2 = new Allegato();
			byte[] allegato2Array = Files.readAllBytes(Paths.get("D:\\progetti\\eclipse oxygen\\sicee\\sicee_siceews\\docs\\ricevuta_trasmissione.pdf"));
			allegato2.setNomeFile("ricevuta_trasmissione.pdf");
			allegato2.setContentType("application/pdf");
			allegato2.setFile(allegato2Array);
			elencoAllegati.add(allegato2);

			it.csi.sicee.siceews.stubs.Allegato[] elencoAllegatiNew = new it.csi.sicee.siceews.stubs.Allegato[elencoAllegati.size()]; 
			elencoAllegatiNew = elencoAllegati.toArray(elencoAllegatiNew);
			
			mail.setElencoAllegati(elencoAllegatiNew);
			/*
			it.csi.sicee.siceews.stubs.Allegato[] elencoAllegati = new it.csi.sicee.siceews.stubs.Allegato[2]; 
			it.csi.sicee.siceews.stubs.Allegato allegato1 = new Allegato();
			byte[] allegato1Array = Files.readAllBytes(Paths.get("D:\\progetti\\eclipse oxygen\\sicee\\sicee_siceews\\docs\\2014_301441_0029.pdf.p7m"));
			allegato1.setNomeFile("2014_301441_0029.pdf.p7m");
			allegato1.setContentType("application/pkcs7-mime");
			allegato1.setFile(allegato1Array);
			elencoAllegati[0] = allegato1;

			it.csi.sicee.siceews.stubs.Allegato allegato2 = new Allegato();
			byte[] allegato2Array = Files.readAllBytes(Paths.get("D:\\progetti\\eclipse oxygen\\sicee\\sicee_siceews\\docs\\ricevuta_trasmissione.pdf"));
			allegato2.setNomeFile("ricevuta_trasmissione.pdf");
			allegato2.setContentType("application/pdf");
			allegato2.setFile(allegato2Array);
			elencoAllegati[1] = allegato2;
			
			mail.setElencoAllegati(elencoAllegati);
			*/
			
			boolean isInvioMail = binding.invioMail(mail);
			
			System.out.println("isInvioMail: "+isInvioMail);


		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			System.out.println("[LancioSiceewsTest::provaInvioMail] END");
		}
	}
	
	// Questo metodo fa vedere la differenza della scitta in txt e quella in html
	// qui non funziona, ma su sigitbatchn --> package test.TestInvioMail funziona
	// comunque nel corpo della mail fa vedere il txt mentre viene allegato il testo in html 
	static void sendMailTxtHtml()
	{
		System.out.println("[LancioSiceewsTest::sendMailTxtHtml] START");

		Properties props = new Properties();
		props.put("mail.smtp.host", "mailfarm-app.csi.it");
		props.put("mail.smtp.port", "25");
		
		Session session = Session.getDefaultInstance(props, null);

        System.out.println("Host: "+props.getProperty("mail.smtp.host"));
        System.out.println("Port: "+props.getProperty("mail.smtp.port"));
        
		//Session session = Session.getInstance(props,null);
		MimeMessage message = new MimeMessage(session);

		try
		{
			InternetAddress from = new InternetAddress("no-reply-energia@csi.it");
			InternetAddress to = new InternetAddress("giuseppe.todaro@csi.it");

			
			message.setSubject("I am a multipart text/html email" );
			message.setFrom(from);
			message.addRecipient(Message.RecipientType.TO, to);

			Multipart multipart = new MimeMultipart();

			// PLAIN TEXT
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("Here is your plain text message");
			multipart.addBodyPart(messageBodyPart);

			// HTML TEXT
			messageBodyPart = new MimeBodyPart();
			String htmlText = "<H1>I am the html part</H1>";
			messageBodyPart.setContent(htmlText, "text/html");
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);
			Transport.send(message);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		System.out.println("[LancioSiceewsTest::sendMailTxtHtml] START");

	}

}
