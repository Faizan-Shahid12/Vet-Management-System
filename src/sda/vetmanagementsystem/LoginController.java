package sda.vetmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import TestMain.*;
import java.util.ArrayList;

public class LoginController {

    public Hyperlink registrationhyperlink;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private ComboBox<String> role;

    @FXML
    private Button loginbutton;

    @FXML
    private Hyperlink registerHyperlink;

    String Approval = "";
    
    @FXML
    private void initialize() 
    {
        role.getItems().addAll("Pet Owner", "Veterinarian","Admin");
    }

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        String user = username.getText();
        String pass = password.getText();
        String selectedRole = role.getValue();
        
        if (user.isEmpty() || pass.isEmpty() || selectedRole == null) 
        {
            showAlert(Alert.AlertType.WARNING, "Missing Fields", "Please fill all the fields!");
            
        }
        else if (validateCredentials(user, pass, selectedRole)) 
        {
            showAlert(Alert.AlertType.INFORMATION, "Login Successful",
                    "Welcome, " + user + "! Redirecting to your dashboard...");

            Parent dashboard = null;
            if ("Veterinarian".equals(selectedRole))
            {
                dashboard = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/VetViews/Dashboard.fxml")));
            } 
            else if ("Pet Owner".equals(selectedRole))
            {
                dashboard = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/ViewAccountScreen.fxml")));
            }
            else if ("Admin".equals(selectedRole))
            {
                dashboard = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/AdminViews/AdminDashboard.fxml")));
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(dashboard);
            stage.setScene(scene);
            stage.setTitle(selectedRole + " Dashboard");
            stage.show();
        } 
        else
        {
            if ("Veterinarian".equals(selectedRole))
            {
                if(Approval.equalsIgnoreCase("Pending"))
                {
                
                    showAlert(Alert.AlertType.ERROR, "Approval Pending", "Request has been submitted.");
                    return;
                }
                showAlert(Alert.AlertType.ERROR, "Invalid Credentials", "Invalid username, password. Please try again.");
            } 
            else if ("Pet Owner".equals(selectedRole))
            {
                showAlert(Alert.AlertType.ERROR, "Invalid Credentials", "Invalid username, password, or role. Please try again.");
            }
            else if ("Admin".equals(selectedRole))
            {
                if(Approval.equalsIgnoreCase("Pending"))
                {
                
                    showAlert(Alert.AlertType.ERROR, "Approval Pending", "Request has been submitted.");
                    return;
                }
                showAlert(Alert.AlertType.ERROR, "Invalid Credentials", "Invalid username, password. Please try again.");
            }
        }
    }

    private boolean validateCredentials(String user, String pass, String role) {
        // Placeholder for database validation logic, replace with queries
        
        Controller Cont = new Controller();
        
        if(role.equals("Pet Owner"))
        {
            ArrayList<PetOwner> Owner = Cont.GetPetOwners();

            for(PetOwner Own : Owner)
            {
               if (Own.getUsername().equals(user) && Own.getPassword().equals(pass) && "Pet Owner".equals(role))
                {
                    Controller.setOwner(Own);
                    return true; // Login successful for a Pet Owner
                }
            }
        }
        else if(role.equals("Veterinarian"))
        {
            ArrayList<Veterinarian> Adm = Cont.GetVets();

            for(Veterinarian Own : Adm)
            {
               if (Own.getUsername().equals(user) && Own.getPassword().equals(pass) && "Veterinarian".equals(role) && Own.getStatus().equals("Approved"))
                {
                    Cont.setVet(Own);
                    return true; // Login successful for a Pet Owner
                }
               else if(Own.getUsername().equals(user) && Own.getPassword().equals(pass) && "Veterinarian".equals(role) && Own.getStatus().equalsIgnoreCase("Pending"))
               {
                   Approval = "Pending";
                   return false;
               }
            }
        }
        else if(role.equals("Admin"))
        {
            ArrayList<Admin> Adm = Cont.GetAdmin();

            for(Admin Own : Adm)
            {
               if (Own.getUsername().equals(user) && Own.getPassword().equals(pass) && "Admin".equals(role) && Own.getStatus().equals("Approved"))
                {
                    Controller.setAdmin(Own);
                    return true; // Login successful for a Pet Owner
                }
               else if(Own.getUsername().equals(user) && Own.getPassword().equals(pass) && "Admin".equals(role) && Own.getStatus().equalsIgnoreCase("Pending"))
               {
                   Approval = "Pending";
                   return false;
               }
            }
            
        }
        return false;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleRegistrationHyperlink(ActionEvent event) throws IOException 
    {      
        String selectedRole = role.getValue();

        if ("Veterinarian".equals(selectedRole))
        {
            System.out.println("Switching to Registration page...");

            Parent registrationScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/VetViews/VetRegistrationScreen.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(registrationScreen);
            stage.setScene(scene);
            stage.setTitle("Registration Screen");
            stage.show();
        } 
        else if ("Pet Owner".equals(selectedRole))
        {
            System.out.println("Switching to Registration page...");

            Parent registrationScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/RegistrationScreen.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(registrationScreen);
            stage.setScene(scene);
            stage.setTitle("Registration Screen");
            stage.show();
        }
        else if ("Admin".equals(selectedRole))
        {
            System.out.println("Switching to Registration page...");

            Parent registrationScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/AdminViews/AdminRegistrationScreen.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(registrationScreen);
            stage.setScene(scene);
            stage.setTitle("Registration Screen");
            stage.show();
        }
        else
        {
            showAlert(Alert.AlertType.ERROR, "Invalid Selection", "Please Select a Role to Continue");
        }
    }
}