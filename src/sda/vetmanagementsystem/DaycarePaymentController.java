package sda.vetmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import TestMain.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class DaycarePaymentController {
    private Stage stage;
    private Scene scene;

    @FXML
    private ComboBox<String> paymentBox;
    @FXML
    private VBox paymentDetailsVbox;

    @FXML
    private TextField cardnum;
    @FXML
    private TextField expiry;
    @FXML
    private TextField cvv;

    @FXML
    private Label total;
    @FXML
    
    private DayCare SelectedDayCare;
    
    public void initialize() 
    {
        paymentBox.getItems().addAll("Credit Card", "Debit Card");

        paymentBox.setOnAction(event -> {
            if (paymentBox.getValue() != null) {
                paymentDetailsVbox.setVisible(true);
            }
        });
    }

    public void setSelectedDayCare(DayCare D)
    {
        SelectedDayCare = D;
        
        int total1 = 0;
        
        LocalDate SDate = SelectedDayCare.getStartDate();
        
        while(SDate.isBefore(SelectedDayCare.getEndDate()) || SDate.isEqual(SelectedDayCare.getEndDate()))
        {
            total1 += 5;
            SDate = SDate.plusDays(1);
        }
        SelectedDayCare.setBill(total1);
        total.setText("$ " + Integer.toString(total1));
    }
    
    @FXML
    public void confirmPayment(ActionEvent event) throws IOException {
        String paymentMethod = paymentBox.getValue();
        String cardNumber = cardnum.getText();
        String expiryDate = expiry.getText();
        String cvvNumber = cvv.getText();

        if (paymentMethod == null || paymentMethod.isEmpty() || cardNumber.isEmpty() || expiryDate.isEmpty() || cvvNumber.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Payment Error");
            alert.setHeaderText("Incomplete Information");
            alert.setContentText("Please fill out all payment details.");
            alert.showAndWait();
            return;
        }
        
        if(!cardNumber.matches("^\\d{13,19}$"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Payment Error");
            alert.setHeaderText("Incomplete Information");
            alert.setContentText("Please correct card Number.");
            alert.showAndWait();
            return;
            
        }
        
        if(!expiryDate.matches("^(0[1-9]|1[0-2])\\/\\d{2}(\\d{2})?$"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Payment Error");
            alert.setHeaderText("Incomplete Information");
            alert.setContentText("Please enter correct expiry date.");
            alert.showAndWait();
            return; 
        }
        
        if(!cvvNumber.matches("^\\d{3,4}$"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Payment Error");
            alert.setHeaderText("Incomplete Information");
            alert.setContentText("Please enter correct cvv number.");
            alert.showAndWait();
            return;
        }
        
        
        Controller Cont = new Controller();
        Cont.BookDayCare(SelectedDayCare);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payment Successful");
        alert.setHeaderText("Payment Processed");
        alert.setContentText("Your payment has been successfully processed.");
        alert.showAndWait();
        
       System.out.println("Redirecting to Dashboard...");
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/ViewAccountScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
        
    }
    @FXML
    public void cancel(ActionEvent event) throws IOException {
        Parent previousScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/DaycareBookingForm.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(previousScreen);
        stage.setScene(scene);
        stage.setTitle("Cart");
        stage.show();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
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
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/ViewAccountScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }
}
