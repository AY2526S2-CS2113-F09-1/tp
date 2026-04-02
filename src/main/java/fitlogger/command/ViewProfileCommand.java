package fitlogger.command;

import fitlogger.profile.UserProfile;
import fitlogger.storage.Storage;
import fitlogger.ui.Ui;
import fitlogger.workoutlist.WorkoutList;

public class ViewProfileCommand extends ProfileCommand {
    @Override
    public void execute(Storage storage, WorkoutList workouts, Ui ui, UserProfile profile) {
        ui.showProfile(profile.getName(), profile.getHeight(), profile.getWeight());
    }
}
