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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kamran Shahid
 */
public class DeleteTreatmentPlanController {
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
    
    public void setSelectedPet(Pet P)
    {
        SelectedPet = P;
        
        treatmentPlans = FXCollections.observableArrayList(Cont.GetVetTreatmentPlan(SelectedPet));

        petnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(SelectedPet.getName()));

        vetnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(SelectedPet.getSpecies()));
        
        StatusCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        
        PlanTableView.setItems(treatmentPlans);
        
    }
    
    @FXML
    private void goback(ActionEvent event) throws IOException {
        try {
            System.out.println("Going back...");
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/VetViews/ManageTreatment.fxml"));
            Parent managePetsScreen = loader.load();
            
            ManageTreatmentController controller = loader.getController();
            controller.setSelectedPet(SelectedPet);
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(managePetsScreen);
            stage.setScene(scene);
            stage.setTitle("Manage Treatment");
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

    @FXML
    private void Delete(ActionEvent event) throws IOException 
    {
        TreatmentPlan SelectedPlan = PlanTableView.getSelectionModel().getSelectedItem();
        
        if(SelectedPlan != null)
        {
            Cont.DeleteTreatmentPlan(SelectedPet,SelectedPlan);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("TreatmentPlan Deleted");
            alert.setHeaderText(null);
            alert.setContentText("TreatmentPlan has been successfully deleted.");
            alert.showAndWait();
            
            godashboard(event);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select an Treatment to Delete.");
            alert.showAndWait();
        }
    }

    @FXML
    private void Complete(ActionEvent event) throws IOException
    {
        TreatmentPlan SelectedPlan = PlanTableView.getSelectionModel().getSelectedItem();
        
        if(SelectedPlan != null)
        {
            Cont.CompleteTreatmentPlan(SelectedPet,SelectedPlan.getPlanId());
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("TreatmentPlan Completed");
            alert.setHeaderText(null);
            alert.setContentText("TreatmentPlan has been successfully Completed.");
            alert.showAndWait();
            
            godashboard(event);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select an Treatment to Completed.");
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
