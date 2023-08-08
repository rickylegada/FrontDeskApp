package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;  

public class CustomerScreen  {
    private static JPanel customerContainer;
    private static JButton addCustomerButton;
    private static JTextField firstNameField;
    private static JLabel firstNameLabel;
    private static JTextField lastNameField;
    private static JLabel lastNameLabel;
    private static JTextField phoneNumberField;
    private static JLabel phoneNumberLabel;

    private static JLabel errorLabel;

    public CustomerScreen(){
        customerContainer = new JPanel();
        customerContainer.setLayout(null);
        customerContainer.setPreferredSize(new Dimension(550, 600));

        firstNameLabel = new JLabel("First Name");
        firstNameLabel.setBounds(50, 50, 150, 50);
        customerContainer.add(firstNameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(150, 60, 150, 30);
        customerContainer.add(firstNameField);

        lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setBounds(50, 80, 150, 50);
        customerContainer.add(lastNameLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(150, 90, 150, 30);
        customerContainer.add(lastNameField);

        phoneNumberLabel = new JLabel("Phone Number");
        phoneNumberLabel.setBounds(50, 110, 150, 50);
        customerContainer.add(phoneNumberLabel);


        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(150, 120, 150, 30);
        customerContainer.add(phoneNumberField);

        

        addCustomerButton = new JButton("Add Customer");
        addCustomerButton.setBounds(50, 200, 130, 30);
        customerContainer.add(addCustomerButton);

        errorLabel = new JLabel("");
        errorLabel.setBounds(50, 230, 300, 30);
        errorLabel.setForeground(Color.red);
        customerContainer.add(errorLabel);

        customerContainer.setBackground(Color.white);


        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                errorLabel.setText("");
                String firstName = firstNameField.getText().trim();
                String lastName = lastNameField.getText().trim();
                String phoneNumber = phoneNumberField.getText().trim();
                
                if(firstName.equals("") || lastName.equals("") || phoneNumber.equals("")){
                    errorLabel.setText("Please complete every missing field");
                    return;
                }

                if(phoneNumber.substring(0, 1).equals("+")){
                    phoneNumber = phoneNumber.substring(1);
                    System.out.println("New Phone Numb "+phoneNumber);
                }
                try{
                    double phone = Double.parseDouble(phoneNumber);
                    
                }catch(NumberFormatException ex){
                    errorLabel.setText("Invalid Phone Number");
                    return;
                }
                Database.Database db = new Database.Database();
                if (!db.hasCustomer(phoneNumber)){
                    db.addCustomer(firstName, lastName, phoneNumber);
                    firstNameField.setText("");
                    lastNameField.setText("");
                    phoneNumberField.setText("");;
                }else{
                    errorLabel.setText("Phone Number already exist");
                }



                
            }
        }); 
    }

    public JPanel getContainer(){
        return customerContainer;
    }

    public void setVisible(boolean visibility){
        firstNameField.setVisible(visibility);
    }


}
