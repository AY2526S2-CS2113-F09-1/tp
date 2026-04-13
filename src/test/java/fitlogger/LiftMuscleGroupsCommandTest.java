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
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LiftMuscleGroupsCommandTest {
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
        // ID 1 is Squat
        LiftMuscleGroupsCommand command = new LiftMuscleGroupsCommand(1, dictionary);
        command.execute(null, null, ui, null);

        String output = outContent.toString();

        assertTrue(output.contains("Muscle groups for Squat (ID: 1):"), "Header format mismatch");
        assertTrue(output.contains("quads"), "Should contain quads in lower case");
        assertTrue(output.contains("glutes"), "Should contain glutes in lower case");
    }

    @Test
    public void execute_exerciseWithNoTags_displaysNoTagsMessage() {
        // ID 5 added without tags
        dictionary.addLiftShortcut(5, "Custom Lift");
        LiftMuscleGroupsCommand command = new LiftMuscleGroupsCommand(5, dictionary);

        command.execute(null, null, ui, null);

        String output = outContent.toString();
        // Matches: "No muscle groups tagged for Custom Lift (ID: 5)."
        assertTrue(output.contains("No muscle groups tagged for Custom Lift (ID: 5)"));
    }

    @Test
    public void execute_afterOverwrite_clearsOldTags() {
        // Overwrite ID 2 (Bench Press) with Bicep Curl
        dictionary.addLiftShortcut(2, "Bicep Curl");

        LiftMuscleGroupsCommand command = new LiftMuscleGroupsCommand(2, dictionary);
        command.execute(null, null, ui, null);

        String output = outContent.toString();
        // Check new name and the fact it's empty
        assertTrue(output.contains("No muscle groups tagged for Bicep Curl (ID: 2)"));
        assertFalse(output.contains("pecs"), "Old tags should have been wiped");
    }

    @Test
    public void execute_untagSingleMuscle_removesOnlyThatMuscle() {
        // ID 2 (Bench Press) has pecs, triceps, delts. Remove pecs.
        dictionary.untagMuscles(2, MuscleGroup.PECS);

        LiftMuscleGroupsCommand command = new LiftMuscleGroupsCommand(2, dictionary);
        command.execute(null, null, ui, null);

        String output = outContent.toString();
        assertFalse(output.contains("pecs"));
        assertTrue(output.contains("triceps"));
        assertTrue(output.contains("delts"));
    }
}
