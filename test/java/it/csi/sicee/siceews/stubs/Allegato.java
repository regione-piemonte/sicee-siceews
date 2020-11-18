/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
/**
 * Allegato.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 13, 2013 (09:13:21 GMT) WSDL2Java emitter.
 */

package it.csi.sicee.siceews.stubs;

public class Allegato  implements java.io.Serializable {
    private java.lang.String contentType;

    private byte[] file;

    private java.lang.String nomeFile;

    public Allegato() {
    }

    public Allegato(
           java.lang.String contentType,
           byte[] file,
           java.lang.String nomeFile) {
           this.contentType = contentType;
           this.file = file;
           this.nomeFile = nomeFile;
    }


    /**
     * Gets the contentType value for this Allegato.
     * 
     * @return contentType
     */
    public java.lang.String getContentType() {
        return contentType;
    }


    /**
     * Sets the contentType value for this Allegato.
     * 
     * @param contentType
     */
    public void setContentType(java.lang.String contentType) {
        this.contentType = contentType;
    }


    /**
     * Gets the file value for this Allegato.
     * 
     * @return file
     */
    public byte[] getFile() {
        return file;
    }


    /**
     * Sets the file value for this Allegato.
     * 
     * @param file
     */
    public void setFile(byte[] file) {
        this.file = file;
    }


    /**
     * Gets the nomeFile value for this Allegato.
     * 
     * @return nomeFile
     */
    public java.lang.String getNomeFile() {
        return nomeFile;
    }


    /**
     * Sets the nomeFile value for this Allegato.
     * 
     * @param nomeFile
     */
    public void setNomeFile(java.lang.String nomeFile) {
        this.nomeFile = nomeFile;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Allegato)) return false;
        Allegato other = (Allegato) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.contentType==null && other.getContentType()==null) || 
             (this.contentType!=null &&
              this.contentType.equals(other.getContentType()))) &&
            ((this.file==null && other.getFile()==null) || 
             (this.file!=null &&
              java.util.Arrays.equals(this.file, other.getFile()))) &&
            ((this.nomeFile==null && other.getNomeFile()==null) || 
             (this.nomeFile!=null &&
              this.nomeFile.equals(other.getNomeFile())));
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
        if (getContentType() != null) {
            _hashCode += getContentType().hashCode();
        }
        if (getFile() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFile());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFile(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNomeFile() != null) {
            _hashCode += getNomeFile().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Allegato.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://it/csi/sicee/siceews/stubs", "allegato"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contentType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("file");
        elemField.setXmlName(new javax.xml.namespace.QName("", "file"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeFile");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeFile"));
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
