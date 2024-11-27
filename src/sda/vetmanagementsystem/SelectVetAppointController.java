package sda.vetmanagementsystem;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import TestMain.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SelectVetAppointController implements Initializable {

    private Stage stage;
    private Scene scene;
    @FXML
    private TableView<Veterinarian> VetTable;
    @FXML
    private TableColumn<Veterinarian, Integer> VetId;
    @FXML
    private TableColumn<Veterinarian, String> VetName;
    @FXML
    private TableView<String> VetSpecTable;
    @FXML
    private TableColumn<String,String> Specialization;

    @FXML
    private Button selectserviceButton;

    @FXML
    private Button backButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button homeButton;

    Controller Cont = new Controller();

    private Pet pet;
    
    private Veterinarian SelectedVet;
    
    private ObservableList<Veterinarian> VetList;
    

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        VetList = FXCollections.observableArrayList();
         
        for(Veterinarian Vet : Cont.GetVets())
        {
            if("Approved".equals(Vet.getStatus()))
            {
                VetList.add(Vet);
            }
        }
        
        VetId.setCellValueFactory(new PropertyValueFactory<>("VetId"));

        VetName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        VetTable.setItems(VetList);
        
    }

    @FXML
    private void SpecialIntialize()
    {
        SelectedVet = VetTable.getSelectionModel().getSelectedItem();
        
        if(SelectedVet != null)
        {
            ObservableList<String> Special = FXCollections.observableArrayList(SelectedVet.getSpecialization());

            Specialization.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

            VetSpecTable.setItems(Special);
        }
    }

    @FXML
    private void goback(ActionEvent event) throws IOException {
        Parent previousScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/BookApptScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(previousScreen);
        stage.setScene(scene);
        stage.setTitle("Select Pet for Appointment");
        stage.show();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
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
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/ViewAccountScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }

    @FXML
    public void selectservice(ActionEvent event) throws IOException 
    {
        if (SelectedVet == null) {
            // If no vet is selected, show a warning alert
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a veterinarian to schedule an appointment.");
            alert.showAndWait();
        } else {
            System.out.println("Navigating to Select Service Screen...");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/Views/SelectServiceScreen.fxml"));
            Parent selectServiceScreen = loader.load();

            SelectServiceController controller = loader.getController();
            controller.setSelectedVet(SelectedVet);
            controller.setSelectedPet(pet);
            
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(selectServiceScreen);
            stage.setScene(scene);
            stage.setTitle("Book Appointment");
            stage.show();
        }
    }

    public void setSelectedPet(Pet p)
    {
        this.pet = p;
    }
}
