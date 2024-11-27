/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sda.AdminController;

import TestMain.Controller;
import TestMain.Medicine;
import TestMain.Pharmacy;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kamran Shahid
 */
public class CheckMedicineController {

    private Stage stage;
    private Scene scene;

    @FXML
    private TableView<Medicine> tableViewMedicines;

    @FXML
    private TableColumn<Medicine, String> nameColumn;

    @FXML
    private TableColumn<Medicine, String> typeColumn;

    @FXML
    private TableColumn<Medicine, String> purposeColumn;

    @FXML
    private TableColumn<Medicine, Double> priceColumn;
    
    @FXML
    private TableColumn<Medicine, Integer> QuanCol;
    
    Controller Cont = new Controller();
    private Pharmacy Phar;
    private ObservableList<Medicine> availableMedicines = FXCollections.observableArrayList();

    
    @FXML
    private void Restock(ActionEvent event) throws IOException 
    {
        Cont.PharRestock(Phar);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Restock Completion");
        alert.setHeaderText(null);
        alert.setContentText("Pharmacy has been Successfully Restocked.");
                
        System.out.println("Redirecting to Dashboard...");
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/AdminViews/AdminDashboard.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }
    
    @FXML
    private void goback(ActionEvent event) throws IOException {
        System.out.println("Going back...");

        Parent managePetsScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/AdminViews/SelectPharmacy.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(managePetsScreen);
        stage.setScene(scene);
        stage.setTitle("Select Pharmacy");
        stage.show();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException 
    {
        System.out.println("Logging out...");
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/LoginScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Login Screen");
        stage.show();
    }
    @FXML
    private void gohome(ActionEvent event) throws IOException 
    {
        System.out.println("Redirecting to Dashboard...");
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/AdminViews/AdminDashboard.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }
    public void setSelectedPharmacy(Pharmacy P)
    {
        Phar = P;
        
        availableMedicines = FXCollections.observableArrayList(Cont.GetMedicine(Phar));
        
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));

        purposeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPurpose()));

        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        QuanCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        
        tableViewMedicines.setItems(availableMedicines);
    }
    
}
