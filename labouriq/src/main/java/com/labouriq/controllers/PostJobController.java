package com.labouriq.controllers;

import com.labouriq.dao.JobDAO;
import com.labouriq.model.Job;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class PostJobController {

    @FXML private TextField titleField;
    @FXML private TextField locationField;
    @FXML private TextField salaryField;
    @FXML private TextArea descArea;
    @FXML private Label messageLabel;
    @FXML private Button postBtn;

    private final JobDAO jobDAO = new JobDAO();
    private int employerId = -1;

    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }

    @FXML
    private void onPostJob() {
        messageLabel.setText("");
        String title = titleField.getText() != null ? titleField.getText().trim() : "";
        String location = locationField.getText() != null ? locationField.getText().trim() : "";
        String salary = salaryField.getText() != null ? salaryField.getText().trim() : "";
        String desc = descArea.getText() != null ? descArea.getText().trim() : "";

        if (title.isEmpty()) { messageLabel.setText("Enter job title"); return; }
        if (employerId <= 0) { messageLabel.setText("Employer not set (login required)"); return; }

        Job j = new Job(employerId, title, desc, location, salary);
        try {
            jobDAO.create(j);
            Alert ok = new Alert(Alert.AlertType.INFORMATION, "Job posted successfully.", ButtonType.OK);
            ok.showAndWait();
            // close window
            Stage st = (Stage) postBtn.getScene().getWindow();
            st.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            messageLabel.setText("Error: " + ex.getMessage());
        }
    }

    @FXML
    private void onCancel() {
        Stage st = (Stage) postBtn.getScene().getWindow();
        st.close();
    }
}
