package sda.vetmanagementsystem;

import TestMain.*;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ViewPrescriptionController {
    private Stage stage;
    private Scene scene;
    @FXML
    private Button logoutButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button backButton;

    @FXML
    private TableView<Prescription> PlanTableView;
    @FXML
    private TableColumn<Prescription, String> idColumn;
    @FXML
    private TableColumn<Prescription, String> petnameColumn;
    @FXML
    private TableColumn<Prescription, String> vetnameColumn;

    private ObservableList<Prescription> treatmentPlans;

    private Pet SelectedPet;
    
    Controller Cont = new Controller();
    @FXML
    private TableView<String> InstructionTable;
    @FXML
    private TableView<Medicine> MedicineTable;
    
    @FXML
    private TableColumn<String, String> Instruction;
    @FXML
    private TableColumn<Medicine, String> MedicineName;
    
    public void SetSelectedPet(Pet P)
    {
        SelectedPet = P;
        
        treatmentPlans = FXCollections.observableArrayList(Cont.GetPrescription(SelectedPet));
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("PrescripId"));

        petnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(SelectedPet.getName()));

        vetnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVet().getName()));
        
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
        Prescription SelectedPlan = PlanTableView.getSelectionModel().getSelectedItem();
        
        if(SelectedPlan != null)
        {
            ObservableList<String> Special = FXCollections.observableArrayList(SelectedPlan.getInstructions());

            Instruction.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

            InstructionTable.setItems(Special);
            
            ObservableList<Medicine> Special1 = FXCollections.observableArrayList(SelectedPlan.getListMedicine());

            MedicineName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

            MedicineTable.setItems(Special1);
        }
    }
}
