package fitlogger.command;

import fitlogger.exercisedictionary.ExerciseDictionary;
import fitlogger.profile.UserProfile;
import fitlogger.storage.Storage;
import fitlogger.ui.Ui;
import fitlogger.workoutlist.WorkoutList;

public class AddShortcutCommand extends Command {
    private final String type;
    private final int id;
    private final String name;
    private final ExerciseDictionary dictionary;

    public AddShortcutCommand(String type, int id, String name, ExerciseDictionary dictionary) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.dictionary = dictionary;
    }

    @Override
    public void execute(Storage storage, WorkoutList workouts, Ui ui, UserProfile profile) {
        if (type.equals("lift")) {
            dictionary.addLiftShortcut(id, name);
            ui.showMessage("Success! Added strength shortcut: [L" + id + "] -> " + name);
        } else if (type.equals("run")) {
            dictionary.addRunShortcut(id, name);
            ui.showMessage("Success! Added run shortcut: [R" + id + "] -> " + name);
        }
    }
}
