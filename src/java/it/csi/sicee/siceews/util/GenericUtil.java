/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
/*
 * 
 */
package it.csi.sicee.siceews.util;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlException;
import org.xml.sax.SAXException;

import it.csi.nodospc.mdp.interfacews.serviziofruitore.ParametriRiceviRT;

//import acaris.dto.Certificato;


/**
 * The Class GenericUtil.
 */
public class GenericUtil {

	private static Logger log = Logger.getLogger(Constants.LOGGER_PREFIX);

	/** The Constant BEGIN. */
	static final int BEGIN = 1;

	/** The Constant END. */
	static final int END = 2;

	/** The Constant VALUE. */
	static final int VALUE = 3;

	/** The Constant TEST. */
	static final int TEST = 4;

	/** The Constant SIMPLE. */
	static final int SIMPLE = 5;
	
	public final static java.text.SimpleDateFormat FORMATTER_DATA = new java.text.SimpleDateFormat(
			"dd/MM/yyyy");

	public final static java.text.SimpleDateFormat FORMATTER_DATA_COMPLETA = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	/**
	 * Lista dei formati decimali
	 */
	private static Hashtable<Integer, String> dicimalFormats = new Hashtable<Integer, String>();

	/**
	 * Stampa.
	 * 
	 * @param o
	 *            the o
	 * @param useLog4j
	 *            the use log4j
	 * @param depth
	 *            the depth
	 * @param testName
	 *            the test name
	 */
	public static void stampa(Object o, boolean useLog4j, int depth,
			String testName) {
		try {
			if (useLog4j) {
				log.debug(testName + " BEGIN");
			} else {
				System.out.println(testName + " BEGIN");
			}
			if (o != null) {
				if (o.getClass().isArray()) {
					Object[] a = (Object[]) o;
					stampa(a, useLog4j, depth);
				} else {
					stampa(o, useLog4j, depth);
				}
			}
			if (useLog4j) {
				log.debug(testName + " END");
			} else {
				System.out.println(testName + " END");
			}
		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * Stampa.
	 * 
	 * @param o
	 *            the o
	 * @param useLog4j
	 *            the use log4j
	 * @param depth
	 *            the depth
	 */
	public static void stampa(Object o, boolean useLog4j, int depth) {

		try {
			if (o == null) {
				print(o, null, useLog4j, depth, BEGIN);
			} else {
				if (o instanceof String) {
					print(o, o, useLog4j, depth, SIMPLE);
				} else {
					print(o, null, useLog4j, depth, BEGIN);
					callGetMethods(o, useLog4j, depth + 1);
					print(o, null, useLog4j, depth, END);
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * Prints the.
	 * 
	 * @param o
	 *            the o
	 * @param value
	 *            the value
	 * @param useLog4j
	 *            the use log4j
	 * @param depth
	 *            the depth
	 * @param type
	 *            the type
	 * @throws Exception
	 *             the exception
	 */
	private static void print(Object o, Object value, boolean useLog4j,
			int depth, int type) throws Exception {

		StringBuffer tab = new StringBuffer();
		for (int i = 0; i < depth; i++) {
			tab.append("\t");
		}
		if (o != null) {
			String className = o.getClass().getName();
			switch (type) {
			case BEGIN:
				tab.append(className);
				tab.append(" BEGIN");
				break;
			case END:
				tab.append(className);
				tab.append(" END");
				break;
			case VALUE:
				tab.append(((Method) o).getName());
				tab.append(" == ");
				tab.append(value);
				break;
			case SIMPLE:
				tab.append(o);
				tab.append(" == ");
				tab.append(value);
				break;
			default:

			}
		} else if (type == TEST) {
			tab.append("");
		} else {
			tab.append("Oggetto nullo!!");
		}

		if (useLog4j) {
			log.debug(tab);
		} else {
			System.out.println(tab);
		}

	}

	/**
	 * Call get methods.
	 * 
	 * @param o
	 *            the o
	 * @param useLog4j
	 *            the use log4j
	 * @param depth
	 *            the depth
	 */
	private static void callGetMethods(Object o, boolean useLog4j, int depth) {
		try {
			Method[] meth = o.getClass().getDeclaredMethods();
			for (int i = 0; i < meth.length; i++) {
				Method thisM = meth[i];
				if (thisM.getName().startsWith("get")) {
					if (!thisM.getName().equals("get")) {
						Object result = thisM.invoke(o, new Object[] {});
						if (result != null && result.getClass().isArray()) {
							Object[] a = (Object[]) result;
							stampa(a, useLog4j, depth);
						} else {
							print(thisM, result, useLog4j, depth, VALUE);
						}
					}
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * Stampa.
	 * 
	 * @param o
	 *            the o
	 * @param useLog4j
	 *            the use log4j
	 * @param depth
	 *            the depth
	 * @throws Exception
	 *             the exception
	 */
	public static void stampa(Object[] o, boolean useLog4j, int depth)
			throws Exception {
		String className = o.getClass().getSimpleName();
		for (int i = 0; i < o.length; i++) {
			stampa(o[i], false, depth);
		}

		if (o.length == 0) {
			System.out.println(className + " vuoto");
		}

	}

	public static String convertToString(Date time) {
		if (time != null) {
			return FORMATTER_DATA.format(time);
		} else
			return null;
	}

	public static String convertToString(java.sql.Timestamp time) {
		if (time != null) {
			return FORMATTER_DATA_COMPLETA.format(time);
		} else
			return null;
	}
	
	public static java.util.Date convertToDateCompleta(String time) {
		if (time != null) {
			try {
				return FORMATTER_DATA_COMPLETA.parse(time);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return null;
			}
		} else
			return null;
	}
	
	public static String convertToString(Long l) {
		String converted = null;

		if(l != null) {
			try {
				converted = l.toString();
			}
			catch(Exception e) {
				log.error("Errore durante la conversione di '" + l + "' in Integer: " + l);
			}
		}
		return converted;
	}
	
	public static Long convertToLong(String s) {
		Long converted = null;

		if(s != null) {
			try {
				converted = new Long(s);
			}
			catch(Exception e) {
				log.error("Errore durante la conversione di '" + s + "' in long: " + s);
			}
		}
		return converted;
	}
	
	public static int convertToInt(String s) {
		int converted = 0;

		if(s != null) {
			try {
				converted = new Integer(s);
			}
			catch(Exception e) {
				log.error("Errore durante la conversione di '" + s + "' in long: " + s);
			}
		}
		return converted;
	}

	public static Integer convertToInteger(long s) {
		Integer converted = null;

		try
		{
			converted = new Long(s).intValue();
		}
		catch (Exception e)
		{
			log.error("Errore durante la conversione di '" + s + "' in Integer: " + s);
		}
		
		return converted;
	}

	
	/**
	 * Converte un {@link BigDecimal} in stringa
	 * 
	 * @param number Numero da convertire
	 * @param decimali Decimali da visualizzare
	 * @return Numero convertito
	 */
	public static String convertToString(Double number, int decimali) {
		DecimalFormat formatter = null;
		String format = null;
		String stringValue = null;

		if(number != null) {
			format = getNumberFormat(decimali);
			formatter = new DecimalFormat(format);
			stringValue = formatter.format(number.doubleValue());
		}
		return stringValue;
	}

	/**
	 * Converte un {@link BigDecimal} in stringa
	 * 
	 * @param number Numero da convertire
	 * @return Numero convertito
	 */
	public static String convertToString(BigDecimal number) {
		DecimalFormat formatter = null;
		String format = null;
		String stringValue = null;

		if(number != null) {
			format = getNumberFormat(2);
			formatter = new DecimalFormat(format);
			stringValue = formatter.format(number.doubleValue());
		}
		return stringValue;
	}
	
	public static BigDecimal convertToBigDecimal(Double bd) {
		BigDecimal converted = null;

		if(bd != null) {
			try {
				String s = bd.toString();
				converted = new BigDecimal(s);
			}
			catch(Exception e) {
				log.error("Errore durante la conversione di '" + bd + "' in BigDecimal: " + bd, e);
			}
		}
		return converted;
	}

	/**
	 * Converte un {@link BigDecimal} in stringa
	 * 
	 * @param number Numero da convertire
	 * @param decimali Decimali da visualizzare
	 * @return Numero convertito
	 */
	public static String convertToString(BigDecimal number, int decimali) {
		DecimalFormat formatter = null;
		String format = null;
		String stringValue = null;

		if(number != null) {
			format = getNumberFormat(decimali);
			formatter = new DecimalFormat(format);
			stringValue = formatter.format(number.doubleValue());
		}
		return stringValue;
	}
	
	/**
	 * Restituisce il formato numerico richiesto
	 * 
	 * @param decimali Decimali da visualizzare
	 * @return Formato numerico
	 */
	private static String getNumberFormat(int decimali) {
		String format = null;
		String decimal = null;
		int decimaliCount;

		format = dicimalFormats.get(decimali);
		if(format == null) {
			format = "#,##0";
			decimal = "";
			for(decimaliCount = 0; decimaliCount < decimali; decimaliCount++) {
				decimal += "0";
			}
			if(decimal.length() > 0) {
				format += ("." + decimal);
			}
			dicimalFormats.put(decimali, format);
		}
		return format;
	}
	/**
	 * Checks if is null or empty.
	 * 
	 * @param s
	 *            the s
	 * @return true, if is null or empty
	 */
	public static boolean isNullOrEmpty(String s) {
		return s == null || s.trim().length() == 0;
	}

	/**
	 * Checks if is null or empty.
	 * 
	 * @param s
	 *            the s
	 * @return true, if is null or empty
	 */
	public static boolean isNullOrEmpty(Integer s) {
		return s == null;
	}

	/**
	 * Checks if is null or empty.
	 * 
	 * @param s
	 *            the s
	 * @return true, if is null or empty
	 */
	public static boolean isNullOrEmpty(Double s) {
		return s == null;
	}

	/**
	 * Checks if is null or empty.
	 * 
	 * @param s
	 *            the s
	 * @return true, if is null or empty
	 */
	public static boolean isNullOrEmpty(Boolean s) {
		return s == null;
	}

	/**
	 * Checks if is null or empty.
	 * 
	 * @param s
	 *            the s
	 * @return true, if is null or empty
	 */
	public static boolean isNullOrEmpty(Object s) {
		return s == null;
	}

	public static boolean isNullOrEmpty(List<?> s) {
		if (s != null) {
			return s.isEmpty();
		}

		return s == null;
	}

	public static byte[] readBytesFromInputStream(InputStream is) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		// TODO Trovare il numero di cache corretto
		
		
//		StopWatch stopWatch = new StopWatch(Constants.APPLICATION_CODE);
//		stopWatch.start();

		//final int FILE_BLOCK_SIZE = 16384; // Numero "magico". Preso dai
										   // sorgenti di ADOBE - DV
		final int FILE_BLOCK_SIZE = 32768; // Numero "magico". Preso dai
		   // sorgenti di ADOBE - DV
		
		log.debug("FILE_BLOCK_SIZE: "+FILE_BLOCK_SIZE);
		byte[] buf = new byte[FILE_BLOCK_SIZE];

		int readed;
		try {
			while ((readed = is.read(buf)) != -1) {
				os.write(buf, 0, readed);
			}
			os.flush();
		} finally {
			is.close();
			os.close();
		}
//		stopWatch.dumpElapsed("SalvaModuloApeXml", "readBytesFromInputStream", "lettura xml",
//				"");
//		
		return os.toByteArray();
	}
	
	public static final String MODOL_ENCODING = "UTF-8";

	public static String readByteArray(byte[] input) throws IOException {
		return new String(input, MODOL_ENCODING);
	}
	
	public static byte[] readString(String input) throws IOException {
    	return input.getBytes(MODOL_ENCODING);
    }
	
	public static String getMacRiceviRT (ParametriRiceviRT parametriRT, String passphrase){
		
		//String passphrase="uidjeiureinedrueireuirueidkdjreiure78437461ashf374383jdkjfd92834938437hrutbxi";

		String sToDigest = passphrase + "%%%%" + parametriRT.getApplicationId() + parametriRT.getIuv() + parametriRT.getIdMsgRicevuta()  + parametriRT.getTimestamp() + "%%%%" + passphrase;

		byte[] bMac = DigestUtils.sha256(sToDigest.getBytes());
		String mac = Base64.encodeBase64String (bMac);
		mac = mac.substring (0,35);
		
		return mac;

	}
	
	public static it.gov.digitpa.schemas.x2011.pagamenti.RTDocument mapToMODDocumentRT(byte[] theXml, boolean isWs){
		try {
			log.debug("[MapDto::mapToMODDocumentRT] start");

			log.debug("mapToMODDocumentRT - PASSO1");

			log.debug("mapToMODDocumentRT - isWs: "+isWs);

			
			String rootTagName = isWs?"pay_i:RT":"RT";
			String theXmlString = XMLUtil.getXMLData(theXml, rootTagName);
			
			log.debug("mapToMODDocumentRT - PASSO2: "+theXmlString);
			
			//log.debug("theXmlString " + theXmlString);
			InputStream is = new ByteArrayInputStream(readString(theXmlString));
			
			log.debug("mapToMODDocumentRT - PASSO3");

			it.gov.digitpa.schemas.x2011.pagamenti.RTDocument apeDoc = it.gov.digitpa.schemas.x2011.pagamenti.RTDocument.Factory.parse(is);
			 
			//log.debug("mapToMODDocumentRT - PASSO4: "+apeDoc);
			
			log.debug("[MapDto::mapToMODDocumentRT] end");
			return apeDoc;
		} catch (XmlException e) {
			log.error("Errore mapToMODDocumentRT - XmlException",e);
			return null;
		} catch (IOException e) {
			log.error("Errore mapToMODDocumentRT - IOException",e);
			return null;
		} catch (ParserConfigurationException e) {
			log.error("Errore mapToMODDocumentRT - ParserConfigurationException",e);
			return null;
		} catch (SAXException e) {
			log.error("Errore mapToMODDocumentRT - SAXException",e);
			return null;
		}		
	}
	
	public static it.gov.digitpa.schemas.x2011.pagamenti.RTDocument mapToMODDocumentRTNew(String theXml, boolean isWs){
		try {
			log.debug("[MapDto::mapToMODDocumentRT] start");

			log.debug("[MapDto::mapToMODDocumentRT] PASSO1");
			
			log.debug("[MapDto::mapToMODDocumentRT] isWs: "+isWs);

			// Cerco di non utilizzare il rootTagName (per renderlo il piu' generico possibile)
			//String rootTagName = isWs?"pay_i:RT":"RT";
			//String theXmlString = XMLUtil.getXMLDataNew(theXml, rootTagName);
			
			log.debug("[MapDto::mapToMODDocumentRT] PASSO2: "+theXml);

			//log.debug("theXmlString " + theXmlString);
			InputStream is = new ByteArrayInputStream(readString(theXml));
			
			log.debug("[MapDto::mapToMODDocumentRT] PASSO3");

			it.gov.digitpa.schemas.x2011.pagamenti.RTDocument apeDoc = it.gov.digitpa.schemas.x2011.pagamenti.RTDocument.Factory.parse(is);
			
			
			log.debug("Stampo getIdentificativoUnivocoVersamento: "+apeDoc.getRT().getDatiPagamento().getIdentificativoUnivocoVersamento());
			GenericUtil.stampa(apeDoc, true, 3);
			
			log.debug("[MapDto::mapToMODDocumentRT] end");
			return apeDoc;
		} catch (XmlException e) {
			log.error("Errore mapToMODDocumentRT - XmlException",e);
			return null;
		} catch (IOException e) {
			log.error("Errore mapToMODDocumentRT - IOException",e);
			return null;
//		} catch (ParserConfigurationException e) {
//			log.error("Errore mapToMODDocumentRT - ParserConfigurationException",e);
//			return null;
//		} catch (SAXException e) {
//			log.error("Errore mapToMODDocumentRT - SAXException",e);
//			return null;
		}		
	}
	
	/*
	public static it.csi.sicee.siceeweb.xml.xmlapecompleto2015.data.DocumentoDocument mapToMODDocumentApe(byte[] theXml){
		try {
			log.debug("[MapDto::mapToMODDocumentRT] start");

			System.out.println("mapToMODDocumentRT - PASSO1");
			
			String theXmlString = XMLUtil.getXMLData(theXml, "documento");
			
			System.out.println("mapToMODDocumentRT - PASSO2: "+theXmlString);
			
			//log.debug("theXmlString " + theXmlString);
			InputStream is = new ByteArrayInputStream(readString(theXmlString));
			
			System.out.println("mapToMODDocumentRT - PASSO3");

			it.csi.sicee.siceeweb.xml.xmlapecompleto2015.data.DocumentoDocument apeDoc = it.csi.sicee.siceeweb.xml.xmlapecompleto2015.data.DocumentoDocument.Factory.parse(is);
			 
			System.out.println("mapToMODDocumentRT - PASSO4: "+apeDoc);
			
			log.debug("[MapDto::mapToMODDocumentRT] end");
			return apeDoc;
		} catch (XmlException e) {
			log.error("Errore mapToMODDocumentRT - XmlException",e);
			return null;
		} catch (IOException e) {
			log.error("Errore mapToMODDocumentRT - IOException",e);
			return null;
		} catch (ParserConfigurationException e) {
			log.error("Errore mapToMODDocumentRT - ParserConfigurationException",e);
			return null;
		} catch (SAXException e) {
			log.error("Errore mapToMODDocumentRT - SAXException",e);
			return null;
		}		
	}
	*/
	 public static String readFile(Reader fileReader) throws IOException {
			StringBuilder fileContent = null;
			BufferedReader reader = null;
			String line = null;

			reader = new BufferedReader(fileReader);
			fileContent = new StringBuilder();
			while((line = reader.readLine()) != null) {
				fileContent.append(line).append("\n");
			}
			return fileContent.toString();
		}
	
}