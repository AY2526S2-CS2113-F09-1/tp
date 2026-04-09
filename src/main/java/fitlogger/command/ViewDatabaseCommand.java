package fitlogger.command;

import fitlogger.exercisedictionary.ExerciseDictionary;
import fitlogger.profile.UserProfile;
import fitlogger.storage.Storage;
import fitlogger.ui.Ui;
import fitlogger.workoutlist.WorkoutList;

public class ViewDatabaseCommand extends Command {
    private final ExerciseDictionary dictionary;
    private final boolean isDetailed;

    public ViewDatabaseCommand(ExerciseDictionary dictionary, boolean isDetailed) {
        this.dictionary = dictionary;
        this.isDetailed = isDetailed;
    }

    @Override
    public void execute(Storage storage, WorkoutList workouts, Ui ui, UserProfile profile) {
        // Just pass the one object!
        ui.showExerciseDatabase(dictionary, isDetailed);
    }
}
