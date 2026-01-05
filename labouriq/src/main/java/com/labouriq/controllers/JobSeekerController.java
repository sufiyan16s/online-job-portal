package com.labouriq.controllers;

import com.labouriq.dao.ApplicationDAO;
import com.labouriq.dao.JobDAO;
import com.labouriq.model.Application;
import com.labouriq.model.Job;
import com.labouriq.util.Session;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class JobSeekerController {

    @FXML private TableView<Job> jobsTable;
    @FXML private TableView<Application> applicationsTable;

    private final JobDAO jobDAO = new JobDAO();
    private final ApplicationDAO applicationDAO = new ApplicationDAO();

    @FXML
    public void initialize() {
        loadJobs();
        loadMyApplications();
    }

    // 🔍 REQUIRED BY FXML
    @FXML
    private void showSearch() {
        loadJobs();
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK).showAndWait();
    }


    private void loadJobs() {
        try {
            List<Job> jobs = jobDAO.findAllOpenJobs();
            jobsTable.getItems().setAll(jobs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void applyJob() {
        Job selected = jobsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("Select a job first");
            return;
        }

        try {
            boolean success = applicationDAO.applyJob(
                    new Application(selected.getId(), Session.getUserId())
            );

            alert(success ? "Applied successfully" : "Already applied");
            loadMyApplications();

        } catch (Exception e) {
            e.printStackTrace();
            alert("Application failed");
        }
    }

    private void loadMyApplications() {
        try {
            applicationsTable.getItems()
                    .setAll(applicationDAO.getMyApplications(Session.getUserId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void alert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK).showAndWait();
    }


    @FXML
    private void showApplications() {
        try {
            FXRouter.goTo("applications");
        } catch (Exception e) {
            e.printStackTrace();
            alert("Unable to open applications page");
        }
    }

    @FXML
    private void showSaved() {
        try {
            FXRouter.goTo("saved_jobs"); // or whatever fxml you use
        } catch (Exception e) {
            e.printStackTrace();
            alert("Unable to open saved jobs");
        }
    }

    @FXML
    private void showProfile() {
        try {
            FXRouter.goTo("profile");
        } catch (Exception e) {
            e.printStackTrace();
            showError("Unable to open profile page");
        }
    }

    @FXML
    private void onLogout() {
        try {
            Session.logout();          // clear session
            FXRouter.goTo("login");    // go back to login
        } catch (Exception e) {
            e.printStackTrace();
            showError("Logout failed");
        }
    }

    @FXML
    private void onRefresh() {
        try {
            loadJobs();   // refresh job list
        } catch (Exception e) {
            e.printStackTrace();
            showError("Unable to refresh jobs");
        }
    }




}
