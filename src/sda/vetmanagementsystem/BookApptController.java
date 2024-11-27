package sda.vetmanagementsystem;

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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookApptController {
    private Stage stage;
    private Scene scene;

    @FXML
    private TableView<Pet> petTableView;

    @FXML
    private Button logoutButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button selectVetButton;

    @FXML
    private Button backButton;

    @FXML
    private Label titleLabel;

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

    private ObservableList<Pet> petList = FXCollections.observableArrayList();
    
    Controller Cont = new Controller();
    
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

    public void selectvet(ActionEvent event) throws IOException
    {
        Pet selectedPet = petTableView.getSelectionModel().getSelectedItem();

        if (selectedPet == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a pet to schedule appointment for.");
            alert.showAndWait();
        } else {
            System.out.println("Navigating to Select Vet Screen...");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/Views/SelectVetAppoint.fxml"));
            Parent selectserviceScreen = loader.load();

            SelectVetAppointController controller = loader.getController();
            controller.setSelectedPet(selectedPet);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(selectserviceScreen);
            stage.setScene(scene);
            stage.setTitle("Book appointment");
            stage.show();
        }
    }
}
