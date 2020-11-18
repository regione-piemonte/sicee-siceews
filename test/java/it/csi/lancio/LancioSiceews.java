/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.lancio;

import it.csi.nodospc.mdp.interfacews.serviziofruitore.ParametriRiceviEsito;
import it.csi.nodospc.mdp.interfacews.serviziofruitore.ParametriRiceviRT;
import it.csi.sicee.siceews.stubs.SiceewsMgrLocator;
import it.csi.sicee.siceews.stubs.SiceewsMgrSoapBindingStub;
import it.csi.sicee.siceews.stubs.SiceewsMgrWS;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

//import javax.xml.ws.BindingProvider
//import javax.xml.bind.annotation.XmlSchema
//import javax.xml.rpc.ServiceException
//import javax.xml.soap.SOAPException
public class LancioSiceews {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Stampo la prova");
		provaRecuperaRT();
		//provaRecuperaStatoTransazione(); // Funziona
		
	}
	
	static void provaRecuperaStatoTransazione()
	{
		try
		{
			SiceewsMgrLocator loc = new SiceewsMgrLocator();
			loc.setSiceewsMgrWSPortEndpointAddress("http://localhost:10110/siceews/SiceewsMgr/SiceewsMgrWS");
			
			SiceewsMgrSoapBindingStub  binding = (SiceewsMgrSoapBindingStub) loc.getSiceewsMgrWSPort();
			
			boolean isAlive = binding.isAlive();
			
			System.out.println("isAlive: "+isAlive);

			String[] elencoId = new String[]{"TST000000000043626"};

			/*
			String[] elencoId = new String[1];
			elencoId[0] = "TST000000000043432";
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

	static void provaRecuperaRT()
	{
		try
		{
			SiceewsMgrLocator loc = new SiceewsMgrLocator();
			loc.setSiceewsMgrWSPortEndpointAddress("http://localhost:10110/siceews/SiceewsMgr/SiceewsMgrWS");
			
			SiceewsMgrSoapBindingStub  binding = (SiceewsMgrSoapBindingStub) loc.getSiceewsMgrWSPort();
			
			boolean isAlive = binding.isAlive();
			
			System.out.println("isAlive: "+isAlive);

			//String[] elencoId = new String[]{"TST000000000043516", "TST000000000043514", "TST000000000043445", "TST000000000043443", "TST000000000043438", "TST000000000043434", "TST000000000043386", "TST000000000043385", "TST000000000043377", "TST000000000043376", "TST000000000043367", "TST000000000043248", "TST000000000043245", "TST000000000043244", "TST000000000043232", "TST000000000043231", "TST000000000043230", "TST000000000042922", "TST000000000042917", "TST000000000042707", "TST000000000042635", "TST000000000042634", "TST000000000042627", "TST000000000042595", "TST000000000042593", "TST000000000042589", "TST000000000042585", "TST000000000042584", "TST000000000042577"};
			String[] elencoId = new String[]{"TST000000000043626"};

			/*
			String[] elencoId = new String[1];
			elencoId[0] = "TST000000000043432";
			//elencoId[1] = "TST000000000043216";
			*/

			String[] elencoIdError = binding.recuperaRT(elencoId);
			
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

}
