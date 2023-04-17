package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Andrew Miller
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.LocalDateStringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListScreenController implements Initializable {
    private static ObservableList<item> currentList   = FXCollections.observableArrayList();
    private static ObservableList<item> selectedItems = FXCollections.observableArrayList();
    private static ObservableList<item> currentItems  = FXCollections.observableArrayList();
    private static boolean selectedAll = false;
    public  boolean testing = false;

    // button(s)
    @FXML private Button returnToMenuButton;

    // filter box
    @FXML private ChoiceBox filterBox;

    // tableview + columns
    @FXML private TableView<item> listDisplay;
    @FXML private TableColumn<item, String> itemDesc;
    @FXML private TableColumn<item, LocalDate> itemDue;
    @FXML private TableColumn<item, CheckBox> completeSlot;
    @FXML private TableColumn<item, CheckBox> selectedSlot;

    // setter methods
    public static void setLists(ObservableList<item> ol){ currentList = ol; currentItems = ol; }
    public static void setCurrentItems(ObservableList<item> ol){
        currentItems = ol;
    }
    // modifier method
    public static void editSelected(boolean b,item i){
        if(b)
            ListScreenController.selectedItems.add(i);
        else
            ListScreenController.selectedItems.remove(i);
    }
    // getters
    public static ObservableList<item> getAllItems(){
        return currentList;
    }
    public static ObservableList<item> getCurrentItems(){
        return currentItems;
    }
    public static ObservableList<item> getSelectedItems(){
        return selectedItems;
    }

    // intialize empty O(bservable)List
    // loop through arraylist
    // for each string in arraylist
    //    convert string to item
    //    store item in OList
    // return OList
    public static ObservableList<item> listToOList(ArrayList<String> al){
        ObservableList<item> ret = FXCollections.observableArrayList();
        for (String str: al){
            ret.add( item.makeIntoItem(str) );
        }
        return ret;
    }

    // prompt warning gui as popup
    public void returnToMenuClicked(ActionEvent event) throws IOException {
        SceneController.createPopupWindow(event, "WarningScreenController",returnToMenuButton);
    }

    /*
     * clear selected items list
     * change value of selectedAll field
     * loop through currentList
     *     set item's selected box to selectedAll
     *     modify the selectedList
     */
    public void toggleSelectionClicked(){
        ListScreenController.selectedItems.removeAll();
        ListScreenController.selectedAll = !selectedAll;
        for (item i : ListScreenController.currentList) {
            ListScreenController.editSelected(ListScreenController.selectedAll, i);
            i.getSelected().setSelected(ListScreenController.selectedAll);
        }
    }

    // prompt file explorer and wait for result
    // if result then
    //    loop through "currentList"
    //        write item to file
    //    return true
    // otherwise
    //    return false
    public void saveToFileClicked(){
        String fp = FileHandler.promptFile();
        if(fp != null && fp != ""){
            FileHandler.writeToFile(currentList,fp);
        }
    }
    // get prev size of list
    // create a new item in the list
    // update ui
    // return new size of list (for testing that adding to list works)
    public boolean addNewItemClicked(){
        int oldSize = currentList.size();
        System.out.println(testing);
        item newItem = (testing) ? new item(false) : new item();
        currentItems.add(newItem);
        currentList.add(newItem);
        if(!testing)
            listDisplay.setItems(currentItems);
        return (oldSize == currentList.size());
    }

    // loop through "selectedItems"
    //    delete/remove item from the 2 main lists
    // update ui with the 'new' list
    // make "selectedItems" empty
    // return the new list
    public ObservableList<item> deleteSelectedClicked(){
        for (item i: selectedItems) {
            currentList.remove(i);
            currentItems.remove(i);
        }
        selectedItems.removeAll();
        if(!testing)
            listDisplay.setItems(currentItems);
        return currentList;
    }

    // call the filterchanged function in eventhandler class
    public void filterChanged(){
        String val = (String)filterBox.getValue();
        listDisplay.setItems(EventHandlerClass.filterChanged(val));
    }

    /*
     * get selected item
     * if that item is not null (it exists)
     *    set that item's description to the new value of the cell given by the user
     */
    public void editDescription(TableColumn.CellEditEvent<item,String> event){
        item selectedItem = listDisplay.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            selectedItem.setDescription(event.getNewValue());
        }
    }
    /*
     * get selected item
     * if that item is not null (it exists)
     *    set that item's due date to the new value of the cell given by the user
     */
    public void editDueDate(TableColumn.CellEditEvent<item,LocalDate> event) throws DateTimeException {
        item selectedItem = listDisplay.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            selectedItem.setDueDate(event.getNewValue());
        }
    }

    @Override
    // set up the filterbox (initialize and populate)
    // set up each column to display proper values
    // set up the two main item columns to be editable
    // set the items of the table to the currentList
    public void initialize(URL url, ResourceBundle rb){
        // filter box
        filterBox.setValue("No filter");
        filterBox.setItems(FXCollections.observableArrayList("No filter", "Complete", "Incomplete","Selected"));

        // set up columns
        itemDesc.setCellValueFactory(new PropertyValueFactory<item,String>(("description").toString()));
        itemDesc.setCellFactory(TextFieldTableCell.forTableColumn());
        itemDue.setCellValueFactory(new PropertyValueFactory<item,LocalDate>("dueDate"));
        itemDue.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));

        completeSlot.setCellValueFactory(new PropertyValueFactory<item,CheckBox>("complete"));
        selectedSlot.setCellValueFactory(new PropertyValueFactory<item,CheckBox>("selected"));

        // display data
        listDisplay.setEditable(true);
        listDisplay.setItems(currentItems);
    }
}
