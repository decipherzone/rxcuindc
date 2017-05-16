/**
 * DBManagerService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.decipherzone.rxcuindc.ws;

public interface DBManagerService extends javax.xml.rpc.Service {
    public java.lang.String getRxNormDBServiceAddress();

    public com.decipherzone.rxcuindc.ws.DBManager getRxNormDBService() throws javax.xml.rpc.ServiceException;

    public com.decipherzone.rxcuindc.ws.DBManager getRxNormDBService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
