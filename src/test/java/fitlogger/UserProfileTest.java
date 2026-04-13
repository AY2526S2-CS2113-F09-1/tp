package fitlogger;

import fitlogger.command.ClearProfileCommand;
import fitlogger.profile.UserProfile;
import fitlogger.ui.Ui;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserProfileTest {

    @Test
    public void constructor_defaultValues_allUnset() {
        UserProfile profile = new UserProfile();
        assertNull(profile.getName());
        assertEquals(-1, profile.getWeight(), 0.001);
        assertEquals(-1, profile.getHeight(), 0.001);
    }

    @Test
    public void setName_validName_updatesCorrectly() {
        UserProfile profile = new UserProfile();
        profile.setName("Alice");
        assertEquals("Alice", profile.getName());
    }

    @Test
    public void setWeight_validWeight_updatesCorrectly() {
        UserProfile profile = new UserProfile();
        profile.setWeight(65.5);
        assertEquals(65.5, profile.getWeight(), 0.001);
    }

    @Test
    public void setHeight_validHeight_updatesCorrectly() {
        UserProfile profile = new UserProfile();
        profile.setHeight(1.75);
        assertEquals(1.75, profile.getHeight(), 0.001);
    }

    @Test
    public void setName_overwriteExistingName_updatesCorrectly() {
        UserProfile profile = new UserProfile();
        profile.setName("Alice");
        profile.setName("Bob");
        assertEquals("Bob", profile.getName());
    }

    @Test
    public void setWeight_overwriteExistingWeight_updatesCorrectly() {
        UserProfile profile = new UserProfile();
        profile.setWeight(65.0);
        profile.setWeight(70.0);
        assertEquals(70.0, profile.getWeight(), 0.001);
    }

    @Test
    public void setHeight_overwriteExistingHeight_updatesCorrectly() {
        UserProfile profile = new UserProfile();
        profile.setHeight(1.70);
        profile.setHeight(1.80);
        assertEquals(1.80, profile.getHeight(), 0.001);
    }

    @Test
    public void toFileFormat_allFieldsSet_correctlyFormattedString() {
        UserProfile profile = new UserProfile();
        profile.setName("Alice");
        profile.setHeight(1.75);
        profile.setWeight(65.5);
        String expected = "name: Alice height: 1.75 weight: 65.5";
        assertEquals(expected, profile.toFileFormat());
    }

    @Test
    public void toFileFormat_noFieldsSet_containsNullAndNegativeOne() {
        UserProfile profile = new UserProfile();
        String result = profile.toFileFormat();
        assertEquals("name: null height: -1.0 weight: -1.0", result);
    }

    @Test
    public void toFileFormat_onlyNameSet_correctlyFormattedString() {
        UserProfile profile = new UserProfile();
        profile.setName("Charlie");
        String result = profile.toFileFormat();
        assertEquals("name: Charlie height: -1.0 weight: -1.0", result);
    }

    @Test
    public void execute_clearCommand_resetsAllFields() {
        UserProfile profile = new UserProfile();
        profile.setName("Alice");
        profile.setHeight(1.75);

        ClearProfileCommand clear = new ClearProfileCommand();
        clear.execute(null, null, new Ui(), profile);

        assertNull(profile.getName());
        assertEquals(-1.0, profile.getHeight(), 0.001);
        assertEquals(-1.0, profile.getWeight(), 0.001);
    }
}
