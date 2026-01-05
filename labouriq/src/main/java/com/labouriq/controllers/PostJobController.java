package com.labouriq.controllers;

import com.labouriq.dao.JobDAO;
import com.labouriq.model.Job;
import com.labouriq.util.Session;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PostJobController {

    @FXML private TextField titleField;
    @FXML private TextField locationField;
    @FXML private TextField salaryField;
    @FXML private TextArea descriptionArea;

    private final JobDAO jobDAO = new JobDAO();

    @FXML
    private void onPostJob() {
        if (titleField.getText().isEmpty()) {
            alert("Title required");
            return;
        }

        Job job = new Job();
        job.setEmployerId(Session.getUserId());
        job.setTitle(titleField.getText());
        job.setLocation(locationField.getText());
        job.setSalary(salaryField.getText());
        job.setDescription(descriptionArea.getText());

        try {
            jobDAO.create(job);
            alert("Job posted successfully");
            FXRouter.goBack();
        } catch (Exception e) {
            e.printStackTrace();
            alert("Failed to post job");
        }
    }

    private void alert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK).showAndWait();
    }
}
