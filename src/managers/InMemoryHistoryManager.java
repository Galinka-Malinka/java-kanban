package managers;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    List<Task> history = new ArrayList<>();

    @Override
    public void add(Task task) {       // Добавление задачи в список истории просмотров
        if (history.size() == 10) {
            history.remove(0);
            history.add(task);
        } else {
            history.add(task);
        }
    }

    @Override
    public List<Task> getHistory() {  // Получение списка просмотренных задач
        return history;
    }
}