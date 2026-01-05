package com.labouriq.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;

public class AdminController {

    @FXML private Label totalUsersLabel;
    @FXML private Label totalJobsLabel;
    @FXML private TableView<?> activityTable;
    @FXML private StackPane contentPane;

    @FXML
    public void initialize() {
        totalUsersLabel.setText("8,420");
        totalJobsLabel.setText("1,205");
    }

    // ================= NAVIGATION =================

    @FXML
    private void showDashboard() {
        contentPane.getChildren().clear();
    }

    @FXML
    private void showUsers() {
        loadPage("/fxml/admin_users.fxml");
    }

    @FXML
    private void showPendingJobs() {
        loadPage("/fxml/admin_jobs.fxml");
    }

    @FXML
    private void showReports() {
        loadPage("/fxml/admin_reports.fxml");
    }

    @FXML
    private void showSettings() {
        System.out.println("Settings clicked");
    }

    @FXML
    private void onLogout() {
        FXRouter.goTo("login");
    }

    // ================= HELPER =================
    private void loadPage(String fxmlPath) {
        try {
            Parent view =
                    FXMLLoader.load(getClass().getResource(fxmlPath));
            contentPane.getChildren().setAll(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
