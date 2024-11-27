package sda.vetmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import TestMain.*;
import java.time.LocalDate;
import javafx.scene.input.MouseEvent;

public class SelectServiceController {
    private Stage stage;
    private Scene scene;

    @FXML
    private ComboBox<String> selectservicebox;
    @FXML
    private ComboBox<String> selecttimebox;
    
    Pet SelectedPet;
    
    Veterinarian SelectedVet;
    
    Controller Cont = new Controller();
    
    @FXML
    private ComboBox<LocalDate> selectDatebox;

    @FXML
    private void bookAppt(ActionEvent event) throws IOException {
        String service = selectservicebox.getValue();
        String timeSlot = selecttimebox.getSelectionModel().getSelectedItem();
        LocalDate Date = selectDatebox.getSelectionModel().getSelectedItem();
        
        if (service == null || timeSlot == null || Date == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incomplete Selection");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Please select both a service and a time slot.");
            alert.showAndWait();
        }
        else
        {
            Slot SelectedSlot = null;
    
            for(Slot Sl : Cont.GetSlot())
            {
                if(Sl.getDate().equals(Date) && Sl.getTime().equals(timeSlot))
                {
                    SelectedSlot = Sl;
                    break;
                }
            }
            if (SelectedSlot == null) 
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Incomplete Selection");
                alert.setHeaderText("Missing Information");
                alert.setContentText("Please select both a correct Slot");
                alert.showAndWait();
                return;
            }
            
            Cont.ScheduleAppointment(SelectedVet, SelectedPet, SelectedSlot.getSlotId(), Date, service, timeSlot);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment Booked");
            alert.setHeaderText("Success");
            alert.setContentText("Your appointment for " + service + " at " + timeSlot + " has been booked.");
            alert.showAndWait();
            
            System.out.println("Redirecting to Dashboard...");
            Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/ViewAccountScreen.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(loginScreen);
            stage.setScene(scene);
            stage.setTitle("Dashboard");
            stage.show();
        }
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        System.out.println("Going back...");
        Parent managePetsScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/BookApptScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(managePetsScreen);
        stage.setScene(scene);
        stage.setTitle("Schedule Appointment");
        stage.show();
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
    
    public void setSelectedVet(Veterinarian V)
    {
        this.SelectedVet = V;
        
        ObservableList<String> serviceOptions = FXCollections.observableArrayList(SelectedVet.getSpecialization());
        
        ObservableList<LocalDate> DateOptions = FXCollections.observableArrayList();
            
        for(Slot Sl : Cont.GetSlot())
        {
            if(!DateOptions.contains(Sl.getDate()))
            {
                DateOptions.add(Sl.getDate());
            }
        }
        
        selectservicebox.setItems(serviceOptions);
        selectDatebox.setItems(DateOptions);
        selectDatebox.getSelectionModel().select(0);
        selectservicebox.getSelectionModel().select(0);
    }
    
    public void setSelectedPet(Pet P)
    {
        this.SelectedPet = P;
    }

    @FXML
    private void TimeSlotIntialize(MouseEvent event)
    {
        LocalDate SelectedDate = selectDatebox.getValue();
        selecttimebox.getItems().clear();
        
        if(SelectedDate != null)
        {
            ObservableList<String> TimeSlotOptions = FXCollections.observableArrayList();
            
            for(Slot Sl : Cont.GetSlot())
            {
                if(Sl.isIsAvail() == 1 && !TimeSlotOptions.contains(Sl.getTime()) && Sl.getDate().equals(SelectedDate))
                {
                    TimeSlotOptions.add(Sl.getTime());
                }
            }
            
                selecttimebox.setItems(TimeSlotOptions);
                selecttimebox.getSelectionModel().select(0);
        }

    }
}
