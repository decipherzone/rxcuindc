/**
 * Created on 16/5/17 1:31 PM by Raja Dushyant Vashishtha
 * Sr. Software Engineer
 * rajad@decipherzone.com
 * Decipher Zone Softwares
 * www.decipherzone.com
 */

package com.decipherzone.rxcuindc;

import com.decipherzone.rxcuindc.ws.DBManager;
import com.decipherzone.rxcuindc.ws.DBManagerService;
import com.decipherzone.rxcuindc.ws.DBManagerServiceLocator;
import com.decipherzone.rxcuindc.ws.HistoricalNDCTime;
import com.decipherzone.rxcuindc.ws.NDCTime;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class Application {

    private static String rxhost = "http://mor.nlm.nih.gov";
    private static String rxURI = rxhost + "/axis/services/RxNormDBService";
    private static URL rxURL = null;
    private static DBManagerService rxnormService = null;
    private static DBManager dbmanager = null;
    static {
        try {
            rxURL = new URL(rxURI);
            rxnormService = new DBManagerServiceLocator();
            dbmanager = rxnormService.getRxNormDBService(rxURL);
        } catch (ServiceException | MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to load data");
        }
    }

    public static void main(String arg[]) throws MalformedURLException, ServiceException, RemoteException {
        getRXCUI("65597011590");
    }


    private static void getRXCUI(String ndc) throws RemoteException {
        // Locate the RxNorm API web service
        String[] ndCs = dbmanager.findRxcuiById("NDC", ndc);
        for(String rxcuid : ndCs) {
                System.out.println("rxcuid = " + rxcuid);
        }
    }


    private static void getNDC(String rxcui) throws RemoteException {
        // Locate the RxNorm API web service
        HistoricalNDCTime[] ndCs = dbmanager.getAllHistoricalNDCs(rxcui, 0);
        for(HistoricalNDCTime historicalNDCTime : ndCs) {
            NDCTime[] ndcTimes = historicalNDCTime.getNdcTimes();
            for (NDCTime ndcTime : ndcTimes) {
                System.out.println("ndcTime.getNDC() = " + ndcTime.getNDC());
            }
        }
    }

}
