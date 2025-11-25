package com.labouriq.controllers;

import com.labouriq.dao.UserDAO;
import com.labouriq.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private CheckBox rememberCheck;
    @FXML private Button signInButton;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    private void onSignIn() {
        String email = emailField.getText().trim();
        String pass = passwordField.getText().trim();

        if (email.isEmpty() || pass.isEmpty()) {
            showAlert("Please enter email and password");
            return;
        }

        try {
            User u = userDAO.findByEmailAndPassword(email, pass);
            if (u != null) {
                // Login success
                Stage stage = (Stage) signInButton.getScene().getWindow();

                switch (u.getRole().toUpperCase()) {
                    case "ADMIN":
                        FXRouter.goToAdmin(stage);
                        break;

                    case "EMPLOYER":
                        FXRouter.goToEmployer(stage);
                        break;

                    case "JOBSEEKER":
                    case "JOB_SEEKER":
                    case "SEEKER":
                        FXRouter.goToSeeker(stage);
                        break;

                    default:
                        showAlert("Unknown role: " + u.getRole());
                        return;
                }

            } else {
                showAlert("Invalid email or password.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Database error: " + e.getMessage());
        }
    }

    private void showAlert(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        a.showAndWait();
    }
}
