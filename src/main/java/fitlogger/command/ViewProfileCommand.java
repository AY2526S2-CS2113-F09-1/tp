package fitlogger.command;

import fitlogger.profile.UserProfile;
import fitlogger.storage.Storage;
import fitlogger.ui.Ui;
import fitlogger.workoutlist.WorkoutList;

public class ViewProfileCommand extends ProfileCommand {
    @Override
    public void execute(Storage storage, WorkoutList workouts, Ui ui, UserProfile profile) {
        ui.showLine();
        ui.showMessageNoNewline("Name: ");
        String nameToDisplay = (profile.getName() == null) ? "name not set yet" : profile.getName();
        ui.showMessage(nameToDisplay);

        ui.showMessageNoNewline("Height: ");
        String heightToDisplay = (profile.getHeight() == -1) ?
                "height not set yet" : profile.getHeight() + "m";
        ui.showMessage(heightToDisplay);

        ui.showMessageNoNewline("Weight: ");
        String weightToDisplay = (profile.getWeight() == -1) ?
                "weight not set yet" : profile.getWeight() + "kg";
        ui.showMessage(weightToDisplay);
        ui.showLine();
    }
}