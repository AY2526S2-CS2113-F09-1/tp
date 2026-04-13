package fitlogger.command;

import fitlogger.profile.UserProfile;
import fitlogger.storage.Storage;
import fitlogger.ui.Ui;
import fitlogger.workoutlist.WorkoutList;

/**
 * Command to display the user's current profile details.
 */
public class ViewProfileCommand extends ProfileCommand {
    /**
     * Retrieves profile details and displays them via the UI.
     *
     * @param storage  The storage handler.
     * @param workouts The list of workouts.
     * @param ui       The user interface to display the profile.
     * @param profile  The user profile containing the data to view.
     */
    @Override
    public void execute(Storage storage, WorkoutList workouts, Ui ui, UserProfile profile) {
        ui.showProfile(profile.getName(), profile.getHeight(), profile.getWeight());
    }
}
