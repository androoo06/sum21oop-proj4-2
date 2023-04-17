package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Andrew Miller
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class WarningScreenController {

    @FXML private Button ContinueButton;
    @FXML private Button CancelButton;

    // call the popupwindow function
    public void continueToHome(ActionEvent event) throws IOException {
        SceneController.createPopupWindow(event, "StartupController",ContinueButton);
    }

    // call the popupwindow function
    public void cancel(ActionEvent event) throws IOException {
        SceneController.createPopupWindow(event, "",CancelButton);
    }
}
