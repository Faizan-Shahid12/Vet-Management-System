/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sda.VetController;

import TestMain.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kamran Shahid
 */
public class AddHistoryController 
{
    private Stage stage;
    private Scene scene;
    @FXML
    private Button logoutButton;
    @FXML
    private Button homeButton;
    @FXML
    private Label symptomsLabel;
    @FXML
    private Label diagnosisLabel;
    @FXML
    private Label treatmentLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private DatePicker apptdatePicker;
    @FXML
    private TextField DiagField;
    @FXML
    private ComboBox<String> DiagBox;
    @FXML
    private TextField TreatField;
    @FXML
    private ComboBox<String> TreatBox;
    @FXML
    private TextField SympField;
    @FXML
    private ComboBox<String> SympBox;
    @FXML
    private Button addhistoryButton;
    @FXML
    private Button backButton;
    
    private Pet SelectedPet;
    
    Controller Cont = new Controller();
    
    private ObservableList<String> DiagCo = FXCollections.observableArrayList();
    
    private ObservableList<String> TreatCo = FXCollections.observableArrayList();
    
    private ObservableList<String> SympCo = FXCollections.observableArrayList();
    
    @FXML
    private void logout(ActionEvent event) throws IOException 
    {
        System.out.println("Logging out...");
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
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/VetViews/Dashboard.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }   

    @FXML
    private void EnterinDiagBox(KeyEvent event) 
    {
        if (event.getCode().equals(KeyCode.ENTER))
        {
            if(DiagField.getText().matches("^[A-Za-z0-9 ]+$"))
            {

                if (!DiagField.getText().isEmpty() && !DiagCo.contains(DiagField.getText().trim()))
                {
                    DiagCo.add(DiagField.getText().trim());
                    DiagBox.getSelectionModel().select(0);
                }

                DiagBox.setItems(DiagCo);
                DiagBox.getSelectionModel().select(0);
                DiagField.clear();
            }
            else
            {
                return;
            }
            
        }
    }

    @FXML
    private void DeleteFromDiagBox(KeyEvent event)
    {
        if (event.getCode().equals(KeyCode.BACK_SPACE))
        {
            DiagCo.remove(DiagBox.getSelectionModel().getSelectedItem());
            DiagBox.getItems().remove(DiagBox.getSelectionModel().getSelectedItem());
            DiagBox.getSelectionModel().select(0);
        }
    }

    @FXML
    private void EnterinTreatBox(KeyEvent event) 
    {
        if (event.getCode().equals(KeyCode.ENTER))
        {
            if(TreatField.getText().matches("^[A-Za-z0-9 ]+$"))
            {

                if (!TreatField.getText().isEmpty() && !TreatCo.contains(TreatField.getText().trim()))
                {
                    TreatCo.add(TreatField.getText().trim());
                    TreatBox.getSelectionModel().select(0);
                }

                TreatBox.setItems(TreatCo);
                TreatBox.getSelectionModel().select(0);
                TreatField.clear();
            }
            else
            {
                return;
            }
            
        }
    }

    @FXML
    private void DeleteFromTreatBox(KeyEvent event)
    {
        if (event.getCode().equals(KeyCode.BACK_SPACE))
        {
            TreatCo.remove(TreatBox.getSelectionModel().getSelectedItem());
            TreatBox.getItems().remove(TreatBox.getSelectionModel().getSelectedItem());
            TreatBox.getSelectionModel().select(0);
        }
    }

    @FXML
    private void EnterinSympBox(KeyEvent event)
    { 
        if (event.getCode().equals(KeyCode.ENTER))
        {
            if(SympField.getText().matches("^[A-Za-z0-9 ]+$"))
            {

                if (!SympField.getText().isEmpty() && !SympCo.contains(SympField.getText().trim()))
                {
                    SympCo.add(SympField.getText().trim());
                    SympBox.getSelectionModel().select(0);
                }

                SympBox.setItems(SympCo);
                SympBox.getSelectionModel().select(0);
                SympField.clear();
            }
            else
            {
                return;
            }
            
        }
    }

    @FXML
    private void DeleteFromSympBox(KeyEvent event) 
    {
         if (event.getCode().equals(KeyCode.BACK_SPACE))
        {
            SympCo.remove(SympBox.getSelectionModel().getSelectedItem());
            SympBox.getItems().remove(SympBox.getSelectionModel().getSelectedItem());
            SympBox.getSelectionModel().select(0);
        }
    }

    @FXML
    private void addhistory(ActionEvent event) throws IOException
    {
        String date = apptdatePicker.getValue() != null ? apptdatePicker.getValue().toString() : "No date selected";

        // Check if all fields are filled before proceeding
        if (SympBox.getSelectionModel().isEmpty() || DiagBox.getSelectionModel().isEmpty() || TreatBox.getSelectionModel().isEmpty() || apptdatePicker.getValue() == null) {
            showErrorAlert("Missing Fields", "Please fill in all the fields before adding history.");
        } 
        else 
        {
            ArrayList<String> dia = new ArrayList<>(DiagBox.getItems());
            ArrayList<String> sym = new ArrayList<>(SympBox.getItems());
            ArrayList<String> trea = new ArrayList<>(TreatBox.getItems());
            
            Cont.AddMedical(SelectedPet,dia,sym,trea,LocalDate.now());
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("History Added");
            alert.setHeaderText("The pet history has been successfully added.");
            alert.setContentText("Symptoms: " + sym.get(0) + "\nDiagnosis: " + dia.get(0) + "\nTreatment: " + trea.get(0) + "\nDate: " + date);
            alert.showAndWait();
            
            goback(event);
        }
    }

    @FXML
    private void goback(ActionEvent event) throws IOException 
    {
        try 
        {
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

    
    private void showInfoAlert(String title, String message) 
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public void setSelectedPet(Pet p)
    {
        SelectedPet = p;
        apptdatePicker.setValue(LocalDate.now());
        apptdatePicker.setDisable(true);
    }

}
