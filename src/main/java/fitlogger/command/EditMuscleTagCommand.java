package fitlogger.command;

import fitlogger.exercisedictionary.ExerciseDictionary;
import fitlogger.musclegroup.MuscleGroup;

/**
 * Abstract base class for commands that modify muscle group tags on exercises.
 * Stores common fields required to identify an exercise and the muscle group involved.
 */
public abstract class EditMuscleTagCommand extends Command {
    protected final int id;
    protected final MuscleGroup muscle;
    protected final ExerciseDictionary dictionary;

    /**
     * Constructs an EditMuscleTagCommand with the necessary identifiers.
     *
     * @param id         The unique ID of the exercise in the dictionary.
     * @param muscle     The MuscleGroup to be added or removed.
     * @param dictionary The exercise dictionary where the data is stored.
     */
    public EditMuscleTagCommand(int id, MuscleGroup muscle, ExerciseDictionary dictionary) {
        this.id = id;
        this.muscle = muscle;
        this.dictionary = dictionary;
    }
}
