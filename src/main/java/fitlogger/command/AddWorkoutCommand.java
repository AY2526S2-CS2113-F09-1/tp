package fitlogger.command;

import fitlogger.ui.Ui;
import fitlogger.workout.Workout;
import fitlogger.workoutlist.WorkoutList;

public class AddWorkoutCommand extends Command {
    private final Workout workoutToAdd;
    private final WorkoutList workouts;

    public AddWorkoutCommand(WorkoutList workouts, Workout workoutToAdd) {
        this.workouts = workouts;
        this.workoutToAdd = workoutToAdd;
    }

    @Override
    public void execute(Ui ui) {
        workouts.addWorkout(workoutToAdd);

        ui.showMessage("Got it. I've added this workout:");
        ui.printWorkout(workoutToAdd);
        ui.showMessage("Now you have " + workouts.getSize() + " workouts in the list.");
    }
}