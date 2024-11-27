/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sda.VetController;

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
import javafx.stage.Stage;
import TestMain.*;

/**
 * FXML Controller class
 *
 * @author Kamran Shahid
 */
public class ManageHistoryController implements Initializable 
{

    private Stage stage;
    private Scene scene;
    
    private Pet SelectedPet;
    
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
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/VetViews/Dashboard.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    } 
    @FXML
    private void godashboard(ActionEvent event) throws IOException {
         System.out.println("Redirecting to Dashboard...");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/VetViews/ManageDashboard.fxml"));
        Parent buyMedicineScreen = loader.load();

        ManageDashboardController controller = loader.getController();
        controller.setSelectedPet(SelectedPet);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(buyMedicineScreen);
        stage.setScene(scene);
        stage.setTitle("Manage Dashboard");
        stage.show();
    }  

    public void setSelectedPet(Pet SelectedPet) {
        this.SelectedPet = SelectedPet;
    }

    @FXML
    private void ViewPrescription(ActionEvent event) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/VetViews/ViewHistory.fxml"));
        Parent buyMedicineScreen = loader.load();

        ViewHistoryController controller = loader.getController();
        controller.setSelectedPet(SelectedPet);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(buyMedicineScreen);
        stage.setScene(scene);
        stage.setTitle("View History");
        stage.show();
    }

    @FXML
    private void AddPrescription(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/VetViews/AddHistory.fxml"));
        Parent buyMedicineScreen = loader.load();

        AddHistoryController controller = loader.getController();
        controller.setSelectedPet(SelectedPet);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(buyMedicineScreen);
        stage.setScene(scene);
        stage.setTitle("Create Prescription");
        stage.show();
      
    }

    @FXML
    private void DeletePrescription(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/VetViews/DeleteHistory.fxml"));
        Parent buyMedicineScreen = loader.load();

        DeleteHistoryController controller = loader.getController();
        controller.setSelectedPet(SelectedPet);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(buyMedicineScreen);
        stage.setScene(scene);
        stage.setTitle("Delete History");
        stage.show();
    }

    @FXML
    private void EditHistory(ActionEvent event) {
    }
    
    
}
