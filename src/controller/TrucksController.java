
package controller;

import model.Truck;
import database.Db;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class TrucksController implements Initializable {

    @FXML
    private AnchorPane trucks_pane;
    @FXML
    private TableView<Truck> trucks_table;
    @FXML
    private TableColumn<Truck, String> column_id;
    @FXML
    private TableColumn<Truck, String> column_model;
    @FXML
    private TableColumn<Truck, String> column_year;
    @FXML
    private TableColumn<Truck, String> column_registration;
    @FXML
    private TableColumn<Truck, String> column_capacity;
    @FXML
    private TableColumn<Truck, String> column_size;
    
    ObservableList<Truck> ob = FXCollections.observableArrayList();
    
    @FXML
    private ImageView image_trucks;
    @FXML
    private Label label1_trucks;
    @FXML
    private Label label2_trucks;
    @FXML
    private Label label3_trucks;
    @FXML
    private Label label3_trucks1;
    @FXML
    private Button bt_close_trucks;
    @FXML
    private Button bt_back_trucks;
    
    @FXML
    private void closetrucksButtonAction(ActionEvent event) {
        Stage stage = (Stage) bt_close_trucks.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void backtrucksButtonAction(ActionEvent event) throws IOException {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/view/FXMLDocument.fxml"));
        trucks_pane.getChildren().setAll(ap);
    }
    



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        try {
            Connection conn = Db.get_connection();
            ResultSet rs = conn.createStatement().executeQuery("select * from truck");
            
            while(rs.next()){
                ob.add(new Truck(rs.getString("id"),rs.getString("model"),rs.getString("year"),rs.getString("registration"),rs.getString("capacity"),rs.getString("size")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TrucksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        column_model.setCellValueFactory(new PropertyValueFactory<>("model"));
        column_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        column_registration.setCellValueFactory(new PropertyValueFactory<>("registration"));
        column_capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        column_size.setCellValueFactory(new PropertyValueFactory<>("size"));
        
        trucks_table.setItems(ob);
        
        
    }    
    
}
