package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InitializeDatabase {

    private static String DB_URL = "jdbc:sqlite:frontdeskDB.db";

    private static String createCustomerTable = "CREATE TABLE Customer ("+
    "id INTEGER NOT NULL,"+
    "FirstName varchar(255),"+
    "LastName varchar(255),"+
    "PhoneNumber varchar(255),"+
    "PRIMARY KEY (id));";
    private static String storageFacilityTable = "CREATE TABLE Storage ("+
    "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
    "Customer_Id INTEGER NOT NULL,"+
    "Box_Id INTEGER NOT NULL,"+
    "Quantity INTEGER,"+
    "FOREIGN KEY (Customer_Id) REFERENCES Customer (id),"+
    "FOREIGN KEY (Box_Id) REFERENCES BoxName (id));";

    private static String RecordsTable = "CREATE TABLE Records ("+
    "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
    "Customer_Id INTEGER NOT NULL,"+
    "Box_Id INTEGER NOT NULL,"+
    "Quantity INTEGER,"+
    "TimeDate DATETIME DEFAULT CURRENT_TIMESTAMP,"+
    "Action BOOL,"+
    "FOREIGN KEY (Customer_Id) REFERENCES Customer (id),"+
    "FOREIGN KEY (Box_Id) REFERENCES BoxName (id));";

    private static String BoxNameTable = "CREATE TABLE BoxName ("+
    "id INTEGER NOT NULL,"+
    "BoxType varchar(255),"+
    "PRIMARY KEY (id));";

    public InitializeDatabase(){
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createCustomerTable);
            stmt.executeUpdate(BoxNameTable);
            stmt.executeUpdate(storageFacilityTable);
            stmt.executeUpdate(RecordsTable);
            conn.close();
        }
        catch ( SQLException  e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Succesfully created DATABASE");
        

    }
    

    private Connection connect(){
        Connection conn = null;
        try {

            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_URL);
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        
        return conn;
    }

    
}
