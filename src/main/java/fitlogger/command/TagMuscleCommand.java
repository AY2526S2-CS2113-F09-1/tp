package fitlogger.command;

import fitlogger.exercisedictionary.ExerciseDictionary;
import fitlogger.musclegroup.MuscleGroup;
import fitlogger.profile.UserProfile;
import fitlogger.storage.Storage;
import fitlogger.ui.Ui;
import fitlogger.workoutlist.WorkoutList;

/**
 * Command to add a muscle group tag to a specific exercise in the dictionary.
 */
public class TagMuscleCommand extends EditMuscleTagCommand {
    public TagMuscleCommand(int id, MuscleGroup muscle, ExerciseDictionary dictionary) {
        super(id, muscle, dictionary);
    }

    /**
     * Executes the tagging logic and notifies the user.
     *
     * @param storage  The storage handler.
     * @param workouts The list of workouts.
     * @param ui       The user interface to display confirmation.
     * @param profile  The user profile.
     */
    @Override
    public void execute(Storage storage, WorkoutList workouts, Ui ui, UserProfile profile) {
        boolean wasAdded = dictionary.tagMuscles(id, muscle);

        if (wasAdded) {
            ui.showMessage("Added " + muscle.displayName() + " to lift " + id);
        } else {
            ui.showMessage(muscle.displayName() + " is already tagged to lift " + id);
        }
    }
}
