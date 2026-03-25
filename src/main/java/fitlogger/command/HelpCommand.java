package fitlogger.command;

import fitlogger.profile.UserProfile;
import fitlogger.storage.Storage;
import fitlogger.ui.Ui;
import fitlogger.workoutlist.WorkoutList;

public class HelpCommand extends Command {

    public HelpCommand() {
        super();
    }

    public void execute(Storage storage, WorkoutList workouts, Ui ui, UserProfile profile) {
        ui.showHelpMenu();
    }
}
