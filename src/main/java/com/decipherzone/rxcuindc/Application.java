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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Application {

    private static String rxhost = "http://mor.nlm.nih.gov";
    private static String rxURI = rxhost + "/axis/services/RxNormDBService";
    private static URL rxURL = null;
    private static DBManagerService rxnormService = null;
    private static DBManager dbmanager = null;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String CONN_URL = "jdbc:mysql://localhost:3306/rxcui";
    private static Connection connection = null;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(CONN_URL, USERNAME, PASSWORD);
            rxURL = new URL(rxURI);
            rxnormService = new DBManagerServiceLocator();
            dbmanager = rxnormService.getRxNormDBService(rxURL);
        } catch (ServiceException | MalformedURLException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to load data");
        }
    }

    public static void main(String arg[]) throws Exception {
        int start = 0, end = 99;
//        for (int i = 0; i < 174; i++) {
        for (int i = 0; i < 1; i++) {

            System.out.println("start = " + start);
            System.out.println("end = " + end);

//            processRXCUI(start, end);
            processNDC();
            start += 100;
            end += 100;
        }

    }

    private static void processRXCUI(final int start, final int end) throws SQLException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        executorService.execute(() -> {
            try {

                Statement stmt = connection.createStatement();

                ResultSet rs = stmt.executeQuery("select NDC from NDC limit " + start + ", " + end);

                while(rs.next()){
//                        getRXCUI("65597011590");
                    getRXCUI(rs.getString(1));
                }
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
        boolean finished = false;
        try {
            finished = executorService.awaitTermination(3, TimeUnit.HOURS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(finished){
            connection.close();
        }

    }

    private static void processNDC() throws SQLException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        executorService.execute(() -> {
            try {

                Statement stmt = connection.createStatement();

                ResultSet rs = stmt.executeQuery("select RXCUI from NDC_TO_RXCUI ");

                while(rs.next()){
                    getNDC(rs.getString(1));
                }
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
        boolean finished = false;
        try {
            finished = executorService.awaitTermination(3, TimeUnit.HOURS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(finished){
            connection.close();
        }
    }

    private static void getRXCUI(String ndc) throws RemoteException {
        // Locate the RxNorm API web service
        String[] ndCs = dbmanager.findRxcuiById("NDC", ndc);
        try {

            for(String rxcuid : ndCs) {
                System.out.println("rxcuid = " + rxcuid);

                PreparedStatement stmt = connection.prepareStatement("insert into NDC_TO_RXCUI values(?,?)");
                stmt.setString(1, ndc);
                stmt.setString(2, rxcuid);

                int i = stmt.executeUpdate();
                System.out.println(i + " rows inserted");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getNDC(String rxcui) throws RemoteException {
        // Locate the RxNorm API web service
        HistoricalNDCTime[] ndCs = dbmanager.getAllHistoricalNDCs(rxcui, 0);
        try {

            for(HistoricalNDCTime historicalNDCTime : ndCs) {
                NDCTime[] ndcTimes = historicalNDCTime.getNdcTimes();
                for (NDCTime ndcTime : ndcTimes) {
                    System.out.println("ndcTime.getNDC() = " + ndcTime.getNDC());
                    PreparedStatement stmt = connection.prepareStatement("insert into RXCUI_TO_NDC values(?,?)");
                    stmt.setString(1, rxcui);
                    stmt.setString(2, ndcTime.getNDC());

                    int i = stmt.executeUpdate();
                    System.out.println(i + " rows inserted");
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
