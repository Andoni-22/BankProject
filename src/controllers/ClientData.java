/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author Andoni
 */
public class ClientData {
    
    @FXML 
    private Button btnClose;
    
    @FXML
    private TextField tfInitial;
    
    @FXML 
    private TextField tfPhone;
    
    @FXML
    private TextField tfEmail;
    
    @FXML
    private TextField tfCity;
    
    @FXML
    private TextField tfState;
       
    @FXML
    private TextField tfStreet;
       
    @FXML
    private TextField tfZip;
    
    @FXML
    private ComboBox comboBoxAccounts;
    
    @FXML
    private TableView tableViewMovements;
    
    @FXML
    private TableColumn tcId;
    
    @FXML
    private TableColumn tcDate;
    
    @FXML
    private TableColumn tcAmount;
    
    @FXML
    private TableColumn tcBalance;
    
    @FXML
    private TableColumn tcDescription;
    
    @FXML
    private Label lblUser;
     
}
