/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.lancio;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import it.csi.mdp.core.business.dto.RicevutaTelematicaNodo;
import it.csi.mdp.core.interfacecxf.IMdpCoreCxf;
import it.csi.sicee.siceews.util.GenericUtil;
import it.csi.sicee.siceews.util.XMLUtil;
import it.gov.digitpa.schemas.x2011.pagamenti.StCodiceEsitoPagamento;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.xmlbeans.XmlException;
import org.xml.sax.SAXException;

public class TestRecuperaRTCXF {

	public static final String coreServiceUrl = "http://tst-applogic.reteunitaria.piemonte.it/mdpcoreservices/MdpCoreCxf/MdpCoreCxf";

public static void main(String[] args){
		
		
		//final String appId="TEST_TEST";
		final String appId="SICEE_NODO";
		
		try {
			JaxWsProxyFactoryBean fact = new JaxWsProxyFactoryBean();
			fact.setServiceClass(IMdpCoreCxf.class);
			String mdpcoreAddress = coreServiceUrl;
			fact.setAddress( mdpcoreAddress);
			fact.getInInterceptors().add(new LoggingInInterceptor());
			fact.getOutInterceptors().add(new LoggingOutInterceptor());
			
			System.out.println("SiceeWsService - PASSO6: "+(fact!=null)); 

			System.out.println("SiceeWsService - PASSO6.1: "+fact.isLoadHandlers()); 
			System.out.println("SiceeWsService - PASSO6.2: "+fact.getWsdlURL()); 
			System.out.println("SiceeWsService - PASSO6.3: "+fact.getWsdlLocation()); 
			System.out.println("SiceeWsService - PASSO6.4: "+fact.getAddress()); 
			System.out.println("SiceeWsService - PASSO6.5: "+fact.getEndpointName()); 
			System.out.println("SiceeWsService - PASSO6.6: "+fact.getServiceClass()); 
			System.out.println("SiceeWsService - PASSO6.7: "+fact.getTransportId()); 
			System.out.println("SiceeWsService - PASSO6.8: "+fact.getBindingId()); 
			
			System.out.println("PRIMA DEL CREATE");
			
			IMdpCoreCxf clientcxfmdp = (IMdpCoreCxf)fact.create();

			RicevutaTelematicaNodo ric = clientcxfmdp.getRTperIUV("RF51180590003TEST00000014");
			//RicevutaTelematicaNodo ric = clientcxfmdp.getRTperIUV("RF19180590003PROD00000008");
			

			//GenericUtils.stampa(ric, false, 3);
			
			System.out.println("Stampo il getApplicationId: "+ric.getApplicationId());
			System.out.println("Stampo il getTransactionId: "+ric.getTransactionId());
			System.out.println("Stampo il getDataMsgRicevuta: "+ric.getDataMsgRicevuta());
			System.out.println("Stampo il getIdMsgRicevuta: "+ric.getIdMsgRicevuta());
			System.out.println("Stampo il getTipoFirma: "+ric.getTipoFirma());
			System.out.println("Stampo il getIuv: "+ric.getIuv());
			System.out.println("Stampo il getIdEsitoPagamento: "+ric.getIdEsitoPagamento());
			System.out.println("Stampo il getDescEsitoPagamento: "+ric.getDescEsitoPagamento());
			System.out.println("Stampo il getIdMsgRichiesta: "+ric.getIdMsgRichiesta());
			System.out.println("Stampo il getRtData: "+ric.getRtData());
			
			
			String rtString = GenericUtil.readByteArray(ric.getRtData());
			
			System.out.println("rtString: "+rtString);
			
			
			// Test RT recuperaroto da riceviRT
			rtString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><pay_i:RT xmlns:pay_i=\"http://www.digitpa.gov.it/schemas/2011/Pagamenti/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"/opt/proctele/resources/PagInf_RPT_RT_6_2_0.xsd\"><pay_i:versioneOggetto>6.2</pay_i:versioneOggetto><pay_i:dominio><pay_i:identificativoDominio>80087670016</pay_i:identificativoDominio></pay_i:dominio><pay_i:identificativoMessaggioRicevuta>Imro02ghd5om54lo5hp63c3h442838gun</pay_i:identificativoMessaggioRicevuta><pay_i:dataOraMessaggioRicevuta>2018-05-25T12:01:55</pay_i:dataOraMessaggioRicevuta><pay_i:riferimentoMessaggioRichiesta>TST000000000043432</pay_i:riferimentoMessaggioRichiesta><pay_i:riferimentoDataRichiesta>2018-05-25</pay_i:riferimentoDataRichiesta><pay_i:istitutoAttestante><pay_i:identificativoUnivocoAttestante><pay_i:tipoIdentificativoUnivoco>B</pay_i:tipoIdentificativoUnivoco><pay_i:codiceIdentificativoUnivoco>BCITITMM</pay_i:codiceIdentificativoUnivoco></pay_i:identificativoUnivocoAttestante><pay_i:denominazioneAttestante>Intesa Sanpaolo</pay_i:denominazioneAttestante></pay_i:istitutoAttestante><pay_i:enteBeneficiario><pay_i:identificativoUnivocoBeneficiario><pay_i:tipoIdentificativoUnivoco>G</pay_i:tipoIdentificativoUnivoco><pay_i:codiceIdentificativoUnivoco>80087670016</pay_i:codiceIdentificativoUnivoco></pay_i:identificativoUnivocoBeneficiario><pay_i:denominazioneBeneficiario>Regione Piemonte</pay_i:denominazioneBeneficiario><pay_i:indirizzoBeneficiario>Piazza Castello</pay_i:indirizzoBeneficiario><pay_i:civicoBeneficiario>165</pay_i:civicoBeneficiario><pay_i:capBeneficiario>10122</pay_i:capBeneficiario><pay_i:localitaBeneficiario>Torino</pay_i:localitaBeneficiario><pay_i:provinciaBeneficiario>TO</pay_i:provinciaBeneficiario><pay_i:nazioneBeneficiario>IT</pay_i:nazioneBeneficiario></pay_i:enteBeneficiario><pay_i:soggettoPagatore><pay_i:identificativoUnivocoPagatore><pay_i:tipoIdentificativoUnivoco>F</pay_i:tipoIdentificativoUnivoco><pay_i:codiceIdentificativoUnivoco>AAAAAA00A11B000J</pay_i:codiceIdentificativoUnivoco></pay_i:identificativoUnivocoPagatore><pay_i:anagraficaPagatore>DEMO 21 CSI PIEMONTE</pay_i:anagraficaPagatore></pay_i:soggettoPagatore><pay_i:datiPagamento><pay_i:codiceEsitoPagamento>0</pay_i:codiceEsitoPagamento><pay_i:importoTotalePagato>11</pay_i:importoTotalePagato><pay_i:identificativoUnivocoVersamento>RF37181450003TEST00000002</pay_i:identificativoUnivocoVersamento><pay_i:CodiceContestoPagamento>n/a</pay_i:CodiceContestoPagamento><pay_i:datiSingoloPagamento><pay_i:singoloImportoPagato>11</pay_i:singoloImportoPagato><pay_i:esitoSingoloPagamento>PAGATA</pay_i:esitoSingoloPagamento><pay_i:dataEsitoSingoloPagamento>2018-05-25</pay_i:dataEsitoSingoloPagamento><pay_i:identificativoUnivocoRiscossione>181451001833</pay_i:identificativoUnivocoRiscossione><pay_i:causaleVersamento>/RFB/RF37181450003TEST00000002/11/Acquisto credito su SIPEE</pay_i:causaleVersamento><pay_i:datiSpecificiRiscossione>9/0000000123</pay_i:datiSpecificiRiscossione></pay_i:datiSingoloPagamento></pay_i:datiPagamento></pay_i:RT>";
			System.out.println("rtStringNew: "+rtString);

			
			rtString = rtString.trim().replaceFirst("^([\\W]+)<","<");
			
			  it.gov.digitpa.schemas.x2011.pagamenti.RTDocument rtObject = GenericUtil.mapToMODDocumentRT(GenericUtil
					  .readString(rtString), false);
			  
//			  it.gov.digitpa.schemas.x2011.pagamenti.RTDocument rtObject = GenericUtil.mapToMODDocumentRT(GenericUtil
//					  .readString(rtString), false);
			  
			  
			  String codEsitoPagString = rtObject.getRT().getDatiPagamento().getCodiceEsitoPagamento().toString();
			  System.out.println("Stampo codEsitoPag (String): "+codEsitoPagString);

			  StCodiceEsitoPagamento.Enum codEsitoPag = rtObject.getRT().getDatiPagamento().getCodiceEsitoPagamento();
			  
			  System.out.println("Stampo codEsitoPag (enum): "+codEsitoPag);
			  System.out.println("Stampo codEsitoPag (String): "+codEsitoPag.toString());
			  System.out.println("Stampo codEsitoPag (int): "+codEsitoPag.intValue());
			
			/*
			Codice	Descrizione	Note
			1	Pagamento eseguito	
			
			2	Pagamento non eseguito	
			
			3	Pagamento parzialmente eseguito	Si applica nei casi in cui la Richiesta di Pagamento Telematico veicola  piu' di un versamento (il limite e' 5). L'esito indica che solo alcuni dei versamenti riportati nella RPT sono stati eseguiti e non tutti.  
			Attualmente MDP supporta solo RPT con un unico versamento
			
			4	Decorrenza termini 	La RT contenente tale esito viene inviata in modo automatico dal Nodo SPC nel caso in cui, trascorsi 60 giorni dall'invio della RPT, il PSP non abbia emesso la RT collegata.
			
			5	Decorrenza termini parziale 	La RT contenente tale esito viene inviata in modo automatico dal Nodo SPC nel caso in cui, trascorsi 60 giorni dall'invio della RPT, il PSP non abbia emesso la RT collegata. Si applica nel caso di RPT contenenti piu' di un versamento (al massimo 5).
			Attualmente MDP supporta solo RPT con un unico versamento9:31 
			*/

		}catch (it.csi.mdp.core.business.exceptions.DaoException e)
		{
			System.out.println("Exception MDP");
			System.out.println("e.getMessage(): "+e.getMessage());
		}catch (Exception e){
			System.err.println(e.toString());
		}
	}


public static it.gov.digitpa.schemas.x2011.pagamenti.RTDocument mapToMODDocumentRTzz(byte[] theXml){
	try {
		System.out.println("[MapDto::mapToMODDocumentRT] start");

		System.out.println("mapToMODDocumentRT - PASSO1");
		
		String theXmlString = XMLUtil.getXMLData(theXml, "pay_i:RT");
		
		System.out.println("mapToMODDocumentRT - PASSO2: "+theXmlString);
		
		//log.debug("theXmlString " + theXmlString);
		InputStream is = new ByteArrayInputStream(GenericUtil.readString(theXmlString));
		
		System.out.println("mapToMODDocumentRT - PASSO3");

		it.gov.digitpa.schemas.x2011.pagamenti.RTDocument apeDoc = it.gov.digitpa.schemas.x2011.pagamenti.RTDocument.Factory.parse(is);
		 
		//System.out.println("mapToMODDocumentRT - PASSO4: "+apeDoc);
		System.out.println("[MapDto::mapToMODDocumentRT] end");
		return apeDoc;
	} catch (XmlException e) {
		System.err.println("Errore mapToMODDocumentRT - XmlException: "+e);
		return null;
	} catch (IOException e) {
		System.err.println("Errore mapToMODDocumentRT - IOException: "+e);
		return null;
	} catch (ParserConfigurationException e) {
		System.err.println("Errore mapToMODDocumentRT - ParserConfigurationException: "+e);
		return null;
	} catch (SAXException e) {
		System.err.println("Errore mapToMODDocumentRT - SAXException: "+e);
		return null;
	}		
}

}
