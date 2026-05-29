package com.gemini.todolistapi.service;

import com.gemini.todolistapi.toDoAppException.ToDoListAppException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ToDoListService {
    HashSet<Task> toDoList = new HashSet<>();
    int index = 0;

    public void addNewTask(String task, LocalDate date){
        toDoList.add(
                Task.builder().task(task).idx(index++).date(date).build()
        );
    }

    public Task getTaskByIdx(int idx) throws ToDoListAppException {
        for(Task task : toDoList){
            if(task.idx == idx) return task;
        }
        throw new ToDoListAppException("Task with index " + idx + " does not exist");
    }

    public List<String> getTasksByDate(LocalDate date) throws ToDoListAppException {
        List<String> result = new ArrayList<>();

        for(Task task : toDoList){
            if(task.date.isEqual(date)) result.add(task.task);
        }
        if(result.isEmpty()) throw new ToDoListAppException("There are not task wtih date " + date);
        return result;
    }

    public List<Task> getAllTasks() throws ToDoListAppException {
        if(toDoList.isEmpty()) throw new ToDoListAppException("The list is empty");

        return toDoList.stream().toList();
    }

    public String deleteByIdx(int idx) throws ToDoListAppException {
        Task task = this.getTaskByIdx(idx);
        String result = task.task;
        toDoList.remove(task);
        return result;
    }

    public List<String> deleteAllByDate(LocalDate date) throws ToDoListAppException {
        List<String> result = new ArrayList<>();
        for(Task task : toDoList){
            if(task.date.isEqual(date)){
                result.add(task.task);
                toDoList.remove(task);
            }
        }
        if(result.isEmpty()) throw new ToDoListAppException("There are no task/s with date " + date);
        return result;
    }

}
