package fitlogger;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

import fitlogger.workout.StrengthWorkout;

public class StrengthWorkoutTest {

    @Test
    public void toFileFormat_validInputs_correctlyFormattedString() {
        LocalDate testDate = LocalDate.parse("2026-03-13");
        StrengthWorkout workout = new StrengthWorkout("Bench Press", 80.5, 3, 8, testDate);

        String expected = "L | Bench Press | 2026-03-13 | 80.5 | 3 | 8";
        assertEquals(expected, workout.toFileFormat());
    }

    @Test
    public void toString_validInputs_correctlyFormattedString() {
        LocalDate testDate = LocalDate.parse("2026-03-13");
        StrengthWorkout workout = new StrengthWorkout("Squat", 100.0, 5, 5, testDate);

        String expected = "[Lift] Squat (Date: 2026-03-13) (100.0kg, 5 sets of 5 reps)";
        assertEquals(expected, workout.toString());
    }

    @Test
    public void constructor_storesFieldsCorrectly() {
        LocalDate date = LocalDate.parse("2026-03-13");
        StrengthWorkout workout = new StrengthWorkout("Deadlift", 120.0, 4, 6, date);
        assertEquals("Deadlift", workout.getDescription());
        assertEquals(120.0, workout.getWeight(), 0.001);
        assertEquals(4, workout.getSets());
        assertEquals(6, workout.getReps());
        assertEquals(date, workout.getDate());
    }

    @Test
    public void constructor_zeroWeight_isAllowed() {
        // Bodyweight exercises
        StrengthWorkout workout = new StrengthWorkout("Pull-up", 0.0, 3, 10, LocalDate.now());
        assertEquals(0.0, workout.getWeight(), 0.001);
    }

    @Test
    public void toFileFormat_strengthWorkout_savesAndLoadsCorrectly() {
        // Round-trip: what we save matches what Storage expects to parse
        LocalDate date = LocalDate.parse("2026-03-13");
        StrengthWorkout workout = new StrengthWorkout("Squat", 100.0, 5, 5, date);
        String[] fields = workout.toFileFormat().split("\\s*\\|\\s*");
        assertEquals("L", fields[0]);
        assertEquals("Squat", fields[1]);
        assertEquals("2026-03-13", fields[2]);
        assertEquals("100.0", fields[3]);
        assertEquals("5", fields[4]);
        assertEquals("5", fields[5]);
    }
}
