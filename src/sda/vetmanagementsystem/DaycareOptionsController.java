package sda.vetmanagementsystem;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import TestMain.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class DaycareOptionsController {
    private Stage stage;
    private Scene scene;
    @FXML
    private TableView<DayCare> daycareTableView;
    @FXML
    private TableColumn<DayCare, String> nameColumn;
    @FXML
    private TableColumn<DayCare, LocalDate> startdateColumn;
    @FXML
    private TableColumn<DayCare, LocalDate> enddateColumn;
    @FXML
    private TableColumn<DayCare, String> statusColumn;
    @FXML
    private Button logoutButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button backButton;
    @FXML
    private Button adddayButton;
    @FXML
    private Button viewdayButton;

    private ObservableList<DayCare> daycareServices = FXCollections.observableArrayList();
    
    Controller Cont = new Controller();
    
    @FXML
    private void initialize() 
    {
        for(Pet P : Cont.GetPets())
        {
            for(DayCare D : Cont.GetDayCare(P))
            {
                if(!"Cancelled".equals(D.getStatus()))
                {
                    daycareServices.add(D);
                }  
            }
        }

        nameColumn.setCellValueFactory(cellData -> {
                    
                    Pet pet = null;
                    
                    for(Pet p : Cont.GetPets())
                    {
                        if(p.getPetId() == cellData.getValue().getPetId())
                        {
                           pet = p;
                           break;
                        }
                    }
                    if (pet != null)
                    {
                        return new SimpleStringProperty(pet.getName());
                    }
                        return new SimpleStringProperty("Unknown Pet");
                    
             });

        startdateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStartDate()));

        enddateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getEndDate()));

        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

        daycareTableView.setItems(daycareServices);
    }

    @FXML
    private void addDaycare(ActionEvent event) throws IOException {
        System.out.println("Add Daycare Service button clicked!");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/Views/DaycareBookingForm.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }

    @FXML
    private void viewDaycare(ActionEvent event) throws IOException {
        System.out.println("View Daycare button clicked!");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/Views/ViewDaycareRecordScreen.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
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
