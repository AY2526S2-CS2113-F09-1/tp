package fitlogger.musclegroup;

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

    public static boolean isValid(String name) {
        for (MuscleGroup mg : MuscleGroup.values()) {
            if (mg.name().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
