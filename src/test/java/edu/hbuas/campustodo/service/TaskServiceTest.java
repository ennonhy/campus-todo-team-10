package edu.hbuas.campustodo.service;

import edu.hbuas.campustodo.model.Priority;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskServiceTest {

    @Test
    void shouldAddTask() {
        TaskService service = new TaskService();
        var task = service.addTask("完成需求评审");

        assertEquals(1L, task.getId());
        assertEquals("完成需求评审", task.getTitle());
        assertFalse(task.isCompleted());
        assertEquals(1, service.listAll().size());
    }

    @Test
    void shouldRejectBlankTitle() {
        TaskService service = new TaskService();

        assertThrows(IllegalArgumentException.class,
            () -> service.addTask(""));
    }

    @Test
    void newTaskDefaultsToMediumPriority() {
        TaskService service = new TaskService();
        service.addTask("写实验报告");
        var task = service.listAll().get(0);
        assertEquals(Priority.MEDIUM, task.getPriority());
    }

    @Test
    void filterByPriorityReturnsEmptyListWhenNoMatch() {
        TaskService service = new TaskService();
        service.addTask("普通任务");
        assertTrue(service.filterByPriority(Priority.HIGH).isEmpty());
        assertTrue(service.filterByPriority(Priority.LOW).isEmpty());
    }

    @Test
    void filterByPriorityReturnsMatchingTasks() {
        TaskService service = new TaskService();
        service.addTask("普通A");
        service.addTask("紧急作业");
        service.listAll().get(1).setPriority(Priority.HIGH);
        service.addTask("普通B");

        var highs = service.filterByPriority(Priority.HIGH);
        assertEquals(1, highs.size());
        assertEquals("紧急作业", highs.get(0).getTitle());

        var mediums = service.filterByPriority(Priority.MEDIUM);
        assertEquals(2, mediums.size());
    }
}
