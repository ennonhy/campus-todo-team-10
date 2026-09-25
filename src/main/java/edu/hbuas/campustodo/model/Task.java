package edu.hbuas.campustodo.model;

public class Task {
    private long id;
    private String title;
    private boolean completed;
    private Priority priority;

    public Task(long id, String title) {
        this.id = id;
        this.title = title;
        this.completed = false;
        // 验收标准：默认 MEDIUM
        this.priority = Priority.MEDIUM;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
}
