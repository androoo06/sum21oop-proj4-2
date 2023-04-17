package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Andrew Miller
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    private static Stage newStage;
    private static ActionEvent savedEvent;

    /*
     * Load the fxmlfile
     * assign stage
     * create new scene
     * set the scene & show it
     */
    public static void switchToScene(ActionEvent event, String fileName,Stage st) throws IOException {
        Parent root = FXMLLoader.load(SceneController.class.getResource(fileName+".fxml"));
        Stage stage = (event!=null) ? (Stage)((Node)event.getSource()).getScene().getWindow() : st;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /*
     * if the id of the button that called the function is returnToMenuButton
     *    popup new window and all of its components
     * otherwise
     *    close the stage
     *    if the id of the button is ContinueButton
     *       switch the scene to the homescreen (startupctrlr)
     *    otherwise
     *       don't do anything else
     */
    public static void createPopupWindow(ActionEvent event, String fileName, Button owner) throws IOException {
        if(owner.getId().equals("returnToMenuButton")){
            savedEvent = event;
            newStage = new Stage();
            Parent root = FXMLLoader.load(SceneController.class.getResource(fileName + ".fxml"));
            newStage.setScene(new Scene(root));
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.initOwner(owner.getScene().getWindow());
            newStage.showAndWait();
        } else{
            newStage.close();
            if(owner.getId().equals("ContinueButton")){
                switchToScene(savedEvent,fileName,null);
            }
        }
    }
}
