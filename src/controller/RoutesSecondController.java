
package controller;

import model.Route;
import database.Db;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class RoutesSecondController implements Initializable {

    @FXML
    private AnchorPane routesSecond_pane;
    @FXML
    private ImageView image_routesSecond;
    @FXML
    private Button bt_close_routesSecond;
    @FXML
    private Button bt_back_routesSecond;
    @FXML
    private Button bt_main_routesSecond;
    @FXML
    private Button bt_delete_routesSecond;
    @FXML
    private TableView<Route> routesSecond_table;
    @FXML
    private TableColumn<Route, String> column_truck;
    @FXML
    private TableColumn<Route, String> column_driver;
    @FXML
    private TableColumn<Route, String> column_route;
    @FXML
    private TableColumn<Route, String> column_start;
    @FXML
    private TableColumn<Route, String> column_coming;
    
    
    @FXML
    private void closeroutesSecondButtonAction(ActionEvent event) {
        Stage stage = (Stage) bt_close_routesSecond.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void backroutesSecondButtonAction(ActionEvent event) throws IOException {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/view/Routes.fxml"));
        routesSecond_pane.getChildren().setAll(ap);
    }
    
    @FXML
    private void mainroutesSecondButtonAction(ActionEvent event) throws IOException {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/view/FXMLDocument.fxml"));
        routesSecond_pane.getChildren().setAll(ap);
    }
    
    @FXML
    private void deleteroutesSecondButtonAction(ActionEvent event) throws IOException, SQLException  {
        
        try {
            Connection conn = Db.get_connection();
            String query = "delete from route where id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            Route selectedrow = routesSecond_table.getSelectionModel().getSelectedItem();
            ps.setString(1, selectedrow.getId());
            ps.executeUpdate();
            
            updateTable();
            
            
        } catch (RuntimeException ex) {
            
            Exception e = new Exception();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Select data to delete!");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(sw.toString())));
            alert.showAndWait();

        }
          
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        updateTable();
        
    }  
    
    
    public void updateTable() {
        
        ObservableList<Route> ob = FXCollections.observableArrayList();
        
        try {
            Connection conn = Db.get_connection();
            ResultSet rs = conn.createStatement().executeQuery("select * from route");
            
            while(rs.next()){
                ob.add(new Route(rs.getString("id"),rs.getString("truck"),rs.getString("driver"),rs.getString("route_number"),rs.getString("start"),rs.getString("coming")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RoutesSecondController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        column_truck.setCellValueFactory(new PropertyValueFactory<>("truck"));
        column_driver.setCellValueFactory(new PropertyValueFactory<>("driver"));
        column_route.setCellValueFactory(new PropertyValueFactory<>("route_number"));
        column_start.setCellValueFactory(new PropertyValueFactory<>("start"));
        column_coming.setCellValueFactory(new PropertyValueFactory<>("coming"));
        
        routesSecond_table.setItems(ob);
        
    }
    
}
