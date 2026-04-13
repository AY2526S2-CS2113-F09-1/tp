package fitlogger;

import fitlogger.command.TagMuscleCommand;
import fitlogger.command.UntagMuscleCommand;
import fitlogger.exercisedictionary.ExerciseDictionary;
import fitlogger.musclegroup.MuscleGroup;
import fitlogger.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MuscleTagCommandTest {
    private ExerciseDictionary dictionary;
    private Ui ui;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        dictionary = new ExerciseDictionary();
        // Assuming ID 1 exists in your dictionary for testing
        ui = new Ui();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void tagMuscleCommand_validId_updatesDictionaryAndShowsMessage() {
        TagMuscleCommand tagCommand = new TagMuscleCommand(1, MuscleGroup.BICEPS, dictionary);

        tagCommand.execute(null, null, ui, null);

        String output = outContent.toString();
        assertTrue(output.contains("Added biceps to lift 1"));
    }

    @Test
    public void untagMuscleCommand_validId_updatesDictionaryAndShowsMessage() {
        // First tag it so we can untag it
        dictionary.tagMuscles(1, MuscleGroup.ABS);
        UntagMuscleCommand untagCommand = new UntagMuscleCommand(1, MuscleGroup.ABS, dictionary);

        untagCommand.execute(null, null, ui, null);

        String output = outContent.toString();
        assertTrue(output.contains("Removed abs from lift ID: 1"));
    }
}
