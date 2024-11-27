/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sda.VetController;

import TestMain.Controller;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sda.vetmanagementsystem.SelectServiceController;

/**
 * FXML Controller class
 *
 * @author Kamran Shahid
 */
public class DashboardController {

    private Stage stage;
    private Scene scene;
    
    Controller Cont = new Controller();
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
    
    @FXML
    private void initialize() 
    {
        ObservableList<Appointments> AppointList = FXCollections.observableArrayList(Cont.GetVetConfirmedAppoint());
       
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
    
    private void gohome(ActionEvent event) throws IOException {
        System.out.println("Redirecting to Dashboard...");
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/VetViews/Dashboard.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }   
    
    @FXML
    private void GoFeedBack(ActionEvent event) throws IOException
    {
        System.out.println("Navigating to Go FeedBack...");
        
        Parent managePetsScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/VetViews/Feedback.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(managePetsScreen);
        stage.setScene(scene);
        stage.setTitle("View FeedBack");
        stage.show();
    }
    @FXML
    private void ManageAppointment(ActionEvent event) throws IOException 
    {
        System.out.println("Navigating to Manage Appointment...");
        // Redirect to Schedule Appointment screen
        Parent appointmentScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/VetViews/ManageAppointmentScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(appointmentScreen);
        stage.setScene(scene);
        stage.setTitle("Manage Appointment");
        stage.show();
    }

    @FXML
    private void ManageDashboard(ActionEvent event) throws IOException 
    {
         if(AppointTable.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select an Appointment to Continue.");
            alert.showAndWait();
        }
        else
        {
            System.out.println("Navigating to Manage Dashboard...");
            // Redirect to Buy Medicine screen

            Pet SelectedPet = AppointTable.getSelectionModel().getSelectedItem().getToPet();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/VetViews/ManageDashboard.fxml"));
            Parent buyMedicineScreen = loader.load();

            ManageDashboardController controller = loader.getController();
            controller.setSelectedPet(SelectedPet);
            controller.setSelectedAppoint(AppointTable.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(buyMedicineScreen);
            stage.setScene(scene);
            stage.setTitle("Manage Dashboard");
            stage.show();
       }
    }
    
}
