package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    private static String DB_URL = "jdbc:sqlite:frontdeskDB.db";
    private static Connection con;

    private static Connection connect(){
        con = null;
        try {

            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(DB_URL);
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return con;
    }


    public boolean hasCustomer(String phoneNumber){
        String checkSQL = "SELECT * FROM Customer WHERE phoneNumber = "+phoneNumber;
        try {

            Connection conn = connect();
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery(checkSQL);
            conn.close();
            
            if(rs.next()){
                return true;
            }else{
                return false;
            }
            
        }
        catch ( SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return false;
    }

    public int checkSmallCapacity(){
        String checkCapacitySQL = "SELECT SUM(QUANTITY) as total FROM Storage WHERE Box_Id = 1";
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery(checkCapacitySQL);
            


            if(rs.next()){
                int cap = rs.getInt("total");
                conn.close();
                return cap;
            }else{
                conn.close();
                System.out.println("Current Capacity of Small Boxes: 0");
            }
            conn.close();            
        }catch(SQLException e){

        }
        
        return 0;

    }
    public int checkMediumCapacity(){
        String checkCapacitySQL = "SELECT SUM(QUANTITY) as total FROM Storage WHERE Box_Id = 2";
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery(checkCapacitySQL);
            

            if(rs.next()){
                int cap = rs.getInt("total");
                conn.close();
                return cap;
            }else{
                conn.close();
                System.out.println("Current Capacity of Small Boxes: 0");
            }

        }catch(SQLException e){

        }
        return 0;

    }
    public int checkLargeCapacity(){
        String checkCapacitySQL = "SELECT SUM(QUANTITY) as total FROM Storage WHERE Box_Id = 3";
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery(checkCapacitySQL);
            

            if(rs.next()){
                int cap = rs.getInt("total");
                conn.close();
                return cap;
            }else{
                conn.close();
                System.out.println("Current Capacity of Small Boxes: 0");
            }
        }catch(SQLException e){

        }
        return 0;

    }

    public int getCustomerId(String phoneNumber){
        String getIdSQL = "SELECT id FROM Customer WHERE phoneNumber = "+phoneNumber;
        try{
            Connection conn = connect();
            
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery(getIdSQL);
            int customerId = rs.getInt("id");
            conn.close();


            return customerId;
        }catch(SQLException e){

        }
        return 0;

    }

    public void removeOne(int customerId,int boxType, int quantity){
        String removeSQL = "UPDATE STORAGE SET QUANTITY = ? WHERE customer_Id = ? AND Box_Id = ?";
        addRecord(customerId,boxType,quantity,false);

        try {

            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(removeSQL); {
                stmt.setInt(1,quantity);
                stmt.setInt(2,customerId);
                stmt.setInt(3,boxType);
            };
            stmt.executeUpdate();
        }
        catch ( SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public int getTotalBoxes(int customerId, String boxType){
        String getSumSQL = "SELECT SUM(QUANTITY) as total FROM Storage WHERE Customer_Id = "+customerId+" AND Box_Id = "+boxType;

        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery(getSumSQL);
            

            if(rs.next()){
                int total = rs.getInt("total");
                conn.close();
                return total;
            }else{
                conn.close();
                System.out.println("Current Capacity of Small Boxes: 0");
            }
        }catch(SQLException e){

        }
        return 0;
    }

    public void addToExisting(){
        String SQL = "";
    }
    public boolean hasExistingData(int customerId,int boxType){
        String SQL = "SELECT * FROM STORAGE WHERE Customer_Id = "+customerId+" AND Box_Id = "+boxType;
        try{
            System.out.println("already has it");
            System.out.println(SQL);
            Connection conn = connect();
            
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery(SQL);

            conn.close();
            if(rs.next()){
                return true;
            }else{
                return false;
            }
            

        }catch(SQLException e){

        }
        return false;
    }
    public void addRecord(int customerId, int BoxId, int Quantity, boolean action){
        // true = added
        // false = removed
        String addRecord =  "INSERT INTO Records(Customer_Id,Box_Id,Quantity,Action) VALUES (?,?,?,?)";
        try{
            Connection conn = connect();
            PreparedStatement Pstmt = conn.prepareStatement(addRecord); {
                Pstmt.setInt(1,customerId);
                Pstmt.setInt(2,1);
                Pstmt.setInt(3,Quantity);
                Pstmt.setBoolean(4,action);

            };
            Pstmt.executeUpdate();
            conn.close();
        }catch( SQLException e){

        }

    }

    public boolean addBoxes(String phoneNumber, int smallBox, int mediumBox ,int largeBox){
        String addSQL = "INSERT INTO Storage(Customer_Id,Box_Id,Quantity) VALUES (?,?,?)";
        String alterSQL = "UPDATE STORAGE SET QUANTITY = ? WHERE customer_Id = ? AND Box_Id = ?";
        int customerId = getCustomerId(phoneNumber);
        try {
            

            if(smallBox!=0){
                addRecord(customerId,1,smallBox,true);
                Connection conn = connect();
                
                if(hasExistingData(customerId,1)){
                    int totalBox = getTotalBoxes(customerId, "1");
                    int total = totalBox + smallBox;
                    System.out.println(hasExistingData(customerId,1));
                    PreparedStatement Pstmt = conn.prepareStatement(alterSQL); {
                    Pstmt.setInt(1,total);
                    Pstmt.setInt(2,customerId);
                    Pstmt.setInt(3,1);

                };
                Pstmt.executeUpdate();
                conn.close();
                }else{

                    PreparedStatement Pstmt = conn.prepareStatement(addSQL); {
                    Pstmt.setInt(1,customerId);
                    Pstmt.setInt(2,1);
                    Pstmt.setInt(3,smallBox);

                };
                Pstmt.executeUpdate();
                conn.close();
                    
                }
            }
            if(mediumBox!=0){
                addRecord(customerId,2,mediumBox,true);
                Connection conn = connect();
                if(hasExistingData(customerId,2)){
                    int totalBox = getTotalBoxes(customerId, "2");
                    int total = totalBox + mediumBox;
                    PreparedStatement Pstmt = conn.prepareStatement(alterSQL); {
                    Pstmt.setInt(1,total);
                    Pstmt.setInt(2,customerId);
                    Pstmt.setInt(3,2);

                };
                Pstmt.executeUpdate();
                conn.close();
            }else{
                PreparedStatement Pstmt = conn.prepareStatement(addSQL); {
                Pstmt.setInt(1,customerId);
                Pstmt.setInt(2,2);
                Pstmt.setInt(3,mediumBox);
                };
                Pstmt.executeUpdate();
                conn.close();
            }
            }
            if(largeBox!=0){
                addRecord(customerId,3,largeBox,true);
                Connection conn = connect();
                if(hasExistingData(customerId,3)){
                    int totalBox = getTotalBoxes(customerId, "3");
                    int total = totalBox + largeBox;
                    PreparedStatement Pstmt = conn.prepareStatement(alterSQL); {
                    Pstmt.setInt(1,total);
                    Pstmt.setInt(2,customerId);
                    Pstmt.setInt(3,3);

                };
                Pstmt.executeUpdate();
                conn.close();
                }else{
                    PreparedStatement Pstmt = conn.prepareStatement(addSQL); {
                    Pstmt.setInt(1,customerId);
                    Pstmt.setInt(2,3);
                    Pstmt.setInt(3,largeBox);
                
                };
                Pstmt.executeUpdate();
                conn.close();
                }
            }
            return true;

            }
        catch ( SQLException e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            return false;
        }

    }

    public void addCustomer(String firstName, String lastName, String phoneNumber){
        String addSQL = "INSERT INTO Customer(FirstName,LastName,PhoneNumber) VALUES (?,?,?)";
        try {

            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(addSQL); {
                stmt.setString(1,firstName);
                stmt.setString(2,lastName);
                stmt.setString(3,phoneNumber);
            };
            stmt.executeUpdate();
        }
        catch ( SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }

    public ArrayList<HashMap> getRecentLogs(){
        String SQL = "SELECT C.FirstName, R.Box_Id, R.Quantity, R.TimeDate, R.Action FROM CUSTOMER C INNER JOIN RECORDS R ON R.Customer_Id = C.Id ORDER BY R.TimeDate DESC";
        ArrayList<HashMap> recordList = new ArrayList<HashMap>(); 
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery(SQL);
            

            

            int incr = 0;

            while(rs.next()){
                HashMap<String,String> record = new HashMap<String,String>();
                System.out.println("Get recent logs");
                String firstName = rs.getString(1);
                System.out.println("fIRST nAME "+firstName);
                record.put("FirstName",firstName);
                int boxId = rs.getInt("Box_Id");
                if(boxId==1){
                    record.put("BoxType","SmallBox");
                }else if(boxId==2){
                    record.put("BoxType","MediumBox");
                }else if(boxId==3){
                    record.put("BoxType","LargeBox");
                }
                int qty = rs.getInt("Quantity");
                record.put("Quantity",String.valueOf(qty));
                record.put("TimeDate",String.valueOf(rs.getDate("timeDate")));
                boolean action = rs.getBoolean("Action");
                if(action){
                    record.put("Action", "Added");

                }else{
                    record.put("Action", "Removed");
                }

                recordList.add(record);
                incr++;


            }
            conn.close();
            return recordList;
        }catch(SQLException e){

            
        }
        return recordList;


    }



    
    
}
