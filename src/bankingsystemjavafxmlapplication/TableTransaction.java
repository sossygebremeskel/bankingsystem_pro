/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingsystemjavafxmlapplication;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author fish
 */
public class TableTransaction {
    
    private final StringProperty customerID;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty address;
    private final StringProperty  phoneNumber;
    private final StringProperty  dateOfBirth;
    private final StringProperty  password;
    private final StringProperty  ssn;
    
    private final StringProperty  balance;
    
     
    public TableTransaction()
    {
        customerID = new SimpleStringProperty(this, "customerID");
        firstName = new SimpleStringProperty(this, "firstname");
        lastName = new SimpleStringProperty(this, "lastname");
        address = new SimpleStringProperty(this, "address");
        phoneNumber = new SimpleStringProperty(this, "phoneNumber");
        dateOfBirth = new SimpleStringProperty(this, "DateOfBirth");
        password = new SimpleStringProperty(this, "password");
        ssn = new SimpleStringProperty(this, "ssn");
        balance = new SimpleStringProperty(this, "balance");
    }

    public StringProperty customerIDProperty() { return customerID; }
    public String getCustomerID() { return customerID.get(); }
    public void setCustomerID(String newCustomerID) { customerID.set(newCustomerID); }
    
    public StringProperty firstNameProperty() { return firstName; }
    public String getFirstName() { return firstName.get(); }
    public void setFirstName(String newFirstName) { firstName.set(newFirstName); }

    public StringProperty lastNameProperty() { return lastName; }
    public String getLastName() { return lastName.get(); }
    public void setLastName(String newLastName) { lastName.set(newLastName); }

    public StringProperty addressProperty() { return address; }
    public String getAddress() { return address.get(); }
    public void setAddress(String newAddress) { address.set(newAddress); }
    
    public StringProperty phoneNumberProperty() { return phoneNumber; }
    public String getPhoneNumber() { return phoneNumber.get(); }
    public void setPhoneNumber(String newPhoneNumber) { phoneNumber.set(newPhoneNumber); }
    
    public StringProperty PasswordProperty() { return password; }
    public String getPassword() { return password.get(); }
    public void setPassword(String newPassword) { password.set(newPassword); }
    
    public StringProperty dateOfBirthProperty() { return dateOfBirth; }
    public String getDateOfBirth() { return dateOfBirth.get(); }
    public void setDateOfBirth(String newDateOfBirth) { dateOfBirth.set(newDateOfBirth); }
    
    public StringProperty ssnProperty() { return ssn; }
    public String getSsn() { return ssn.get(); }
    public void setSsn(String newSsn) { ssn.set(newSsn); }
    
    public StringProperty balanceProperty() { return balance; }
    public String getBalance() { return balance.get(); }
    public void setBalance(String newBalance) { balance.set(newBalance); }
}
