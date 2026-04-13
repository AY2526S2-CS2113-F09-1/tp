package fitlogger;

import fitlogger.command.ViewHistoryCommand;
import fitlogger.exception.FitLoggerException;
import fitlogger.profile.UserProfile;
import fitlogger.storage.Storage;
import fitlogger.ui.Ui;
import fitlogger.workout.RunWorkout;
import fitlogger.workoutlist.WorkoutList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HistoryCommandTest {
    private WorkoutList workouts;
    private TestUi ui;
    private Storage storage;
    private UserProfile profile;

    @BeforeEach
    void setUp() {
        workouts = new WorkoutList();
        ui = new TestUi();
        storage = new Storage();
        profile = new UserProfile();
    }

    @Test
    void execute_emptyList_showsOnlyHeader() {
        ViewHistoryCommand historyCommand = new ViewHistoryCommand();
        historyCommand.execute(storage, workouts, ui, profile);

        assertTrue(ui.getOutputs().contains("Here's your past exercises:"));
        assertEquals(3, ui.getOutputs().size()); // Header + 2 lines
    }

    @Test
    void execute_showRecent_correctlyOffsetsStartIndex() throws FitLoggerException {
        workouts.addWorkout(new RunWorkout("Run 1", LocalDate.now(), 1.0, 10.0));
        workouts.addWorkout(new RunWorkout("Run 2", LocalDate.now(), 2.0, 20.0));
        workouts.addWorkout(new RunWorkout("Run 3", LocalDate.now(), 3.0, 30.0));

        ViewHistoryCommand historyCommand = new ViewHistoryCommand(2);
        historyCommand.execute(storage, workouts, ui, profile);

        List<String> outputs = ui.getOutputs();

        assertTrue(outputs.contains("Showing the last 2 exercise(s):"));

        // Check for Run 2: It should be at index 2 (1-indexed)
        // We check if "2. " is followed by the Run 2 description
        boolean hasRun2 = false;
        for (int i = 0; i < outputs.size() - 1; i++) {
            if (outputs.get(i).equals("2. ") && outputs.get(i+1).contains("Run 2")) {
                hasRun2 = true;
                break;
            }
        }

        boolean hasRun1 = outputs.stream().anyMatch(s -> s.contains("Run 1"));

        assertTrue(!hasRun1, "Run 1 should be hidden");
        assertTrue(hasRun2, "Run 2 should be visible with correct index");
    }

    @Test
    void execute_requestedMoreThanExist_showsAllWithCorrectMessage() throws FitLoggerException {
        workouts.addWorkout(new RunWorkout("Solo Run", LocalDate.now(), 5.0, 30.0));

        ViewHistoryCommand historyCommand = new ViewHistoryCommand(5);
        historyCommand.execute(storage, workouts, ui, profile);

        List<String> outputs = ui.getOutputs();
        // Corrected string to match class exactly
        assertTrue(outputs.contains("You only have 1 exercises, showing all past exercises:"));

        boolean hasSoloRun = false;
        for (int i = 0; i < outputs.size() - 1; i++) {
            if (outputs.get(i).equals("1. ") && outputs.get(i+1).contains("Solo Run")) {
                hasSoloRun = true;
                break;
            }
        }
        assertTrue(hasSoloRun);
    }

    @Test
    void execute_fullHistory_matchesExactSequence() throws FitLoggerException {
        workouts.addWorkout(new RunWorkout("test 1", LocalDate.of(2026, 3, 20), 1.0, 1.0));

        ViewHistoryCommand historyCommand = new ViewHistoryCommand();
        historyCommand.execute(storage, workouts, ui, profile);

        List<String> results = ui.getOutputs();
        String expectedHeader = "Here's your past exercises:";
        String expectedWorkout = "1. [Run] test 1 (Date: 2026-03-20) (Distance: 1.00km, Duration: 1.00 mins)";

        assertTrue(results.contains(expectedHeader));
        // Stitching NoNewline + printWorkout output
        String actualFullLine = results.get(2) + results.get(3);
        assertEquals(expectedWorkout, actualFullLine);
    }

    /**
     * Specialized UI stub for testing history output.
     */
    private static class TestUi extends Ui {
        private final List<String> outputs = new ArrayList<>();

        @Override
        public void showMessage(String message) {
            outputs.add(message);
        }

        @Override
        public void showMessageNoNewline(String message) {
            outputs.add(message);
        }

        @Override
        public void showLine() {
            outputs.add("---line---");
        }

        @Override
        public void printWorkout(fitlogger.workout.Workout workout) {
            outputs.add(workout.toString());
        }

        public List<String> getOutputs() {
            return outputs;
        }
    }
}
