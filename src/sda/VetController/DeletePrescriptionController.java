/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sda.VetController;

import TestMain.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kamran Shahid
 */
public class DeletePrescriptionController  
{
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
    private TableColumn<Prescription, String> petnameColumn;
    @FXML
    private TableColumn<Prescription, String> vetnameColumn;;
    @FXML
    private TableColumn<Prescription, String> StatusCol;

    private ObservableList<Prescription> PrescriptionList;

    private Pet SelectedPet;
    
    private Prescription SelectedPrescription;

    Controller Cont = new Controller();
    
    @FXML
    private TableView<String> InstructionTable;
    @FXML
    private TableColumn<String, String> InstructionCol;
    @FXML
    private Button ViewMedicine;
    @FXML
    private Pane ViewMedicinePane;
    @FXML
    private TableView<Medicine> tableViewMedicines;
    @FXML
    private TableColumn<Medicine, String> nameColumn;
    @FXML
    private TableColumn<Medicine, String> typeColumn;
    @FXML
    private TableColumn<Medicine, String> purposeColumn;
    @FXML
    private TableColumn<Medicine, Integer> QuanCol;
    @FXML
    private Button DeleteButton;
    @FXML
    private Button CompleteButton;
    
    
    public void setSelectedPet(Pet P)
    {
        SelectedPet = P;
        
        PrescriptionList = FXCollections.observableArrayList(Cont.GetPrescription(SelectedPet));

        petnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(SelectedPet.getName()));

        vetnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(SelectedPet.getSpecies()));
        
        StatusCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        
        PlanTableView.setItems(PrescriptionList);        
        
        ViewMedicinePane.setVisible(false);

    }
    
    @FXML
    private void goback(ActionEvent event) throws IOException {
        try {
            System.out.println("Going back...");
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/VetViews/ManagePrescription.fxml"));
            Parent managePetsScreen = loader.load();
            
            ManagePrescriptionController controller = loader.getController();
            controller.setSelectedPet(SelectedPet);
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(managePetsScreen);
            stage.setScene(scene);
            stage.setTitle("Manage Prescription");
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
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/VetViews/Dashboard.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }

    @FXML
    private void TableIntialize(MouseEvent event) 
    {
        SelectedPrescription = PlanTableView.getSelectionModel().getSelectedItem();
        
        if(SelectedPrescription != null)
        {
            ObservableList<String> Special = FXCollections.observableArrayList(SelectedPrescription.getInstructions());

            InstructionCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

            InstructionTable.setItems(Special);
            
            ObservableList<Medicine>availableMedicines = FXCollections.observableArrayList(Cont.GetPresMed(SelectedPrescription));
        
            nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

            typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));

            purposeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPurpose()));

            QuanCol.setCellValueFactory(new PropertyValueFactory<>("Dosage"));

            tableViewMedicines.setItems(availableMedicines);
            
        }
    }

    @FXML
    private void OpenMedicinePane(ActionEvent event)
    {
        if(SelectedPrescription != null)
        {
            ViewMedicinePane.setVisible(true);
            InstructionTable.setVisible(false);
            ViewMedicine.setVisible(false);
            PlanTableView.setVisible(false);
            backButton.setVisible(false);
            DeleteButton.setVisible(false);
            CompleteButton.setVisible(false);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select an Prescription to View.");
            alert.showAndWait();
        }
    }

    @FXML
    private void ViewPrescriptionOpen(ActionEvent event) 
    {
        ViewMedicinePane.setVisible(false);
        InstructionTable.setVisible(true);
        ViewMedicine.setVisible(true);
        PlanTableView.setVisible(true);
        backButton.setVisible(true);
        DeleteButton.setVisible(true);
        CompleteButton.setVisible(true);
    }

    @FXML
    private void Delete(ActionEvent event) throws IOException
    {
        if(SelectedPrescription != null)
        {
            Cont.DeletePrescription(SelectedPet,SelectedPrescription);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Prescription Deleted");
            alert.setHeaderText(null);
            alert.setContentText("Prescription has been successfully deleted.");
            alert.showAndWait();
            
            godashboard(event);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select an Prescription to Delete.");
            alert.showAndWait();
        }
    }

    @FXML
    private void Complete(ActionEvent event) throws IOException 
    {
         if(SelectedPrescription != null)
        {
            Cont.CompletePrescription(SelectedPet,SelectedPrescription.getPrescripId());
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Prescription Completed");
            alert.setHeaderText(null);
            alert.setContentText("Prescription has been successfully Completed.");
            alert.showAndWait();
            
            godashboard(event);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select an Treatment to Complete.");
            alert.showAndWait();
        }
    }
    @FXML
    private void godashboard(ActionEvent event) throws IOException {
        System.out.println("Redirecting to Dashboard...");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/VetViews/ManageDashboard.fxml"));
        Parent buyMedicineScreen = loader.load();

        ManageDashboardController controller = loader.getController();
        controller.setSelectedPet(SelectedPet);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(buyMedicineScreen);
        stage.setScene(scene);
        stage.setTitle("Manage Dashboard");
        stage.show();
    }  
    
}
