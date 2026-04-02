# User Guide

## Introduction

FitLogger is a command-line workout logger for users who want fast text-based tracking.

It is designed for users who:
- prefer keyboard-first workflows,
- want quick workout logging without navigating multiple screens,
- need simple history tracking and correction commands.

This guide documents the command features implemented in my scope:
- `add-lift`
- `edit`
- `delete`
- `search-date`
- `exit`

## Quick Start

1. Ensure that you have Java 17 or above installed.
1. Build and run the app.
1. Type commands directly into the terminal.
1. Use `help` to see the full command list.

## Features

### Add a lift workout: `add-lift`

Logs a strength workout with exercise name, weight, sets, and reps.

Format:
`add-lift <name_or_id> w/<weightKg> s/<sets> r/<reps>`

Examples:
- `add-lift Bench Press w/80 s/3 r/8`
- `add-lift 1 w/60 s/4 r/10`

Sample output:
`Got it. I've added this workout:`

Notes:
- `weight` must be `>= 0`.
- `sets` and `reps` must be positive integers.

Invalid input example:
`add-lift Bench Press w/80 s/0 r/8`

Expected error:
`Sets must be a positive integer.`

### Edit an existing workout: `edit`

Updates one field of an existing workout by index.

Format:
`edit <index> <field>/<value>`

Examples:
- `edit 1 distance/4.7`
- `edit 2 reps/10`
- `edit 3 name/Tempo Run`

Sample output:
`Updated workout 2: [Run] Easy Run (Date: 2026-04-02) (Distance: 3.0km, Duration: 3.0 mins)`

Editable fields:
- For all workouts: `name`, `description`
- For run workouts: `distance`, `duration`
- For lift workouts: `weight`, `sets`, `reps`

Important:
- Use full field names in `edit` commands.
- Shorthand flags such as `d/` and `t/` are for `add-run`, not `edit`.
- For example, use `edit 1 distance/5` instead of `edit 1 d/5`.

Invalid input example:
`edit 1 weight/abc`

Expected error:
`Invalid weight value: abc`

### Delete a workout: `delete`

Deletes one workout by index.

Format:
`delete <index>`

Examples:
- `delete 1`
- `delete 3`

Sample output:
`Deleted workout: Bench Press`

Invalid input example:
`delete abc`

Expected error:
`Workout index must be a positive integer.`

### Search workouts by date: `search-date`

Shows workouts completed on the specified date.

Format:
`search-date <YYYY-MM-DD>`

Example:
- `search-date 2026-03-15`

Sample output when matches exist:
`Workouts on 2026-03-15:`

Sample output when no matches exist:
`No workouts found.`

Invalid input example:
`search-date 2026/03/15`

Expected error:
`Invalid date format for search-date.`

### Exit the app: `exit`

Saves data and closes FitLogger.

Format:
`exit`

Sample output:
`Workouts saved.`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Copy the `data/fitlogger.txt` file to the same relative location in the other machine.

**Q**: Why did `edit` or `delete` fail even though the command format looked correct?

**A**: Check index bounds and field type compatibility. For `edit`, use full field names (e.g., `distance`, `duration`, `weight`). Short forms like `d/5` or `t/20` are not valid for `edit`.

## Command Summary

Commands implemented in my scope:

* Add lift: `add-lift <name_or_id> w/<weightKg> s/<sets> r/<reps>`
* Edit workout: `edit <index> <field>/<value>`
* Delete workout: `delete <index>`
* Search by date: `search-date <YYYY-MM-DD>`
* Exit app: `exit`
