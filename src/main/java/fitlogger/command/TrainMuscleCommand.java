package fitlogger.command;

import fitlogger.exercisedictionary.ExerciseDictionary;
import fitlogger.musclegroup.MuscleGroup;
import fitlogger.profile.UserProfile;
import fitlogger.storage.Storage;
import fitlogger.ui.Ui;
import fitlogger.workoutlist.WorkoutList;

import java.util.Map;

public class TrainMuscleCommand extends Command {
    private final MuscleGroup targetMuscle;
    private final ExerciseDictionary dictionary;

    public TrainMuscleCommand(MuscleGroup targetMuscle, ExerciseDictionary dictionary) {
        this.targetMuscle = targetMuscle;
        this.dictionary   = dictionary;
    }

    @Override
    public void execute(Storage storage, WorkoutList workouts, Ui ui, UserProfile profile) {
        ui.showMessage("Exercises targeting: " + targetMuscle.displayName());
        boolean exerciseFound = false;
        for (Map.Entry<Integer, String> entry : dictionary.getLiftShortcuts().entrySet()) {
            if (dictionary.getMusclesFor(entry.getKey()).contains(targetMuscle)) {
                exerciseFound = true;
                ui.showMessage("   [" + entry.getKey() + "] -> " + entry.getValue());
            }
        }
        if (!exerciseFound) {
            ui.showMessage("No lift exercises currently targeting " + targetMuscle.displayName());
            ui.showMessage("Use 'tag-muscle <shortcut-ID> <muscle>' to tag an exercise");
        }
    }
}
