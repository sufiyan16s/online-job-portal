package com.labouriq.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

public class FXRouter {

    private static Stage stage;
    private static final Stack<Scene> history = new Stack<>();

    // INITIAL SETUP
    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void init(Stage primaryStage) {
        setStage(primaryStage);
    }

    // LOAD BY FXML NAME
    public static void goTo(String fxml) {
        try {
            Parent root = FXMLLoader.load(
                    FXRouter.class.getResource("/fxml/" + fxml + ".fxml")
            );

            Scene newScene = new Scene(root);

            if (stage.getScene() != null) {
                history.push(stage.getScene());
            }

            stage.setScene(newScene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔥 THIS METHOD WAS MISSING (FIX)
    public static void goBack() {
        if (!history.isEmpty()) {
            stage.setScene(history.pop());
            stage.show();
        }
    }

    // OPTIONAL SHORTCUTS (KEEP IF YOU USE THEM)
    public static void goToAdmin() {
        goTo("admin_dashboard");
    }

    public static void goToEmployer() {
        goTo("employer_dashboard");
    }

    public static void goToJobSeeker() {
        goTo("jobseeker_dashboard");
    }
}
