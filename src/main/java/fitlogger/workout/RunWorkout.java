package fitlogger.workout;

import java.time.LocalDate;

/**
 * Represents a running workout logged by the user.
 *
 * <p>Distance is stored in kilometres. Duration is stored in <em>minutes</em>
 * as a {@code double} to allow fractional values (e.g., 25.5 for 25 min 30 sec).
 */
public class RunWorkout extends Workout {
    /** Distance covered, in kilometres. */
    protected double distance;

    /** Duration of the run, in minutes. */
    protected double durationMinutes;

    /**
     * Creates a new RunWorkout.
     *
     * @param description     A short label for this run (e.g., "Morning jog").
     * @param date            The date the run was performed.
     * @param distance        Distance in kilometres (must be positive).
     * @param durationMinutes Duration in minutes (must be positive).
     */
    public RunWorkout(String description, LocalDate date, double distance, double durationMinutes) {
        super(description, date);
        this.distance = distance;
        this.durationMinutes = durationMinutes;
    }

    public double getDistance() {
        return distance;
    }

    /** Returns the run duration in minutes. */
    public double getDurationMinutes() {
        return durationMinutes;
    }

    /**
     * Formats the workout for file storage.
     *
     * <p>Format: {@code R | <description> | <date> | <distance> | <durationMinutes>}
     */
    @Override
    public String toFileFormat() {
        return "R | " + description + " | " + date + " | " + distance + " | " + durationMinutes;
    }

    @Override
    public String toString() {
        return "[Run] " + super.toString()
                + " (Distance: " + distance + "km, Duration: " + durationMinutes + " mins)";
    }
}
