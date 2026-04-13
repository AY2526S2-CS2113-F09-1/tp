package fitlogger.command;

import fitlogger.exercisedictionary.ExerciseDictionary;
import fitlogger.musclegroup.MuscleGroup;
import fitlogger.profile.UserProfile;
import fitlogger.storage.Storage;
import fitlogger.ui.Ui;
import fitlogger.workoutlist.WorkoutList;

import java.util.Set;

/**
 * Command to display all muscle groups associated with a specific lift exercise.
 * Retrieves exercise names and muscle tags from the provided ExerciseDictionary.
 */
public class LiftMuscleGroupsCommand extends Command {
    private final int id;
    private final ExerciseDictionary dictionary;

    /**
     * Constructs a LiftMuscleGroupsCommand for a specific exercise ID.
     *
     * @param id         The unique ID of the lift exercise.
     * @param dictionary The dictionary source for exercise data.
     */
    public LiftMuscleGroupsCommand(int id, ExerciseDictionary dictionary) {
        assert id > 0 : "id is not valid, possible error in ExerciseDictionary";
        this.id = id;
        this.dictionary = dictionary;
    }

    /**
     * Executes the command to show muscle groups for the specified lift.
     * Displays the associated muscle groups or a message indicating no tags exist.
     *
     * @param storage  The storage handler.
     * @param workouts The list of workouts.
     * @param ui       The user interface used to output the results.
     * @param profile  The user profile.
     */
    @Override
    public void execute(Storage storage, WorkoutList workouts, Ui ui, UserProfile profile) {
        String exerciseName = dictionary.getLiftName(id);
        Set<MuscleGroup> muscles = dictionary.getMusclesFor(id);

        if (muscles.isEmpty()) {
            ui.showMessage("No muscle groups tagged for " + exerciseName + " (ID: " + id + ").");
        } else {
            ui.showMessage("Muscle groups for " + exerciseName + ": " + muscles);
        }
    }
}
