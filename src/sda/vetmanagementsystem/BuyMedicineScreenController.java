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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import TestMain.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;

public class BuyMedicineScreenController
{
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

    @FXML
    public void SelectMedicine(ActionEvent event) throws IOException {
        Pharmacy selectedPhar = PharTable.getSelectionModel().getSelectedItem();
        
        if(Con.CheckCart() == true)
        {
            if (selectedPhar == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Selection");
                alert.setHeaderText(null);
                alert.setContentText("Please select a Pharmacy to buy medicines.");
                alert.showAndWait();
            } else {
                System.out.println("Navigating to Select Medicine Screen...");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/Views/SelectMedicineScreen.fxml"));
                Parent selectserviceScreen = loader.load();

                SelectMedicineController controller = loader.getController();
                controller.setSelectedPharmacy(selectedPhar);

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(selectserviceScreen);
                stage.setScene(scene);
                stage.setTitle("Select Medicine");
                stage.show();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cart not empty");
            alert.setHeaderText(null);
            alert.setContentText("Please select One of the options.");
            ButtonType increaseButton = new ButtonType("Move to CheckOut");
            ButtonType decreaseButton = new ButtonType("Clear Cart");
            ButtonType cancelButton = ButtonType.CANCEL;

            alert.getButtonTypes().setAll(increaseButton, decreaseButton, cancelButton);

            alert.showAndWait().ifPresent(response ->
            {
                if (response == increaseButton) 
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/Views/CheckoutScreen.fxml"));
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(BuyMedicineScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    CheckoutController controller = loader.getController();
                    controller.setSelectedPhar(selectedPhar);

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Checkout");
                    stage.show();
                } 
                else if (response == decreaseButton) 
                {
                    Con.CartEmpty();
                    if (selectedPhar == null) 
                    {
                        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                        alert1.setTitle("No Selection");
                        alert1.setHeaderText(null);
                        alert1.setContentText("Please select a Pharmacy to buy medicines.");
                        alert1.showAndWait();
                    } 
                    else 
                    {
                        System.out.println("Navigating to Select Medicine Screen...");

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/Views/SelectMedicineScreen.fxml"));
                        Parent selectserviceScreen = null;
                        try {
                            selectserviceScreen = loader.load();
                        } catch (IOException ex) {
                            Logger.getLogger(BuyMedicineScreenController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        SelectMedicineController controller = loader.getController();
                        controller.setSelectedPharmacy(selectedPhar);

                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(selectserviceScreen);
                        stage.setScene(scene);
                        stage.setTitle("Select Medicine");
                        stage.show();
                    }
                }
            });
            
        }
    }
    
}

