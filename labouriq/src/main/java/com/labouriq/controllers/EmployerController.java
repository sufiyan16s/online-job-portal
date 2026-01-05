package com.labouriq.controllers;

import com.labouriq.dao.JobDAO;
import com.labouriq.model.Job;
import com.labouriq.util.Session;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class EmployerController {

    @FXML private TableView<Job> jobsTable;
    @FXML private Label jobCountLabel;

    private final JobDAO jobDAO = new JobDAO();

    @FXML
    public void initialize() {
        loadJobs();
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK).showAndWait();
    }


    private void loadJobs() {
        try {
            List<Job> jobs = jobDAO.findByEmployer(Session.getUserId());
            jobsTable.getItems().setAll(jobs);
            jobCountLabel.setText(jobs.size() + " jobs");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // REQUIRED BY FXML
    @FXML
    private void showJobs() {
        loadJobs();
    }

    // REQUIRED BY FXML
    @FXML
    private void showApplications() {
        FXRouter.goTo("applications");
    }

    // REQUIRED BY FXML
    @FXML
    private void onNewJob() {
        FXRouter.goTo("post_job");
    }

    @FXML
    private void onLogout() {
        Session.logout();
        FXRouter.goTo("login");
    }

    @FXML
    private void showPostJob() {
        try {
            FXRouter.goTo("post_job");
        } catch (Exception e) {
            e.printStackTrace();
            showError("Unable to open Post Job page");
        }
    }

    @FXML
    private void showMessages() {
        try {
            FXRouter.goTo("messages");
        } catch (Exception e) {
            e.printStackTrace();
            showError("Unable to open messages");
        }
    }


}
