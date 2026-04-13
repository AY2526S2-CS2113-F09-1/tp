package fitlogger.command;

import fitlogger.profile.UserProfile;
import fitlogger.storage.Storage;
import fitlogger.ui.Ui;
import fitlogger.workoutlist.WorkoutList;

/**
 * Represents an abstract command that specifically interacts with the user profile.
 * Subclasses must implement the execution logic to view or modify profile data.
 */
public abstract class ProfileCommand extends Command {
    /**
     * Executes the profile-related command.
     *
     * @param storage  The storage handler for saving or loading data.
     * @param workouts The list of workouts.
     * @param ui       The user interface for user interaction.
     * @param profile  The user profile to be accessed or modified.
     */
    @Override
    public abstract void execute(Storage storage, WorkoutList workouts, Ui ui, UserProfile profile);
}
