package com.labouriq.controllers;

import com.labouriq.dao.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AdminController {

    @FXML private StackPane contentPane;
    @FXML private Label totalUsersLabel;
    @FXML private Label totalJobsLabel;
    @FXML private TableView<?> activityTable;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    public void initialize() {
        loadSummary();
        // load recent activity placeholder (can be wired to DB later)
    }

    private void loadSummary() {
        try {
            int users = userDAO.findAll().size();
            totalUsersLabel.setText(String.valueOf(users));
            // For jobs count use a JobDAO (not included here) - placeholder
            totalJobsLabel.setText("—"); // replace with JobDAO.findAll().size()
        } catch (Exception e) {
            e.printStackTrace();
            totalUsersLabel.setText("0");
        }
    }

    @FXML
    private void onLogout() {
        // return to login screen
        Stage stage = (Stage) contentPane.getScene().getWindow();
        FXRouter.goToLogin(stage);
    }

    @FXML private void showDashboard() { /* content switching if needed */ }
    @FXML private void showUsers() { /* load user management UI - later */ }
    @FXML private void showPendingJobs() { /* load pending jobs UI - later */ }
    @FXML private void showSettings() { /* settings UI */ }
    @FXML private void showReports() { /* reports UI */ }
}
