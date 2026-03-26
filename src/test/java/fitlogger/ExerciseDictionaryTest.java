package fitlogger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import fitlogger.exercisedictionary.ExerciseDictionary;

public class ExerciseDictionaryTest {

    private ExerciseDictionary dictionary;

    @BeforeEach
    public void setUp() {
        dictionary = new ExerciseDictionary();
    }

    @Test
    public void getLiftName_defaultLifts_returnsCorrectNames() {
        assertEquals("Squat", dictionary.getLiftName(1));
        assertEquals("Bench Press", dictionary.getLiftName(2));
    }

    @Test
    public void getRunName_defaultRuns_returnsCorrectNames() {
        assertEquals("Easy Run", dictionary.getRunName(1));
    }

    @Test
    public void getRunName_nonExistentId_returnsNull() {
        assertNull(dictionary.getRunName(999));
    }
}
