package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class itemTest {

    /* FOR BOTH:
     * create 2 hardcoded items with values
     * compare the two
     */
    @Test void testToString() {
        item i1 = new item(true);
        item i2 = new item(false);
        assertNotEquals(i1.toString(),i2.toString());
    }
    @Test void testToString2() {
        item i1 = new item(true);
        item i2 = new item(true);
        assertEquals(i1.toString(),i2.toString());
    }

    /***
     * makeIntoItem cannot be unit tested or incorporated at all into any unit test
     * because it requires initializing checkboxes
     ***/
}