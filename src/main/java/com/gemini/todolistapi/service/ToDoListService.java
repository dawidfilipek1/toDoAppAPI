package com.gemini.todolistapi.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ToDoListService {
    Map<Integer, Task> toDoList = new ConcurrentHashMap<>();
    int index = 0;

    public void addNewTask(String task, Date date){
        toDoList.put(
                index++,
                Task.builder().task(task).date(date).build()
        );
    }

    public String getTasByIdx(int idx){
        return toDoList.get(idx).toString();
    }

    public List<String> getTasksByDate(Date date){
        List<String> result = new ArrayList<>();

        for(Task task : toDoList.values()){
            if(task.date.compareTo(date) == 0) result.add(task.task);
        }
        return result;
    }

    public Collection<Task> getAllTasks(){
        return toDoList.values();
    }

    public String deleteByIdx(int idx) throws Exception {
        if(toDoList.containsKey(idx)){
            String task = toDoList.get(idx).task;
            toDoList.remove(idx);
            return task;
        }
        //zrób swoje exception
        throw new Exception("Brak takiego taska");
    }

    public List<String> deleteAllByDate(Date date){
        List<String> result = new ArrayList<>();
        for(int idx : toDoList.keySet()){
            if(toDoList.get(idx).date.compareTo(date) == 0){
                result.add(toDoList.get(idx).task);
                toDoList.remove(idx);
            }
        }
        return result;
    }

}
