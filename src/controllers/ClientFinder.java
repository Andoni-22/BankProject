/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Customer;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.ws.rs.core.GenericType;
import servicesRestfull.CustomerService;

/**
 *
 * @author Andoni
 */
public class ClientFinder {
    
    
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnClose;
    
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfLastName;
    
    @FXML
    private Label lblIdNotFound;
    @FXML
    private Label lblLastNameNotFOund;
    
 
    
    private Customer customer;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initStage(Parent root) {
        Scene clientScene = new Scene(root);
        stage = new Stage();
        stage.setScene(clientScene);
        stage.setResizable(false);
        stage.setTitle("Bank statment");
        stage.show();
        
        lblIdNotFound.setVisible(false);
        lblLastNameNotFOund.setVisible(false);
         
        
        stage.setOnCloseRequest((WindowEvent event) -> {
            event.consume();
            onClose();
        });
        
        tfID.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
            tfID.setText(newValue.replaceAll("[^\\d]", ""));
        }
            }
        
        });
        
        btnClose.setOnAction((event) ->{
            onClose();
        });
        
        btnSearch.setOnAction((event) ->{
            lblIdNotFound.setVisible(false);
            lblLastNameNotFOund.setVisible(false);
            String auxId, name;
     
            auxId = tfID.getText().toString();
            
            name = tfLastName.getText().toString();
            if(name.length()==0 && auxId.length()==0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error, you have to insert id or name");
                alert.initOwner(stage);
                Optional<ButtonType> result = alert.showAndWait();
            }else{
                if(auxId.length()>0 && name.length()>0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error, you can olny search by id or name, no both");
                    alert.initOwner(stage);
                    Optional<ButtonType> result = alert.showAndWait();
                }else{
                    if(name.length()>0){
                        String auxName = tfLastName.getText().toString();
                        
                        CustomerService client = new CustomerService();
                        Set<Customer>custList = client.findAll(new GenericType<Set<Customer>>() {});
                        ArrayList<Customer> list = new ArrayList(custList);
                        ArrayList<Customer> listCustomer = new ArrayList();
                        for(int i=0; i<list.size(); i++){
                            if(list.get(i).getLastName().contains(name)==true){
                                listCustomer.add(list.get(i));
                            }
                        }
                        
                        if(listCustomer.size()==0){
                            lblLastNameNotFOund.setVisible(true);
                        }else{
                            try {
                                openWindowChooser(listCustomer);
                            } catch (IOException ex) {
                                Logger.getLogger(ClientFinder.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        
                        
                        
                    }else if(auxId.length()>0){
                        Long id = Long.parseLong(auxId);
                        if(id<=0){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Error, id must be more than 0");
                            alert.initOwner(stage);
                            Optional<ButtonType> result = alert.showAndWait();
                        }else{
                            customer = new Customer();
                            try {
                                CustomerService client = new CustomerService();
                                customer = client.find_XML(Customer.class, auxId);
                                if(customer!=null){
                                    openWindowData(customer);
                                }else{
                                    lblIdNotFound.setVisible(true);
                                }
                            } catch (NullPointerException ex) {
                                lblIdNotFound.setVisible(true);
                            } catch (IOException ex) {
                                Logger.getLogger(ClientFinder.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    
                }
            }
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

    private void openWindowData(Customer customer) throws IOException {
        ClientData controller = new ClientData();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlWindows/clientData.fxml"));
        Parent root = (Parent) loader.load();
        controller = loader.getController();
        controller.initStage(root, customer);
        stage.close();    
    }
    
     private void openWindowChooser(ArrayList<Customer> customer) throws IOException {
        ClientChooser controller = new ClientChooser();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlWindows/clientChooser.fxml"));
        Parent root = (Parent) loader.load();
        controller = loader.getController();
        controller.initStage(root, customer);
        stage.close();    
    } 
}
