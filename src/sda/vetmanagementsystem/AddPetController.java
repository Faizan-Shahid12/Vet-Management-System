package sda.vetmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import TestMain.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.*;
import javafx.scene.control.DateCell;
import javafx.util.Callback;

public class AddPetController {

    private Stage stage;
    private Scene scene;

    @FXML
    private TextField petNameField;

    @FXML
    private TextField breedField;

    @FXML
    private TextField speciesField;

    @FXML
    private TextField ageField;

    @FXML
    private DatePicker dobPicker;

    @FXML
    private Button registerButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button backButton;
    
    Controller Cont = new Controller();
    
    @FXML
    public void initialize() 
    {
         dobPicker.setDayCellFactory(new Callback<DatePicker, DateCell>() 
         {
            @Override
            public DateCell call(DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty)
                    {
                        super.updateItem(date, empty);
                        
                        if (date.isAfter(LocalDate.now()))
                        {
                            setDisable(true); 
                        }
                    }
                };
            }
        });
    }
    
    @FXML
    public void handleRegisterButton(ActionEvent event) throws IOException, ClassNotFoundException 
    {
        String petName = petNameField.getText();
        String breed = breedField.getText();
        String species = speciesField.getText();
        String age = ageField.getText();
        LocalDate dob = dobPicker.getValue();

        if(age.matches("^(?:[1-9]|[1-9][0-9]|1[01][0-9]|120)$") == false)
        {
            showAlert(Alert.AlertType.ERROR, "Form Error", "Please enter correct value for Age.");
            ageField.clear();
            return;
        }
        
        if(petName.matches("^[a-zA-Z]*$") == false )
        {
            showAlert(Alert.AlertType.ERROR, "Form Error", "Please enter correct value for Name.");
            petNameField.clear();
            return;
        }
        
        if(breed.matches("^[a-zA-Z]*$") == false )
        {
            showAlert(Alert.AlertType.ERROR, "Form Error", "Please enter correct value for Breed.");
            breedField.clear();
            return;
        }
        
        if(species.matches("^[a-zA-Z]*$") == false )
        {
            showAlert(Alert.AlertType.ERROR, "Form Error", "Please enter correct value for Species.");
            speciesField.clear();
            return;
        }
        
        if (petName.isEmpty() || breed.isEmpty() || species.isEmpty() || age.isEmpty())
        {
            showAlert(Alert.AlertType.ERROR, "Form Error", "Please fill in all fields.");
            return;
        }
        else 
        {
            Cont.RegisterNewPet(petName,breed,species,Integer.parseInt(age),dob);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Pet " + petName + " registered successfully.");
            
            
            System.out.println("Redirecting to Dashboard...");
            Parent dashboardscreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/ViewAccountScreen.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(dashboardscreen);
            stage.setScene(scene);
            stage.setTitle("Dashboard");
            stage.show();
        }
    }

    @FXML
    public void handleCancelButton(ActionEvent event) {
        // Clear all fields
        petNameField.clear();
        breedField.clear();
        speciesField.clear();
        ageField.clear();
        dobPicker.setValue(null);
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
        Parent dashboardscreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/ViewAccountScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(dashboardscreen);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }

    @FXML
    private void goback(ActionEvent event) throws IOException {
        System.out.println("Going back...");

        Parent managePetsScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/ManagePetScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(managePetsScreen);
        stage.setScene(scene);
        stage.setTitle("Manage Pet");
        stage.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
