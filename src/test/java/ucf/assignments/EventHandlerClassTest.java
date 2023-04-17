package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventHandlerClassTest {

    /*** IN EACH TEST:
     * manipulate listscreencontroller's lists
     * create hardcoded equality list
     * compare the two (assertEquals)
     ***/
    @Test void filterChanged1() {
        ObservableList<item> expected = ListScreenController.getSelectedItems();

        EventHandlerClass.filterChanged("Selected");
        assertEquals(expected,ListScreenController.getCurrentItems());
    }
    @Test void filterChanged2() {
        ObservableList<item> expected = EventHandlerClass.getCompleteItems();

        EventHandlerClass.filterChanged("Complete");
        assertEquals(expected,ListScreenController.getCurrentItems());
    }
    @Test void filterChanged3() {
        ObservableList<item> expected = EventHandlerClass.getIncompleteItems();

        EventHandlerClass.filterChanged("Incomplete");
        assertEquals(expected,ListScreenController.getCurrentItems());
    }
    @Test void filterChanged4() {
        ObservableList<item> expected = ListScreenController.getAllItems();

        EventHandlerClass.filterChanged("No filter");
        assertEquals(expected,ListScreenController.getCurrentItems());
    }

    /* IN BOTH TESTS:
     * hardcode a dummy test item
     * see if it returns correctly (assertEquals)
     */
    @Test void handleCbStuff() {
        item t1 = new item(true);
        assertEquals(EventHandlerClass.handleCbStuff("selectedSlot",true,t1),"selectedSlot");
    }
    @Test void handleCbStuff2() {
        item t1 = new item(true);
        assertEquals(EventHandlerClass.handleCbStuff("completeSlot",true,t1),"completeSlot");
    }
}