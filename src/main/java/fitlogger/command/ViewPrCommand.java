package fitlogger.command;

import fitlogger.profile.UserProfile;
import fitlogger.storage.Storage;
import fitlogger.ui.Ui;
import fitlogger.workout.RunWorkout;
import fitlogger.workout.StrengthWorkout;
import fitlogger.workout.Workout;
import fitlogger.workoutlist.WorkoutList;

public class ViewPrCommand extends Command {

    private final String exerciseId;

    public ViewPrCommand(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    @Override
    public void execute(Storage storage, WorkoutList workouts, Ui ui, UserProfile profile) {
        assert workouts != null : "WorkoutList must not be null";
        assert exerciseId != null : "Exercise ID must not be null";

        if (exerciseId.isBlank()) {
            ui.showMessage("Please specify an exercise name or shortcut ID. Usage: pr <EXERCISE_NAME_OR_ID>");
            return;
        }

        Workout prWorkout = null;
        double maxValue = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < workouts.getSize(); i++) {
            Workout workout = workouts.getWorkoutAtIndex(i);

            if (!workout.getDescription().equalsIgnoreCase(exerciseId)) {
                continue;
            }

            if (workout instanceof StrengthWorkout) {
                double weight = ((StrengthWorkout) workout).getWeight();
                if (weight > maxValue) {
                    maxValue = weight;
                    prWorkout = workout;
                }
            } else if (workout instanceof RunWorkout) {
                double distance = ((RunWorkout) workout).getDistance();
                if (distance > maxValue) {
                    maxValue = distance;
                    prWorkout = workout;
                }
            }
        }

        if (prWorkout != null) {
            ui.showPr(prWorkout);
        } else {
            ui.showMessage("No record found for exercise: " + exerciseId);
        }
    }
}
