import java.sql.Connection;
import java.sql.DriverManager;

import GUI.MainScreen;

public class App {
    static final String AppName = "Front Desk App";
    static final int ScreenWidth = 800;
    static final int ScreenHeight = 600;


    public static void main(String[] args) throws Exception {
        GUI.MainScreen mainScreen = new MainScreen("FrontDeskApp", ScreenWidth, ScreenHeight);
        

    } 

    
}
