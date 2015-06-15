package DAO;

import java.io.FileInputStream;
import java.util.Properties;

        import java.io.IOException;
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;
        import java.util.logging.Level;
        import java.util.logging.Logger;

/**
 * Created by Yevhenii on 5/3/15.
 */
public class ConnectorDAO {
    private static final String PROPERTY_FILE_PATH = "/Users/Yevhenii/Documents/workspace/SalleryCalcJDBC/src/db.properties";
    private static final String DB_URL = "jdbc:mysql://localhost/EmployeeCompany";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    private static Connection myConnection ;
    public static Connection getConnection(){
        try {

            FileInputStream in = null;
            String connectionString = "";
            String user ="";
            String pass="";
            try{
                in = new FileInputStream(PROPERTY_FILE_PATH);
                Properties props = new Properties();
                props.load(in);
                connectionString = props.getProperty(DB_URL);
                user=props.getProperty(DB_USER);
                pass = props.getProperty(DB_PASS);
            }finally{
                if( in != null)
                    in.close();
            }

            if( myConnection==null || myConnection.isClosed() ){
                myConnection = DriverManager.getConnection(connectionString, user,
                        pass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConnectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myConnection;
    }
}

