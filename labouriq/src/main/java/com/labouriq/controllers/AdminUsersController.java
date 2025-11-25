package com.labouriq.controllers;

import com.labouriq.dao.UserDAO;
import com.labouriq.model.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;

import java.util.List;

public class AdminUsersController {

    @FXML private TableView<User> usersTable;
    private final UserDAO userDAO = new UserDAO();

    public void initialize() {
        loadUsers();
    }

    private void loadUsers() {
        try {
            List<User> list = userDAO.findAll();
            ObservableList<User> items = usersTable.getItems();
            items.clear();
            items.addAll(list);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Unable to load users").showAndWait();
        }
    }

    @FXML
    private void onEdit() {
        User sel = usersTable.getSelectionModel().getSelectedItem();
        if (sel == null) { new Alert(Alert.AlertType.INFORMATION, "Select a user").showAndWait(); return; }
        // Simple inline edit: in a real app open edit dialog. For demo toggle role between EMPLOYER and JOBSEEKER
        String newRole = sel.getRole().equalsIgnoreCase("ADMIN") ? "ADMIN" : sel.getRole().equalsIgnoreCase("EMPLOYER") ? "JOBSEEKER" : "EMPLOYER";
        sel.setRole(newRole);
        try {
            userDAO.update(sel);
            usersTable.refresh();
            new Alert(Alert.AlertType.INFORMATION, "Updated role to " + newRole).showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Update failed: " + e.getMessage()).showAndWait();
        }
    }

    @FXML
    private void onDelete() {
        User sel = usersTable.getSelectionModel().getSelectedItem();
        if (sel == null) { new Alert(Alert.AlertType.INFORMATION, "Select a user").showAndWait(); return; }
        try {
            boolean ok = userDAO.delete(sel.getId());
            if (ok) { loadUsers(); new Alert(Alert.AlertType.INFORMATION, "User deleted").showAndWait(); }
            else new Alert(Alert.AlertType.ERROR, "Delete failed").showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).showAndWait();
        }
    }

    @FXML
    private void onRefresh() {
        loadUsers();
    }
}
