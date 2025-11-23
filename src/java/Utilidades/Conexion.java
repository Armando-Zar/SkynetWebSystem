package Utilidades;

import java.io.Serializable;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Armando-Zar
 */
public class Conexion implements Serializable {
    Connection conn;
    private String host = "caboose.proxy.rlwy.net";
    private String port = "31799";
    private String dbName = "railway";
    private String userName = "root";
    private String password = "SXMtiPdcGeEkMIXKzlIciYfbcbrFvHaW";
    private String urlReportePdf = "http://localhost:31311/SkynetWebSystem/Reportes/";


    public Conexion() {
    }

    public Connection connect() throws ConnectException, SQLException {
         
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dbName;
            conn = DriverManager.getConnection(url, this.userName, this.password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
        return conn;
    }
    
    public void disconnect(Connection conn){
        if(conn != null){
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    } 

    public String getUrlReportePdf() {
        return urlReportePdf;
    }

    public void setUrlReportePdf(String urlReportePdf) {
        this.urlReportePdf = urlReportePdf;
    }
    
}
