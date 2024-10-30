# CLI Task Tracker

This is a simple command-line interface (CLI) application for managing tasks. You can add, update, delete, mark, and list tasks directly from the terminal.

## Features

- **Add a Task:** Add a new task with a description.
- **Update a Task:** Update the description of an existing task.
- **Delete a Task:** Remove a task by its ID.
- **Mark a Task:** Mark a task as "in progress" or "done."
- **List Tasks:** List all tasks or filter them by status (e.g., `todo`, `in progress`, `done`).

## Usage
```bash
# Adding a new task
java CLITaskTracker add "Buy groceries"
# Output: Task added successfully (ID: 1)

# Updating a task
java CLITaskTracker update 1 "Buy groceries and cook dinner"
# Output: Task updated successfully (ID: 1)

# Deleting a task
java CLITaskTracker delete 1
# Output: Task deleted successfully (ID: 1)

# Marking a task as in progress
java CLITaskTracker mark-in-progress 1
# Output: Task marked as in progress (ID: 1)

# Marking a task as done
java CLITaskTracker mark-done 1
# Output: Task marked as done (ID: 1)

# Listing all tasks
java CLITaskTracker list
# Output: List of all tasks

# Listing tasks by status
java CLITaskTracker list todo
java CLITaskTracker list in-progress
java CLITaskTracker list done

```
