package fitlogger;

import fitlogger.command.EditCommand;
import fitlogger.exception.FitLoggerException;
import fitlogger.profile.UserProfile;
import fitlogger.storage.Storage;
import fitlogger.ui.Ui;
import fitlogger.workout.RunWorkout;
import fitlogger.workout.StrengthWorkout;
import fitlogger.workoutlist.WorkoutList;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EditCommandTest {

    private static final Storage STORAGE = new Storage();

    @Test
    void editStrengthWeight_validIndexAndValue_updatesWeight() throws FitLoggerException {
        WorkoutList workouts = new WorkoutList();
        workouts.addWorkout(new StrengthWorkout("Bench Press", 80.0, 3, 8, LocalDate.of(2026, 3, 13)));
        TestUi ui = new TestUi();
        UserProfile profile = new UserProfile();

        EditCommand command = new EditCommand(1, "weight", "85.5");
        command.execute(STORAGE, workouts, ui, profile);

        StrengthWorkout edited = (StrengthWorkout) workouts.getWorkoutAtIndex(0);
        assertEquals(85.5, edited.getWeight(), 0.001);
        assertTrue(ui.lastOutput.startsWith("Updated workout 1:"));
    }

    @Test
    void editStrengthReps_validIndexAndValue_updatesReps() throws FitLoggerException {
        WorkoutList workouts = new WorkoutList();
        workouts.addWorkout(new StrengthWorkout("Bench Press", 80.0, 3, 8, LocalDate.of(2026, 3, 13)));
        TestUi ui = new TestUi();
        UserProfile profile = new UserProfile();

        EditCommand command = new EditCommand(1, "reps", "10");
        command.execute(STORAGE, workouts, ui, profile);

        StrengthWorkout edited = (StrengthWorkout) workouts.getWorkoutAtIndex(0);
        assertEquals(10, edited.getReps());
        assertTrue(ui.lastOutput.startsWith("Updated workout 1:"));
    }

    @Test
    void editRunDistance_validIndexAndValue_updatesDistance() throws FitLoggerException {
        WorkoutList workouts = new WorkoutList();
        workouts.addWorkout(new RunWorkout("Morning Jog", LocalDate.of(2026, 3, 13), 5.0, 30.0));
        TestUi ui = new TestUi();
        UserProfile profile = new UserProfile();

        EditCommand command = new EditCommand(1, "distance", "7.5");
        command.execute(STORAGE, workouts, ui, profile);

        RunWorkout edited = (RunWorkout) workouts.getWorkoutAtIndex(0);
        assertEquals(7.5, edited.getDistance(), 0.001);
        assertTrue(ui.lastOutput.startsWith("Updated workout 1:"));
    }

    @Test
    void editInvalidIndex_showsMessage() throws FitLoggerException {
        WorkoutList workouts = new WorkoutList();
        workouts.addWorkout(new RunWorkout("Morning Jog", LocalDate.of(2026, 3, 13), 5.0, 30.0));
        TestUi ui = new TestUi();
        UserProfile profile = new UserProfile();

        EditCommand command = new EditCommand(2, "distance", "7.5");
        command.execute(STORAGE, workouts, ui, profile);

        assertEquals("Invalid workout index: 2", ui.lastOutput);
    }

    @Test
    void editName_withPipe_rejectedAndUnchanged() throws FitLoggerException {
        WorkoutList workouts = new WorkoutList();
        workouts.addWorkout(new StrengthWorkout("Bench Press", 80.0, 3, 8, LocalDate.of(2026, 3, 13)));
        TestUi ui = new TestUi();
        UserProfile profile = new UserProfile();

        EditCommand command = new EditCommand(1, "name", "Bench | Press");
        command.execute(STORAGE, workouts, ui, profile);

        StrengthWorkout workout = (StrengthWorkout) workouts.getWorkoutAtIndex(0);
        assertEquals("Bench Press", workout.getDescription());
        assertTrue(ui.lastOutput.contains("must not contain '|' or '/'"));
    }

    @Test
    void editName_withSlash_rejectedAndUnchanged() throws FitLoggerException {
        WorkoutList workouts = new WorkoutList();
        workouts.addWorkout(new RunWorkout("Morning Jog", LocalDate.of(2026, 3, 13), 5.0, 30.0));
        TestUi ui = new TestUi();
        UserProfile profile = new UserProfile();

        EditCommand command = new EditCommand(1, "description", "push/pull");
        command.execute(STORAGE, workouts, ui, profile);

        RunWorkout workout = (RunWorkout) workouts.getWorkoutAtIndex(0);
        assertEquals("Morning Jog", workout.getDescription());
        assertTrue(ui.lastOutput.contains("must not contain '|' or '/'"));
    }

    @Test
    void editDistance_nan_rejectedAndUnchanged() throws FitLoggerException {
        WorkoutList workouts = new WorkoutList();
        workouts.addWorkout(new RunWorkout("Morning Jog", LocalDate.of(2026, 3, 13), 5.0, 30.0));
        TestUi ui = new TestUi();
        UserProfile profile = new UserProfile();

        EditCommand command = new EditCommand(1, "distance", "NaN");
        command.execute(STORAGE, workouts, ui, profile);

        RunWorkout workout = (RunWorkout) workouts.getWorkoutAtIndex(0);
        assertEquals(5.0, workout.getDistance(), 0.001);
        assertEquals("Distance must be a positive number.", ui.lastOutput);
    }

    @Test
    void editDistance_infinity_rejectedAndUnchanged() throws FitLoggerException {
        WorkoutList workouts = new WorkoutList();
        workouts.addWorkout(new RunWorkout("Morning Jog", LocalDate.of(2026, 3, 13), 5.0, 30.0));
        TestUi ui = new TestUi();
        UserProfile profile = new UserProfile();

        EditCommand command = new EditCommand(1, "distance", "Infinity");
        command.execute(STORAGE, workouts, ui, profile);

        RunWorkout workout = (RunWorkout) workouts.getWorkoutAtIndex(0);
        assertEquals(5.0, workout.getDistance(), 0.001);
        assertEquals("Distance must be a positive number.", ui.lastOutput);
    }

    private static class TestUi extends Ui {
        private String lastOutput;

        @Override
        public void showMessage(String command) {
            lastOutput = command;
        }
    }
}
