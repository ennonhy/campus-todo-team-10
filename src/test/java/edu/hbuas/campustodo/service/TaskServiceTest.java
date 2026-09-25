package edu.hbuas.campustodo.service;

import edu.hbuas.campustodo.model.Priority;
import edu.hbuas.campustodo.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private TaskService service;

    @BeforeEach
    void setUp() {
        service = new TaskService();
    }

    @Test
    void shouldAddTask() {
        var task = service.addTask("完成需求评审");
        assertEquals(1L, task.getId());
        assertEquals("完成需求评审", task.getTitle());
        assertFalse(task.isCompleted());
        assertEquals(1, service.listAll().size());
        // 你的验收标准：默认MEDIUM
        assertEquals(Priority.MEDIUM, task.getPriority());
    }

    @Test
    void shouldRejectBlankTitle() {
        assertThrows(IllegalArgumentException.class, () -> service.addTask("   "));
    }

    // ==========================================
    // 以下是你自己分支的测试（优先级筛选）
    // ==========================================
    @Test
    void shouldReturnEmptyListWhenNoMatchingPriority() {
        service.addTask("普通任务"); // 默认 MEDIUM
        List<Task> highTasks = service.filterByPriority(Priority.HIGH);
        assertTrue(highTasks.isEmpty());
    }

    @Test
    void shouldFilterTasksByPriority() {
        Task highTask = service.addTask("高优先级任务");
        highTask.setPriority(Priority.HIGH);

        Task lowTask = service.addTask("低优先级任务");
        lowTask.setPriority(Priority.LOW);

        assertEquals(1, service.filterByPriority(Priority.HIGH).size());
        assertEquals("高优先级任务", service.filterByPriority(Priority.HIGH).get(0).getTitle());
        assertEquals(1, service.filterByPriority(Priority.LOW).size());
    }

    // ==========================================
    // 以下是远程 main 分支合并进来的测试（开发者B的完成任务测试）
    // ==========================================
    @Test
    void shouldCompleteTask() {
        Task task = service.addTask("待完成任务");
        service.completeTask(task.getId());
        assertTrue(task.isCompleted());
    }

    @Test
    void shouldThrowWhenCompletingNonExistentTask() {
        assertThrows(IllegalArgumentException.class, () -> service.completeTask(999L));
    }

    @Test
    void shouldThrowWhenCompletingAlreadyCompletedTask() {
        Task task = service.addTask("重复完成任务");
        service.completeTask(task.getId());
        assertThrows(IllegalStateException.class, () -> service.completeTask(task.getId()));
    }
}

