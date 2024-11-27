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

import java.io.IOException;
import java.util.Objects;
import TestMain.*;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UpdatePetController {
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
    private DatePicker dobField;
    @FXML
    private Button homeButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button updateButton;

    private Pet pet;
    
    Controller Cont = new Controller();
    
    @FXML
    private void handleUpdate(ActionEvent event) throws IOException {
        String Name = petNameField.getText();
        String Breed = breedField.getText();
        String Species = speciesField.getText();
        String Age = ageField.getText();
        LocalDate DOB = dobField.getValue();
        
       if(Age.matches("^(?:[1-9]|[1-9][0-9]|1[01][0-9]|120)$") == false)
        {
            Alert successAlert = new Alert(Alert.AlertType.ERROR);
            successAlert.setTitle("Form Error");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Please enter correct value for Age.");
            successAlert.showAndWait();
            ageField.clear();
            return;
        }
        
        if(Name.matches("^[a-zA-Z]*$") == false )
        {
            Alert successAlert = new Alert(Alert.AlertType.ERROR);
            successAlert.setTitle("Form Error");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Please enter correct value for Name.");
            successAlert.showAndWait();
            petNameField.clear();
            return;
        }
        
        if(Breed.matches("^[a-zA-Z]*$") == false )
        {
            Alert successAlert = new Alert(Alert.AlertType.ERROR);
            successAlert.setTitle("Form Error");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Please enter correct value for Breed.");
            successAlert.showAndWait();
            breedField.clear();
            return;
        }
        
        if(Species.matches("^[a-zA-Z]*$") == false )
        {
            Alert successAlert = new Alert(Alert.AlertType.ERROR);
            successAlert.setTitle("Form Error");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Please enter correct value for Species.");
            successAlert.showAndWait();
            speciesField.clear();
            return;
        }
        
        if (Name.isEmpty() || Breed.isEmpty() || Species.isEmpty() || ageField.getText().isEmpty())
        {
            Alert successAlert = new Alert(Alert.AlertType.ERROR);
            successAlert.setTitle("Form Error");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Please fill in all fields.");
            successAlert.showAndWait();
            return;
        }
        
        Cont.UpdatePet(pet, Name, Breed, Species, Integer.parseInt(Age), DOB);

        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Update Successful");
        successAlert.setHeaderText(null);
        successAlert.setContentText("The pet details have been successfully updated.");
        successAlert.showAndWait();

        System.out.println("Redirecting to Dashboard...");
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/ViewAccountScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
}

    @FXML
    private void handleCancel(ActionEvent event) throws IOException {
        System.out.println("Going back...");

        Parent managePetsScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/ManagePetScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(managePetsScreen);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
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
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Views/ViewAccountScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }
    @FXML
    public void setSelectedPet(Pet selectedPet)
    {
        this.pet = selectedPet;
        petNameField.setText(pet.getName());
        breedField.setText(pet.getBreed());
        speciesField.setText(pet.getSpecies());
        ageField.setText(String.valueOf(pet.getAge()));
        dobField.setValue(pet.getDOB());
    }
}
