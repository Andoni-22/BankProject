/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name = "account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private Long id;
    private AccountType type;
    private String description;
    private Double balance;
    private Double creditLine;
    private Double beginBalance;
    private Date beginBalanceTimestamp;
    private Set<Customer> customers;
    private Set<Movement> movements;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public AccountType getType() {
        return type;
    }
    public void setType(AccountType type) {
        this.type = type;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public Double getCreditLine() {
        return creditLine;
    }
    public void setCreditLine(Double creditLine) {
        this.creditLine = creditLine;
    }
    public Double getBeginBalance() {
        return beginBalance;
    }
    public void setBeginBalance(Double beginBalance) {
        this.beginBalance = beginBalance;
    }
    public Date getBeginBalanceTimestamp() {
        return beginBalanceTimestamp;
    }
    public void setBeginBalanceTimestamp(Date beginBalanceTimestamp) {
        this.beginBalanceTimestamp = beginBalanceTimestamp;
    }
    public Set<Customer> getCustomers() {
        return customers;
    }
    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
    public Set<Movement> getMovements() {
        return movements;
    }
    public void setMovements(Set<Movement> movements) {
        this.movements = movements;
    }
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "serverside.entity.Account[ id=" + id + " ]";
    }  
}
