package sda.vetmanagementsystem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import TestMain.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManagePetsController {
    private Stage stage;
    private Scene scene;

    @FXML
    private TableView<Pet> petTableView;

    @FXML
    private TableColumn<Pet, Integer> petIdColumn;

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
    private Button addPetButton;

    @FXML
    private Button deletePetButton;

    @FXML
    private Button updatePetButton;

    @FXML
    private Button backButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button logoutButton;

    // ObservableList to hold pet data
    private ObservableList<Pet> petList;
    
    Controller Cont = new Controller();
    
    // Initialize the TableView with placeholder data
    @FXML
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
    private void AddPet(ActionEvent event) throws IOException {
        System.out.println("Navigating to Add Pet Screen...");

        Parent managePetsScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/AddPetScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(managePetsScreen);
        stage.setScene(scene);
        stage.setTitle("Add Pets");
        stage.show();
    }

    @FXML
    private void UpdatePet(ActionEvent event) throws IOException {
        Pet selectedPet = petTableView.getSelectionModel().getSelectedItem();

        if (selectedPet == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a pet to update.");
            alert.showAndWait();
        } else {
            System.out.println("Navigating to Update Pet Screen...");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/Views/UpdatePetScreen.fxml"));
            Parent managePetsScreen = loader.load();

            UpdatePetController controller = loader.getController();
            controller.setSelectedPet(selectedPet);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(managePetsScreen);
            stage.setScene(scene);
            stage.setTitle("Update Pet");
            stage.show();
        }
    }

    @FXML
    private void deletepet(ActionEvent event) {

        Pet selectedPet = petTableView.getSelectionModel().getSelectedItem();

        if (selectedPet != null) {
            // confirmation message
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Pet");
            alert.setHeaderText("Are you sure you want to delete this pet?");
            alert.setContentText("Pet: " + selectedPet.getName());

            if (alert.showAndWait().get() == ButtonType.OK) {
                
                Cont.DeletePet(selectedPet);
                petList.remove(selectedPet);
                showAlert("Pet Deleted", "The selected pet has been deleted successfully.");
            }
        } else {
            showAlert("No Pet Selected", "Please select a pet to delete.");
        }
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void goback(ActionEvent event) throws IOException {
        System.out.println("Going back...");

        Parent managePetsScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/ViewAccountScreen.fxml")));
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
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/ViewAccountScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }

}
