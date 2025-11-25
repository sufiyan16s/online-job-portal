package com.labouriq.controllers;



import com.labouriq.dao.UserDAO;
import com.labouriq.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * SignupController - handles the signup form and creates users via UserDAO.
 *
 * Note: This uses plain-text passwords for demo. Mark TODO to hash passwords before production.
 */
public class SignupController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private ChoiceBox<String> roleChoice;
    @FXML private CheckBox termsCheck;
    @FXML private Button signupButton;
    @FXML private Label messageLabel;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    public void initialize() {
        // default selection if not set in FXML
        if (roleChoice.getValue() == null) roleChoice.setValue("JOBSEEKER");
    }

    @FXML
    private void onSignUp() {
        // clear previous messages
        messageLabel.setText("");

        String name = nameField.getText() != null ? nameField.getText().trim() : "";
        String email = emailField.getText() != null ? emailField.getText().trim() : "";
        String password = passwordField.getText() != null ? passwordField.getText() : "";
        String role = roleChoice.getValue();

        // Basic validation
        if (name.isEmpty()) { messageLabel.setText("Enter your full name."); return; }
        if (email.isEmpty() || !email.contains("@")) { messageLabel.setText("Enter a valid email."); return; }
        if (password.length() < 6) { messageLabel.setText("Password must be at least 6 characters."); return; }
        if (!termsCheck.isSelected()) { messageLabel.setText("You must agree to the terms."); return; }

        // Create user model and call DAO
        User u = new User();
        u.setUsername(name);
        u.setEmail(email);
        u.setPassword(password); // TODO: hash for production
        u.setRole(role);

        try {
            User created = userDAO.create(u);
            if (created != null && created.getId() > 0) {
                // success -> show info and redirect to login
                Alert info = new Alert(Alert.AlertType.INFORMATION,
                        "Account created successfully. Please login.", ButtonType.OK);
                info.showAndWait();
                goToLogin();
            } else {
                messageLabel.setText("Failed to create account (duplicate email?).");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            messageLabel.setText("Error: " + ex.getMessage());
        }
    }

    @FXML
    private void onBackToLogin() {
        goToLogin();
    }

    private void goToLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
            Stage stage = (Stage) signupButton.getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setTitle("WorkNearMe - Login");
        } catch (IOException e) {
            e.printStackTrace();
            // fallback: show alert
            Alert err = new Alert(Alert.AlertType.ERROR, "Unable to load Login page.", ButtonType.OK);
            err.showAndWait();
        }
    }
}
