package com.labouriq.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminUsersController implements Initializable {

    @FXML private TextField searchField;
    @FXML private ComboBox<String> roleFilter;
    @FXML private Label totalCountLabel;

    @FXML private TableView<User> usersTable;
    @FXML private TableColumn<User, Integer> colId;
    @FXML private TableColumn<User, String> colName;
    @FXML private TableColumn<User, String> colEmail;
    @FXML private TableColumn<User, String> colRole;
    @FXML private TableColumn<User, String> colStatus;
    @FXML private TableColumn<User, String> colJoined;

    // Dummy User Class for Table
    public static class User {
        private int id;
        private String name, email, role, status, joined;

        public User(int id, String name, String email, String role, String status, String joined) {
            this.id = id; this.name = name; this.email = email; this.role = role; this.status = status; this.joined = joined;
        }
        // Getters are required for PropertyValueFactory
        public int getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getRole() { return role; }
        public String getStatus() { return status; }
        public String getJoined() { return joined; }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 1. Setup Columns
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colJoined.setCellValueFactory(new PropertyValueFactory<>("joined"));

        // 2. Load Dummy Data
        loadData();

        // 3. Setup Filters
        roleFilter.setItems(FXCollections.observableArrayList("All Roles", "Job Seeker", "Employer", "Admin"));
    }

    private void loadData() {
        ObservableList<User> list = FXCollections.observableArrayList(
                new User(101, "Sufiyan Khan", "sufiyan@example.com", "Job Seeker", "Active", "2025-01-10"),
                new User(102, "Tech Solutions", "hr@techsol.com", "Employer", "Active", "2025-01-12"),
                new User(103, "John Doe", "john@badactor.com", "Job Seeker", "Banned", "2025-02-01")
        );
        usersTable.setItems(list);
        totalCountLabel.setText("Total: " + list.size() + " users");
    }

    @FXML private void onAddUser() { System.out.println("Add User Clicked"); }
    @FXML private void onEdit() { System.out.println("Edit User Clicked"); }
    @FXML private void onDelete() { System.out.println("Delete User Clicked"); }
    @FXML private void onRefresh() { loadData(); }
}