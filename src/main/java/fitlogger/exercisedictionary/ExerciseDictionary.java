package fitlogger.exercisedictionary;

import java.util.HashMap;
import java.util.Map;

public class ExerciseDictionary {
    private final HashMap<Integer, String> liftDictionary;
    private final HashMap<Integer, String> runDictionary;

    public ExerciseDictionary() {
        this.liftDictionary = new HashMap<>();
        this.runDictionary = new HashMap<>();
        loadDefaultExercises();
    }

    private void loadDefaultExercises() {
        // Default Lifts
        liftDictionary.put(1, "Squat");
        liftDictionary.put(2, "Bench Press");
        liftDictionary.put(3, "Deadlift");
        liftDictionary.put(4, "Overhead Press");

        // Default Runs
        runDictionary.put(1, "Easy Run");
        runDictionary.put(2, "Tempo Run");
        runDictionary.put(3, "Intervals");
    }

    public String getLiftName(int id) {
        assert id > 0 : "Shortcut ID must be positive";
        return liftDictionary.getOrDefault(id, null);
    }

    public String getRunName(int id) {
        assert id > 0 : "Shortcut ID must be positive";
        return runDictionary.getOrDefault(id, null);
    }

    public void addLiftShortcut(int id, String name) {
        assert id > 0 && name != null && !name.trim().isEmpty();
        liftDictionary.put(id, name);
    }

    public void addRunShortcut(int id, String name) {
        assert id > 0 && name != null && !name.trim().isEmpty();
        runDictionary.put(id, name);
    }

    public Map<Integer, String> getLiftShortcuts() {
        return liftDictionary;
    }

    public Map<Integer, String> getRunShortcuts() {
        return runDictionary;
    }
}
