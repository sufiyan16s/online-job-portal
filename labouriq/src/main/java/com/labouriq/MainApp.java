package com.labouriq;

import com.labouriq.controllers.FXRouter;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        FXRouter.init(stage);
        FXRouter.goTo("login");
        stage.setTitle("LabourIQ");
    }

    public static void main(String[] args) {
        launch();
    }
}
