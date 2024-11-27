package sda.vetmanagementsystem;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import TestMain.*;
import java.io.IOException;
import java.util.Objects;
import javafx.scene.control.Label;

public class ViewAccountController {
    private Stage stage;
    private Scene scene;
    @FXML
    private Label UserName;
    @FXML
    private Label Name;
    @FXML
    private Label Age;
    @FXML
    private Label Contact;
    @FXML
    private Label DOB;
    @FXML
    private Label Email;
    
    Controller Cont = new Controller();
    
    @FXML
    private void initialize() 
    {
       PetOwner Owner = Cont.getOwner();
       UserName.setText(UserName.getText()+Owner.getUsername());
       Name.setText(Name.getText()+Owner.getName());
       Age.setText(Age.getText()+Integer.toString(Owner.getAge()));
       Contact.setText(Contact.getText()+Owner.getContact());
       DOB.setText(DOB.getText()+Owner.getDOB().toString());
       Email.setText(Email.getText()+Owner.getEmail());

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
    private void managePets(ActionEvent event) throws IOException
    {
        System.out.println("Navigating to Manage Pets...");

        Parent managePetsScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/ManagePetScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(managePetsScreen);
        stage.setScene(scene);
        stage.setTitle("Manage Pets");
        stage.show();
    }

    @FXML
    private void scheduleAppt(ActionEvent event) throws IOException 
    {
        System.out.println("Navigating to Schedule Appointment...");
        // Redirect to Schedule Appointment screen
        Parent appointmentScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/BookApptScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(appointmentScreen);
        stage.setScene(scene);
        stage.setTitle("Schedule Appointment");
        stage.show();
    }

    @FXML
    private void buyMedicine(ActionEvent event) throws IOException 
    {
        System.out.println("Navigating to Buy Medicine...");
        // Redirect to Buy Medicine screen
        Parent buyMedicineScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/BuyMedicineScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(buyMedicineScreen);
        stage.setScene(scene);
        stage.setTitle("Buy Medicine");
        stage.show();
    }

    @FXML
    private void giveFeedback(ActionEvent event) throws IOException 
    {
        System.out.println("Navigating to Give Feedback...");
        // Redirect to Feedback screen
        Parent feedbackScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/FeedbackScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(feedbackScreen);
        stage.setScene(scene);
        stage.setTitle("Give Feedback");
        stage.show();
    }

    @FXML
    private void trackMedicalHistory(ActionEvent event) throws IOException 
    {
        System.out.println("Navigating to Track Medical History...");
        // Redirect to Medical History screen
        Parent medicalHistoryScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/TrackMedicalHistoryScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(medicalHistoryScreen);
        stage.setScene(scene);
        stage.setTitle("Medical History");
        stage.show();
    }

    @FXML
    private void daycareService(ActionEvent event) throws IOException 
    {
        System.out.println("Navigating to Daycare Service...");
        // Redirect to Daycare Service screen
        Parent daycareScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/ViewDaycareOptions.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(daycareScreen);
        stage.setScene(scene);
        stage.setTitle("Daycare Service");
        stage.show();
    }
    
    @FXML
    private void upcomingappt(ActionEvent event) throws IOException {
        System.out.println("Navigating to Upcoming Appointment...");
        Parent appointmentScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/UpcomingAppointments.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(appointmentScreen);
        stage.setScene(scene);
        stage.setTitle("Upcoming Appointment");
        stage.show();
    }

}
