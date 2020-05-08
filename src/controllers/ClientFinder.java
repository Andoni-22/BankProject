/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.Optional;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
        
        btnClose.setOnAction((event) ->{
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
    
}
