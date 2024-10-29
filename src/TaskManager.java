package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskManager {
    private final Path FILE_PATH = Path.of("tasks.json");
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = loadTasks();
    }

    private List<Task> loadTasks() {
        List<Task> stored_tasks = new ArrayList<>();

        if (!Files.exists(FILE_PATH)) {
            return new ArrayList<>();
        }

        try {
            String jsonContent = Files.readString(FILE_PATH);
            String[] taskList = jsonContent.replace("[", "").replace("]", "").split("},");
            for (String taskJson : taskList) {
                if (!taskJson.endsWith("}")) {
                    taskJson = taskJson + "}";
                    stored_tasks.add(Task.fromJson(taskJson));
                } else {
                    stored_tasks.add(Task.fromJson(taskJson));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stored_tasks;
    }

    public void saveTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(tasks.get(i).toJson());
            if (i < tasks.size() - 1) {
                sb.append(",\n");
            }
        }
        sb.append("\n]");

        String jsonContent = sb.toString();
        try {
            Files.writeString(FILE_PATH, jsonContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTask(String description) {
        Task new_task = new Task(description);
        tasks.add(new_task);
        System.out.println("Task added (ID: " + new_task.getId() + ")");
    }

    public Optional<Task> findTask(String id) {
        return tasks.stream().filter((task) -> task.getId() == Integer.parseInt(id)).findFirst();
    }

    public void updateTask(String id, String new_description) {
        findTask(id).ifPresentOrElse(task -> task.updateDescription(new_description), () -> {
            throw new IllegalArgumentException("ask with ID \" + id + \" not found!");
        });
    }

    public void deleteTask(String id) {
        findTask(id).ifPresentOrElse(task -> tasks.remove(task), () -> {
            throw new IllegalArgumentException("ask with ID \" + id + \" not found!");
        });
    }

    public void markInProgress(String id) {
        findTask(id).ifPresentOrElse(Task::markInProgress, () -> {
            throw new IllegalArgumentException("ask with ID \" + id + \" not found!");
        });
    }

    public void markDone(String id) {
        findTask(id).ifPresentOrElse(Task::markDone, () -> {
            throw new IllegalArgumentException("ask with ID \" + id + \" not found!");
        });
    }

    public void listTasks(String type) {
        for (Task task : tasks) {
            String status = task.getStatus().toString().strip();
            if (type.equals("All") || status.equals(type)) {
                System.out.println(task.toString());
            }
        }
    }
}
