package edu.hbuas.campustodo.service;

import edu.hbuas.campustodo.model.Priority;
import edu.hbuas.campustodo.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private long nextId = 1;

    public Task addTask(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be blank");
        }
        Task task = new Task(nextId++, title);
        tasks.add(task);
        return task;
    }
    /**
     * 根据优先级筛选任务列表。
     * @return 符合该优先级的任务列表，若没有匹配则返回空列表
     */
    public List<Task> listAll() {
        return new ArrayList<>(tasks);
    }

    // ==========================================
    // 以下是远程 main 分支合并进来的代码（开发者B的完成任务功能）
    // ==========================================
    public void completeTask(long id) {
        Task task = tasks.stream()
            .filter(t -> t.getId() == id)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Task not found: " + id));

        if (task.isCompleted()) {
            throw new IllegalStateException("Task is already completed");
        }
        task.setCompleted(true);
    }

    // ==========================================
    // 以下是你自己分支的代码（优先级筛选功能）
    // ==========================================
    public List<Task> filterByPriority(Priority priority) {
        if (priority == null) {
            throw new IllegalArgumentException("Priority cannot be null");
        }
        return tasks.stream()
            .filter(task -> task.getPriority() == priority)
            .collect(Collectors.toList());
    }
}
