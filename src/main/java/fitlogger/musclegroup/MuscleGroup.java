package fitlogger.musclegroup;

/**
 * Enumerates the various muscle groups supported by the application.
 * Provides utility methods for validation and string formatting.
 */
public enum MuscleGroup {
    DELTS,
    PECS,
    FOREARMS,
    UPPER_BACK,
    LOWER_BACK,
    ABS,
    LATS,
    BICEPS,
    TRICEPS,
    TRAPS,
    GLUTES,
    QUADS,
    HAMSTRING,
    CALVES;

    /**
     * Validates if a given string corresponds to a defined MuscleGroup.
     *
     * @param name The string representation of the muscle group to validate.
     * @return true if the name matches a MuscleGroup constant, false otherwise.
     */
    public static boolean isValid(String name) {
        for (MuscleGroup mg : MuscleGroup.values()) {
            if (mg.name().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Converts the enum constant name into a more readable display format.
     * Replaces underscores with spaces and converts the text to lowercase.
     *
     * @return A formatted string suitable for UI display.
     */
    public String displayName() {
        return name().toLowerCase().replace('_', ' ');
    }
}
