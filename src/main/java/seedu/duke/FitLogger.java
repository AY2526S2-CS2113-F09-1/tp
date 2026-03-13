package seedu.duke;

public class FitLogger {
    private Ui ui;
    private Parser parser;

    public FitLogger() {
        ui = new Ui();
        parser = new Parser();
    }

    public void run() {
        ui.showWelcome();
        String command = ui.readCommand();
        String[] temps = parser.parse(command);
        ui.temporaryOutput(command);
        for (String temp : temps) {
            System.out.println(temp);
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new FitLogger().run();
    }
}
