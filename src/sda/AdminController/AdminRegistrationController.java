/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sda.AdminController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import TestMain.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import javafx.scene.control.ComboBox;

public class AdminRegistrationController {

    @FXML
    private TextField fullNameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private DatePicker dobPicker;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox<String> GenderBox;
    @FXML
    private Hyperlink loginHyperlink;
    
    Controller Cont = new Controller();
    
    @FXML
    private void initialize() 
    {
        GenderBox.getItems().addAll("Male", "Female");
    }

    @FXML
    public void onRegisterButtonClicked(ActionEvent event) throws IOException  
    {
        String fullName = fullNameField.getText();
        String gender = GenderBox.getSelectionModel().getSelectedItem();
        String username = usernameField.getText();
        String email = emailField.getText();
        LocalDate dob = dobPicker.getValue();
        String password = passwordField.getText();

        if (fullName.isEmpty() || !fullName.matches("^[a-zA-Z\\s]+$")) {
            showAlert("Error", "Please enter a valid full name ", AlertType.ERROR);
            return;
        }

        if (gender.isEmpty()) {
            showAlert("Error", "Please enter a valid Gender.", AlertType.ERROR);
            return;
        }

        if (username.isEmpty() || !username.matches("^[a-zA-Z0-9]{5,15}$")) {
            showAlert("Error", "Username must be 5-15 characters and alphanumeric.", AlertType.ERROR);
            return;
        }

        if (email.isEmpty() || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            showAlert("Error", "Please enter a valid email address.", AlertType.ERROR);
            return;
        }

        if (dob == null || dob.isAfter(LocalDate.now().minusYears(18))) {
            showAlert("Error", "You must be at least 18 years old.", AlertType.ERROR);
            return;
        }

        if (password.isEmpty() || password.length() < 6) {
            showAlert("Error", "Password must be at least 8 characters long with at least 1 uppercase, 1 lowercase, 1 number, and 1 special character.", AlertType.ERROR);
            return;
        }
        
        for(String Us : Cont.GetUserName())
        {
            if(Us.equalsIgnoreCase(username))
            {
                showAlert("Error","Username already in use.",AlertType.ERROR);
                return ;
            }
        }
        for(String Us : Cont.GetEmail())
        {
            if(Us.equalsIgnoreCase(email))
            {
                showAlert("Error","Email already in use.",AlertType.ERROR);
                return ;
            }
        }
        
        Cont.RegisterAdmin(username, password, fullName, gender, Period.between(dobPicker.getValue(), LocalDate.now()).getYears(), email, dob);
        
        showAlert("Registration Success", "Your account has been created successfully!", AlertType.INFORMATION);

        resetFields();
        
        handleLoginHyperlink(event);
    }

    @FXML
    public void handleLoginHyperlink(ActionEvent event) throws IOException {
        System.out.println("Switching to login page...");

        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/LoginScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Login Screen");
        stage.show();

    }

    private void showAlert(String title, String message, AlertType alertType) {

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Stage currentStage = (Stage) fullNameField.getScene().getWindow();
        alert.initOwner(currentStage);

        alert.showAndWait();
    }

    // reset all input fields
    private void resetFields() {
        fullNameField.clear();
        usernameField.clear();
        emailField.clear();
        dobPicker.setValue(null);
        passwordField.clear();
    }
}
