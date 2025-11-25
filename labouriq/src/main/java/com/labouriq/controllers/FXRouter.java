package com.labouriq.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXRouter {
    public static void goToLogin(Stage stage) {
        loadAndSet(stage, "/fxml/login.fxml", "WorkNearMe - Login");
    }

    public static void goToAdmin(Stage stage) {
        loadAndSet(stage, "/fxml/admin_dashboard.fxml", "WorkNearMe - Admin");
    }

    public static void goToEmployer(Stage stage) {
        loadAndSet(stage, "/fxml/employer_dashboard.fxml", "WorkNearMe - Employer");
    }

    public static void goToSeeker(Stage stage) {
        loadAndSet(stage, "/fxml/jobseeker_dashboard.fxml", "WorkNearMe - JobSeeker");
    }

    private static void loadAndSet(Stage stage, String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(FXRouter.class.getResource(fxmlPath));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(FXRouter.class.getResource("/css/styles.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle(title);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
