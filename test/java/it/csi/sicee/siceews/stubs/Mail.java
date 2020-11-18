/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
/**
 * Mail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 13, 2013 (09:13:21 GMT) WSDL2Java emitter.
 */

package it.csi.sicee.siceews.stubs;

public class Mail  implements java.io.Serializable {
    private java.lang.String destinatario;

    private java.lang.String destinatarioCC;

    private it.csi.sicee.siceews.stubs.Allegato[] elencoAllegati;

    private java.lang.String host;

    private java.lang.String html;

    private java.lang.String mittente;

    private java.lang.String nomeAllegato;

    private java.lang.String oggetto;

    private java.lang.String port;

    private java.lang.String protocol;

    private java.lang.String testo;

    public Mail() {
    }

    public Mail(
           java.lang.String destinatario,
           java.lang.String destinatarioCC,
           it.csi.sicee.siceews.stubs.Allegato[] elencoAllegati,
           java.lang.String host,
           java.lang.String html,
           java.lang.String mittente,
           java.lang.String nomeAllegato,
           java.lang.String oggetto,
           java.lang.String port,
           java.lang.String protocol,
           java.lang.String testo) {
           this.destinatario = destinatario;
           this.destinatarioCC = destinatarioCC;
           this.elencoAllegati = elencoAllegati;
           this.host = host;
           this.html = html;
           this.mittente = mittente;
           this.nomeAllegato = nomeAllegato;
           this.oggetto = oggetto;
           this.port = port;
           this.protocol = protocol;
           this.testo = testo;
    }


    /**
     * Gets the destinatario value for this Mail.
     * 
     * @return destinatario
     */
    public java.lang.String getDestinatario() {
        return destinatario;
    }


    /**
     * Sets the destinatario value for this Mail.
     * 
     * @param destinatario
     */
    public void setDestinatario(java.lang.String destinatario) {
        this.destinatario = destinatario;
    }


    /**
     * Gets the destinatarioCC value for this Mail.
     * 
     * @return destinatarioCC
     */
    public java.lang.String getDestinatarioCC() {
        return destinatarioCC;
    }


    /**
     * Sets the destinatarioCC value for this Mail.
     * 
     * @param destinatarioCC
     */
    public void setDestinatarioCC(java.lang.String destinatarioCC) {
        this.destinatarioCC = destinatarioCC;
    }


    /**
     * Gets the elencoAllegati value for this Mail.
     * 
     * @return elencoAllegati
     */
    public it.csi.sicee.siceews.stubs.Allegato[] getElencoAllegati() {
        return elencoAllegati;
    }


    /**
     * Sets the elencoAllegati value for this Mail.
     * 
     * @param elencoAllegati
     */
    public void setElencoAllegati(it.csi.sicee.siceews.stubs.Allegato[] elencoAllegati) {
        this.elencoAllegati = elencoAllegati;
    }

    public it.csi.sicee.siceews.stubs.Allegato getElencoAllegati(int i) {
        return this.elencoAllegati[i];
    }

    public void setElencoAllegati(int i, it.csi.sicee.siceews.stubs.Allegato _value) {
        this.elencoAllegati[i] = _value;
    }


    /**
     * Gets the host value for this Mail.
     * 
     * @return host
     */
    public java.lang.String getHost() {
        return host;
    }


    /**
     * Sets the host value for this Mail.
     * 
     * @param host
     */
    public void setHost(java.lang.String host) {
        this.host = host;
    }


    /**
     * Gets the html value for this Mail.
     * 
     * @return html
     */
    public java.lang.String getHtml() {
        return html;
    }


    /**
     * Sets the html value for this Mail.
     * 
     * @param html
     */
    public void setHtml(java.lang.String html) {
        this.html = html;
    }


    /**
     * Gets the mittente value for this Mail.
     * 
     * @return mittente
     */
    public java.lang.String getMittente() {
        return mittente;
    }


    /**
     * Sets the mittente value for this Mail.
     * 
     * @param mittente
     */
    public void setMittente(java.lang.String mittente) {
        this.mittente = mittente;
    }


    /**
     * Gets the nomeAllegato value for this Mail.
     * 
     * @return nomeAllegato
     */
    public java.lang.String getNomeAllegato() {
        return nomeAllegato;
    }


    /**
     * Sets the nomeAllegato value for this Mail.
     * 
     * @param nomeAllegato
     */
    public void setNomeAllegato(java.lang.String nomeAllegato) {
        this.nomeAllegato = nomeAllegato;
    }


    /**
     * Gets the oggetto value for this Mail.
     * 
     * @return oggetto
     */
    public java.lang.String getOggetto() {
        return oggetto;
    }


