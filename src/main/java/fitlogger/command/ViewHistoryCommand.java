package fitlogger.command;

import fitlogger.ui.Ui;
import fitlogger.workoutlist.WorkoutList;

public class ViewHistoryCommand extends Command {
    private final WorkoutList workouts;

    public ViewHistoryCommand(WorkoutList workouts) {
        this.workouts = workouts;
    }

    @Override
    public void execute(Ui ui) {
        ui.showMessage("Here's your past exercises");
        ui.showLine();
        for (int i = 0; i < workouts.getSize(); i++) {
            ui.showMessageNoNewline(i + 1 + ". ");
            ui.printWorkout(workouts.getWorkoutAtIndex(i));
        }
        ui.showLine();
    }
}
