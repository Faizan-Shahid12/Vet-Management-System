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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kamran Shahid
 */
public class ManageMedicalStuffController implements Initializable
{

    private Stage stage;
    private Scene scene;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    @FXML
    private void MedicineForm(ActionEvent event) throws IOException 
    {
        System.out.println("Redirecting to Add Medicine...");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/AdminViews/AddMedicineForm.fxml"));
        Parent loginScreen = loader.load();
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Add Medicine");
        stage.show();
    }
    
    @FXML
    private void SelectPhar(ActionEvent event) throws IOException
    {
        System.out.println("Redirecting to Select Pharmacy...");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/AdminViews/SelectPharmacy.fxml"));
        Parent loginScreen = loader.load();
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Select Pharmacy");
        stage.show();
    }

    @FXML
    private void SelectMedicine(ActionEvent event) throws IOException
    {
        System.out.println("Redirecting to Delete Medicine...");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/AdminViews/DeleteMedicine.fxml"));
        Parent loginScreen = loader.load();
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Delete Medicine");
        stage.show();
    }
    
}
