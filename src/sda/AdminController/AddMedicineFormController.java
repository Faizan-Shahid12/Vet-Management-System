package sda.AdminController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import TestMain.*;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyEvent;

public class AddMedicineFormController 
{
    private Stage stage;
    private Scene scene;
    
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button backButton;

    Controller Cont = new Controller();
    @FXML
    private Button logoutButton;
    @FXML
    private Button homeButton;
    @FXML
    private Label nameLabel;
    @FXML
    private Label startdateLabel;
    @FXML
    private Label total;
    @FXML
    private Label enddateLabel;
    @FXML
    private Label specialinstrucLabel;
    @FXML
    private TextField PriceName;
    @FXML
    private TextField MedicineName;
    @FXML
    private TextField TypeName;
    @FXML
    private TextField PurposeName;

    public void initialize() 
    {
       
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        System.out.println("Cancel button clicked. Clearing the form.");
        
        Parent managePetsScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/AdminViews/ManageMedicalStuff.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(managePetsScreen);
        stage.setScene(scene);
        stage.setTitle("Manage Medical Stuff");
        stage.show();
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void AddMedicine(ActionEvent event) 
    {
        if(MedicineName.getText().isEmpty() || !MedicineName.getText().matches("^[A-Za-z ]+$"))
        {
            showAlert("Invalid Input", "Please Renter Medicine Name");
            return;
        }
        if(TypeName.getText().isEmpty() || !TypeName.getText().matches("^[A-Za-z ]+$"))
        {
            showAlert("Invalid Input", "Please Renter Type of Medicine");
            return;
        }
        if(PurposeName.getText().isEmpty() || !PurposeName.getText().matches("^[A-Za-z ]+$"))
        {
            showAlert("Invalid Input", "Please Renter Purpose of Medicine");
            return;
        }
        if(PriceName.getText().isEmpty() || !PriceName.getText().matches("^[+]?\\d+(\\.\\d+)?$"))
        {
            showAlert("Invalid Input", "Please Renter Price of Medicine");
            return;
        }
        
        Cont.AddMedicine(MedicineName.getText().trim(), TypeName.getText().trim(), PurposeName.getText().trim(), Double.parseDouble(PriceName.getText().trim()));
        
        try 
        {
            gohome(event);
        }
        catch (IOException ex)
        {
            Logger.getLogger(AddMedicineFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void AddinCombo(KeyEvent event) {
    }
}
