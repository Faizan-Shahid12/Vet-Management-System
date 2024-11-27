/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sda.VetController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import TestMain.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Kamran Shahid
 */
public class CustomizePrescriptionController implements Initializable 
{
    private Stage stage;
    private Scene scene;

    @FXML
    private Button loginbutton;
    @FXML
    private TextField NoteField;
    @FXML
    private ComboBox<String> NoteComb;
    @FXML
    private Button HistoryButton;
    
    private Pet SelectedPet;
    
    Controller Cont;
    
    private ObservableList<String> NoteBox;
    @FXML
    private Pane RecordPane;
    @FXML
    private Pane HistoryPane;
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
    @FXML
    private Button MedicineButton;
    @FXML
    private Pane MedicinePane;
    @FXML
    private TableView<Medicine> tableViewMedicines;
    @FXML
    private TableColumn<Medicine, String> nameColumn;
    @FXML
    private TableColumn<Medicine, String> typeColumn;
    @FXML
    private TableColumn<Medicine, String> purposeColumn;
    @FXML
    private TableView<Medicine> SelectedMedicineTable;
    @FXML
    private TableColumn<Medicine, String> SelectedMedicineName;
    @FXML
    private TableColumn<Medicine, Integer> DosageMed;
    
    private ObservableList<Medicine> SelectedMedicine1 = FXCollections.observableArrayList();
    @FXML
    private Button MedAddButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        NoteBox = FXCollections.observableArrayList();
        Cont = new Controller();
        HistoryPane.setVisible(false);
        MedicinePane.setVisible(false);
        
        ObservableList<Medicine> availableMedicines = FXCollections.observableArrayList(Cont.GetAllMedicine());
        
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));

        purposeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPurpose()));

        tableViewMedicines.setItems(availableMedicines);
        
        SelectedMedicineName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        
        DosageMed.setCellValueFactory(new PropertyValueFactory<>("Dosage"));
        
        SelectedMedicineTable.setItems(SelectedMedicine1);
        
    }    
    
     @FXML
    private void goback(ActionEvent event) throws IOException {
        try {
            System.out.println("Going back...");
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sda/VetViews/ManagePrescription.fxml"));
            Parent managePetsScreen = loader.load();
            
            ManagePrescriptionController controller = loader.getController();
            controller.setSelectedPet(SelectedPet);
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(managePetsScreen);
            stage.setScene(scene);
            stage.setTitle("Manage Prescription");
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
    private void AddinNoteCombo(KeyEvent event) 
    {
        if (event.getCode().equals(KeyCode.ENTER))
        {
            if(NoteField.getText().matches("^[A-Za-z0-9 ]+$"))
            {
                if (!NoteField.getText().isEmpty() && !NoteBox.contains(NoteField.getText().trim()))
                {
                    NoteBox.add(NoteField.getText().trim());
                    NoteComb.getSelectionModel().select(0);
                }

                NoteComb.setItems(NoteBox);
                NoteComb.getSelectionModel().select(0);
                NoteField.clear();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Input in notes");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Input in notes.");
                alert.showAndWait();
                return;
            }
        }
        
    }
    
    @FXML
    private void DeleteFromNoteCombo(KeyEvent event) 
    {
         if (event.getCode().equals(KeyCode.BACK_SPACE))
        {
            NoteBox.remove(NoteComb.getSelectionModel().getSelectedItem());
            NoteComb.getItems().remove(NoteComb.getSelectionModel().getSelectedItem());
            NoteComb.getSelectionModel().select(0);
        }
    }

    @FXML
    private void SavePlan(ActionEvent event) throws IOException 
    {
        
        if(NoteComb.getSelectionModel().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Input in Instructions.");
            alert.showAndWait();
            return;
        }
        
        ArrayList<Medicine> Meds = new ArrayList<>(SelectedMedicineTable.getItems());
        ArrayList<String> Note = new ArrayList<>(NoteComb.getItems());
        
        Cont.CreatePrescription(SelectedPet,Note,Meds);
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Prescription created successfully");
        alert.setHeaderText(null);
        alert.setContentText("Prescription has been created successfully.");
        alert.showAndWait();
        
        godashboard(event);
    }

    @FXML
    private void RecordPaneOpen(ActionEvent event) 
    {
        HistoryPane.setVisible(false);
        MedicinePane.setVisible(false);
    }
    
    @FXML
    private void HistoryPaneOpen(ActionEvent event)
    {
        HistoryPane.setVisible(true);
        MedicinePane.setVisible(false);
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
    private void MedicinePaneOpen(ActionEvent event)
    {
        MedicinePane.setVisible(true);
    }

    @FXML
    private void AddMedicine(MouseEvent event) 
    {
        if (event.getClickCount() == 2) 
        {
           Action();
        }
    }
    
    @FXML
    private void AddMedicine1(MouseEvent event) 
    {
       
        Action();
        
    }
    
    private void Action()
    {
        Medicine selectedMedicine = tableViewMedicines.getSelectionModel().getSelectedItem();

            if (selectedMedicine != null)
            {
                TextInputDialog dialog = new TextInputDialog();

                dialog.setTitle("Input Dosage");
                dialog.setContentText("Enter the Dosage of Medicine (Only Numbers)");
                dialog.showAndWait();  
                String result = dialog.getEditor().getText();

                if(result != null && result.matches("[0-9]+"))
                {

                    int Quan = Integer.parseInt(result);

                    Medicine NewMedicine = new Medicine(selectedMedicine.getMedicineId(),selectedMedicine.getName(),selectedMedicine.getType(),selectedMedicine.getPurpose()
                                                        ,selectedMedicine.getQuantity(),selectedMedicine.getPrice());
                    NewMedicine.setDosage(Quan);

                    SelectedMedicine1.add(NewMedicine);

                    SelectedMedicineTable.refresh();

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
