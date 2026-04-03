package fitlogger.command;

import fitlogger.exercisedictionary.ExerciseDictionary;
import fitlogger.musclegroup.MuscleGroup;
import fitlogger.profile.UserProfile;
import fitlogger.storage.Storage;
import fitlogger.ui.Ui;
import fitlogger.workoutlist.WorkoutList;

public class TagMuscleCommand extends EditMuscleTagCommand {
    public TagMuscleCommand(int id, MuscleGroup muscle, ExerciseDictionary dictionary) {
        super(id, muscle, dictionary);
    }

    @Override
    public void execute(Storage storage, WorkoutList workouts, Ui ui, UserProfile profile) {
        dictionary.tagMuscles(id, muscle);
        ui.showMessage("Added " + muscle.displayName() + " to lift " + id);
    }
}
