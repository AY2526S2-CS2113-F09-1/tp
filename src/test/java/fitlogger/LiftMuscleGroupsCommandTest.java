package fitlogger;

import fitlogger.command.LiftMuscleGroupsCommand;
import fitlogger.exercisedictionary.ExerciseDictionary;
import fitlogger.musclegroup.MuscleGroup;
import fitlogger.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the LiftMuscleGroupsCommand to ensure correct display of exercise tags.
 */
class LiftMuscleGroupsCommandTest {
    private ExerciseDictionary dictionary;
    private Ui ui;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        dictionary = new ExerciseDictionary();
        ui = new Ui();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void execute_validDefaultId_displaysMuscleGroups() {
        // ID 1 is Squat in the default dictionary
        LiftMuscleGroupsCommand command = new LiftMuscleGroupsCommand(1, dictionary);

        command.execute(null, null, ui, null);

        String output = outContent.toString();
        assertTrue(output.contains("Muscle groups for Squat:"));
        assertTrue(output.contains("QUADS"));
        assertTrue(output.contains("GLUTES"));
    }

    @Test
    public void execute_exerciseWithNoTags_displaysNoTagsMessage() {
        // ID 5 doesn't exist/have tags in default dictionary
        // First add a lift name so it doesn't return null for name
        dictionary.addLiftShortcut(5, "Custom Lift");
        LiftMuscleGroupsCommand command = new LiftMuscleGroupsCommand(5, dictionary);

        command.execute(null, null, ui, null);

        String output = outContent.toString();
        assertTrue(output.contains("No muscle groups tagged for Custom Lift (ID: 5)"));
    }

    @Test
    public void execute_afterRemovingTags_displaysNoTagsMessage() {
        // ID 2 is Bench Press. Remove all default tags.
        dictionary.untagMuscles(2, MuscleGroup.PECS);
        dictionary.untagMuscles(2, MuscleGroup.TRICEPS);
        dictionary.untagMuscles(2, MuscleGroup.DELTS);

        LiftMuscleGroupsCommand command = new LiftMuscleGroupsCommand(2, dictionary);
        command.execute(null, null, ui, null);

        String output = outContent.toString();
        assertTrue(output.contains("No muscle groups tagged for Bench Press (ID: 2)"));
    }
}
