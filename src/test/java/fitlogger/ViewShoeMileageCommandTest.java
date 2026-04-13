package fitlogger;

import fitlogger.command.ViewShoeMileageCommand;
import fitlogger.exception.FitLoggerException;
import fitlogger.ui.Ui;
import fitlogger.workout.RunWorkout;
import fitlogger.workoutlist.WorkoutList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the ViewShoeMileageCommand to ensure total distance is calculated correctly.
 */
public class ViewShoeMileageCommandTest {

    @Test
    public void execute_multipleRunWorkouts_correctTotalCalculated() throws FitLoggerException {
        // Arrange
        WorkoutList workouts = new WorkoutList();
        LocalDate today = LocalDate.now();

        // Using the full constructor: (description, date, distance, duration)
        workouts.addWorkout(new RunWorkout("Morning Run", today, 5.5, 30.0));
        workouts.addWorkout(new RunWorkout("Evening Jog", today, 4.5, 25.0));

        // Capture system output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            Ui ui = new Ui();
            ViewShoeMileageCommand command = new ViewShoeMileageCommand();

            // Act
            command.execute(null, workouts, ui, null);

            // Assert
            String output = outContent.toString();
            // Checking for formatted "10.00km" and plural "2 runs"
            assertTrue(output.contains("10.00km"), "Total distance should be 10.00km");
            assertTrue(output.contains("2 runs"), "Should reflect 2 runs");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void execute_singleRunWorkout_singularLabelUsed() throws FitLoggerException {
        // Arrange
        WorkoutList workouts = new WorkoutList();
        workouts.addWorkout(new RunWorkout("Solo Run", LocalDate.now(), 10.0, 60.0));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            Ui ui = new Ui();
            ViewShoeMileageCommand command = new ViewShoeMileageCommand();

            // Act
            command.execute(null, workouts, ui, null);

            // Assert
            String output = outContent.toString();
            assertTrue(output.contains("10.00km"));
            assertTrue(output.contains("1 run."), "Should use singular 'run' for count of 1");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void execute_emptyWorkoutList_showsZero() {
        // Arrange
        WorkoutList workouts = new WorkoutList();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            Ui ui = new Ui();
            ViewShoeMileageCommand command = new ViewShoeMileageCommand();

            // Act
            command.execute(null, workouts, ui, null);

            // Assert
            String output = outContent.toString();
            assertTrue(output.contains("0.00km"));
            assertTrue(output.contains("0 runs"));
        } finally {
            System.setOut(originalOut);
        }
    }
}
