# Javierseow - Project Portfolio Page

## Overview

FitLogger is a command-line fitness tracking application for hybrid athletes who prefer fast keyboard-driven logging of both strength and running workouts.

## Summary of Contributions

### Code Contributed

- [Code Dashboard](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=Javierseow&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2026-02-20T00%3A00%3A00&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&filteredFileName=)

### Enhancements Implemented

- Set up the initial project skeleton, including the `Parser`, `Ui`, and main application entry point, establishing the command-parse-execute architecture used throughout the project.
- Implemented core data models: `RunWorkout`, `WorkoutList`, and the user profile storage class, providing the foundational data structures for the rest of the team to build on.
- Implemented the `add-run` command and `RunWorkout` class, including input validation for distance and duration and flag order enforcement (`d/` before `t/`).
- Implemented `view-total-mileage` (`ViewShoeMileageCommand`), aggregating distance across all logged run workouts.
- Implemented `history` (`ViewHistoryCommand`), displaying all logged workouts in order with numbered indices.
- Implemented `profile view`, `profile set` and `profile clear` (`ViewProfileCommand`, `UpdateProfileCommand`, `ClearProfileCommand`), allowing users to store, update and clear their name, height, and weight with bounds validation.
- Implemented `view-detailed-database` (under `ViewDatabaseCommand`), displaying shortcuts together with their respective muscle groups trained.
- Implemented `tag-muscle`, `untag-muscle` (`TagMuscleCommand`, `UntagMuscleCommand`) to allow tagging and untagging of muscles to various lift exercises.
- Implemented `train` (`TrainMuscleCommand`) to view which exercises a user can do to train a certain muscle
- Implemented `view-muscle-groups` (`ViewMuscleGroupCommand`) to show all the possible muscle groups that can be targetted and trained.
- Implemented `muscle-groups` (`LiftMuscleGroupsCommand`) to show all the muscle groups that a specific exercise targets.
- Implemented all relevant functions for the above in storage and parser to ensure correct implementation and flow.

### Contributions to User Guide

- Wrote sections for:
    - `add-run`
    - `profile view`, `profile set`, `profile clear`
    - `view-total-mileage`
    - `history`
    - `view-detailed-database`
    - `muscle-groups`, `view-muscle-groups`, `train`
    - `tag-muscle`, `untag-muscle`

### Contributions to Developer Guide

- Wrote design and implementation documentation for:
  - `add-run` command and `RunWorkout` class (Enhancement 4)
  - `view-total-mileage` command (Enhancement 5)
  - `profile view` and `profile set` commands (Enhancement 6)
  - muscle commands (Enhancement 14)
  - `history` command (Enhancement 15)

#### UML Diagrams Contributed

I authored the following diagrams to document the core logic and architectural relationships of my features:

|**Diagram**|**Type**|**Description**|
|---|---|---|
|`AddRunWorkoutClassDiagram`|Class|Models the inheritance of `RunWorkout` from the abstract `Workout` base.|
|`AddRunWorkoutSequenceDiagram`|Sequence|Illustrates the "Fail-Fast" object creation and validation flow in the `Parser`.|
|`HistorySequenceDiagram`|Sequence|Shows the windowed search logic for retrieving a partial workout history.|
|`MuscleTaggingClassDiagram`|Class|Displays the command hierarchy and association with `ExerciseDictionary`.|
|`MuscleTaggingObjectDiagram`|Object|Provides a memory snapshot of the `EnumSet` metadata storage.|
|`ProfileInheritanceDiagram`|Class|Models the tiered inheritance for View, Update, and Clear profile commands.|
|`ProfileUpdateSequenceDiagram`|Sequence|Demonstrates the "Selective Invocation" of setters using sentinel values.|
|`TagMuscleSequenceDiagram`|Sequence|Details the interaction between the Parser and the metadata dictionary.|
|`ViewShoeMileageSequenceDiagram`|Sequence|Illustrates polymorphic filtering and date windowing for run analytics.|

### Contributions to Team-Based Tasks

- Established the initial project structure and architecture that the rest of the team built on top of.
- [Pull Requests authored](https://github.com/AY2526S2-CS2113-F09-1/tp/pulls?q=is%3Apr+author%3Adinvsh)

---

## Contributions to the Developer Guide (Extracts)

### Enhancement 6: User Profile Management

The profile system utilizes a tiered inheritance model to categorize actions under a common parent, `ProfileCommand`. This ensures a unified interface for profile tasks while maintaining a clean separation of concerns.

**Class Diagram: Profile Command Hierarchy**
![ProfileInheritanceDiagram](docs/images/ProfileInheritanceDiagram.png)

**Sequence Diagram: Partial Update Logic (Scenario: `profile set height 1.75`)**
![ProfileUpdateSequenceDiagram](docs/images/ProfileUpdateSequenceDiagram.png)

The sequence diagram below highlights the **Selective Invocation** logic. By utilizing sentinel values (`null` and `-1.0`), the command determines which `UserProfile` setters to trigger without affecting existing data.

### Enhancement 14: Muscle Group Tagging System

The system stores exercise metadata in a `Map<Integer, EnumSet<MuscleGroup>>` within the `ExerciseDictionary`.

**Object Diagram: ExerciseDictionary Memory Snapshot**
![MuscleTaggingObjectDiagram](docs/images/MuscleTaggingObjectDiagram.png)

**Technical Rationale**: An `EnumSet` is used instead of a `HashSet` because it is represented internally as a bit-vector. This results in $O(1)$ lookup time and a significantly smaller memory footprint, making it ideal for the high-frequency filtering required by the `train` and `filter` commands.