package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test_title", "Test_content");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertNotNull(task);
        assertEquals(taskDto.getId(), task.getId());
        assertEquals(taskDto.getTitle(), task.getTitle());
        assertEquals(taskDto.getContent(), task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(2L, "Testing", "Testing content");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertNotNull(taskDto);
        assertEquals(task.getId(), taskDto.getId());
        assertEquals(task.getTitle(), taskDto.getTitle());
        assertEquals(task.getContent(), taskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        Task task1 = new Task(3L, "Test title 1", "Test content 1");
        Task task2 = new Task(4L, "Test title 2", "Test content 2");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertNotNull(taskDtos);
        assertEquals(2, taskDtos.size());
        assertEquals(taskList.get(0).getId(), taskDtos.get(0).getId());
        assertEquals(taskList.get(0).getTitle(), taskDtos.get(0).getTitle());
        assertEquals(taskList.get(0).getContent(), taskDtos.get(0).getContent());
        assertEquals(taskList.get(1).getId(), taskDtos.get(1).getId());
        assertEquals(taskList.get(1).getTitle(), taskDtos.get(1).getTitle());
        assertEquals(taskList.get(1).getContent(), taskDtos.get(1).getContent());
    }
}
