package fitlogger.command;

import fitlogger.profile.UserProfile;
import fitlogger.storage.Storage;
import fitlogger.ui.Ui;
import fitlogger.workoutlist.WorkoutList;

public class UpdateProfileCommand extends ProfileCommand{
    private String newName;
    private double newHeight;
    private double newWeight;

    public UpdateProfileCommand(String newName, double newHeight, double newWeight) {
        this.newName = newName;
        assert newHeight == -1 || newHeight >= 0;
        this.newHeight = newHeight;
        assert newWeight == -1 || newWeight >= 0;
        this.newWeight = newWeight;
    }

    @Override
    public void execute(Storage storage, WorkoutList workouts, Ui ui, UserProfile profile) {
        if (newName != null) {
            profile.setName(newName);
            ui.showMessage("Name has been updated to " + newName);
        }
        if (newHeight != -1) {
            profile.setHeight(newHeight);
            ui.showMessage("Height has been updated to " + newHeight + "m");
        }
        if (newWeight != -1) {
            profile.setWeight(newWeight);
            ui.showMessage("Weight has been updated to " + newWeight + "kg");
        }
    }
}
