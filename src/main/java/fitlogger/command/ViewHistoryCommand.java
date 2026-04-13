package fitlogger.command;

import fitlogger.profile.UserProfile;
import fitlogger.storage.Storage;
import fitlogger.ui.Ui;
import fitlogger.workoutlist.WorkoutList;

/**
 * Command to display the entire history of logged workouts.
 * Iterates through the workout list and formats them into a numbered list for the UI.
 */
public class ViewHistoryCommand extends Command {

    /**
     * Constructs a ViewHistoryCommand.
     */
    public ViewHistoryCommand() {
        super();
    }

    /**
     * Executes the command by printing each workout in the history.
     * Workouts are displayed with a 1-based index for better user readability.
     *
     * @param storage  The storage handler.
     * @param workouts The list of workouts to be displayed.
     * @param ui       The user interface used to print the history.
     * @param profile  The user profile.
     */
    @Override
    public void execute(Storage storage, WorkoutList workouts, Ui ui, UserProfile profile) {
        ui.showMessage("Here's your past exercises");
        ui.showLine();
        for (int i = 0; i < workouts.getSize(); i++) {
            // Display as 1-indexed for the user
            ui.showMessageNoNewline(i + 1 + ". ");
            ui.printWorkout(workouts.getWorkoutAtIndex(i));
        }
        ui.showLine();
    }
}
