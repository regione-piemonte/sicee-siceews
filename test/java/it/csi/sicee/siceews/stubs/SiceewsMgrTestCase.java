/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
/**
 * SiceewsMgrTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 13, 2013 (09:13:21 GMT) WSDL2Java emitter.
 */

package it.csi.sicee.siceews.stubs;

public class SiceewsMgrTestCase extends junit.framework.TestCase {
    public SiceewsMgrTestCase(java.lang.String name) {
        super(name);
    }

    public void testSiceewsMgrWSPortWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new it.csi.sicee.siceews.stubs.SiceewsMgrLocator().getSiceewsMgrWSPortAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new it.csi.sicee.siceews.stubs.SiceewsMgrLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1SiceewsMgrWSPortRecuperaStatoTransazione() throws Exception {
        it.csi.sicee.siceews.stubs.SiceewsMgrSoapBindingStub binding;
        try {
            binding = (it.csi.sicee.siceews.stubs.SiceewsMgrSoapBindingStub)
                          new it.csi.sicee.siceews.stubs.SiceewsMgrLocator().getSiceewsMgrWSPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String[] value = null;
        value = binding.recuperaStatoTransazione(new java.lang.String[0]);
        // TBD - validate results
    }

    public void test2SiceewsMgrWSPortInvioMail() throws Exception {
        it.csi.sicee.siceews.stubs.SiceewsMgrSoapBindingStub binding;
        try {
            binding = (it.csi.sicee.siceews.stubs.SiceewsMgrSoapBindingStub)
                          new it.csi.sicee.siceews.stubs.SiceewsMgrLocator().getSiceewsMgrWSPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        boolean value = false;
        value = binding.invioMail(new it.csi.sicee.siceews.stubs.Mail());
        // TBD - validate results
    }

    public void test3SiceewsMgrWSPortIsAlive() throws Exception {
        it.csi.sicee.siceews.stubs.SiceewsMgrSoapBindingStub binding;
        try {
            binding = (it.csi.sicee.siceews.stubs.SiceewsMgrSoapBindingStub)
                          new it.csi.sicee.siceews.stubs.SiceewsMgrLocator().getSiceewsMgrWSPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        boolean value = false;
        value = binding.isAlive();
        // TBD - validate results
    }

    public void test4SiceewsMgrWSPortRecuperaRT() throws Exception {
        it.csi.sicee.siceews.stubs.SiceewsMgrSoapBindingStub binding;
        try {
            binding = (it.csi.sicee.siceews.stubs.SiceewsMgrSoapBindingStub)
                          new it.csi.sicee.siceews.stubs.SiceewsMgrLocator().getSiceewsMgrWSPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String[] value = null;
        value = binding.recuperaRT(new java.lang.String[0]);
        // TBD - validate results
    }

}
