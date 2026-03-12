package seedu.duke;

public class FitLogger {
    private Ui ui;

    public FitLogger() {
        ui = new Ui();
    }

    public void run() {
        ui.showWelcome();
        String command = ui.readCommand();
        ui.temporaryOutput(command);
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new FitLogger().run();
    }
}
