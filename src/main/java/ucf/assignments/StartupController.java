package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Andrew Miller
 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.ArrayList;

public class StartupController{

    @FXML private Button createNewButton;
    @FXML private Button openExistingButton;

    // don't actually create anything because it's already made static
    // prompt screenchanger
    public void createNewList(ActionEvent event) throws IOException {
        SceneController.switchToScene(event, "ListScreenController",null);
    }

    // prompt file from FileHandler and store into a string
    // if they didn't cancel the request
    //    read full file into temporary ArrayList
    //    call listctrlr.setList to (listctrlr.listToOList)
    //    switch screens
    //    return true (success)
    // otherwise
    //    return false (failure)
    public void openExistingList(ActionEvent event) throws IOException {
        String fp = FileHandler.promptFile();
        if(fp != null && fp != ""){
            ArrayList<String> temp = FileHandler.readFromFile(fp);
            ListScreenController.setLists(ListScreenController.listToOList(temp));
            SceneController.switchToScene(event, "ListScreenController",null);
        }
    }
}
