/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sda.AdminController;

import TestMain.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kamran Shahid
 */
public class ManageVetController 
{

   private Stage stage;
    private Scene scene;
    @FXML
    private TableView<Veterinarian> AdminTable;
    @FXML
    private TableColumn<Veterinarian, String> Ucol;
    @FXML
    private TableColumn<Veterinarian, String> NamCol;
    @FXML
    private TableColumn<Veterinarian, String> GenCol;
    @FXML
    private TableColumn<Veterinarian, String> EmCol;
    @FXML
    private TableColumn<Veterinarian, String> TypeCol;
    @FXML
    private TableColumn<Veterinarian, String> StatCol;
    
    Controller Cont = new Controller();
    
    public void initialize() 
    {
        ObservableList<Veterinarian> AdminList = FXCollections.observableArrayList();
        
        for (Veterinarian Adm : Cont.GetVets())
        {
            if("Pending".equals(Adm.getStatus()))
            {
                AdminList.add(Adm);
            }
        }
        
        Ucol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        
        NamCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        
        GenCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGender()));
        
        EmCol.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getEmail()));
        
        TypeCol.setCellValueFactory(cellData -> new SimpleStringProperty("Veterinarian"));

        StatCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        
        AdminTable.setItems(AdminList);
    }    
    
    @FXML
    private void Delete(ActionEvent event)
    {
        Veterinarian SelectedVet = AdminTable.getSelectionModel().getSelectedItem();
        
        if(SelectedVet != null)
        {
            Cont.DeleteVet(SelectedVet);
            
            showAlert("Deletion Successful","The Unauthenticated Veterinarian has been deleted");
            
            initialize();
        }
        else
        {
            showAlert("No Selection","Please Select a entry from the table to delete");
        }
    }
    
    @FXML
    private void logout(ActionEvent event) throws IOException {
        System.out.println("Logging out...");
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/LoginScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Login Screen");
        stage.show();
    }
    @FXML
    private void gohome(ActionEvent event) throws IOException {
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

        Parent managePetsScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/AdminViews/ManageAccounts.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(managePetsScreen);
        stage.setScene(scene);
        stage.setTitle("ManageAccounts");
        stage.show();
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void Authenticate(ActionEvent event)
    {
        Veterinarian SelectedVet = AdminTable.getSelectionModel().getSelectedItem();
        
        if(SelectedVet != null)
        {
            Cont.AuthenticateVet(SelectedVet);
            
            showAlert("Authentication Successful","The Unauthenticated Veterinarian has been Authenticated");
            
            initialize();
        }
        else
        {
            showAlert("No Selection","Please Select a entry from the table to Authenticate");
        }
    }
    
}
