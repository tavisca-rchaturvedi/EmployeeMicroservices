package com.tavisca.Validation;

import jdk.nashorn.internal.runtime.regexp.joni.Matcher;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;
    public String firstName;
    public String lastName;
    public long salary;
    public String email;

    public Employee() {
    }

    public String isNotValid(){
//        Regex emailRegex = new Regex("[A-Za-z]+@[a-z]+.[a-z]{3}");
//        Matcher matcher = emailRegex.matcher(this.email);
        if( this.firstName == null || this.firstName.isEmpty() ){
            return "firstName";
        }
        if( this.lastName == null || this.lastName.isEmpty() ){
            return "lastName";
        }
        if(this.salary == 0){
            return "salary";
        }
        if(this.email == null || this.email.isEmpty()){
            return "email";
        }

        return "none";
    }

    public Employee(long id, String firstName, String lastName, long salary, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.email = email;
    }

    @Override
    public String toString(){
        return "{\"id\":"+id+","+
                "\"firstName\":"+ firstName+ ","+
                "\"lastName\":"+ lastName+ ","+
                "\"salary\":"+ salary+ ","+
                "\"email\":"+ email+ "}" ;
    }
}
