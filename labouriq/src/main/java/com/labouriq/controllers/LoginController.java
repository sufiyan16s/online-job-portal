package com.labouriq.controllers;

import com.labouriq.dao.UserDAO;
import com.labouriq.model.User;
import com.labouriq.util.Session;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    // ===================== FXML BINDINGS =====================
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private CheckBox rememberCheck;
    @FXML private ToggleGroup userTypeGroup;
    @FXML private Button signInButton;

    private final UserDAO userDAO = new UserDAO();

    // ===================== INIT =====================
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        passwordField.setOnAction(e -> onSignIn());
    }

    // ===================== LOGIN =====================
    @FXML
    private void onSignIn() {

        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        // 1️⃣ Basic validation
        if (email.isEmpty() || password.isEmpty()) {
            showError("Email and password are required.");
            shake(signInButton);
            return;
        }

        try {
            // 2️⃣ Authenticate from DB
            User user = userDAO.findByEmailAndPassword(email, password);

            if (user == null) {
                showError("Invalid email or password.");
                shake(signInButton);
                return;
            }

            // 3️⃣ Validate selected role (UI vs DB)
            ToggleButton selectedToggle =
                    (ToggleButton) userTypeGroup.getSelectedToggle();

            if (selectedToggle == null) {
                showError("Please select user type.");
                return;
            }

            String selectedRole =
                    selectedToggle.getText().equalsIgnoreCase("Employer")
                            ? "EMPLOYER"
                            : "JOBSEEKER";

            // Admin can login from any toggle
            if (!user.getRole().equals("ADMIN")
                    && !user.getRole().equals(selectedRole)) {
                showError("Incorrect user type selected.");
                return;
            }

            // 4️⃣ Start session
            Session.start(user);

            // 5️⃣ Route by role
            switch (user.getRole()) {
                case "ADMIN" -> FXRouter.goToAdmin();
                case "EMPLOYER" -> FXRouter.goToEmployer();
                case "JOBSEEKER" -> FXRouter.goToJobSeeker();
                default -> showError("Unknown role.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showError("System error. Please try again.");
        }
    }

    // ===================== NAVIGATION =====================
    @FXML
    private void goToSignup() {
        FXRouter.goTo("signup");
    }

    // ===================== UI HELPERS =====================
    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.showAndWait();
    }

    private void shake(Control node) {
        TranslateTransition t =
                new TranslateTransition(Duration.millis(50), node);
        t.setByX(10);
        t.setCycleCount(4);
        t.setAutoReverse(true);
        t.play();
    }
}
