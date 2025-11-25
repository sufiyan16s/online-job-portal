package com.labouriq.controllers;

import com.labouriq.dao.ApplicationDAO;
import com.labouriq.model.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class ApplicationsController {

    @FXML private TableView<Application> appsTable;
    private final ApplicationDAO applicationDAO = new ApplicationDAO();

    // show applications for a job id (set by caller)
    private int jobId = -1;

    public void setJobId(int jobId) {
        this.jobId = jobId;
        loadApplications();
    }

    public void initialize() {
        // configure simple row display: you can set cell factories to show user names by joining UserDAO (later)
    }

    private void loadApplications() {
        if (jobId <= 0) return;
        try {
            List<Application> list = applicationDAO.findByJobId(jobId);
            ObservableList<Application> items = appsTable.getItems();
            items.clear();
            items.addAll(list);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Unable to load applications: " + e.getMessage()).showAndWait();
        }
    }

    @FXML
    private void onAccept() {
        Application sel = appsTable.getSelectionModel().getSelectedItem();
        if (sel == null) { show("Select application"); return; }
        updateStatus(sel, "ACCEPTED");
    }

    @FXML
    private void onReject() {
        Application sel = appsTable.getSelectionModel().getSelectedItem();
        if (sel == null) { show("Select application"); return; }
        updateStatus(sel, "REJECTED");
    }

    private void updateStatus(Application sel, String status) {
        try {
            boolean ok = applicationDAO.updateStatus(sel.getId(), status);
            if (ok) {
                sel.setStatus(status);
                appsTable.refresh();
                show("Application " + status.toLowerCase());
            } else show("Failed to update");
        } catch (Exception e) {
            e.printStackTrace();
            show("Error: " + e.getMessage());
        }
    }

    private void show(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK).showAndWait();
    }
}
