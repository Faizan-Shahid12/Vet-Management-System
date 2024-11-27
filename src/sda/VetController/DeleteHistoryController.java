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
public class DeleteHistoryController implements Initializable 
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
    private TableView<MedicalHistory> PetTable;

    @FXML
    private TableView<String> DiagTable;
    @FXML
    private TableColumn<String, String> Diag;
    @FXML
    private TableView<String> SympTable;
    @FXML
    private TableColumn<String, String> Symp;
    @FXML
    private TableView<String> TreatTable;
    @FXML
    private TableColumn<String, String> Treat;
    @FXML
    private TableColumn<MedicalHistory, String> MedId;
    @FXML
    private TableColumn<MedicalHistory, String> MedDate;
    
    private Pet SelectedPet;
    
    Controller Cont = new Controller();
    
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void goback(ActionEvent event) throws IOException {
        try {
            System.out.println("Going back...");
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/VetViews/ManageHistory.fxml"));
            Parent managePetsScreen = loader.load();
            
            ManageHistoryController controller = loader.getController();
            controller.setSelectedPet(SelectedPet);
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(managePetsScreen);
            stage.setScene(scene);
            stage.setTitle("Manage History");
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
    
    public void setSelectedPet(Pet SelectedPet) 
    {
        this.SelectedPet = SelectedPet;
        
        ObservableList<MedicalHistory> MedList = FXCollections.observableArrayList(Cont.GetMedicalHistory(SelectedPet));

        MedId.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getMedicalId())));

        MedDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate().toString()));
        
        PetTable.setItems(MedList);
    }
    
    @FXML
    private void TableIntial(MouseEvent event) 
    {
         MedicalHistory SelectedMedical = PetTable.getSelectionModel().getSelectedItem();
        if(SelectedMedical == null)
        {
            return;
        }
        else
        {
            
            Diag.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
            Symp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
            Treat.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

            ObservableList<String> DiaList = FXCollections.observableArrayList(SelectedMedical.getDiagnosis());
            ObservableList<String> SympList = FXCollections.observableArrayList(SelectedMedical.getSymptoms());
            ObservableList<String> TreatList = FXCollections.observableArrayList(SelectedMedical.getTreatmentDetails());

            DiagTable.setItems(DiaList);
            SympTable.setItems(SympList);
            TreatTable.setItems(TreatList);
        }
    }

    @FXML
    private void Delete(ActionEvent event) throws IOException
    {
        MedicalHistory SelectedHistory = PetTable.getSelectionModel().getSelectedItem();
        
        if(SelectedHistory != null)
        {
            Cont.DeleteMedical(SelectedPet,SelectedHistory);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("History Deleted");
            alert.setHeaderText(null);
            alert.setContentText("History has been successfully deleted.");
            alert.showAndWait();
            
            goback(event);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select an History to Delete.");
            alert.showAndWait();
        }
    }
    
}
