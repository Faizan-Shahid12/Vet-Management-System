package sda.vetmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import TestMain.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/sda/Views/LoginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 586, 395);
        stage.setTitle("Vet Management System - login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        Controller Cont = new Controller();
        Cont.MakeAppointSlots();
        
        launch();
    }
}