    /**
     * Sets the oggetto value for this Mail.
     * 
     * @param oggetto
     */
    public void setOggetto(java.lang.String oggetto) {
        this.oggetto = oggetto;
    }


    /**
     * Gets the port value for this Mail.
     * 
     * @return port
     */
    public java.lang.String getPort() {
        return port;
    }


    /**
     * Sets the port value for this Mail.
     * 
     * @param port
     */
    public void setPort(java.lang.String port) {
        this.port = port;
    }


    /**
     * Gets the protocol value for this Mail.
     * 
     * @return protocol
     */
    public java.lang.String getProtocol() {
        return protocol;
    }


    /**
     * Sets the protocol value for this Mail.
     * 
     * @param protocol
     */
    public void setProtocol(java.lang.String protocol) {
        this.protocol = protocol;
    }


    /**
     * Gets the testo value for this Mail.
     * 
     * @return testo
     */
    public java.lang.String getTesto() {
        return testo;
    }


    /**
     * Sets the testo value for this Mail.
     * 
     * @param testo
     */
    public void setTesto(java.lang.String testo) {
        this.testo = testo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Mail)) return false;
        Mail other = (Mail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.destinatario==null && other.getDestinatario()==null) || 
             (this.destinatario!=null &&
              this.destinatario.equals(other.getDestinatario()))) &&
            ((this.destinatarioCC==null && other.getDestinatarioCC()==null) || 
             (this.destinatarioCC!=null &&
              this.destinatarioCC.equals(other.getDestinatarioCC()))) &&
            ((this.elencoAllegati==null && other.getElencoAllegati()==null) || 
             (this.elencoAllegati!=null &&
              java.util.Arrays.equals(this.elencoAllegati, other.getElencoAllegati()))) &&
            ((this.host==null && other.getHost()==null) || 
             (this.host!=null &&
              this.host.equals(other.getHost()))) &&
            ((this.html==null && other.getHtml()==null) || 
             (this.html!=null &&
              this.html.equals(other.getHtml()))) &&
            ((this.mittente==null && other.getMittente()==null) || 
             (this.mittente!=null &&
              this.mittente.equals(other.getMittente()))) &&
            ((this.nomeAllegato==null && other.getNomeAllegato()==null) || 
             (this.nomeAllegato!=null &&
              this.nomeAllegato.equals(other.getNomeAllegato()))) &&
            ((this.oggetto==null && other.getOggetto()==null) || 
             (this.oggetto!=null &&
              this.oggetto.equals(other.getOggetto()))) &&
            ((this.port==null && other.getPort()==null) || 
             (this.port!=null &&
              this.port.equals(other.getPort()))) &&
            ((this.protocol==null && other.getProtocol()==null) || 
             (this.protocol!=null &&
              this.protocol.equals(other.getProtocol()))) &&
            ((this.testo==null && other.getTesto()==null) || 
             (this.testo!=null &&
              this.testo.equals(other.getTesto())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getDestinatario() != null) {
            _hashCode += getDestinatario().hashCode();
        }
        if (getDestinatarioCC() != null) {
            _hashCode += getDestinatarioCC().hashCode();
        }
        if (getElencoAllegati() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getElencoAllegati());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getElencoAllegati(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getHost() != null) {
            _hashCode += getHost().hashCode();
        }
        if (getHtml() != null) {
            _hashCode += getHtml().hashCode();
        }
        if (getMittente() != null) {
            _hashCode += getMittente().hashCode();
        }
        if (getNomeAllegato() != null) {
            _hashCode += getNomeAllegato().hashCode();
        }
        if (getOggetto() != null) {
            _hashCode += getOggetto().hashCode();
        }
        if (getPort() != null) {
            _hashCode += getPort().hashCode();
        }
        if (getProtocol() != null) {
            _hashCode += getProtocol().hashCode();
        }
        if (getTesto() != null) {
            _hashCode += getTesto().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Mail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://it/csi/sicee/siceews/stubs", "mail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinatario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "destinatario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinatarioCC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "destinatarioCC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elencoAllegati");
        elemField.setXmlName(new javax.xml.namespace.QName("", "elencoAllegati"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://it/csi/sicee/siceews/stubs", "allegato"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("host");
        elemField.setXmlName(new javax.xml.namespace.QName("", "host"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("html");
        elemField.setXmlName(new javax.xml.namespace.QName("", "html"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mittente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mittente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeAllegato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeAllegato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oggetto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "oggetto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("port");
        elemField.setXmlName(new javax.xml.namespace.QName("", "port"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protocol");
        elemField.setXmlName(new javax.xml.namespace.QName("", "protocol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "testo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
