package edu.hbuas.campustodo.model;

import java.util.Objects;

public class Task {
    private long id;
    private String title;
    private boolean completed;
    private Priority priority;

    public Task(long id, String title) {
        // 保留远程 main 的严格校验（右侧的功能）
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be blank");
        }
        this.id = id;
        this.title = title.trim();
        this.completed = false;
        // 保留你的功能：默认优先级为 MEDIUM（左侧的功能）
        this.priority = Priority.MEDIUM;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // 保留你的功能：优先级 getter/setter
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
