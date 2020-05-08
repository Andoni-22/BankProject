/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import java.util.Set;

/**
 *
 * @author Andoni
 */
public class Account {
    
    private long id;
    private AccountType type;
    private String description;
    private double balance;
    private double creditLine;
    private double beginBalance;
    private Date beginBalanceTimestamp;
    private Set<Customer> customers;
    private Set<Movement> movements;


    
}
