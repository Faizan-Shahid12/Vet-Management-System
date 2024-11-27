/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sda.vetmanagementsystem;

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
import java.time.LocalDate;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Kamran Shahid
 */
public class UpcomingAppointmentsController implements Initializable 
{

    private Stage stage;
    private Scene scene;
    @FXML
    private TableView<Appointments> AppointTable;
    @FXML
    private TableColumn<Appointments, String> VetNameCol;
    @FXML
    private TableColumn<Appointments, String> PetNameCol;
    @FXML
    private TableColumn<Appointments, String> breedcol;
    @FXML
    private TableColumn<Appointments, LocalDate> Datecol;
    @FXML
    private TableColumn<Appointments, String> Statuscol;
    @FXML
    private TableColumn<Appointments ,String> TimeCol;
    
    Controller Cont = new Controller();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ObservableList<Appointments> AppointList = FXCollections.observableArrayList(Cont.GetAppoint());
        
        VetNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVet().getName()));
        
        PetNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getToPet().getName()));
        
        breedcol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getToPet().getBreed()));
        
        Datecol.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getAppointDate()));
        
        TimeCol.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getStartTime()));

        Statuscol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        
        AppointTable.setItems(AppointList);
    }    
    
    @FXML
    private void logout(ActionEvent event) throws IOException {
        System.out.println("Logging out...");
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/LoginScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Login Screen");
        stage.show();
    }
    @FXML
    private void gohome(ActionEvent event) throws IOException {
        System.out.println("Redirecting to Dashboard...");
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/ViewAccountScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }
}
