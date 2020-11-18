/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceews.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLUtil
{
    private final static String ENCODING = "UTF-8";
    private final static String XFA_DATA_TAGNAME = "xfa:data";
    
    /** The log. */
	private static Logger log = Logger.getLogger(Constants.LOGGER_PREFIX);
	
    public static String getXMLData(byte[] datiXFAModol, String rootTagName) throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        
        log.debug("Stampo il DocumentBuilder: "+db);
        
        InputSource inputSource = new InputSource(new ByteArrayInputStream(datiXFAModol));

        
        //Document document = db.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("UTF-8"))))
        
        log.debug("Stampo il inputSource: "+inputSource);

        Document document = null;
        try
        {
        	document = db.parse(inputSource);
        }
        catch (Exception e)
        {
            log.error("Errore nella parsificazione ",e);

        }
        log.debug("Stampo il document: "+document);

        
        Document docResult = getNewDocumentFromElement(db, document, XFA_DATA_TAGNAME);
        
        log.debug("Stampo il docResult: "+docResult);
        if(docResult==null){
        	 docResult = getNewDocumentFromElement(db, document, rootTagName);
        }else{
        	 docResult = getNewDocumentFromElement(db, docResult, rootTagName);
        }
        
       

        log.debug("Stampo il docResult: "+docResult);

        
        return getStringFromDocument(docResult);
    }

    public static String getXMLDataNew(String datiXFAModol, String rootTagName) throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        
        log.debug("Stampo il DocumentBuilder: "+db);
        
        //InputSource inputSource = new InputSource(new ByteArrayInputStream(datiXFAModol));

        
        Document document = null;
        try
        {
            document = db.parse(new InputSource(new ByteArrayInputStream(datiXFAModol.getBytes("UTF-8"))));
            
            //log.debug("Stampo il inputSource: "+inputSource);
            //System.out.println("Stampo il inputSource: "+inputSource);
        }
        catch (Exception e)
        {
            log.error("Errore nella parsificazione ",e);
            System.err.println("Errore nella parsificazione: "+e);

        }
        log.debug("Stampo il document: "+document);

        
        Document docResult = getNewDocumentFromElement(db, document, XFA_DATA_TAGNAME);
        
        log.debug("Stampo il docResult: "+docResult);
        if(docResult==null){
        	 docResult = getNewDocumentFromElement(db, document, rootTagName);
        }else{
        	 docResult = getNewDocumentFromElement(db, docResult, rootTagName);
        }
        
       

        log.debug("Stampo il docResult: "+docResult);

        
        return getStringFromDocument(docResult);
    }
    
    private static Document getNewDocumentFromElement(DocumentBuilder db, Document document, String tagname)
    {
        Document docResult = null;
        NodeList results = document.getElementsByTagName(tagname);
        
        /*
        log.debug("\n\nStampo il NodeList: "+results);
        System.out.println("\n\nStampo il NodeList: "+results);
        GenericUtil.stampa(document, false, 3);
        */
        
        assert (results != null);
        assert (results.getLength() == 1);
        if (results != null && results.getLength() > 0)
        {
            Element node = (Element) results.item(0);
            
            log.debug("Stampo il node: "+node);
            
            docResult = db.newDocument();

            log.debug("Stampo il docResult: "+docResult);

            docResult.appendChild(docResult.importNode(node, true));
        }
        return docResult;
    }

    private static String getStringFromDocument(Document doc)
    {
        try
        {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, ENCODING);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(domSource, result);

            String s = new String(writer.toString().getBytes(ENCODING), ENCODING);
            return s;
        }
        catch (TransformerException ex)
        {
            log.error("Errore TransformerException",ex);
            throw new RuntimeException(ex);
        }
        catch (UnsupportedEncodingException e)
        {
            log.error("Errore UnsupportedEncodingException",e);
            throw new RuntimeException(e);
        }        
    }

}
