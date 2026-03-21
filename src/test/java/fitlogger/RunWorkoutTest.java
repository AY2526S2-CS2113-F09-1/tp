package fitlogger;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import fitlogger.workout.RunWorkout;

/**
 * Tests the functionality of the RunWorkout class, including inheritance
 * from Workout and file string formatting.
 */
public class RunWorkoutTest {

    @Test
    public void testRunWorkoutInitialization() {
        LocalDate date = LocalDate.of(2026, 3, 13);
        RunWorkout run = new RunWorkout("Morning Jog", date, 5, 0.20);

        // Test if fields are assigned correctly
        assertEquals("Morning Jog", run.getDescription(), "Description should match constructor input");
        assertEquals(date, run.getDate(), "Date should match constructor input");
        assertEquals(5, run.getDistance(), "Distance should match constructor input");
        assertEquals(0.2, run.getDurationMinutes(), "Time should match constructor input");
    }

    @Test
    public void testToFileFormat() {
        LocalDate date = LocalDate.of(2025, 7, 30);
        RunWorkout run = new RunWorkout("Tempo Run", date, 14, 50.3);

        // Check format for uncompleted run
        String expectedUncompleted = "R | Tempo Run | 2025-07-30 | 14.0 | 50.3";
        assertEquals(expectedUncompleted, run.toFileFormat(), "File format mismatch");
    }

    @Test
    public void testInvalidInputHandling() {
        // Example of using fail() - you can expand this when you add validation logic
        try {
            new RunWorkout(null, null, 0, 0);
            // If you eventually add checks for nulls or negative distance,
            // you would uncomment the line below:
            // fail("Should have thrown an exception for invalid inputs");
        } catch (Exception e) {
            // Success: Exception caught
        }
    }
}
