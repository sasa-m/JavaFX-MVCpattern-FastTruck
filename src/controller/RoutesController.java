
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class RoutesController implements Initializable {

    @FXML
    private AnchorPane routes_pane;
    @FXML
    private ImageView image_routes;
    @FXML
    private ComboBox<String> combo1_routes;
    @FXML
    private Label label1_routes;
    @FXML
    private Label label2_routes;
    @FXML
    private ComboBox<String> combo2_routes;
    @FXML
    private Label label3_routes;
    @FXML
    private TextField textField1_routes;
    @FXML
    private DatePicker date1_routes;
    @FXML
    private DatePicker date2_routes;
    @FXML
    private Label label4_routes;
    @FXML
    private Label label5_routes;
    @FXML
    private Button bt_close_routes;
    @FXML
    private Button bt_back_routes;
    @FXML
    private Button bt_submit_routes;
    @FXML
    private TextField textField2_routes;
    
    
    @FXML
    private void closeroutesButtonAction(ActionEvent event) {
        Stage stage = (Stage) bt_close_routes.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void backroutesButtonAction(ActionEvent event) throws IOException {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/view/FXMLDocument.fxml"));
        routes_pane.getChildren().setAll(ap);
    }
    
    @FXML
    private void submitroutesButtonAction(ActionEvent event) throws IOException, SQLException {

        try {
            Connection conn = Db.get_connection();
            String query = "insert into route (id,truck,driver,route_number,start,coming) values(null,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, combo1_routes.getSelectionModel().getSelectedItem());
            ps.setString(2, combo2_routes.getSelectionModel().getSelectedItem());
            ps.setInt(3, Integer.parseInt(textField1_routes.getText()));
            ps.setString(4, ((String) date1_routes.getEditor().getText()));
            ps.setString(5, ((String) date2_routes.getEditor().getText()));
            ps.execute();

            AnchorPane ap = FXMLLoader.load(getClass().getResource("/view/RoutesSecond.fxml"));
            routes_pane.getChildren().setAll(ap);

        } catch (RuntimeException ex) {
            AnchorPane ap = FXMLLoader.load(getClass().getResource("/view/FXMLDocument.fxml"));
            routes_pane.getChildren().setAll(ap);
        }

    }
    
    @FXML
    private void scheduleroutesButtonAction(ActionEvent event) throws IOException {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/view/RoutesSecond.fxml"));
        routes_pane.getChildren().setAll(ap);
    }
        
   
    @FXML
    private void textFieldKeyTyped(KeyEvent event) {
        if (event.getCharacter().matches("[^\\e\t\r\\d+$]")) {
        event.consume();
        textField2_routes.setText("WARNING! Enter numbers only. ");
        textField2_routes.setStyle("-fx-text-inner-color: red;");
       }
    }
    
    ObservableList list1 = FXCollections.observableArrayList();
    ObservableList list2 = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         try {
            loadTrucks();
            loadDrivers();
        } catch (SQLException ex) {
            Logger.getLogger(RoutesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    private void loadTrucks() throws SQLException{
        Connection conn = Db.get_connection();
        String query = "select registration from truck";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            list1.add(rs.getString("registration"));
        }
        ps.close();
        rs.close();
        
        combo1_routes.getItems().addAll(list1);
        combo1_routes.setStyle("-fx-font-size: 14px;");
    }
    
    private void loadDrivers() throws SQLException{
        Connection conn = Db.get_connection();
        String query = "select name from driver";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            list2.add(rs.getString("name"));
        }
        ps.close();
        rs.close();
        
        combo2_routes.getItems().addAll(list2);
        combo2_routes.setStyle("-fx-font-size: 14px;");
    }

        
    }    
    

