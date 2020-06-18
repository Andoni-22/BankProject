/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Account;
import entity.Customer;
import entity.Movement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.ws.rs.core.GenericType;
import servicesRestfull.AccountService;
import servicesRestfull.MovementsService;

/**
 *
 * @author Andoni
 */
public class ClientData {
    
    @FXML 
    private Button btnClose;
    
    @FXML
    private Label lblUser;
    
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

    
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    
     public void initStage(Parent root, Customer customer) {
        Scene clientScene = new Scene(root);
        stage = new Stage();
        stage.setScene(clientScene);
        stage.setResizable(false);
        stage.setTitle("Choose a client");
        stage.show();
        
        tfCity.setEditable(false);
        tfEmail.setEditable(false);
        tfInitial.setEditable(false);
        tfPhone.setEditable(false);
        tfState.setEditable(false);
        tfStreet.setEditable(false);
        tfZip.setEditable(false);
                
        
        insertAccountCombo(customer);
        insterCustomerData(customer);
        insertTableView();
        
        comboBoxAccounts.getSelectionModel().selectedItemProperty().addListener(this::handleOnSelectAccount);
        
        
        btnClose.setOnAction((event) ->{
            onClose();
        });
        stage.setOnCloseRequest((WindowEvent event) -> {
            event.consume();
            onClose();
        });
        
            
    }

    private void onClose() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("¿Está seguro que desea salir de la aplicación?");
        alert.initOwner(stage);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();              
        }
    }

    private void insertAccountCombo(Customer customer) {
        
        AccountService accountService = new AccountService();
        Set<Account>accountList = accountService.findAll(new GenericType<Set<Account>>() {});
        if(accountList.size()==0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No accounts found");
            alert.setHeaderText("The selected customer doesn´t have accounts");
            alert.initOwner(stage);
            Optional<ButtonType> result = alert.showAndWait();
        
        }else{
             ArrayList<Account> auxList = new ArrayList(accountList);
            ArrayList<Account> list = new ArrayList();
            ArrayList<Customer>cust = new ArrayList();
            System.out.println(auxList.size());
            Long id = customer.getId();

            for(int i = 0; i<auxList.size(); i++){
                cust = new ArrayList<Customer>(auxList.get(i).getCustomers());
                System.out.println(cust.size());
                for(int j = 0; j<cust.size(); j++){
                    if(id.equals(cust.get(j).getId())){
                        list.add(auxList.get(i));
                    }
                }
            }
            ArrayList <String> accountString = new ArrayList();
            for(int i = 0; i<list.size();i++){
                accountString.add(list.get(i).getId().toString());
            }
            System.out.println(list.size());
            comboBoxAccounts.setItems(FXCollections.observableList(accountString));
            comboBoxAccounts.getSelectionModel().selectFirst();   
        }   
    }

    private void insterCustomerData(Customer customer) {
        String label = "#"+customer.getId().toString()+" // "+customer.getFirstName()+" "+customer.getLastName();
        lblUser.setText(label);
        tfCity.setText(customer.getCity());
        tfEmail.setText(customer.getEmail());
        tfInitial.setText(customer.getMiddleInitial());
        tfPhone.setText(customer.getPhone().toString());
        tfState.setText(customer.getState());
        tfStreet.setText(customer.getState());
        tfZip.setText(customer.getZip().toString());
    }    
    
    public void handleOnSelectAccount(ObservableValue<Object> observable,
                                        Object oldValue,Object newValue){
        insertTableView();   
    }

    private void insertTableView() {
        
        String id = comboBoxAccounts.getSelectionModel().getSelectedItem().toString();
        
        MovementsService client = new MovementsService();
        
        Set<Movement>movList = client.findMovementByAccount_XML(new GenericType<Set<Movement>>() {}, id);
                
        ArrayList<Movement> list = new ArrayList(movList);
        
        if(list.size()==0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not data found");
            alert.setHeaderText("The selected account doesn´t have movements");
            alert.initOwner(stage);
            Optional<ButtonType> result = alert.showAndWait();
        
        }else{
            tcId.setCellValueFactory(
                new PropertyValueFactory("id"));
            tcDate.setCellValueFactory(
                new PropertyValueFactory("timestamp"));
            tcAmount.setCellValueFactory(
                new PropertyValueFactory("amount"));
            tcBalance.setCellValueFactory(
                new PropertyValueFactory("balance"));
            tcDescription.setCellValueFactory(
                new PropertyValueFactory("description"));
        
            ObservableList<Movement>oList = FXCollections.observableArrayList(list);
            tableViewMovements.setItems(oList);
        }   
    }  
}
