package fitlogger.command;

import fitlogger.exercisedictionary.ExerciseDictionary;
import fitlogger.musclegroup.MuscleGroup;
import fitlogger.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the TrainMuscleCommand using the default ExerciseDictionary values.
 */
class TrainMuscleCommandTest {
    private ExerciseDictionary dictionary;
    private Ui ui;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        // ExerciseDictionary automatically loads default exercises (Squat, Bench Press, etc.)
        dictionary = new ExerciseDictionary();
        ui = new Ui();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void execute_targetQuads_findsDefaultSquat() {
        // QUADS is a default tag for ID 1 (Squat)
        TrainMuscleCommand command = new TrainMuscleCommand(MuscleGroup.QUADS, dictionary);

        command.execute(null, null, ui, null);

        String output = outContent.toString();
        assertTrue(output.contains("Exercises targeting: quads"));
        assertTrue(output.contains("[1] -> Squat"));
    }

    @Test
    public void execute_targetPecs_findsDefaultBenchPress() {
        // PECS is a default tag for ID 2 (Bench Press)
        TrainMuscleCommand command = new TrainMuscleCommand(MuscleGroup.PECS, dictionary);

        command.execute(null, null, ui, null);

        String output = outContent.toString();
        assertTrue(output.contains("Exercises targeting: pecs"));
        assertTrue(output.contains("[2] -> Bench Press"));
    }

    @Test
    public void execute_muscleWithNoTaggedExercises_displaysEmptyMessage() {
        // ABS is a MuscleGroup but not assigned to any default exercises in loadDefaultExercises()
        TrainMuscleCommand command = new TrainMuscleCommand(MuscleGroup.ABS, dictionary);

        command.execute(null, null, ui, null);

        String output = outContent.toString();
        assertTrue(output.contains("No lift exercises currently targeting abs"));
        assertTrue(output.contains("tag-muscle"));
    }

    @Test
    public void execute_afterUntagging_displaysEmptyMessage() {
        // Arrange: Remove PECS from Bench Press (ID 2)
        dictionary.untagMuscles(2, MuscleGroup.PECS);
        TrainMuscleCommand command = new TrainMuscleCommand(MuscleGroup.PECS, dictionary);

        // Act
        command.execute(null, null, ui, null);

        // Assert
        String output = outContent.toString();
        assertTrue(output.contains("No lift exercises currently targeting pecs"));
    }
}
