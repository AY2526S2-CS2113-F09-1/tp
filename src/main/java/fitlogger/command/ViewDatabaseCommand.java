package fitlogger.command;

import fitlogger.exercisedictionary.ExerciseDictionary;
import fitlogger.profile.UserProfile;
import fitlogger.storage.Storage;
import fitlogger.ui.Ui;
import fitlogger.workoutlist.WorkoutList;

public class ViewDatabaseCommand extends Command {
    private final ExerciseDictionary dictionary;

    public ViewDatabaseCommand(ExerciseDictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public void execute(Storage storage, WorkoutList workouts, Ui ui, UserProfile profile) {
        // Just pass the one object!
        ui.showExerciseDatabase(dictionary);
    }
}
