package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Andrew Miller
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

public class EventHandlerClass {

    // if the event happened to a checkbox
    //    get the checkbox
    //    send it & item to the specific handlefunc
    public static void handle(Object event,item i){
        if (event instanceof CheckBox){
            CheckBox cb = (CheckBox) event;
            handleCbStuff(cb.getParent().getId(),cb.selectedProperty().getValue(),i);
        }
    }

    /* if the choice is selected
     *    set current items to all of the selected items
     *    return list
     * if choice is complete
     *    make temp list = getCompleteItems() callback
     *    set current items = temp list
     *    return current items
     * if choice is incomplete
     *    repeat code above but replace with appropriate function
     * otherwise
     *    return all items (no filter)
     */
    public static ObservableList<item> filterChanged(String val){
        if(val.equals("Selected")){
            ListScreenController.setCurrentItems(ListScreenController.getSelectedItems());
            return ListScreenController.getCurrentItems();
        } else if(val.equals("Complete")){
            ObservableList<item> temp_ol = getCompleteItems();
            ListScreenController.setCurrentItems(temp_ol);
            return ListScreenController.getCurrentItems();
        } else if(val.equals("Incomplete")){
            ObservableList<item> temp_ol = getIncompleteItems();
            ListScreenController.setCurrentItems(temp_ol);
            return ListScreenController.getCurrentItems();
        }
        return ListScreenController.getAllItems();
    }

    // if the checkbox is in the selected column
    //    change the selected list
    // elseif it's in the completed column
    //    set that item's completed boolean value to true
    // return the column ID
    public static String handleCbStuff(String id,boolean checked,item i){
        if(id.equals("selectedSlot")){
            ListScreenController.editSelected(checked,i);
        } else if (id.equals("completeSlot")){
            i.setCompleteBool(checked);
        }
        return id;
    }

    // make new temporary OL
    // loop through all the items
    // if the item is completed
    //    add it to temporary OL
    // return temporary OL
    public static ObservableList<item> getCompleteItems(){
        ObservableList<item> temp_ol = FXCollections.observableArrayList();
        for (item i: ListScreenController.getAllItems()) {
            if(i.isCompleteBool()){
                temp_ol.add(i);
            }
        }
        return temp_ol;
    }
    public static ObservableList<item> getIncompleteItems(){
        ObservableList<item> temp_ol = FXCollections.observableArrayList();
        for (item i: ListScreenController.getAllItems()) {
            if(!i.isCompleteBool()){
                temp_ol.add(i);
            }
        }
        return temp_ol;
    }
}
