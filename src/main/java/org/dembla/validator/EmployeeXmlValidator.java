package org.dembla.validator;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Email;

public class EmployeeXmlValidator {

    private int id ;

    private String name ;

    private String email ;

    private String creditCardNumber ;

    //default no-args constructor
    public EmployeeXmlValidator(){}

    public EmployeeXmlValidator(int id, String name, String email, String ccNum){
        this.id=id;
        this.name=name;
        this.email=email;
        this.creditCardNumber=ccNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
}
