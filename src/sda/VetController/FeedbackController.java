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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import TestMain.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Kamran Shahid
 */
public class FeedbackController 
{

    private Stage stage;
    private Scene scene;
    @FXML
    private TableView<FeedBack> FeedbackTable;
    @FXML
    private TableColumn<FeedBack, String> Name;
    @FXML
    private TableColumn<FeedBack, String> Rate;
    @FXML
    private TableColumn<FeedBack, String> Comment;
    @FXML
    private TableColumn<FeedBack, LocalDate> Date;
    
    Controller Cont = new Controller();
    
    @FXML
    private void initialize() 
    {
        ObservableList<FeedBack> FeedList = FXCollections.observableArrayList(Cont.GetFeedbacks());
       
        Name.setCellValueFactory(cellData -> new SimpleStringProperty(Cont.getVet().getName()));
        
        Rate.setCellValueFactory(cellData -> new SimpleStringProperty(Double.toString(cellData.getValue().getRating())));
        
        Comment.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getComments()));
        
        Date.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getDate()));

        FeedbackTable.setItems(FeedList);
    }
    
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
    
}
