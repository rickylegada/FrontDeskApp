package GUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Database.Database; 

public class LogScreen {
    private static JPanel logPanel;
    private static JTextArea area;
    private static JTable table;

    public LogScreen(){
        logPanel = new JPanel(null);
        logPanel.setPreferredSize(new Dimension(550, 600));
        table = new JTable();
        table.setBounds(0, 0, 550, 600);
        
        JScrollPane scrollPane = new JScrollPane(table);
        logPanel.add(scrollPane);


        
        logPanel.add(table);
        
    }

    public void refreshData(){
        String[] columnNames = { "FIRST NAME", "BOX TYPE", "QUANTITY" , "TIMEDATE" , "ACTION" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);  

        Database db = new Database();
        ArrayList<HashMap> recordList =  db.getRecentLogs();
        for(int i = 0; i<recordList.size(); i++){
            HashMap<String,String> map = recordList.get(i);
            String firstName = map.get("FirstName");
            String boxType = map.get("BoxType");
            String Quantity = map.get("Quantity");
            String TimeDate = map.get("TimeDate");
            String Action = map.get("Action");
            model.addRow(new Object[]{firstName, boxType, Quantity,TimeDate,Action});
            

        }
        table.setModel(model);

    }

    public JPanel getContainer(){
        return logPanel;

    }
}
