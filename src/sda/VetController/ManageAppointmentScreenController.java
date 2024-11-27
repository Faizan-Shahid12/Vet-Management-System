/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sda.VetController;

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
import sda.vetmanagementsystem.SelectServiceController;

/**
 * FXML Controller class
 *
 * @author Kamran Shahid
 */
public class ManageAppointmentScreenController implements Initializable
{
    private Stage stage;
    private Scene scene;
    
    @FXML
    private TableView<Appointments> AppointTable;
    @FXML
    private TableColumn<Appointments, String> PetName;
    @FXML
    private TableColumn<Appointments, String> PetBreed;
    @FXML
    private TableColumn<Appointments, String> OwnerName;
    @FXML
    private TableColumn<Appointments, String> Purpose;
    @FXML
    private TableColumn<Appointments, String> Date;
    @FXML
    private TableColumn<Appointments, String> StartTime;
    @FXML
    private TableColumn<Appointments, String> Status;
    
    Controller Cont = new Controller();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ObservableList<Appointments> AppointList = FXCollections.observableArrayList(Cont.GetVetPendingAppoints());
       
        OwnerName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getToPet().getOwnerName()));
        
        PetName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getToPet().getName()));
        
        PetBreed.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getToPet().getBreed()));
        
        Date.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getAppointDate()));
        
        Purpose.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPurpose()));
        
        StartTime.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getStartTime()));

        Status.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        
        AppointTable.setItems(AppointList);
    }    
    
    @FXML
    private void logout(ActionEvent event) throws IOException 
    {
        System.out.println("Logging out...");
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/LoginScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Login Screen");
        stage.show();
    }
    
    @FXML
    private void gohome(ActionEvent event) throws IOException 
    {
        System.out.println("Redirecting to Dashboard...");
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/VetViews/Dashboard.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }   

    @FXML
    private void Confirm(ActionEvent event) throws IOException 
    {
        Appointments App = AppointTable.getSelectionModel().getSelectedItem();
        
        if (App == null) {
            // If no Appointment is selected, show a warning alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a appointment to confirm appointment.");
            alert.showAndWait();
        } 
        else
        {
            Cont.Confirm_DeclineAppoints(App, "Confirmed");
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Appointment Confirmed");
            alert.setHeaderText(null);
            alert.setContentText("The Appointment has been Confirmed.");
            alert.showAndWait();
            
            gohome(event);
        }
    }

    @FXML
    private void Decline(ActionEvent event) throws IOException 
    {
         Appointments App = AppointTable.getSelectionModel().getSelectedItem();
        
        if (App == null) {
            // If no Appointment is selected, show a warning alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a appointment to decline appointment.");
            alert.showAndWait();
        } else
        {
            Cont.Confirm_DeclineAppoints(App, "Declined");
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment Declined");
            alert.setHeaderText(null);
            alert.setContentText("The Appointment has been Declined.");
            alert.showAndWait();
            
            gohome(event);
        }
    }
    
    
}
