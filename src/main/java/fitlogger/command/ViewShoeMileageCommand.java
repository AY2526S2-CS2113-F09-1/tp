package fitlogger.command;

import fitlogger.profile.UserProfile;
import fitlogger.storage.Storage;
import fitlogger.ui.Ui;
import fitlogger.workout.RunWorkout;
import fitlogger.workout.Workout;
import fitlogger.workoutlist.WorkoutList;

/**
 * Command to calculate and display the cumulative distance of all run workouts.
 * Iterates through the workout list to sum the mileage from RunWorkout instances.
 */
public class ViewShoeMileageCommand extends Command {

    /**
     * Executes the command to calculate total mileage and display it via the UI.
     *
     * @param storage  The storage handler (not used in this specific command).
     * @param workouts The list of all recorded workouts.
     * @param ui       The user interface to display the result.
     * @param profile  The user profile (not used in this specific command).
     */
    @Override
    public void execute(Storage storage, WorkoutList workouts, Ui ui, UserProfile profile) {
        double totalMileage = 0;
        int runWorkoutCount = 0;

        for (Workout workout : workouts.getWorkouts()) {
            if (workout instanceof RunWorkout runWorkout) {
                assert runWorkout.getDistance() >= 0 : "Distance cannot be negative";
                totalMileage += runWorkout.getDistance();
                runWorkoutCount++;
            }
        }

        String unitSuffix = (runWorkoutCount == 1) ? " run." : " runs.";
        ui.showMessage("Your total distance ran is " + String.format("%.2fkm", totalMileage) +
                " across " + runWorkoutCount + unitSuffix);
    }
}
