/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Set;

/**
 *
 * @author Andoni
 */
public class Customer {
    
    private Long id;
    /**
     * First name of the customer.
     */
    private String firstName;
    /**
     * Customer's last name.
     */
    private String lastName;
    /**
     * Customer's middle name initial.
     */
    private String middleInitial;
    /**
     * Customer's address street.
     */
    private String street;
    /**
     * Customer's address city.
     */
    private String city;
    /**
     * Customer's address state.
     */
    private String state;
        /**
     * Customer's address zip.
     */
    private Integer zip;
    /**
     * Customer's phone.
     */
    private Long phone;
    /**
     * Customer's email.
     */
    private String email;
    /**
     * Relational field for customer's accounts.
     */
    private Set<Account> accounts;
    
    
}
