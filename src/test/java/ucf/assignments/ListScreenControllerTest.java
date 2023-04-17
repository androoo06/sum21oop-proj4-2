package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListScreenControllerTest {
    /*
     * add an item to selected
     * run the method
     * compare actual list's size to 1 (assertTrue)
     */
    @Test void deleteSelectedClicked() {
        ListScreenController.setLists(FXCollections.observableArrayList());

        // nonstatic issue happened, borrowed code from above test
        ListScreenController LC = new ListScreenController();
        LC.testing = true;

        LC.deleteSelectedClicked();
        assertTrue(LC.getSelectedItems().size() == 1);
    }
    /*
     * make expected list
     * set both lists to empty
     * add an item to selected list
     * compare results (use the tostrings because the observableIds are different)
     */
    @Test void editSelected() {
        ObservableList<item> expected = FXCollections.observableArrayList(new item(true));
        ListScreenController.editSelected(true,new item(true));
        assertEquals(expected.get(0).toString(), ListScreenController.getSelectedItems().get(0).toString());
        ListScreenController.editSelected(false,new item(true));
    }

    /*** when testing, set "testing" to true in ListScreenController.addNewItemClicked() ***/
    /*
     * this method was set up to be a 1-line test, always assertFalse().
     */
    @Test void addNewItemClicked() {
        // since the method is actually nonstatic, i have to instantiate a new LCController and change its testing value
        // in order to use the method
        ListScreenController LC = new ListScreenController();
        LC.testing = true;
        LC.addNewItemClicked();
        assertFalse(LC.addNewItemClicked());
        ListScreenController.setLists(FXCollections.observableArrayList());
        LC.editSelected(false,new item(true));
    }
}