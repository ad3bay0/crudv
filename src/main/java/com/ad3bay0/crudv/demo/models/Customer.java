package com.ad3bay0.crudv.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Customer
 */
@Entity
@Data
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue
    private Long id ;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public Customer(String firstname,String lastname){

        this.firstName = firstname;
        this.lastName = lastname;

    }

    public Customer(){
        
    }


    
    
}