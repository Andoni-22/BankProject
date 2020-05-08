/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankproject;

import controllers.ClientFinder;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Andoni
 */
public class BankProject extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
    
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/fxmlWindows/clientFinder.fxml"));
        Parent root = (Parent) loader.load();
        ClientFinder controller = (ClientFinder) loader.getController();
        controller.setStage(stage);
        controller.initStage(root);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
