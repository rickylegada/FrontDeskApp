package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

 

public class StorageScreen {
    private static JPanel storagePanel;
    private static JButton retrieveButton;
    private static JButton depositButton;
    private static JLabel storageTitle;

    private static JLabel smallBoxTitle;
    private static JLabel smallBoxNum;
    private static JLabel mediumBoxTitle;
    private static JLabel mediumBoxNum;

    private static JLabel largeBoxTitle;
    private static JLabel largeBoxNum;

    private static JLabel phoneNumberLabel;
    private static JTextField phoneNumberField;

    private static JLabel smallBoxLabel;
    private static JTextField smallBoxField;
    private static JLabel errorSmallBox;

    private static JLabel mediumBoxLabel;
    private static JTextField mediumBoxField;
    private static JLabel errorMediumBox;

    private static JLabel largeBoxLabel;
    private static JTextField largeBoxField;
    private static JLabel errorLargeBox;

    private static JLabel errorLabel;
    private static JButton cancelButton;

    private static JButton addButton;
    private static int smallCap;
    private static int mediumCap;
    private static int largeCap;
    private static int smallMAX = 48;
    private static int mediumMAX = 14;
    private static int largeMAX = 12;

    private static String phoneNum;
    /////////////////////
    private static JLabel quantityTitle;
    private static JLabel smallQuantityLabel;
    private static JLabel mediumQuantityLabel;
    private static JLabel largeQuantityLabel;
    
    private static JButton smallRetrieveButton;
    private static JButton mediumRetrieveButton;
    private static JButton largeRetrieveButton;






