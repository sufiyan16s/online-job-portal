package com.labouriq.controllers;

import com.labouriq.dao.UserDAO;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SignupController {

    // ===================== FXML BINDINGS =====================
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private CheckBox termsCheck;
    @FXML private ToggleGroup userTypeGroup;
    @FXML private Button signupButton;
    @FXML private Label messageLabel;

    private final UserDAO userDAO = new UserDAO();

    // ===================== SIGNUP ACTION =====================
    @FXML
    private void onSignUp(ActionEvent event) {

        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        // 1️⃣ Get selected user type
        ToggleButton selectedToggle =
                (ToggleButton) userTypeGroup.getSelectedToggle();

        if (selectedToggle == null) {
            showError("Please select Job Seeker or Employer.");
            return;
        }

        // Convert UI role → DB role
        String role = selectedToggle.getText().equalsIgnoreCase("Employer")
                ? "EMPLOYER"
                : "JOBSEEKER";

        // 2️⃣ Validation
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showError("All fields are required.");
            shake(signupButton);
            return;
        }

        if (password.length() < 6) {
            showError("Password must be at least 6 characters.");
            shake(passwordField);
            return;
        }

        if (!termsCheck.isSelected()) {
            showError("You must accept terms & conditions.");
            shake(termsCheck);
            return;
        }

        // 3️⃣ Save user to DB (hashed password)
        try {
            boolean success =
                    userDAO.createUser(name, email, password, role);

            if (!success) {
                showError("Email already exists.");
                return;
            }

            showSuccess("Account created successfully!");
            goToLogin(event);

        } catch (Exception e) {
            e.printStackTrace();
            showError("System error. Please try again.");
        }
    }

    // ===================== NAVIGATION =====================
    @FXML
    private void onBackToLogin(ActionEvent event) {
        goToLogin(event);
    }

    private void goToLogin(ActionEvent event) {
        try {
            Parent root =
                    FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));

            Stage stage =
                    (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("LabourIQ - Login");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ===================== UI HELPERS =====================
    private void showError(String msg) {
        messageLabel.setText(msg);
        messageLabel.setStyle("-fx-text-fill:#e74c3c;");
        messageLabel.setVisible(true);
    }

    private void showSuccess(String msg) {
        messageLabel.setText(msg);
        messageLabel.setStyle("-fx-text-fill:#2ecc71;");
        messageLabel.setVisible(true);
    }

    private void shake(Node node) {
        TranslateTransition t =
                new TranslateTransition(Duration.millis(50), node);
        t.setByX(10);
        t.setCycleCount(4);
        t.setAutoReverse(true);
        t.play();
    }
}
