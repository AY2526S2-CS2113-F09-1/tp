package seedu.duke;

public class ExitCommand extends Command{
    public void execute(Ui ui) {
        ui.showGoodbye();
    }
    public boolean isExit() {
        return true;
    }
}
