/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sda.VetController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import TestMain.*;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Kamran Shahid
 */
public class ManageDashboardController implements Initializable 
{

    private Stage stage;
    private Scene scene;
    private Pet SelectedPet;
    private static Appointments SelectedAppoint;
    
    Controller Cont = new Controller();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void gohome(ActionEvent event) throws IOException {
        System.out.println("Redirecting to Dashboard...");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/VetViews/Dashboard.fxml"));
        Parent buyMedicineScreen = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(buyMedicineScreen);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }   


    public void setSelectedPet(Pet SelectedPet) {
        this.SelectedPet = SelectedPet;
    }

    @FXML
    private void ManageTreatment(ActionEvent event) throws IOException
    {
        System.out.println("Navigating to Manage Treatment...");
            // Redirect to Buy Medicine screen

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/VetViews/ManageTreatment.fxml"));
            Parent buyMedicineScreen = loader.load();

            ManageTreatmentController controller = loader.getController();
            controller.setSelectedPet(SelectedPet);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(buyMedicineScreen);
            stage.setScene(scene);
            stage.setTitle("Manage Treatment");
            stage.show();
    }

    @FXML
    private void ManagePrescription(ActionEvent event) throws IOException 
    {
        System.out.println("Navigating to Manage Prescription...");
            // Redirect to Buy Medicine screen

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/VetViews/ManagePrescription.fxml"));
        Parent buyMedicineScreen = loader.load();

        ManagePrescriptionController controller = loader.getController();
        controller.setSelectedPet(SelectedPet);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(buyMedicineScreen);
        stage.setScene(scene);
        stage.setTitle("Manage Prescription");
        stage.show();
    }

    @FXML
    private void ManageHistory(ActionEvent event) throws IOException 
    {
         System.out.println("Navigating to Manage History...");
            // Redirect to Buy Medicine screen

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/VetViews/ManageHistory.fxml"));
            Parent buyMedicineScreen = loader.load();

            ManageHistoryController controller = loader.getController();
            controller.setSelectedPet(SelectedPet);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(buyMedicineScreen);
            stage.setScene(scene);
            stage.setTitle("Manage History");
            stage.show();
    }

    @FXML
    private void CompleteAppointment(ActionEvent event) throws IOException 
    {
        Cont.CompleteAppointment(SelectedAppoint);
        gohome(event);
    }

    public void setSelectedAppoint(Appointments SelectedAppoint) {
        this.SelectedAppoint = SelectedAppoint;
    }
    
}
