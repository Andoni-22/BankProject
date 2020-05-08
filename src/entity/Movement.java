/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity representing movements for accounts. It contains the following
 * fields: id, account id, description, movement timestamp, account balance after the
 * movement and amount of the movement. 
 * @author Javier Martín Uría
 */

@XmlRootElement
public class Movement{

    /**
     * Identification field for Movement. 
     */
    private Long id;
    /**
     * Account for the movement.
     */
    private Account account;
    /**
     * Timestamp for the movement.
     */
    private Date timestamp;
    /**
     * Amount of the movement.
     */
    private Double amount;
    /**
     * Account balance after the movement.
     */
    private Double balance;
    /**
     * Description of the movement.
     */
    private String description;
     
  
    /**
     * Identification field for Movement.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Identification field for Movement.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Account for the movement.
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Account for the movement.
     * @param account the account to set
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Timestamp for the movement.
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Timestamp for the movement.
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Amount of the movement.
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Amount of the movement.
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Account balance after the movement.
     * @return the balance
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * Account balance after the movement.
     * @param balance the balance to set
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * Description of the movement.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Description of the movement.
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
}
