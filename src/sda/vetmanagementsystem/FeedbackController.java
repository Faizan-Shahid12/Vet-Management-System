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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import TestMain.*;
import java.io.IOException;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;

public class FeedbackController {
    private Stage stage;
    private Scene scene;
    @FXML
    private Button logoutButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button fillFormButton;

    @FXML
    private Button backButton;

    @FXML
    private Label vetlistLabel;

    @FXML
    private TableView<Veterinarian> VetTable;

    @FXML
    private TableColumn<Veterinarian, Integer> VetId;

    @FXML
    private TableColumn<Veterinarian, String> VetName;

    @FXML
    private TableView<String> VetSpecTable;

    @FXML
    private TableColumn<String, String> Specialization;

    @FXML
    private ImageView backgroundImage;
    
    Controller Cont = new Controller();
    
    Veterinarian SelectedVet;
    
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
    private void fillForm(ActionEvent event)
    {
        if (VetTable.getSelectionModel().getSelectedItem() == null) {
            showAlert(Alert.AlertType.WARNING, "No Veterinarian Selected", "Please select a veterinarian from the list before filling the form.");
        } else {
            Veterinarian selectedVet = VetTable.getSelectionModel().getSelectedItem();
            try 
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/Views/Formfeedback.fxml"));
                Parent selectserviceScreen = loader.load();

                FormFeedbackController controller = loader.getController();
                controller.SetSelectedVet(selectedVet);

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(selectserviceScreen);
                stage.setScene(scene);
                stage.setTitle("Feedback Form " + selectedVet.getName());
                stage.show();
                
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void initialize() 
    {
        ObservableList<Veterinarian> VetList = FXCollections.observableArrayList(Cont.GetVets());

        VetId.setCellValueFactory(new PropertyValueFactory<>("VetId"));

        VetName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        VetTable.setItems(VetList);
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
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}

