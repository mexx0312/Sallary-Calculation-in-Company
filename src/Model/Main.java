package Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Yevhenii on 5/3/15.
 */
public class Main {

    private static final String PROPERTY_FILE_PATH = "/Users/Yevhenii/Documents/workspace/SalleryCalcJDBC/src/db.properties";
    private static final String DB_URL = "url";
    private static final String DB_USER = "user";
    private static final String DB_PASS = "pass";
    private static Connection myConnection ;
    public static Connection getConnection(){
        try {
            if( myConnection==null || myConnection.isClosed() ){
                FileInputStream in = null;
                String connectionString = "", user="", pass="";
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


                myConnection = DriverManager.getConnection(connectionString, user, pass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myConnection;
    }

    public static void main(String[] args) throws SQLException {
        AutoFillDB autofill = new AutoFillDB();
        PreparedQueries queries = new PreparedQueries();

        queries.clearDatabase();
        autofill.createCompany("Test organization", 2, "department", 40, "Ivan", "Ivanovich");
        autofill.printCompany();
        System.out.println("\n==================================================================================\n");
        autofill.printSaleryWithBonusDirectly();
    }
}
