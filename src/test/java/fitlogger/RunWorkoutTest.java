package fitlogger;

import fitlogger.exception.FitLoggerException;
import fitlogger.workout.RunWorkout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the RunWorkout class to ensure proper initialization,
 * validation, and formatting.
 */
class RunWorkoutTest {
    private RunWorkout run;
    private LocalDate testDate;

    @BeforeEach
    public void setUp() throws FitLoggerException {
        testDate = LocalDate.of(2026, 3, 13);
        // Using a standard 5km, 30min run for setup
        run = new RunWorkout("Morning Jog", testDate, 5.0, 30.0);
    }

    @Test
    public void constructor_validInput_setsAllFields() {
        assertEquals("Morning Jog", run.getDescription());
        assertEquals(testDate, run.getDate());
        assertEquals(5.0, run.getDistance(), 0.001);
        assertEquals(30.0, run.getDurationMinutes(), 0.001);
    }

    @Test
    public void setDistance_validUpdate_updatesCorrectly() throws FitLoggerException {
        run.setDistance(10.5);
        assertEquals(10.5, run.getDistance(), 0.001);
    }

    @Test
    public void setDistance_negativeValue_throwsException() {
        assertThrows(FitLoggerException.class, () -> {
            run.setDistance(-1.0);
        });
    }

    @Test
    public void setDurationMinutes_zeroValue_throwsException() {
        assertThrows(FitLoggerException.class, () -> {
            run.setDurationMinutes(0);
        });
    }

    @Test
    public void setDurationMinutes_nonFiniteValue_throwsException() {
        assertThrows(FitLoggerException.class, () -> {
            run.setDurationMinutes(Double.NaN);
        });
    }

    @Test
    public void toFileFormat_standardInput_matchesExpectedString() {
        String expected = "R | Morning Jog | 2026-03-13 | 5.0 | 30.0";
        assertEquals(expected, run.toFileFormat());
    }

    @Test
    public void toString_standardInput_matchesExpectedString() {
        String expected = "[Run] Morning Jog (Date: 2026-03-13) (Distance: 5.0km, Duration: 30.0 mins)";
        assertEquals(expected, run.toString());
    }

    @Test
    public void setDescription_blankName_throwsException() {
        assertThrows(FitLoggerException.class, () -> {
            run.setDescription("   ");
        });
    }
}
