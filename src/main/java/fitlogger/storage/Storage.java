package fitlogger.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fitlogger.workout.RunWorkout;
import fitlogger.workout.StrengthWorkout;
import fitlogger.workout.Workout;

/**
 * Handles persistence of workout data to and from the file system.
 * Workout entries are stored in a plain-text file, one entry per line,
 * using each workout's {@code toFileFormat()} representation.
 *
 * <p>Supported file formats:
 * <pre>
 *   R | 0/1 | description | yyyy-MM-dd | distance | duration
 *   L | 0/1 | description | yyyy-MM-dd | weight   | sets | reps
 * </pre>
 */
public class Storage {

    /** Path to the file where workout data is persisted. */
    private static final String FILE_PATH = "data/fitlogger.txt";

    /** Expected number of fields for each workout type. */
    private static final int RUN_FIELD_COUNT = 5;
    private static final int STRENGTH_FIELD_COUNT = 6;

    /** Index constants for shared fields. */
    private static final int INDEX_TYPE = 0;
    private static final int INDEX_DESCRIPTION = 1;
    private static final int INDEX_DATE = 2;

    /** Index constants for RunWorkout-specific fields. */
    private static final int INDEX_RUN_DISTANCE = 3;
    private static final int INDEX_RUN_DURATION = 4;

    /** Index constants for StrengthWorkout-specific fields. */
    private static final int INDEX_STRENGTH_WEIGHT = 3;
    private static final int INDEX_STRENGTH_SETS = 4;
    private static final int INDEX_STRENGTH_REPS = 5;

    // ── saveData ─────────────────────────────────────────────────────────────

    /**
     * Saves all workouts in the provided list to {@code data/fitlogger.txt}.
     * The parent directory is created automatically if it does not exist.
     * Each workout is written on its own line using {@link Workout#toFileFormat()}.
     *
     * @param workouts The list of {@link Workout} objects to persist.
     */
    public void saveData(List<Workout> workouts) {
        assert workouts != null : "Workout list passed to saveData() must not be null";

        File file = new File(FILE_PATH);

        // Ensure the data/ directory exists before writing
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (Workout workout : workouts) {
                writer.write(workout.toFileFormat());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Error saving workouts: " + e.getMessage());
        }
    }

    // ── loadData ─────────────────────────────────────────────────────────────

    /**
     * Loads workouts from {@code data/fitlogger.txt} and returns them as a list.
     * If the file or directory does not exist yet, an empty list is returned
     * silently — this is expected on first run.
     * Corrupted or unrecognised lines are skipped with a warning.
     *
     * @return A {@link List} of {@link Workout} objects read from disk,
     *         or an empty list if the file does not exist or cannot be read.
     */
    public List<Workout> loadData() {
        List<Workout> workouts = new ArrayList<>();
        File file = new File(FILE_PATH);

        // First run: file doesn't exist yet — return empty list silently
        if (!file.exists()) {
            return workouts;
        }

        try (Scanner scanner = new Scanner(file)) {
            int lineNumber = 0;
            while (scanner.hasNextLine()) {
                lineNumber++;
                String line = scanner.nextLine().trim();

                // Skip blank lines
                if (line.isEmpty()) {
                    continue;
                }

                try {
                    Workout workout = parseLine(line);
                    if (workout != null) {
                        workouts.add(workout);
                    }
                } catch (Exception e) {
                    System.out.println("Warning: Skipping corrupted line " + lineNumber
                            + " in save file: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Save file not found: " + e.getMessage());
        }

        return workouts;
    }

    // ── helpers ───────────────────────────────────────────────────────────────

    /**
     * Parses a single line from the save file into a {@link Workout} object.
     * Dispatches to the appropriate parse method based on the type field.
     *
     * @param line A raw line read from the file.
     * @return The reconstructed {@link Workout}, or {@code null} if the type
     *         is unrecognised.
     * @throws IllegalArgumentException If required fields are missing or malformed.
     */
    private Workout parseLine(String line) {
        String[] fields = line.split("\\s*\\|\\s*");
        String type = fields[INDEX_TYPE].trim();

        switch (type) {
        case "R":
            return parseRunWorkout(fields);
        case "L":
            return parseStrengthWorkout(fields);
        default:
            System.out.println("Warning: Unknown workout type '" + type + "' — skipping.");
            return null;
        }
    }

    /**
     * Reconstructs a {@link RunWorkout} from split save-file fields.
     * Expected format: {@code R | description | yyyy-MM-dd | distance | duration}
     *
     * @param fields Fields split from a single save-file line.
     * @return The reconstructed {@link RunWorkout}.
     * @throws IllegalArgumentException If the field count is wrong or values are malformed.
     */
    private RunWorkout parseRunWorkout(String[] fields) {
        if (fields.length < RUN_FIELD_COUNT) {
            throw new IllegalArgumentException("RunWorkout entry has too few fields.");
        }

        String description = fields[INDEX_DESCRIPTION].trim();
        LocalDate date = parseDate(fields[INDEX_DATE]);
        double distance = parseDouble(fields[INDEX_RUN_DISTANCE], "distance");
        double duration = parseDouble(fields[INDEX_RUN_DURATION], "duration");

        RunWorkout run = new RunWorkout(description, date, distance, duration);
        return run;
    }

    /**
     * Reconstructs a {@link StrengthWorkout} from split save-file fields.
     * Expected format: {@code L | description | yyyy-MM-dd | weight | sets | reps}
     *
     * @param fields Fields split from a single save-file line.
     * @return The reconstructed {@link StrengthWorkout}.
     * @throws IllegalArgumentException If the field count is wrong or values are malformed.
     */
    private StrengthWorkout parseStrengthWorkout(String[] fields) {
        if (fields.length < STRENGTH_FIELD_COUNT) {
            throw new IllegalArgumentException("StrengthWorkout entry has too few fields.");
        }

        String description = fields[INDEX_DESCRIPTION].trim();
        LocalDate date = parseDate(fields[INDEX_DATE]);
        double weight = parseDouble(fields[INDEX_STRENGTH_WEIGHT], "weight");
        int sets = parseInt(fields[INDEX_STRENGTH_SETS], "sets");
        int reps = parseInt(fields[INDEX_STRENGTH_REPS], "reps");

        StrengthWorkout strength = new StrengthWorkout(description, weight, sets, reps, date);
        return strength;
    }

    /**
     * Parses a date string in {@code yyyy-MM-dd} format.
     *
     * @param raw The raw field string from the save file.
     * @return The parsed {@link LocalDate}.
     * @throws IllegalArgumentException If the date format is invalid.
     */
    private LocalDate parseDate(String raw) {
        try {
            return LocalDate.parse(raw.trim());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + raw);
        }
    }

    /**
     * Parses a double value from a raw field string.
     *
     * @param raw       The raw field string from the save file.
     * @param fieldName The field name used in the error message.
     * @return The parsed double value.
     * @throws IllegalArgumentException If the value is not a valid number.
     */
    private double parseDouble(String raw, String fieldName) {
        try {
            return Double.parseDouble(raw.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid " + fieldName + " value: " + raw);
        }
    }

    /**
     * Parses an integer value from a raw field string.
     *
     * @param raw       The raw field string from the save file.
     * @param fieldName The field name used in the error message.
     * @return The parsed integer value.
     * @throws IllegalArgumentException If the value is not a valid integer.
     */
    private int parseInt(String raw, String fieldName) {
        try {
            return Integer.parseInt(raw.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid " + fieldName + " value: " + raw);
        }
    }
}
