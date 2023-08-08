package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;    

public class MainScreen {
    private static JFrame frame;
    private static JPanel mainPanel;
    private static JPanel activityPanel;
    LogScreen logScreen;
    CardLayout crd;

    public MainScreen(String AppName,int width, int height){
        Database.InitializeDatabase iniDB = new Database.InitializeDatabase();
        frame = new JFrame(AppName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height); 

        mainPanel = new JPanel();
        mainPanel.setSize(width, height);
        //mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        mainPanel.setBackground(Color.BLUE);

        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 0;
        mainPanel.add(navigationPanel(),cons);

        cons.gridx = 1;
        cons.gridy = 0;
        mainPanel.add(activityPanel(),cons);

        frame.add(mainPanel,BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel activityPanel(){
        crd = new CardLayout();
        activityPanel = new JPanel();
        activityPanel.setLayout(crd);
        //activityPanel.setLayout(new CardLayout(0, 0));
        activityPanel.setPreferredSize(new Dimension(550, 600));

        activityPanel.setBackground(Color.ORANGE);

        CustomerScreen customerScreen = new CustomerScreen();
        activityPanel.add("a",customerScreen.getContainer());

        StorageScreen storageScreen = new StorageScreen();
        activityPanel.add("b",storageScreen.getContainer());

        logScreen = new LogScreen();
        activityPanel.add("c",logScreen.getContainer());

        return activityPanel;

    }

    public JPanel navigationPanel( ){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,0));
        panel.setPreferredSize(new Dimension(190, 600));
        panel.setBackground(Color.BLACK);

        JButton customerButton = new JButton("Customer");
        customerButton.setFont(new Font("Arial", Font.PLAIN, 20));
        customerButton.setMargin(new Insets(10, 0, 10, 0));
        customerButton.setEnabled(false);
        panel.add(customerButton);

        JButton storageButton = new JButton("Storage Facility");
        storageButton.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(storageButton);

        JButton logButton = new JButton("Logs");
        logButton.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(logButton);


        customerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                customerButton.setEnabled(false);
                storageButton.setEnabled(true);
                logButton.setEnabled(true);
                crd.show(activityPanel,"a");

            }
        });
        storageButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                customerButton.setEnabled(true);
                storageButton.setEnabled(false);
                logButton.setEnabled(true);
                crd.show(activityPanel,"b");
                
            }
        }); 
        logButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                customerButton.setEnabled(true);
                storageButton.setEnabled(true);
                logButton.setEnabled(false);
                logScreen.refreshData();
                crd.show(activityPanel,"c");


                
            }
        }); 



        return panel;
    }





    
}
