
package controller;

import database.Db;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class DriversController implements Initializable {

    @FXML
    private AnchorPane drivers_pane;
    @FXML
    private ImageView image_drivers;
    @FXML
    private ListView<String> list_drivers;
    @FXML
    private Button bt_close_drivers;
    @FXML
    private Button bt_back_drivers;
    @FXML
    private TextField textField1_drivers;
    @FXML
    private Label label1_drivers;
    @FXML
    private Label label2_drivers;
    @FXML
    private Label label3_drivers;
    @FXML
    private TextField textField2_drivers;
    
    
    @FXML
    private void closedriversButtonAction(ActionEvent event) {
        Stage stage = (Stage) bt_close_drivers.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void backdriversButtonAction(ActionEvent event) throws IOException {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/view/FXMLDocument.fxml"));
        drivers_pane.getChildren().setAll(ap);
    }
    
    
     @FXML
    private void displaySelected(MouseEvent event) throws SQLException {
        
        String driver_name = list_drivers.getSelectionModel().getSelectedItem();
        
        Connection conn = Db.get_connection();
        PreparedStatement ps = conn.prepareStatement("select started, phone from driver where name=?");
        ps.setString(1, driver_name);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int year = rs.getInt(1);
        String ph = rs.getString(2);
        textField1_drivers.setText(String.valueOf(year));
        textField2_drivers.setText(ph);
                 
    }
    
    
    ObservableList<String> ob = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         try {
            Connection conn = Db.get_connection();
            ResultSet rs = conn.createStatement().executeQuery("select * from driver");
            
            while(rs.next()){
                String st = rs.getString("name");
                ob.add(st);  
            }
            list_drivers.getItems().addAll(ob);
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(TrucksController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
    }    

   
    
}
