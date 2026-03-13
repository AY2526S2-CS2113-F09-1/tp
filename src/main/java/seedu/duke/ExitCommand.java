package seedu.duke;

public class ExitCommand extends Command{
    public void execute(Ui ui) {
        System.out.println("execute function run");
    }
    public boolean isExit() {
        return true;
    }
}
