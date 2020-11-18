/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
/**
 * SiceewsMgr.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 13, 2013 (09:13:21 GMT) WSDL2Java emitter.
 */

package it.csi.sicee.siceews.stubs;

public interface SiceewsMgr extends javax.xml.rpc.Service {
    public java.lang.String getSiceewsMgrWSPortAddress();

    public it.csi.sicee.siceews.stubs.SiceewsMgrWS getSiceewsMgrWSPort() throws javax.xml.rpc.ServiceException;

    public it.csi.sicee.siceews.stubs.SiceewsMgrWS getSiceewsMgrWSPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
