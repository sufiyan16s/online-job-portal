package com.labouriq.controllers;

import com.labouriq.dao.UserDAO;
import com.labouriq.util.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;

public class ProfileController {

    @FXML private Label resumeLabel;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    private void onUploadResume() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
        );

        File file = chooser.showOpenDialog(null);
        if (file == null) return;

        try {
            userDAO.updateResume(Session.getUserId(), file.getAbsolutePath());
            resumeLabel.setText("Uploaded: " + file.getName());
        } catch (Exception e) {
            e.printStackTrace();
            alert("Resume upload failed");
        }
    }

    private void alert(String msg) {
        new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK).showAndWait();
    }
}