    public StorageScreen(){


        Database.Database db = new Database.Database();

        smallCap = db.checkSmallCapacity();
        mediumCap = db.checkMediumCapacity();
        largeCap  = db.checkLargeCapacity();

        storagePanel = new JPanel(null);
        storagePanel.setPreferredSize(new Dimension(550, 600));
        storagePanel.setBackground(Color.white);


        retrieveButton = new JButton("Retrieve");
        retrieveButton.setBounds(180, 300, 120, 30);
        storagePanel.add(retrieveButton);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(50, 300, 120, 30);
        storagePanel.add(depositButton);


        storageTitle = new JLabel("Storage Facility 1");
        storageTitle.setFont(new Font("Arial", Font.BOLD, 20));
        storageTitle.setBounds(170, 30, 250, 50);
        storagePanel.add(storageTitle);

        smallBoxTitle = new JLabel("Small Box");
        smallBoxTitle.setFont(new Font("Arial", Font.PLAIN, 15));
        smallBoxTitle.setBounds(30, 100, 250, 50);
        storagePanel.add(smallBoxTitle);

        smallBoxNum = new JLabel(smallCap+" / "+smallMAX);
        smallBoxNum.setFont(new Font("Arial", Font.PLAIN, 15));
        smallBoxNum.setBounds(30, 130, 250, 50);
        storagePanel.add(smallBoxNum);

        mediumBoxTitle = new JLabel("Medium Box");
        mediumBoxTitle.setFont(new Font("Arial", Font.PLAIN, 15));
        mediumBoxTitle.setBounds(150, 100, 250, 50);
        storagePanel.add(mediumBoxTitle);

        mediumBoxNum = new JLabel(mediumCap+" / "+mediumMAX);
        mediumBoxNum.setFont(new Font("Arial", Font.PLAIN, 15));
        mediumBoxNum.setBounds(150, 130, 250, 50);
        storagePanel.add(mediumBoxNum);

        largeBoxTitle = new JLabel("Large Box");
        largeBoxTitle.setFont(new Font("Arial", Font.PLAIN, 15));
        largeBoxTitle.setBounds(300, 100, 250, 50);
        storagePanel.add(largeBoxTitle);

        largeBoxNum = new JLabel(largeCap+" / "+largeMAX);
        largeBoxNum.setFont(new Font("Arial", Font.PLAIN, 15));
        largeBoxNum.setBounds(300, 130, 250, 50);
        storagePanel.add(largeBoxNum);

        JSeparator s = new JSeparator();
         
        // set layout as vertical
        s.setOrientation(SwingConstants.HORIZONTAL);
        s.setBounds(0, 200, 600, 3);
        s.setSize(600,5);
        s.setBackground(Color.BLACK);
        storagePanel.add(s);

        errorLabel = new JLabel("");
        errorLabel.setBounds(310, 250, 200, 50);
        errorLabel.setForeground(Color.red);
        storagePanel.add(errorLabel);


        phoneNumberLabel = new JLabel("Phone Number");
        phoneNumberLabel.setBounds(50, 250, 150, 50);
        storagePanel.add(phoneNumberLabel);

        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(150, 260, 150, 30);
        storagePanel.add(phoneNumberField);





        
        smallBoxLabel = new JLabel("Small Box");
        smallBoxLabel.setBounds(50, 350, 150, 50);
        //smallBoxLabel.setBounds(50, 20, 150, 50);
        storagePanel.add(smallBoxLabel);

        smallBoxField = new JTextField();
        smallBoxField.setBounds(130, 365, 70, 25);
        //smallBoxField.setBounds(130, 30, 70, 25);
        storagePanel.add(smallBoxField);

        smallQuantityLabel = new JLabel("0");
        smallQuantityLabel.setBounds(130, 350, 150, 50);
        //smallBoxLabel.setBounds(50, 20, 150, 50);
        storagePanel.add(smallQuantityLabel);

        smallRetrieveButton = new JButton("Retrieve 1 from Small storage");
        smallRetrieveButton.setBounds(150, 365, 230, 20);
        storagePanel.add(smallRetrieveButton);

        errorSmallBox = new JLabel("");
        errorSmallBox.setBounds(220, 350, 200, 50);
        errorSmallBox.setForeground(Color.red);
        storagePanel.add(errorSmallBox);

        mediumBoxLabel = new JLabel("Medium Box");
        mediumBoxLabel.setBounds(50, 380, 150, 50);
        //mediumBoxLabel.setBounds(50, 60, 150, 50);
        storagePanel.add(mediumBoxLabel);

        mediumBoxField = new JTextField();
        mediumBoxField.setBounds(130, 395, 70, 25);
        //mediumBoxField.setBounds(130, 70, 70, 25);
        storagePanel.add(mediumBoxField);


        mediumQuantityLabel = new JLabel("0");
        mediumQuantityLabel.setBounds(130, 395, 70, 25);
        //mediumBoxField.setBounds(130, 70, 70, 25);
        storagePanel.add(mediumQuantityLabel);

        mediumRetrieveButton = new JButton("Retrieve 1 from Medium storage");
        mediumRetrieveButton.setBounds(150, 395, 230, 20);
        storagePanel.add(mediumRetrieveButton);

        errorMediumBox = new JLabel("");
        errorMediumBox.setBounds(220, 380, 200, 50);
        errorMediumBox.setForeground(Color.red);
        storagePanel.add(errorMediumBox);

        largeBoxLabel = new JLabel("Large Box");
        largeBoxLabel.setBounds(50, 410, 150, 50);
        //largeBoxLabel.setBounds(50, 100, 150, 50);
        storagePanel.add(largeBoxLabel);

        largeBoxField = new JTextField();
        largeBoxField.setBounds(130, 425, 70, 25);
        //largeBoxField.setBounds(130, 110, 70, 25);
        storagePanel.add(largeBoxField);

        largeQuantityLabel = new JLabel("0");
        largeQuantityLabel.setBounds(130, 425, 70, 25);
        //largeBoxField.setBounds(130, 110, 70, 25);
        storagePanel.add(largeQuantityLabel);

        errorLargeBox = new JLabel("");
        errorLargeBox.setBounds(220, 410, 200, 50);
        errorLargeBox.setForeground(Color.red);
        storagePanel.add(errorLargeBox);

        largeRetrieveButton = new JButton("Retrieve 1 from Large storage");
        largeRetrieveButton.setBounds(150, 425, 230, 20);
        storagePanel.add(largeRetrieveButton);


        addButton = new JButton("Add to storage");
        addButton.setBounds(50, 470, 120, 30);
        storagePanel.add(addButton);


        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(170, 470, 120, 30);
        storagePanel.add(cancelButton);
        depositVisible(false);
        retrieveVisible(false);

        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                depositVisible(false);
                retrieveVisible(false);
                phoneNumberField.setEditable(true);
            }
            
        });

        smallRetrieveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int customerId = db.getCustomerId(phoneNum);
                int smallQuantity = Integer.parseInt(smallQuantityLabel.getText());
                if(smallQuantity>0){
                    db.removeOne(customerId, 1,smallQuantity-1 );
                    smallQuantityLabel.setText((smallQuantity-1)+"");
                    refreshData(db);
                }
                
            }
            
        });

        mediumRetrieveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int customerId = db.getCustomerId(phoneNum);
                int mediumQuantity = Integer.parseInt(mediumQuantityLabel.getText());
                if(mediumQuantity>0){
                    db.removeOne(customerId, 2,mediumQuantity-1 );
                    mediumQuantityLabel.setText((mediumQuantity-1)+"");
                    refreshData(db);
                }
            }
            
        });

        largeRetrieveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int customerId = db.getCustomerId(phoneNum);
                int largeQuantity = Integer.parseInt(largeQuantityLabel.getText());
                if(largeQuantity>0){
                    db.removeOne(customerId, 3,largeQuantity-1 );
                    largeQuantityLabel.setText((largeQuantity-1)+"");
                    refreshData(db);
                }
                
            }
            
        });



        depositButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                errorLabel.setText("");
                retrieveVisible(false);

                // TODO Auto-generated method stub
                String phoneNumber = phoneNumberField.getText().trim();

                if(phoneNumber.equals("")){
                    errorLabel.setText("Enter Valid PhoneNumber");
                    return;
                }
                if(phoneNumber.substring(0, 1).equals("+")){
                    phoneNumber = phoneNumber.substring(1);
                    System.out.println("New Phone Numb "+phoneNumber);
                }
                try{
                    Double phone = Double.parseDouble(phoneNumber);
                    
                }catch(NumberFormatException ex){
                    errorLabel.setText("Invalid Phone Number");
                    return;
                }

                if (!db.hasCustomer(phoneNumber)){
                    errorLabel.setText("Phone number does not exist");
                    return;
                }else{
                    phoneNum = phoneNumber;
                    phoneNumberField.setEditable(false);
                    depositVisible(true);
                    

                    
                }
                
            }
            
        });

        retrieveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                errorLabel.setText("");
                depositVisible(false);
                // TODO Auto-generated method stub
                String phoneNumber = phoneNumberField.getText().trim();
                if(phoneNumber.equals("")){
                    errorLabel.setText("Enter Valid PhoneNumber");
                    return;
                }

                if(phoneNumber.substring(0, 1).equals("+")){
                    phoneNumber = phoneNumber.substring(1);
                    System.out.println("New Phone Numb "+phoneNumber);
                }
                try{
                    Double phone = Double.parseDouble(phoneNumber);
                    
                }catch(NumberFormatException ex){
                    errorLabel.setText("Invalid Phone Number");
                    return;
                }

                if (!db.hasCustomer(phoneNumber)){
                    errorLabel.setText("Phone number does not exist");
                    return;
                }else{
                    phoneNum = phoneNumber;
                    phoneNumberField.setEditable(false);
                    retrieveVisible(true);
                    int customerId = db.getCustomerId(phoneNumber);

                    int totalSmall = db.getTotalBoxes(customerId, "1");
                    int totalMedium = db.getTotalBoxes(customerId, "2");
                    int totalLarge = db.getTotalBoxes(customerId, "3");
                    System.out.println("TOTAL");
                    if(totalSmall!=0){
                        smallQuantityLabel.setText(totalSmall+"");
                    }else{
                        smallRetrieveButton.setVisible(false);
                    }
                    if(totalMedium!=0){
                        mediumQuantityLabel.setText(totalMedium+"");
                    }else{
                        mediumRetrieveButton.setVisible(false);
                    }
                    if(totalLarge!=0){
                        largeQuantityLabel.setText(totalLarge+"");
                    }else{
                        largeRetrieveButton.setVisible(false);
                    }



                }
                
            }
            
        });

        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                errorSmallBox.setText("");
                errorMediumBox.setText("");
                errorLargeBox.setText("");
                int smallBoxes = 0;
                int mediumBoxes = 0;
                int largeBoxes = 0;
                try{
                    if(!smallBoxField.getText().equals("")){
                        smallBoxes = Integer.parseInt(smallBoxField.getText().trim());
                        if((smallBoxes+smallCap)>smallMAX){
                            errorSmallBox.setText("Overcapacity");
                            return;
                        }
                        
                    }
                    if(!mediumBoxField.getText().equals("")){
                        mediumBoxes = Integer.parseInt(mediumBoxField.getText().trim());
                        if((mediumBoxes+mediumCap)>mediumMAX){
                            errorMediumBox.setText("Overcapacity");
                            return;
                        }
                        
                        
                    }
                    if(!largeBoxField.getText().equals("")){
                        largeBoxes = Integer.parseInt(largeBoxField.getText().trim());
                        if((largeBoxes+largeCap)>largeMAX){
                            errorLargeBox.setText("Overcapacity");
                            return;
                        }
                        
                    }
                    db.addBoxes(phoneNum, smallBoxes, mediumBoxes, largeBoxes);
                    refreshData(db);
                    smallBoxField.setText("");
                    mediumBoxField.setText("");
                    largeBoxField.setText("");

                }catch(NumberFormatException ex){
                    errorLabel.setText("Invalid Quantity");
                    System.out.println(ex);
                }
                
            }
            
        });


    }

    public void depositVisible(boolean visibility){
        smallBoxLabel.setVisible(visibility);
        smallBoxField.setVisible(visibility);
        mediumBoxLabel.setVisible(visibility);
        largeBoxField.setVisible(visibility);
        largeBoxLabel.setVisible(visibility);
        mediumBoxField.setVisible(visibility);
        errorSmallBox.setVisible(visibility);
        errorMediumBox.setVisible(visibility);
        errorLargeBox.setVisible(visibility);
        addButton.setVisible(visibility);
        cancelButton.setVisible(visibility);
        smallBoxField.setText("");
        mediumBoxField.setText("");
        largeBoxField.setText("");
        errorSmallBox.setText("");
        errorMediumBox.setText("");
        errorLargeBox.setText("");

    }

    public void retrieveVisible(boolean visibility){
        smallBoxLabel.setVisible(visibility);

        mediumBoxLabel.setVisible(visibility);
        largeBoxLabel.setVisible(visibility);
        mediumQuantityLabel.setVisible(visibility);
        largeQuantityLabel.setVisible(visibility);
        smallQuantityLabel.setVisible(visibility);
        cancelButton.setVisible(visibility);
        smallRetrieveButton.setVisible(visibility);
        mediumRetrieveButton.setVisible(visibility);
        largeRetrieveButton.setVisible(visibility);


    }



    public void hideRetrieve(){

    }

    public void refreshData(Database.Database db){


        smallCap = db.checkSmallCapacity();
        smallBoxNum.setText(smallCap+" / "+smallMAX);
        mediumCap = db.checkMediumCapacity();
        mediumBoxNum.setText(mediumCap+" / "+mediumMAX);
        largeCap  = db.checkLargeCapacity();
        largeBoxNum.setText(largeCap+" / "+largeMAX);

    }

    public JPanel getContainer(){
        return storagePanel;

    }
    
}
