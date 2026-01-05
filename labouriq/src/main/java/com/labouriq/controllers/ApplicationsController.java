package com.labouriq.controllers;

import com.labouriq.dao.ApplicationDAO;
import com.labouriq.model.Application;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ApplicationsController {

    @FXML private TableView<Application> applicationsTable;

    private final ApplicationDAO dao = new ApplicationDAO();

    @FXML
    private void accept() {
        update("SHORTLISTED");
    }

    @FXML
    private void reject() {
        update("REJECTED");
    }

    private void update(String status) {
        Application app =
                applicationsTable.getSelectionModel().getSelectedItem();

        if (app == null) return;

        try {
            dao.updateStatus(app.getId(), status);
            applicationsTable.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
