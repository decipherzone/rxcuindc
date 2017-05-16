/**
 * DBManager.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.decipherzone.rxcuindc.ws;

public interface DBManager extends java.rmi.Remote {
    public java.lang.String[] findRxcuiByString(java.lang.String term) throws java.rmi.RemoteException;
    public java.lang.String[] findRxcuiByString(java.lang.String term, java.lang.String[] sources, int allSourcesFlag) throws java.rmi.RemoteException;
    public java.lang.String[] findRxcuiByString(java.lang.String term, java.lang.String[] sources, int allSourcesFlag, int matchType) throws java.rmi.RemoteException;
    public java.lang.String[] findRxcuiById(java.lang.String idType, java.lang.String id) throws java.rmi.RemoteException;
    public java.lang.String[] findRxcuiById(java.lang.String idType, java.lang.String id, int allSourcesFlag) throws java.rmi.RemoteException;
    public java.lang.String[] getSpellingSuggestions(java.lang.String term) throws java.rmi.RemoteException;
    public com.decipherzone.rxcuindc.ws.RxConcept getRxConceptProperties(java.lang.String rxcui) throws java.rmi.RemoteException;
    public com.decipherzone.rxcuindc.ws.RxConceptGroup[] getRelatedByRelationship(java.lang.String rxcui, java.lang.String[] rela_list) throws java.rmi.RemoteException;
    public com.decipherzone.rxcuindc.ws.RxConceptGroup[] getRelatedByType(java.lang.String rxcui, java.lang.String[] termType_list) throws java.rmi.RemoteException;
    public com.decipherzone.rxcuindc.ws.RxConceptGroup[] getAllRelatedInfo(java.lang.String rxcui) throws java.rmi.RemoteException;
    public com.decipherzone.rxcuindc.ws.RxConceptGroup[] getDrugs(java.lang.String name) throws java.rmi.RemoteException;
    public java.lang.String[] getNDCs(java.lang.String rxcui) throws java.rmi.RemoteException;
    public com.decipherzone.rxcuindc.ws.NDCTime[] getAllNDCs(java.lang.String rxcui, int allNDC) throws java.rmi.RemoteException;
    public com.decipherzone.rxcuindc.ws.HistoricalNDCTime[] getAllHistoricalNDCs(java.lang.String rxcui, int allNDC) throws java.rmi.RemoteException;
    public java.lang.String getRxNormVersion() throws java.rmi.RemoteException;
    public java.lang.String[] getIdTypes() throws java.rmi.RemoteException;
    public java.lang.String[] getRelaTypes() throws java.rmi.RemoteException;
    public java.lang.String[] getSourceTypes() throws java.rmi.RemoteException;
    public java.lang.String[] getTermTypes() throws java.rmi.RemoteException;
    public com.decipherzone.rxcuindc.ws.RxTermInfo[] getProprietaryInformation(java.lang.String rxcui, java.lang.String[] sources, java.lang.String proxyTicket) throws java.rmi.RemoteException;
    public com.decipherzone.rxcuindc.ws.RxTermInfo[] getProprietaryInformation(java.lang.String rxcui, java.lang.String[] sources, java.lang.String proxyTicket, java.lang.String rxaui) throws java.rmi.RemoteException;
    public com.decipherzone.rxcuindc.ws.RxConcept[] getMultiIngredBrand(java.lang.String[] ids) throws java.rmi.RemoteException;
    public java.lang.String[] getDisplayTerms() throws java.rmi.RemoteException;
    public java.lang.String[] findRemapped(java.lang.String rxcui) throws java.rmi.RemoteException;
    public com.decipherzone.rxcuindc.ws.RxMinimalConcept[] getAllConceptsByTTY(java.lang.String[] ttyList) throws java.rmi.RemoteException;
    public java.lang.String[] getPropCategories() throws java.rmi.RemoteException;
    public java.lang.String[] getPropNames() throws java.rmi.RemoteException;
    public com.decipherzone.rxcuindc.ws.RxPropertyConcept[] getAllProperties(java.lang.String rxcui, java.lang.String[] propCat) throws java.rmi.RemoteException;
    public java.lang.String filterByProperty(java.lang.String rxcui, java.lang.String prop_name, java.lang.String[] prop_value) throws java.rmi.RemoteException;
    public com.decipherzone.rxcuindc.ws.RxPropertyConcept[] getRxProperty(java.lang.String rxcui, java.lang.String prop_name) throws java.rmi.RemoteException;
    public com.decipherzone.rxcuindc.ws.NDCStatus getNDCStatus(java.lang.String ndc, java.lang.String startDate, java.lang.String endDate, int option) throws java.rmi.RemoteException;
    public com.decipherzone.rxcuindc.ws.RxcuiStatus getRxcuiStatus(java.lang.String rxcui) throws java.rmi.RemoteException;
    public com.decipherzone.rxcuindc.ws.ReverseRxcuiStatus getReverseRxcuiStatus(java.lang.String rxcui) throws java.rmi.RemoteException;
    public com.decipherzone.rxcuindc.ws.RxMatchGroup getApproximateMatch(java.lang.String term, int ranks, int options) throws java.rmi.RemoteException;
    public com.decipherzone.rxcuindc.ws.RxNode[] getAllClasses(java.lang.String src, java.lang.String type) throws java.rmi.RemoteException;
    public com.decipherzone.rxcuindc.ws.RxTree getClassHierarchy(java.lang.String rxcui, java.lang.String src, java.lang.String type, int oneLevel) throws java.rmi.RemoteException;
    public com.decipherzone.rxcuindc.ws.RxDrugMember[] getClassMembers(java.lang.String id, java.lang.String src, java.lang.String rela, java.lang.String trans, java.lang.String[] ttyfilter) throws java.rmi.RemoteException;
    public com.decipherzone.rxcuindc.ws.NDCProperty[] getNDCProperties(java.lang.String id) throws java.rmi.RemoteException;
    public void close() throws java.rmi.RemoteException;
}
