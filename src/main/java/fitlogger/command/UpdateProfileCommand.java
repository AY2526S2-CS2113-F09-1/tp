package fitlogger.command;

import fitlogger.profile.UserProfile;
import fitlogger.storage.Storage;
import fitlogger.ui.Ui;
import fitlogger.workoutlist.WorkoutList;

public class UpdateProfileCommand extends ProfileCommand{
    private String newName;
    private float newHeight;
    private float newWeight;

    public UpdateProfileCommand(String newName, float newHeight, float newWeight) {
        this.newName = newName;
        this.newHeight = newHeight;
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
