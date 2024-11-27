package sda.vetmanagementsystem;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import TestMain.*;
import java.io.IOException;
import java.util.Objects;

public class ViewCartController {
    private Stage stage;
    private Scene scene;

    @FXML
    private TableView<Medicine> CartTable;
    @FXML
    private TableColumn<Medicine, String> nameCol;
    @FXML
    private TableColumn<Medicine, Integer> quantityCol;
    @FXML
    private TableColumn<Medicine, Double> priceCol;
    @FXML
    private TableColumn<Medicine, Double> subtotalCol;
    @FXML
    private Button checkoutButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button backButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button homeButton;
    @FXML
    private Label cartLabel;
    
    private Pharmacy SelectedPhar;
    
    Controller Cont = new Controller();

    private ObservableList<Medicine> cartItems;

    public void initialize() {
        
        cartItems = FXCollections.observableArrayList(Cont.CartMed());
        
        nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        quantityCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

        priceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));

        subtotalCol.setCellValueFactory(cellData ->
        {
                    Medicine Med = cellData.getValue();
                    double subtotal = Med.getPrice() * Med.getQuantity();
                    return new SimpleDoubleProperty(subtotal).asObject();
        });
        
        CartTable.setItems(cartItems);
    }

    @FXML
    private void checkout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/Views/CheckoutScreen.fxml"));
        Parent root = loader.load();
        
        CheckoutController controller = loader.getController();
        controller.setSelectedPhar(SelectedPhar);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Checkout");
        stage.show();

    }


    @FXML
    private void update()
    {
        Medicine selectedItem = CartTable.getSelectionModel().getSelectedItem();
        
        if (selectedItem != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Update Quantity");
            alert.setHeaderText("Update the quantity for " + selectedItem.getName());
            alert.setContentText("Would you like to increase or decrease the quantity?");

            ButtonType increaseButton = new ButtonType("Increase");
            ButtonType decreaseButton = new ButtonType("Decrease");
            ButtonType cancelButton = ButtonType.CANCEL;

            alert.getButtonTypes().setAll(increaseButton, decreaseButton, cancelButton);

            alert.showAndWait().ifPresent(response -> {
                if (response == increaseButton) 
                {
                    Cont.UpdateCartMed(selectedItem.getMedicineId(),selectedItem.getQuantity()+1);
                    CartTable.refresh();
                } 
                else if (response == decreaseButton) 
                {
                    if (selectedItem.getQuantity() > 1) 
                    {
                        
                        Cont.UpdateCartMed(selectedItem.getMedicineId(),selectedItem.getQuantity()-1);
                        CartTable.refresh();
                    } 
                    else 
                    {
                        Alert warningAlert = new Alert(Alert.AlertType.WARNING);
                        warningAlert.setTitle("Invalid Quantity");
                        warningAlert.setHeaderText(null);
                        warningAlert.setContentText("Quantity cannot go below 1.");
                        warningAlert.showAndWait();
                    }
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Item Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select an item to update.");
            alert.showAndWait();
        }
    }

    @FXML
    private void remove() 
    {
        Medicine selectedItem = CartTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) 
        {
            Cont.RemoveCartMed(selectedItem.getMedicineId());
            initialize();
            
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Item Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select an item to remove.");
            alert.showAndWait();
        }
    }

    @FXML
    private void goback(ActionEvent event) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/Views/SelectMedicineScreen.fxml"));
        Parent previousScreen = loader.load();
        
        SelectMedicineController controller = loader.getController();
        controller.setSelectedPharmacy(SelectedPhar);
        
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(previousScreen);
        stage.setScene(scene);
        stage.setTitle("Select Medicine");
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
    
    public void SetSelectedPhar(Pharmacy P)
    {
        SelectedPhar = P;
    }

}
