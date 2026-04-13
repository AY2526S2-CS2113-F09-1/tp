package fitlogger.command;

import fitlogger.profile.UserProfile;
import fitlogger.storage.Storage;
import fitlogger.ui.Ui;
import fitlogger.workoutlist.WorkoutList;

/**
 * Command to display all supported muscle groups to the user.
 * This serves as a reference for the user to know which muscle groups
 * can be targeted in their workouts.
 */
public class ViewMuscleGroupCommand extends Command {

    /**
     * Executes the command by requesting the UI to display the list of muscle groups.
     *
     * @param storage  The storage handler (not used by this command).
     * @param workouts The list of workouts (not used by this command).
     * @param ui       The user interface used to print the muscle group list.
     * @param profile  The user profile (not used by this command).
     */
    @Override
    public void execute(Storage storage, WorkoutList workouts, Ui ui, UserProfile profile) {
        ui.showMuscleGroups();
    }
}
