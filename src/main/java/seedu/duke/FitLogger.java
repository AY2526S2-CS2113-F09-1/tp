package seedu.duke;

public class FitLogger {
    private Ui ui;

    public FitLogger() {
        ui = new Ui();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.readCommand();
            ui.temporaryOutput(command);
            Command c = new ExitCommand();
            c.execute(ui);
            isExit = c.isExit();
        }
    }

    public static void main(String[] args) {
        new FitLogger().run();
    }
}
