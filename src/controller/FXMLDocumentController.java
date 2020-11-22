
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private AnchorPane main_pane;
    @FXML
    private Button bt_main_close;
    @FXML
    private ImageView image_main;
    @FXML
    private Button bt_main_trucks;
    @FXML
    private Button bt_main_drivers;
    @FXML
    private Button bt_main_route;
    
    @FXML
    private void closeButtonAction(ActionEvent event) {
        Stage stage = (Stage) bt_main_close.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void trucksButtonAction(ActionEvent event) throws IOException {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/view/Trucks.fxml"));
        main_pane.getChildren().setAll(ap);
    }
    
     @FXML
    private void driversButtonAction(ActionEvent event) throws IOException {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/view/Drivers.fxml"));
        main_pane.getChildren().setAll(ap);
    }
    
    @FXML
    private void routesButtonAction(ActionEvent event) throws IOException {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/view/Routes.fxml"));
        main_pane.getChildren().setAll(ap);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Fast Truck Application");
    }    

    
    
}
