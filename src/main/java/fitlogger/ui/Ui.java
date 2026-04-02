package fitlogger.ui;

import fitlogger.workout.Workout;
import fitlogger.exercisedictionary.ExerciseDictionary;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "-----------------------------------------------------";

    private Scanner in = new Scanner(System.in);

    public String readCommand() {
        return in.nextLine();
    }

    public void showWelcome() {
        String logo = " ______   _   _                                 \n"
                + "|  ____(_) | | |                                \n"
                + "| |__   _| |_| |     ___   __ _  __ _  ___ _ __  \n"
                + "|  __| | | __| |    / _ \\ / _` |/ _` |/ _ \\ '__| \n"
                + "| |    | | |_| |___| (_) | (_| | (_| |  __/ |    \n"
                + "|_|    |_|\\__|______\\___/ \\__, |\\__, |\\___|_|    \n"
                + "                           __/ | __/ |           \n"
                + "                          |___/ |___/            ";
        showMessage(logo);
        showLine();
        showMessage("Welcome to FitLogger!");
        showMessage("Type 'help' to see available commands.");
    }

    public void showGoodbye() {
        showMessage("Goodbye! See you at your next workout!");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message prefixed with "[ERROR]".
     * All {@link fitlogger.exception.FitLoggerException} messages should be
     * routed here rather than printed directly.
     *
     * @param message The error description to display.
     */
    public void showError(String message) {
        System.out.println("[ERROR] " + message);
    }

    public void showHelpMenu() {
        String helpMessage = "Command Guide:\n"
                + "    help                                       List available commands\n"
                + "    profile view                               View your profile\n"
                + "    profile set <field> <value>                Update your profile. "
                + "Available fields: name/weight/height \n"
                + "    add-run <n> d/<dist> t/<mins>              Log a run\n"
                + "    add-lift <n> w/<kg> s/<sets> r/<reps>      Log a lift workout\n"
                + "    edit <index> <field>/<value>               "
                + "Edit field: name/description/weight/sets/reps/distance/duration\n"
                + "    view-database                              View exercise shortcuts and their IDs\n"
                + "    history                                    View all logged workouts\n"
                + "    delete <index>                             Delete workout by number\n"
                + "    search-date <YYYY-MM-DD>                   View workouts completed on a date\n"
                + "    exit                                       Save and close FitLogger";
        showMessage(helpMessage);
    }

    public void showMessageNoNewline(String message) {
        System.out.print(message);
    }

    public void showLine() {
        showMessage(LINE);
    }

    public void printWorkout(Workout workout) {
        showMessage(workout.toString());
    }

    public void showWorkoutList(List<Workout> workouts) {
        if (workouts.isEmpty()) {
            showMessage("No workouts found.");
            return;
        }

        for (int i = 0; i < workouts.size(); i++) {
            showMessageNoNewline(i + 1 + ". ");
            printWorkout(workouts.get(i));
        }
    }

    public void showExerciseDatabase(ExerciseDictionary dictionary) {
        showMessage("Strength Shortcuts:");
        for (java.util.Map.Entry<Integer, String> entry : dictionary.getLiftShortcuts().entrySet()) {
            showMessage("  [" + entry.getKey() + "] -> " + entry.getValue());
        }
        showMessage("");
        showMessage("Run Shortcuts:");
        for (java.util.Map.Entry<Integer, String> entry : dictionary.getRunShortcuts().entrySet()) {
            showMessage("  [" + entry.getKey() + "] -> " + entry.getValue());
        }
        showLine();
    }}
