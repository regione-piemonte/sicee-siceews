/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceews.integration.mgr;

import it.csi.sicee.siceews.util.Constants;
import it.csi.sicee.siceews.util.GenericUtil;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;


import org.apache.log4j.Logger;



public class SiceeWsServiceMgrFactory {
	
	private static final Logger log = Logger.getLogger(Constants.LOGGER_PREFIX);

	//private Service s = null;

	private String mailHost;
	private String mailPort;
	private String actaHost;
	private int actaPort = 0;
	private String actaContext;
	private String endpointMDP;


	public String getMailHost() throws Exception {
		if (mailHost == null)
		{
			Properties properties = new Properties();
			InputStream stream = this.getClass().getResourceAsStream("/contants.properties");
			properties.load(stream);
			mailHost = properties.getProperty("mail.host");
			
			log.debug("[SiceeWsServiceFactory::getMailHost] Stampo mailHost: "+mailHost);
		}
		return mailHost;
	}

	public String getMailPort() throws Exception {
		if (mailPort == null)
		{
			Properties properties = new Properties();
			InputStream stream = this.getClass().getResourceAsStream("/contants.properties");
			properties.load(stream);
			mailPort = properties.getProperty("mail.port");
			
			log.debug("[SiceeWsServiceFactory::getMailPort] Stampo mailPort: "+mailPort);

		}
		return mailPort;
	}
	
	public String getMdpEndpoint() throws Exception {
	    Properties properties = new Properties();
	    InputStream stream = this.getClass().getResourceAsStream("/wsEndpointUrls.properties");
	    properties.load(stream);
	    endpointMDP = properties.getProperty("wsmdp.endpoint");
	    return endpointMDP;
	  }

	/*

	public String getActaHost() {
		log.debug("[SiceeWsServiceFactory::getActaHost] BEGIN");

		if (actaHost == null)
		{
			try
			{
				Properties properties = new Properties();
				InputStream stream = this.getClass().getResourceAsStream("/contants.properties");
				properties.load(stream);
				actaHost = properties.getProperty("acta.host");
			}
			catch (Exception e)
			{
				log.error("[SiceeWsServiceFactory::getActaHost] si e' verificato un errore nel reperimento della risorsa");
			}
		}
		log.debug("[SiceeWsServiceFactory::getActaHost] END");

		return actaHost;
	}

	public int getActaPort() {
		log.debug("[SiceeWsServiceFactory::getActaPort] BEGIN");

		if (actaPort == 0)
		{  
			try
			{
				Properties properties = new Properties();
				InputStream stream = this.getClass().getResourceAsStream("/contants.properties");
				properties.load(stream);
				actaPort = GenericUtil.convertToInt(properties.getProperty("acta.port"));
			}
			catch (Exception e)
			{
				log.error("[SiceeWsServiceFactory::getActaPort] si e' verificato un errore nel reperimento della risorsa");
			}
		}
		log.debug("[SiceeWsServiceFactory::getActaPort] END");

		return actaPort;
	}

	public String getSiceeWSContext() {
		log.debug("[SiceeWsServiceFactory::getSiceeWSContext] BEGIN");

		if (actaContext == null)
		{  
			try
			{
				Properties properties = new Properties();
				InputStream stream = this.getClass().getResourceAsStream("/contants.properties");
				properties.load(stream);
				actaContext = properties.getProperty("acta.context");
			}
			catch (Exception e)
			{
				log.error("[SiceeWsServiceFactory::getSiceeWSContext] si e' verificato un errore nel reperimento della risorsa");
			}
		}
		log.debug("[SiceeWsServiceFactory::getSiceeWSContext] END");

		return actaContext;
	}
*/

	  /*
	  public String getWsSecurityUserName() throws Exception {
		    Properties properties = new Properties();
		    InputStream stream = this.getClass().getResourceAsStream("/wsEndpointUrls.properties");
		    properties.load(stream);
		    wsSecurityUsername = properties.getProperty("ws-security.username");
		    return wsSecurityUsername;
	  }
	  
	  public String getWsSecurityPassword() throws Exception {
		    Properties properties = new Properties();
		    InputStream stream = this.getClass().getResourceAsStream("/wsEndpointUrls.properties");
		    properties.load(stream);
		    wsSecurityPassword = properties.getProperty("ws-security.password");
		    return wsSecurityPassword;
	  }
	  
	  
	  public String getKeyStoreLocalPath() throws Exception {
		    Properties properties = new Properties();
		    InputStream stream = this.getClass().getResourceAsStream("/wsEndpointUrls.properties");
		    properties.load(stream);
		    keystoreLocalPath = properties.getProperty("keystore.local.path");
		    return keystoreLocalPath;
	  }
	  
	  public String getKeyStoreLocalEnabled() throws Exception {
		    Properties properties = new Properties();
		    InputStream stream = this.getClass().getResourceAsStream("/wsEndpointUrls.properties");
		    properties.load(stream);
		    keystoreLocalEnabled = properties.getProperty("keystore.local.enabled");
		    return keystoreLocalEnabled;
	  }
	  
	  */

}
