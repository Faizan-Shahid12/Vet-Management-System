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
import TestMain.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

public class AddDaycareFormController 
{
    private Stage stage;
    private Scene scene;
    @FXML
    private ComboBox<Pet> nameBox;
    @FXML
    private DatePicker startdatePick;
    @FXML
    private DatePicker enddatePick;
    @FXML
    private TextField specialinstructField;
    @FXML
    private ComboBox<String> specialinstructBox;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button backButton;

    private ObservableList<Pet> petNames;
    
    private ObservableList<String> InstructBox = FXCollections.observableArrayList();
    
    Controller Cont = new Controller();

    @FXML
    public void initialize() 
    {
        petNames = FXCollections.observableArrayList(Cont.GetPets());
        
        nameBox.setItems(petNames);
        
        Callback<ListView<Pet>, ListCell<Pet>> factory = lv -> new ListCell<Pet>() 
        {
            @Override
            protected void updateItem(Pet item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        };

        nameBox.setCellFactory(factory);
        nameBox.setButtonCell(factory.call(null));

        enddatePick.setDayCellFactory(new Callback<DatePicker, DateCell>() 
         {
            @Override
            public DateCell call(DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty)
                    {
                        super.updateItem(date, empty);
                        
                        if (date.isBefore(LocalDate.now()))
                        {
                            setDisable(true); 
                        }
                    }
                };
            }
        });
        
        startdatePick.setDayCellFactory(new Callback<DatePicker, DateCell>() 
         {
            @Override
            public DateCell call(DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty)
                    {
                        super.updateItem(date, empty);
                        
                        if (date.isBefore(LocalDate.now()))
                        {
                            setDisable(true); 
                        }
                    }
                };
            }
        });
    }
    @FXML
    private void AddinCombo(KeyEvent event) 
    {
        if (event.getCode().equals(KeyCode.ENTER))
        {
            String ins = specialinstructField.getText().trim();
            
            if (!specialinstructField.getText().isEmpty() && !InstructBox.contains(ins))
            {
                InstructBox.add(ins);
                specialinstructBox.getSelectionModel().select(0);
            }
            
            specialinstructBox.setItems(InstructBox);
            specialinstructBox.getSelectionModel().select(0);
            specialinstructField.clear();
        }
        
    }

    @FXML
    private void DeleteFromCombo(KeyEvent event) 
    {
         if (event.getCode().equals(KeyCode.BACK_SPACE))
        {
            InstructBox.remove(specialinstructBox.getSelectionModel().getSelectedItem());
            specialinstructBox.getItems().remove(specialinstructBox.getSelectionModel().getSelectedItem());
            specialinstructBox.getSelectionModel().select(0);
        }
    }

    @FXML
    private void confirmPayment(ActionEvent event) throws IOException 
    {
        if (nameBox.getSelectionModel().isEmpty()) {
            showAlert("Selection Required", "Please select a pet name.");
            return;
        }

        if (startdatePick.getValue() == null) {
            showAlert("Selection Required", "Please select a start date.");
            return;
        }

        if (enddatePick.getValue() == null || enddatePick.getValue().isBefore(startdatePick.getValue())) {
            showAlert("Selection Required", "Please select an valid end date.");
            return;
        }

        if (specialinstructBox.getSelectionModel().isEmpty() && specialinstructField.getText().trim().isEmpty()) {
            showAlert("Input Required", "Please add special instructions.");
            return;
        }
        
        ArrayList<String> Spec = new ArrayList<>(specialinstructBox.getItems());
        
        DayCare Day = new DayCare(0,nameBox.getValue().getPetId(),startdatePick.getValue(),enddatePick.getValue(),Spec);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/Views/DaycarePaymentScreen.fxml"));
        Parent root = loader.load();
        
        DaycarePaymentController controller = loader.getController();
        controller.setSelectedDayCare(Day);
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Confirm Payment");
        stage.show();

    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        System.out.println("Cancel button clicked. Clearing the form.");
        nameBox.getSelectionModel().clearSelection();
        startdatePick.setValue(null);
        enddatePick.setValue(null);
        specialinstructField.clear();
        specialinstructBox.getSelectionModel().clearSelection();
        
        Parent managePetsScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/ViewAccountScreen.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(managePetsScreen);
        stage.setScene(scene);
        stage.setTitle("Daycare");
        stage.show();
    }


    @FXML
    private void goback(ActionEvent event) throws IOException {
        System.out.println("Going back...");

        Parent managePetsScreen = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sda/Views/ViewDaycareOptions.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(managePetsScreen);
        stage.setScene(scene);
        stage.setTitle("Daycare");
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
