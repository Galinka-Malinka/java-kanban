import java.util.*;

public class Manager {
    Map<Integer, Task> taskHashMap = new HashMap<>();
    Map<Integer, Subtask> subtaskHashMap = new HashMap<>();
    Map<Integer, Epic> epicHashMap = new HashMap<>();


    private int id = 0;

    public List getListOfOllTasks() { // Получение списка всех задач.

        ArrayList<Object> listOllTask = new ArrayList<>();
        listOllTask.add(taskHashMap.values());
        listOllTask.add(subtaskHashMap.values());
        listOllTask.add(epicHashMap.values());

        return listOllTask;
    }

    public void clearTask() { //Удаление всех задач.
        taskHashMap.clear();
        subtaskHashMap.clear();
        epicHashMap.clear();
    }

    public Task getTaskById(int id) {  //Получение по идентификатору
        Task newObject = null;
        if (taskHashMap.containsValue(taskHashMap.get(id))) {
            newObject = taskHashMap.get(id);
        } else if (subtaskHashMap.containsValue(subtaskHashMap.get(id))) {
            newObject = subtaskHashMap.get(id);
        } else if (epicHashMap.containsValue(epicHashMap.get(id))) {
            newObject = epicHashMap.get(id);
        }
        return newObject;
    }

    public Task createTask(Task task) {  //Создание задачи
        task.setId(id + 1);
        id = task.getId();
        taskHashMap.put(id, task);
        return task;
    }

    public Subtask createSubTask(Subtask subtask) { //Создание подзадачи
        subtask.setId(id + 1);
        id = subtask.getId();
        subtaskHashMap.put(id, subtask);
        return subtask;
    }

    public Epic createEpic(Epic epic) { //Создание эпика
        epic.setId(id + 1);
        id = epic.getId();
        epicHashMap.put(id, epic);
        return epic;
    }

    public Epic getSubtaskEpicId(int id) {  // Получение эпика по id
        return epicHashMap.get(id);
    }

    public void updateTask(int firstId, Task task) { //Обновление задачи
        task.setId(firstId);
        taskHashMap.put(firstId, task);
    }

    public void updateSubtask(int firstId, Subtask subtask) { //Обновление подзадачи
        subtask.setEpicId(subtaskHashMap.get(firstId).getEpicId());

        subtask.setId(firstId);

        subtaskHashMap.put(firstId, subtask);
        epicHashMap.get(subtask.getEpicId()).getSubTask(subtask);
        epicHashMap.get(subtask.getEpicId()).reviewStatus();
    }

    public void updateEpic(int firstId, Epic epic) { //Обновление эпика
        epic.setId(firstId);
        epicHashMap.put(firstId, epic);
    }

    public void removeById(int id) {// Удаление по идентификатору.

        if (taskHashMap.containsValue(taskHashMap.get(id))) {
            taskHashMap.remove(id);
        } else if (subtaskHashMap.containsValue(subtaskHashMap.get(id))) {
            Subtask subtask = subtaskHashMap.get(id);

            epicHashMap.get(subtask.getEpicId()).removeSubtask(subtask.getId());
            subtaskHashMap.remove(id);
            epicHashMap.get(subtask.getEpicId()).reviewStatus();

        } else if (epicHashMap.containsValue(epicHashMap.get(id))) {
            epicHashMap.remove(id);
        }
    }

    public Object getArrayTask(int id) {//Получение списка всех подзадач определённого эпика.
        Epic epic = epicHashMap.get(id);
        return epic.subtasks;
    }
}
