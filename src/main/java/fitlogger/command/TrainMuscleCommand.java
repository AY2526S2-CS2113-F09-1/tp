package fitlogger.command;

import fitlogger.exercisedictionary.ExerciseDictionary;
import fitlogger.musclegroup.MuscleGroup;

public class TrainMuscleCommand extends Command {
    private final MuscleGroup targetMuscle;
    private final ExerciseDictionary dictionary;

    public TrainMuscleCommand(MuscleGroup targetMuscle, ExerciseDictionary dictionary) {
        this.targetMuscle = targetMuscle;
        this.dictionary   = dictionary;
    }
}
