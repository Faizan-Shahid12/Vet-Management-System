/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sda.AdminController;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import TestMain.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * FXML Controller class
 *
 * @author Kamran Shahid
 */
public class AdminDashboardController implements Initializable 
{
    private Stage stage;
    private Scene scene;
    @FXML
    private TableView<Admin> AdminTable;
    @FXML
    private TableColumn<Admin, String> Ucol;
    @FXML
    private TableColumn<Admin, String> NamCol;
    @FXML
    private TableColumn<Admin, String> GenCol;
    @FXML
    private TableColumn<Admin, String> EmCol;
    @FXML
    private TableColumn<Admin, String> TypeCol;
    @FXML
    private TableColumn<Admin, String> StatCol;
    
    Controller Cont = new Controller();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ObservableList<Admin> AdminList = FXCollections.observableArrayList();
        
        for (Admin Adm : Cont.GetAdmin())
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
        
        TypeCol.setCellValueFactory(cellData -> new SimpleStringProperty("Admin"));

        StatCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        
        AdminTable.setItems(AdminList);
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
    private void manageaccount(ActionEvent event) throws IOException {
        System.out.println("Redirecting to manageaccount...");
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/AdminViews/ManageAccounts.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Manage Accounts");
        stage.show();
    }
    @FXML
    private void managepharmacy(ActionEvent event) throws IOException {        System.out.println("Redirecting to manage Medical Stuff...");
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/AdminViews/ManageMedicalStuff.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Manage Medical Stuff");
        stage.show();
    }
}
