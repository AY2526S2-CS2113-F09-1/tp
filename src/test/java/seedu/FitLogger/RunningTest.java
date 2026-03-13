package seedu.FitLogger;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests the functionality of the Running class, including inheritance
 * from Workout and file string formatting.
 */
public class RunningTest {

    @Test
    public void testRunningInitialization() {
        LocalDate date = LocalDate.of(2026, 3, 13);
        Running run = new Running("Morning Jog", date, 5.0);

        // Test if fields are assigned correctly
        assertEquals("Morning Jog", run.description, "Description should match constructor input");
        assertEquals(date, run.date, "Date should match constructor input");
        assertEquals(5.0, run.distance, 0.001, "Distance should match constructor input");

        // Test default status
        assertFalse(run.isDone, "New workout should have isDone as false");
    }

    @Test
    public void testMarkAsDone() {
        Running run = new Running("Sprint", LocalDate.now(), 2.0);
        run.markAsDone();
        assertTrue(run.isDone, "isDone should be true after calling markAsDone()");
    }

    @Test
    public void testToFileFormat() {
        LocalDate date = LocalDate.of(2026, 3, 13);
        Running run = new Running("Tempo Run", date, 8.5);

        // Check format for uncompleted run
        String expectedUncompleted = "R | 0 | Tempo Run | 2026-03-13 | 8.5";
        assertEquals(expectedUncompleted, run.toFileFormat(), "Uncompleted file format mismatch");

        // Check format after completion
        run.markAsDone();
        String expectedCompleted = "R | 1 | Tempo Run | 2026-03-13 | 8.5";
        assertEquals(expectedCompleted, run.toFileFormat(), "Completed file format mismatch");
    }

    @Test
    public void testInvalidInputHandling() {
        // Example of using fail() - you can expand this when you add validation logic
        try {
            Running run = new Running(null, null, -1.0);
            // If you eventually add checks for nulls or negative distance,
            // you would uncomment the line below:
            // fail("Should have thrown an exception for invalid inputs");
        } catch (Exception e) {
            // Success: Exception caught
        }
    }
}
