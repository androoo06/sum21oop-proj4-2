package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Andrew Miller
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;

import java.time.LocalDate;

public class item {
    private static final String returnStr = "/!!S_E_G/split!!/";

    private SimpleStringProperty description;
    private LocalDate dueDate;
    private CheckBox complete = null;
    private CheckBox selected = null;
    private boolean completeBool;

    /*
     * create a default item with example properties that the user can edit
     * * will be used when adding new item
     */
    public item(){
        this.description = new SimpleStringProperty("example");
        this.dueDate     = LocalDate.now();
        this.completeBool= false;
        this.initializeCheckboxes();
    }
    /*
     * create an item based on provided values
     * * will be used when loading an existing item
     */
    public item(String description,LocalDate dueDate, boolean complete){
        this.description = new SimpleStringProperty(description);
        this.dueDate     = dueDate;
        this.completeBool= complete;
        this.initializeCheckboxes();
        this.complete.setSelected(complete);//initialize the checkbox
    }
    /*
     * constructor for tests! Will not have checkboxes!
     * same as default constructor but with no checkbox init.
     */
    public item(boolean completeBool){
        this.description = new SimpleStringProperty("example");
        this.dueDate     = LocalDate.now();
        this.completeBool= completeBool;
    }
    /*
     * common method found in both constructors
     * create new checkboxes
     * set their actionevents to a handle function
     */
    public void initializeCheckboxes(){
        this.complete = new CheckBox();
        this.selected = new CheckBox();

        complete.setOnAction(this.handle);
        selected.setOnAction(this.handle);
    }

    // call appropriate wrapper function with the checkbox and current item object
    public EventHandler handle = (EventHandler<ActionEvent>) event -> {
        EventHandlerClass.handle(event.getSource(),this);
    };

    // getters and setters
    public boolean isCompleteBool() {
        return completeBool;
    }

    public void setCompleteBool(boolean completeBool) {
        this.completeBool = completeBool;
    }

    // if the char count is < 256 then set
    // if the char count is < 1 then return
    // otherwise cut it off with substring to 0-256 (256 not included
    public void setDescription(String description) {
        if(description.length() < 256)
            this.description.set(description);
        else if (description.length() == 0)
            return;
        else
            this.description.set(description.substring(0,256));
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /*** shows as unused but is used by fxml ***/
    public LocalDate getDueDate() {
        return dueDate;
    }

    public CheckBox getComplete() {
        return complete;
    }

    public void setComplete(CheckBox complete) {
        this.complete = complete;
    }

    public CheckBox getSelected() {
        return selected;
    }

    public void setSelected(CheckBox selected) {
        this.selected = selected;
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }
    /*******************************************/

    // decoding string into item:
    // split string by the returnStr variable
    // if the size is not 3 return null
    // make 3 new variables with each split segment
    // return new item constructor with those 3 variables
    public static item makeIntoItem(String s){
        String[] split = s.split(returnStr);
        if(split.length != 3)
            return null;
        String desc = split[0];
        LocalDate date = LocalDate.parse(split[1]);
        boolean complete = Boolean.parseBoolean(split[2]);

        return new item(desc,date,complete);
    }

    // format item to be split by the returnStr variable when reading from file
    // return 'returnStr' in front of every instance variable concatenated into a string
    @Override
    public String toString(){
        return this.description.getValue()+returnStr+this.dueDate.toString()+
               returnStr+this.completeBool;
    }
}
