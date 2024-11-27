package sda.vetmanagementsystem;

import TestMain.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class SelectMedicineController {

    private Stage stage;
    private Scene scene;

    @FXML
    private TableView<Medicine> tableViewMedicines;

    @FXML
    private TableColumn<Medicine, String> nameColumn;

    @FXML
    private TableColumn<Medicine, String> typeColumn;

    @FXML
    private TableColumn<Medicine, String> purposeColumn;

    @FXML
    private TableColumn<Medicine, Double> priceColumn;
    
    @FXML
    private TableColumn<Medicine, Integer> QuanCol;
    
    Controller Cont = new Controller();
    private Pharmacy Phar;
    private ObservableList<Medicine> availableMedicines = FXCollections.observableArrayList();

    @FXML
    public void addtocart(ActionEvent event)
    {
        Medicine selectedMedicine = tableViewMedicines.getSelectionModel().getSelectedItem();

        if (selectedMedicine != null)
        {
            TextInputDialog dialog = new TextInputDialog();

            dialog.setTitle("Input Quantity");
            dialog.setContentText("Enter the Amount of Medicine required");
            dialog.showAndWait();  
            String result = dialog.getEditor().getText();
            
            if(result != null && result.matches("[0-9]+"))
            {

                int Quan = Integer.parseInt(result);
                
                if(Cont.PharCheckStock(Phar, selectedMedicine, Quan) == false)
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Not Enough Stock");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Select another Pharmacy or Medicine");
                    alert.showAndWait();
                    return ;
                }
                
                Medicine NewMedicine = new Medicine(selectedMedicine.getMedicineId(),selectedMedicine.getName(),selectedMedicine.getType(),selectedMedicine.getPurpose()
                                                    ,selectedMedicine.getQuantity(),selectedMedicine.getPrice());
                
                Cont.AddtoCart(NewMedicine, Quan);        

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Item Added");
                alert.setHeaderText(null);
                alert.setContentText(selectedMedicine.getName() + " has been added to your cart.");
                alert.showAndWait();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please renter the correct input value.");
                alert.showAndWait();
            }
        } 
        else 
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Item Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select an item to add to your cart.");
            alert.showAndWait();
        }
    }

    @FXML
    private void viewCart(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/Views/ViewCart.fxml"));
        Parent previousScreen = loader.load();
        
        ViewCartController controller = loader.getController();
        controller.SetSelectedPhar(Phar);
        
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(previousScreen);
        stage.setScene(scene);
        stage.setTitle("View Cart");
        stage.show();
    }

    @FXML
    private void goback(ActionEvent event) throws IOException {
        Parent previousScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/BuyMedicineScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(previousScreen);
        stage.setScene(scene);
        stage.setTitle("Select Pharmacy");
        stage.show();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
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
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/ViewAccountScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }
    
    public void setSelectedPharmacy(Pharmacy P)
    {
        Phar = P;
        
        availableMedicines = FXCollections.observableArrayList(Cont.GetMedicine(Phar));
        
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));

        purposeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPurpose()));

        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        QuanCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        
        tableViewMedicines.setItems(availableMedicines);
    }
    
}
