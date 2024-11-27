package sda.vetmanagementsystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import TestMain.*;
import java.io.IOException;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.cell.PropertyValueFactory;

public class TrackHistoryController {
    private Stage stage;
    private Scene scene;
    @FXML
    private TableView<Pet> petTableView;
    @FXML
    private TableColumn<Pet, String> petIdColumn;
    @FXML
    private TableColumn<Pet, String> petNameColumn;
    @FXML
    private TableColumn<Pet, String> breedColumn;
    @FXML
    private TableColumn<Pet, String> speciesColumn;
    @FXML
    private TableColumn<Pet, Integer> ageColumn;
    @FXML
    private TableColumn<Pet, String> dobColumn;

    @FXML
    private Button viewhistoryButton;


    @FXML
    private Button backButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button logoutButton;

    private ObservableList<Pet> petList = FXCollections.observableArrayList();
    
    Controller Cont = new Controller();

    public void initialize()
    {
        petList = FXCollections.observableArrayList(Cont.GetPets());
        
        petIdColumn.setCellValueFactory(new PropertyValueFactory<>("petId"));

        petNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        breedColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBreed()));

        speciesColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSpecies()));

        ageColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAge()).asObject());

        dobColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDOB().toString()));

        petTableView.setItems(petList);
    }
    
    @FXML
    private void viewhistory(ActionEvent event)
    {
        Pet selectedPet = petTableView.getSelectionModel().getSelectedItem();

        if (selectedPet != null) {
            System.out.println("Opening View History for Pet: " + selectedPet.getName());

            try 
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/Views/ViewHistoryScreen.fxml"));
                Parent root1 = loader.load();
                
                ViewHistoryController controller = loader.getController();
                controller.setSelectedPet(selectedPet);
                
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                
                Scene scene = new Scene(root1);
                currentStage.setScene(scene);
                currentStage.show();
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(AlertType.ERROR, "Error", "Failed to load View History screen.");
            }
        } else {
            showAlert(AlertType.WARNING, "No Pet Selected", "Please select a pet to view history.");
        }
    }
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void goback(ActionEvent event) throws IOException {
        try {
            System.out.println("Going back...");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/Views/ViewAccountScreen.fxml"));
            Parent managePetsScreen = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(managePetsScreen);
            stage.setScene(scene);
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/ViewAccountScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }

    @FXML
    private void viewTreatment(ActionEvent event)
    {
        Pet selectedPet = petTableView.getSelectionModel().getSelectedItem();

        if (selectedPet != null) {
            System.out.println("Opening View Treatment for Pet: " + selectedPet.getName());

            try 
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/Views/ViewTreatmentScreen.fxml"));
                Parent root1 = loader.load();
                
                ViewTreatmentController controller = loader.getController();
                controller.SetSelectedPet(selectedPet);
                
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                
                Scene scene = new Scene(root1);
                currentStage.setScene(scene);
                currentStage.show();
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(AlertType.ERROR, "Error", "Failed to load View Treatment screen.");
            }
        } else {
            showAlert(AlertType.WARNING, "No Pet Selected", "Please select a pet to view treatment.");
        }
    }

    @FXML
    private void ViewPrescription(ActionEvent event) 
    {
        Pet selectedPet = petTableView.getSelectionModel().getSelectedItem();

        if (selectedPet != null) {
            System.out.println("Opening View Prescription for Pet: " + selectedPet.getName());

            try 
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/Views/ViewPrescriptionScreen.fxml"));
                Parent root1 = loader.load();
                
                ViewPrescriptionController controller = loader.getController();
                controller.SetSelectedPet(selectedPet);
                
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                
                Scene scene = new Scene(root1);
                currentStage.setScene(scene);
                currentStage.show();
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(AlertType.ERROR, "Error", "Failed to load View Prescription screen.");
            }
        } else {
            showAlert(AlertType.WARNING, "No Pet Selected", "Please select a pet to view prescription.");
        }
    }
}
