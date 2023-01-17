import java.util.HashMap;

public class Epic extends Task {
    private int id;
    HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public Epic(String name, String description, String status) {
        super(name, description, status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    void getSubTask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        subtask.setEpicId(id);
    }

    void removeSubtask(int id) {
        subtasks.remove(id);
    }

    void reviewStatus() {
        if (subtasks.isEmpty()) {
            this.setStatus("NEW");
            return;
        }

        int amountStatusNew = 0;

        int amountStatusDone = 0;

        for (Subtask title : subtasks.values()) {
            if (title.getStatus() == "NEW") {
                amountStatusNew = amountStatusNew + 1;
            } else if (title.getStatus() == "DONE") {
                amountStatusDone = amountStatusDone + 1;
            }
        }
        if (amountStatusNew == subtasks.size()) {
            this.setStatus("NEW");
        } else if (amountStatusDone == subtasks.size()) {
            this.setStatus("DONE");
        } else {
            this.setStatus("IN_PROGRESS");
        }
    }

    @Override
    public String toString() {
        String result = "Task{" +
                "name='" + this.getName() + '\'' +
                ", description='" + this.getDescription() + '\'';
        if (this.getDescription() != null) {
            result = result + ", description.length='" + this.getDescription().length() + '\'';
        } else {
            result = result + ", description.length='null'";
        }
        return result + ", status='" + this.getStatus() + '\'' +
                ", id=" + id +
                '}';
    }
}

