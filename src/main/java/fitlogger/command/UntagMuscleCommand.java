package fitlogger.command;

import fitlogger.exercisedictionary.ExerciseDictionary;
import fitlogger.musclegroup.MuscleGroup;
import fitlogger.profile.UserProfile;
import fitlogger.storage.Storage;
import fitlogger.ui.Ui;
import fitlogger.workoutlist.WorkoutList;

/**
 * Command to remove a muscle group tag from a specific exercise in the dictionary.
 */
public class UntagMuscleCommand extends EditMuscleTagCommand {
    public UntagMuscleCommand(int id, MuscleGroup muscle, ExerciseDictionary dictionary) {
        super(id, muscle, dictionary);
    }

    /**
     * Executes the untagging logic and notifies the user.
     *
     * @param storage  The storage handler.
     * @param workouts The list of workouts.
     * @param ui       The user interface to display confirmation.
     * @param profile  The user profile.
     */
    @Override
    public void execute(Storage storage, WorkoutList workouts, Ui ui, UserProfile profile) {
        boolean wasRemoved = dictionary.untagMuscles(id, muscle);

        if (wasRemoved) {
            ui.showMessage("Removed " + muscle.displayName() + " from lift ID: " + id);
        } else {
            ui.showMessage(muscle.displayName() + " was not found on lift ID: " + id);
        }
    }
}
