package sda.vetmanagementsystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import TestMain.*;
import java.io.IOException;
import java.util.Objects;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ViewTreatmentController {
    private Stage stage;
    private Scene scene;
    @FXML
    private Button logoutButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button backButton;

    @FXML
    private TableView<TreatmentPlan> PlanTableView;
    @FXML
    private TableColumn<TreatmentPlan, String> petnameColumn;
    @FXML
    private TableColumn<TreatmentPlan, String> vetnameColumn;;
    @FXML
    private TableColumn<TreatmentPlan, String> StatusCol;

    private ObservableList<TreatmentPlan> treatmentPlans;

    private Pet SelectedPet;
    
    Controller Cont = new Controller();
    
    @FXML
    private TableView<String> DetTable;
    @FXML
    private TableColumn<String, String> DetaCol;
    @FXML
    private TableView<String> NoteTable;
    @FXML
    private TableColumn<String, String> NoteCol;
    
    public void SetSelectedPet(Pet P)
    {
        SelectedPet = P;
        
        treatmentPlans = FXCollections.observableArrayList(Cont.GetTreatmentPlans(SelectedPet));

        petnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(SelectedPet.getName()));

        vetnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVet().getName()));
        
        StatusCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        
        PlanTableView.setItems(treatmentPlans);
        
    }
    
    @FXML
    private void goback(ActionEvent event) throws IOException {
        try {
            System.out.println("Going back...");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/Views/TrackMedicalHistoryScreen.fxml"));
            Parent managePetsScreen = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(managePetsScreen);
            stage.setScene(scene);
            stage.setTitle("View History");
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
    private void TableIntialize(MouseEvent event) 
    {
        TreatmentPlan SelectedPlan = PlanTableView.getSelectionModel().getSelectedItem();
        
        if(SelectedPlan != null)
        {
            ObservableList<String> Special = FXCollections.observableArrayList(SelectedPlan.getDetails());

            DetaCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

            DetTable.setItems(Special);
            
            ObservableList<String> Special1 = FXCollections.observableArrayList(SelectedPlan.getNotes());

            NoteCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

            NoteTable.setItems(Special1);
        }
    }

}
