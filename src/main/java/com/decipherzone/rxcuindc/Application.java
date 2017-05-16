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
    public static void main(String arg[]) throws MalformedURLException, ServiceException, RemoteException {
        String rxhost = "http://mor.nlm.nih.gov";
        String rxURI = rxhost + "/axis/services/RxNormDBService";

// Locate the RxNorm API web service
        URL rxURL = new URL(rxURI);
        DBManagerService rxnormService = new DBManagerServiceLocator();
        DBManager dbmanager = rxnormService.getRxNormDBService(rxURL);
        HistoricalNDCTime[] ndCs = dbmanager.getAllHistoricalNDCs("1668240", 0);
        for(HistoricalNDCTime historicalNDCTime : ndCs) {
            NDCTime[] ndcTimes = historicalNDCTime.getNdcTimes();
            for (NDCTime ndcTime : ndcTimes) {
                System.out.println("ndcTime.getNDC() = " + ndcTime.getNDC());
            }
        }
    }
}
