/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sda.AdminController;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kamran Shahid
 */
public class SelectPharmacyController {

    private Stage stage;
    private Scene scene;

    private ObservableList<Pharmacy> PharList;

    Controller Con = new Controller();
    @FXML
    private TableColumn<Pharmacy, String> PharName;
    @FXML
    private TableColumn<Pharmacy, String> PharLoc;
    @FXML
    private TableColumn<Pharmacy, String> PharCont;
    @FXML
    private TableView<Pharmacy> PharTable;
    
    public void initialize() 
    {
        PharList = FXCollections.observableArrayList(Con.GetPharmacy());

        PharName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPharName()));

        PharLoc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));

        PharCont.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContact()));

        PharTable.setItems(PharList);
    }

    @FXML
    private void goback(ActionEvent event) throws IOException {
        System.out.println("Going back...");

        Parent managePetsScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/AdminViews/ManageMedicalStuff.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(managePetsScreen);
        stage.setScene(scene);
        stage.setTitle("Manage Medical Stuff");
        stage.show();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException 
    {
        System.out.println("Logging out...");
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/LoginScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Login Screen");
        stage.show();
    }
    @FXML
    private void gohome(ActionEvent event) throws IOException 
    {
        System.out.println("Redirecting to Dashboard...");
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/AdminViews/AdminDashboard.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }

    @FXML
    private void CheckStock(ActionEvent event) throws IOException 
    {
        Pharmacy SelectedPhar = PharTable.getSelectionModel().getSelectedItem();
        
         if (SelectedPhar == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Selection");
                alert.setHeaderText(null);
                alert.setContentText("Please select a Pharmacy to Check Stock.");
                alert.showAndWait();
            } else {
                System.out.println("Navigating to Check Medicine Screen...");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/AdminViews/CheckMedicine.fxml"));
                Parent selectserviceScreen = loader.load();

                CheckMedicineController controller = loader.getController();
                controller.setSelectedPharmacy(SelectedPhar);

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(selectserviceScreen);
                stage.setScene(scene);
                stage.setTitle("Check Medicine");
                stage.show();
            }
    }

    
}
