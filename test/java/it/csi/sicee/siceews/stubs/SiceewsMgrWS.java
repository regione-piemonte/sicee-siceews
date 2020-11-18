/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
/**
 * SiceewsMgrWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 13, 2013 (09:13:21 GMT) WSDL2Java emitter.
 */

package it.csi.sicee.siceews.stubs;

public interface SiceewsMgrWS extends java.rmi.Remote {
    public java.lang.String[] recuperaStatoTransazione(java.lang.String[] arg0) throws java.rmi.RemoteException;
    public boolean invioMail(it.csi.sicee.siceews.stubs.Mail arg0) throws java.rmi.RemoteException;
    public boolean isAlive() throws java.rmi.RemoteException;
    public java.lang.String[] recuperaRT(java.lang.String[] arg0) throws java.rmi.RemoteException;
}
