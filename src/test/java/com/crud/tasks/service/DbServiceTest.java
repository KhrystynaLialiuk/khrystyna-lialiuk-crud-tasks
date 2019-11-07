package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void shouldGetAllTasks() {
        //Given
        Task task1 = new Task(1L, "Do shopping", "Test description");
        Task task2 = new Task(2L, "Do cleaning", "Test description");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        when(taskRepository.findAll()).thenReturn(tasks);

        //When
        List<Task> foundTasks = dbService.getAllTasks();

        //Then
        assertEquals(tasks.size(), foundTasks.size());
    }

    @Test
    public void shouldGetZeroTasks() {
        //Given
        List<Task> tasks = new ArrayList<>();
        when(taskRepository.findAll()).thenReturn(tasks);

        //When
        List<Task> foundTasks = dbService.getAllTasks();

        //Then
        assertEquals(0, foundTasks.size());
    }

    @Test
    public void testGetTask() {
        //Given
        Task task1 = new Task(1L, "Do shopping", "Test description");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));

        //When
        Optional<Task> foundTask = dbService.getTask(1L);

        //Then
        assertNotNull(foundTask);
    }

    @Test
    public void testGetNotExistingTask() {
        //Given
        when(taskRepository.findById(anyLong())).thenReturn(null);

        //When
        Optional<Task> foundTask = dbService.getTask(1L);

        //Then
        assertNull(foundTask);
    }

    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task(1L, "Do shopping", "Test description");
        when(taskRepository.save(task)).thenReturn(task);

        //When
        Task savedTask = dbService.saveTask(task);

        //Then
        assertEquals(task.getId(), savedTask.getId());
        assertEquals(task.getTitle(), savedTask.getTitle());
        assertEquals(task.getContent(), savedTask.getContent());
    }

    @Test
    public void testDeleteTask() throws TaskNotFoundException {
        //When
        dbService.deleteTask(1L);

        //Then
        verify(taskRepository, times(1)).deleteById(1L);
    }

    @Test(expected = TaskNotFoundException.class)
    public void testDeleteNotExistingTask() throws TaskNotFoundException {
        //Given
        doThrow(IllegalArgumentException.class).when(taskRepository).deleteById(anyLong());

        //When
        dbService.deleteTask(1L);
    }
}
