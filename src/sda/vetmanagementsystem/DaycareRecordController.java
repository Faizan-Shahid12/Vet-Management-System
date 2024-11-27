package sda.vetmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import TestMain.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.input.MouseEvent;

public class DaycareRecordController {
    private Stage stage;
    private Scene scene;

    @FXML
    private Button logoutButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button backButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button adddayButton;

    @FXML
    private TableView<DayCare> daycareTableView;

    @FXML
    private TableColumn<DayCare, String> nameColumn;

    @FXML
    private TableColumn<DayCare, LocalDate> startdateColumn;

    @FXML
    private TableColumn<DayCare, LocalDate> enddateColumn;

    @FXML
    private TableColumn<DayCare, String> billCol;

    private ObservableList<DayCare> daycareData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<DayCare, String > durationCol;
    @FXML
    private TableColumn<DayCare, String> StatusCol;
    @FXML
    private TableView<String> SpecialInstTable;
    @FXML
    private TableColumn<String, String> InstructionCol;
    
    private DayCare SelectedDay; 
    
    Controller Cont = new Controller();
    
    public void initialize() 
    {
         for(Pet P : Cont.GetPets())
        {
            for(DayCare D : Cont.GetDayCare(P))
            {
                daycareData.add(D);
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

        StatusCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        
        billCol.setCellValueFactory(cellData -> new SimpleStringProperty("$ " + Integer.toString(cellData.getValue().getBill())));
        
        durationCol.setCellValueFactory(cellData -> new SimpleStringProperty(Long.toString(cellData.getValue().CalDuration())));
        
        daycareTableView.setItems(daycareData);
    }
    @FXML
    private void addDaycare(ActionEvent event) throws IOException {
        System.out.println("Add Daycare Service button clicked!");
        Parent daycareFormScreen = FXMLLoader.load(getClass().getResource("/sda/Views/DaycareBookingForm.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(daycareFormScreen);
        stage.setScene(scene);
        stage.setTitle("Add Daycare Service");
        stage.show();
    }


    @FXML
    private void cancelservice(ActionEvent event) {
        
        DayCare selectedDayCare = daycareTableView.getSelectionModel().getSelectedItem();
        
        if (selectedDayCare != null) 
        {
            Cont.CancelDayCare(selectedDayCare);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Service Cancelled");
            alert.setHeaderText(null);
            alert.setContentText("The selected daycare service has been successfully cancelled.");
            alert.showAndWait();
            daycareTableView.refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Record Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a daycare record to cancel.");
            alert.showAndWait();
        }
    }


    @FXML
    private void goback(ActionEvent event) throws IOException {
        System.out.println("Going back...");

        Parent managePetsScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/ViewDaycareOptions.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(managePetsScreen);
        stage.setScene(scene);
        stage.setTitle("Daycare Options");
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

    @FXML
    private void SpecialIntial(MouseEvent event) 
    {
        SelectedDay = daycareTableView.getSelectionModel().getSelectedItem();
        
        if(SelectedDay != null)
        {
            ObservableList<String> Special = FXCollections.observableArrayList(SelectedDay.getSpecial());

            InstructionCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

            SpecialInstTable.setItems(Special);
        }
    }

}
