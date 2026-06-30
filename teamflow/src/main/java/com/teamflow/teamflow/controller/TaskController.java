package com.teamflow.teamflow.controller;

import com.teamflow.teamflow.dto.request.TaskRequest;
import com.teamflow.teamflow.dto.response.TaskResponse;
import com.teamflow.teamflow.security.CurrentUser;
import com.teamflow.teamflow.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final CurrentUser currentUser;

    @PostMapping("/{projectId}/tasks")
    public ResponseEntity<TaskResponse> createTask(
            @PathVariable Long projectId,
            @Valid @RequestBody TaskRequest request,
            HttpServletRequest httpRequest) {
        Long userId = currentUser.getUserId(httpRequest);
        TaskResponse response = taskService.createTask(projectId, request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<List<TaskResponse>> getProjectTasks(
            @PathVariable Long projectId,
            HttpServletRequest httpRequest) {
        Long userId = currentUser.getUserId(httpRequest);
        return ResponseEntity.ok(taskService.getProjectTasks(projectId, userId));
    }

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long taskId,
            @Valid @RequestBody TaskRequest request,
            HttpServletRequest httpRequest) {
        Long userId = currentUser.getUserId(httpRequest);
        return ResponseEntity.ok(taskService.updateTask(taskId, request, userId));
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long taskId,
            HttpServletRequest httpRequest) {
        Long userId = currentUser.getUserId(httpRequest);
        taskService.deleteTask(taskId, userId);
        return ResponseEntity.noContent().build();
    }
}
