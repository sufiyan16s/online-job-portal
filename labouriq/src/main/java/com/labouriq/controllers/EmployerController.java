package com.labouriq.controllers;

import com.labouriq.dao.JobDAO;
import com.labouriq.model.Job;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class EmployerController {

    @FXML private StackPane contentPane;
    @FXML private TableView<Job> jobsTable;
    @FXML private Label jobCountLabel;
    @FXML private Button newJobButton;

    private final JobDAO jobDAO = new JobDAO();

    @FXML
    public void initialize() {
        loadJobs();
    }

    private void loadJobs() {
        // TODO: replace demo employer id with logged-in employer id
        int demoEmployerId = 2;

        try {
            var jobs = jobDAO.findByEmployerId(demoEmployerId);
            jobsTable.getItems().setAll(jobs);
            jobCountLabel.setText(jobs.size() + " jobs");
        } catch (Exception e) {
            e.printStackTrace();
            jobCountLabel.setText("0 jobs");
        }
    }

    @FXML
    private void onNewJob() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/post_job.fxml"));
            Parent root = loader.load();

            // Get controller & inject employer ID
            PostJobController ctrl = loader.getController();
            int demoEmployerId = 2;   // Replace with session user later
            ctrl.setEmployerId(demoEmployerId);

            Stage stage = new Stage();
            stage.setTitle("Post Job - WorkNearMe");

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            stage.setScene(scene);
            stage.initOwner(contentPane.getScene().getWindow());
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            stage.showAndWait();

            // Refresh table after posting job
            loadJobs();

        } catch (Exception e) {
            e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR,
                    "Unable to open Post Job window: " + e.getMessage(),
                    ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void onLogout() {
        Stage stage = (Stage) contentPane.getScene().getWindow();
        FXRouter.goToLogin(stage);
    }

    // Placeholder navigation functions (to implement later)
    @FXML private void showJobs() {}
    @FXML private void showPostJob() {}
    @FXML private void showApplications() {}
    @FXML private void showMessages() {}
    @FXML private void showStats() {}
}
