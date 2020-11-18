/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
/**
 * SiceewsMgrLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 13, 2013 (09:13:21 GMT) WSDL2Java emitter.
 */

package it.csi.sicee.siceews.stubs;

public class SiceewsMgrLocator extends org.apache.axis.client.Service implements it.csi.sicee.siceews.stubs.SiceewsMgr {

    public SiceewsMgrLocator() {
    }


    public SiceewsMgrLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SiceewsMgrLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SiceewsMgrWSPort
    //private java.lang.String SiceewsMgrWSPort_address = "http://localhost:10110/siceews/SiceewsMgr/SiceewsMgrWS";
    private java.lang.String SiceewsMgrWSPort_address = "http://10.102.40.106:25210/siceews/SiceewsMgr/SiceewsMgrWS";
    
    public java.lang.String getSiceewsMgrWSPortAddress() {
        return SiceewsMgrWSPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SiceewsMgrWSPortWSDDServiceName = "SiceewsMgrWSPort";

    public java.lang.String getSiceewsMgrWSPortWSDDServiceName() {
        return SiceewsMgrWSPortWSDDServiceName;
    }

    public void setSiceewsMgrWSPortWSDDServiceName(java.lang.String name) {
        SiceewsMgrWSPortWSDDServiceName = name;
    }

    public it.csi.sicee.siceews.stubs.SiceewsMgrWS getSiceewsMgrWSPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SiceewsMgrWSPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSiceewsMgrWSPort(endpoint);
    }

    public it.csi.sicee.siceews.stubs.SiceewsMgrWS getSiceewsMgrWSPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.csi.sicee.siceews.stubs.SiceewsMgrSoapBindingStub _stub = new it.csi.sicee.siceews.stubs.SiceewsMgrSoapBindingStub(portAddress, this);
            _stub.setPortName(getSiceewsMgrWSPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSiceewsMgrWSPortEndpointAddress(java.lang.String address) {
        SiceewsMgrWSPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (it.csi.sicee.siceews.stubs.SiceewsMgrWS.class.isAssignableFrom(serviceEndpointInterface)) {
                it.csi.sicee.siceews.stubs.SiceewsMgrSoapBindingStub _stub = new it.csi.sicee.siceews.stubs.SiceewsMgrSoapBindingStub(new java.net.URL(SiceewsMgrWSPort_address), this);
                _stub.setPortName(getSiceewsMgrWSPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SiceewsMgrWSPort".equals(inputPortName)) {
            return getSiceewsMgrWSPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://it/csi/sicee/siceews/stubs", "SiceewsMgr");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://it/csi/sicee/siceews/stubs", "SiceewsMgrWSPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SiceewsMgrWSPort".equals(portName)) {
            setSiceewsMgrWSPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
