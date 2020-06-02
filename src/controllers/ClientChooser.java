/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Andoni
 */
public class ClientChooser {
    
    @FXML
    private Button btnCancel;
    
    @FXML
    private TableView tvClients;
    
    @FXML
    private TableColumn tcId;
    
    @FXML
    private TableColumn tcFullName;
    
    @FXML 
    private TableColumn tcEmail;
            
    
}
