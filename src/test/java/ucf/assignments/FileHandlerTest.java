package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    /*
     * make hardcoded expected arraylist
     * read from hardcoded file
     * compare the two lists
     */
    @Test void readFromFile() {
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("example/!!S_E_G/split!!/2021-07-08/!!S_E_G/split!!/false");
        expected.add("example/!!S_E_G/split!!/2021-07-08/!!S_E_G/split!!/false");
        expected.add("example/!!S_E_G/split!!/2021-07-08/!!S_E_G/split!!/true");

        ArrayList<String> actual = FileHandler.readFromFile("src/test/java/ucf/assignments/TEST_READ.txt");
        assertEquals(expected,actual);
    }

    /*
     * make list to write to file
     * write list to file
     * make expected list
     * read from file
     * compare that list to hardcoded list
     */
    @Test void writeToFile() {
        ObservableList<item> writeThis = FXCollections.observableArrayList();
        writeThis.add(new item(true));
        writeThis.add(new item(true));
        writeThis.add(new item(true));
        writeThis.add(new item(false));

        FileHandler.writeToFile(writeThis,"src/test/java/ucf/assignments/TEST_WRITE.txt");

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("example/!!S_E_G/split!!/2021-07-08/!!S_E_G/split!!/true");
        expected.add("example/!!S_E_G/split!!/2021-07-08/!!S_E_G/split!!/true");
        expected.add("example/!!S_E_G/split!!/2021-07-08/!!S_E_G/split!!/true");
        expected.add("example/!!S_E_G/split!!/2021-07-08/!!S_E_G/split!!/false");

        ArrayList<String> actual = FileHandler.readFromFile("src/test/java/ucf/assignments/TEST_WRITE.txt");

        assertEquals(expected,actual);
    }
}