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
        // 验收标准：默认MEDIUM
        assertEquals(Priority.MEDIUM, task.getPriority());
    }

    @Test
    void shouldRejectBlankTitle() {
        assertThrows(IllegalArgumentException.class, () -> service.addTask("   "));
    }

    // 验收标准：空结果返回空列表
    @Test
    void shouldReturnEmptyListWhenNoMatchingPriority() {
        service.addTask("普通任务"); // 默认 MEDIUM
        List<Task> highTasks = service.filterByPriority(Priority.HIGH);
        assertTrue(highTasks.isEmpty());
    }

    // 验收标准：测试覆盖（按优先级筛选命中）
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
}
