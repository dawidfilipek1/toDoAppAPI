package com.gemini.todolistapi.Controller;

import com.gemini.todolistapi.service.Task;
import com.gemini.todolistapi.toDoAppException.ToDoListAppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gemini.todolistapi.service.ToDoListService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/toDoApp")
public class ToDoListController {
    ToDoListService toDoListService;

    public ToDoListController(ToDoListService toDoListService){
        this.toDoListService = toDoListService;
    }

    @PostMapping("/addTask/{task}/{date}")
    public ResponseEntity<String> addNewTask(@PathVariable String task, @PathVariable LocalDate date){
        toDoListService.addNewTask(task, date);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dodane taska: " + task);
    }

    @GetMapping("/getTaskByIdx/{idx}")
    public ResponseEntity<Task> getTaskByIdx(@PathVariable int idx) throws ToDoListAppException {
        Task result = toDoListService.getTaskByIdx(idx);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/getTasksByDate/{date}")
    public ResponseEntity<List<String>> getTasksByDate(@PathVariable LocalDate date) throws ToDoListAppException {
        List<String> result = toDoListService.getTasksByDate(date);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/getAllTasks")
    public ResponseEntity<Collection<Task>> getAllTasks() throws ToDoListAppException {
        Collection<Task> result = toDoListService.getAllTasks();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/deleteByIdx/{idx}")
    public ResponseEntity<String> deleteByIdx(@PathVariable int idx) throws ToDoListAppException {
        String result = toDoListService.deleteByIdx(idx);
        return ResponseEntity.status(HttpStatus.OK).body("Usunieto: " + result);
    }

    @DeleteMapping("/deleteByDate/{date}")
    public ResponseEntity<List<String>> deleteByDate(@PathVariable LocalDate date) throws ToDoListAppException {
        List<String> result = toDoListService.deleteAllByDate(date);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
