package sda.vetmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import TestMain.*;
import java.io.IOException;
import java.util.Objects;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class FormFeedbackController {
    private Stage stage;
    private Scene scene;
    @FXML
    private Button logoutButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button submitFeedbackButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField vetnameTextBox = new TextField();

    @FXML
    private TextArea FeedbackArea;

    @FXML
    private ComboBox<String> ratingBox;
    
    private Veterinarian SelectedVet;
    
    Controller Cont = new Controller();
    @FXML
    private Label vetnameLabel;
    @FXML
    private Label feedbackLabel;
    @FXML
    private Label ratingLabel;
    
    public void SetSelectedVet(Veterinarian Vet)
    {
        SelectedVet = Vet;
    }

    public void initialize()
    {
        for (int i = 1; i <= 5; i++) {
            ratingBox.getItems().add(String.valueOf(i));
        }
    }

    @FXML
    private void submitFeedback(ActionEvent event) throws IOException {
        String vetName = vetnameTextBox.getText().trim();
        String feedback = FeedbackArea.getText().trim();
        String rating = ratingBox.getValue();

        if (vetName.isEmpty() || feedback.isEmpty() || rating == null) {
            showAlert("Error", "Please fill all fields before submitting.");
            return;
        }
        
        Cont.GiveFeedBack(SelectedVet,Double.parseDouble(rating),feedback);
        
        showAlert("Feedback Submitted", "Thank you for your feedback!");
        clearForm();
        
        System.out.println("Redirecting to Dashboard...");
        Parent loginScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/ViewAccountScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }

    @FXML
    private void cancel() {
        clearForm();
    }

    private void clearForm() {
        FeedbackArea.clear();
        ratingBox.setValue(null);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void goback(ActionEvent event) throws IOException {
        System.out.println("Going back...");

        Parent managePetsScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/FeedbackScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(managePetsScreen);
        stage.setScene(scene);
        stage.setTitle("feedback");
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
    private void TextIntial1(MouseEvent event) 
    {
        vetnameTextBox.setText(SelectedVet.getName());
        vetnameTextBox.setEditable(false);
    }
}

