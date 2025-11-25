package com.labouriq.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JobSeekerController {

    @FXML private StackPane contentPane;
    @FXML private ListView<String> recommendationList;
    @FXML private Label recCount;

    @FXML
    public void initialize() {
        loadRecommendations();
    }

    private void loadRecommendations() {
        // TODO: Wire Recommendation Engine / JobDAO - placeholder sample
        recommendationList.getItems().clear();
        recommendationList.getItems().addAll(
                "Software Engineer — 2 days ago",
                "Construction Worker — 1 day ago",
                "Site Supervisor — 3 days ago"
        );
        recCount.setText(recommendationList.getItems().size() + " recommendations");
    }

    @FXML
    private void onRefresh() {
        loadRecommendations();
    }

    @FXML
    private void onLogout() {
        Stage stage = (Stage) contentPane.getScene().getWindow();
        FXRouter.goToLogin(stage);
    }

    @FXML private void showSearch() {}
    @FXML private void showApplications() {}
    @FXML private void showProfile() {}
    @FXML private void showSaved() {}
    @FXML private void showRecommendations() {}
}
