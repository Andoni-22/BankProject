/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Customer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Andoni
 */
public class ClientChooser {
    
    @FXML
    private Button btnCancel;
    
    @FXML
    private Button btnSelect;
    
    @FXML
    private TableView tvClients;
    
    @FXML
    private TableColumn tcId;
    
    @FXML
    private TableColumn tcName;
    
    @FXML
    private TableColumn tcLastName;
    
    @FXML 
    private TableColumn tcEmail;
    
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    
     public void initStage(Parent root, ArrayList<Customer> customer) {
        Scene clientScene = new Scene(root);
        stage = new Stage();
        stage.setScene(clientScene);
        stage.setResizable(false);
        stage.setTitle("Choose a client");
        stage.show();
        
        llenarTabla(customer);  
        
        btnCancel.setOnAction((event) ->{
            onClose();
        });
        
        btnSelect.setOnAction((event) ->{
            try {
                onSelect();
            } catch (IOException ex) {
                Logger.getLogger(ClientChooser.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        stage.setOnCloseRequest((WindowEvent event) -> {
            event.consume();
            onClose();
        });
    }

    private void llenarTabla(ArrayList<Customer> customer) {
        
        tcId.setCellValueFactory(
            new PropertyValueFactory("id"));
        tcName.setCellValueFactory(
            new PropertyValueFactory("firstName"));
        tcLastName.setCellValueFactory(
            new PropertyValueFactory("lastName"));
        tcEmail.setCellValueFactory(
            new PropertyValueFactory("email"));
        
        ObservableList<Customer>oList = FXCollections.observableArrayList(customer);
        tvClients.setItems(oList);
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

    private void onSelect() throws IOException {
        
        Customer cust = (Customer) tvClients.getSelectionModel().getSelectedItem();
        if(cust!=null){
            System.out.println(cust.getId());
            ClientData controller = new ClientData();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlWindows/clientData.fxml"));
            Parent root = (Parent) loader.load();
            controller = loader.getController();
            controller.initStage(root, cust);
            stage.close(); 
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("You need to select a customer");
            alert.initOwner(stage);
            Optional<ButtonType> result = alert.showAndWait();
        }
        
    }
}
