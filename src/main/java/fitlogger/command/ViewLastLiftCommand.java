package fitlogger.command;

import fitlogger.profile.UserProfile;
import fitlogger.storage.Storage;
import fitlogger.ui.Ui;
import fitlogger.workout.StrengthWorkout;
import fitlogger.workout.Workout;
import fitlogger.workoutlist.WorkoutList;

public class ViewLastLiftCommand extends Command {

    private final String exerciseId;

    public ViewLastLiftCommand(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    @Override
    public void execute(Storage storage, WorkoutList workouts, Ui ui, UserProfile profile) {
        assert workouts != null : "WorkoutList must not be null";
        assert exerciseId != null : "Exercise ID must not be null";

        if (exerciseId.isBlank()) {
            ui.showMessage("Please specify an exercise name. Usage: lastlift <EXERCISE_NAME>");
            return;
        }

        for (int i = workouts.getSize() - 1; i >= 0; i--) {
            Workout workout = workouts.getWorkoutAtIndex(i);

            if (workout instanceof StrengthWorkout
                    && workout.getDescription().equalsIgnoreCase(exerciseId)) {
                ui.showLastLift((StrengthWorkout) workout);
                return;
            }
        }

        ui.showMessage("No record found for exercise: " + exerciseId);
    }
}
