package com.teamflow.teamflow.controller;

import com.teamflow.teamflow.dto.request.ProjectRequest;
import com.teamflow.teamflow.dto.response.ProjectResponse;
import com.teamflow.teamflow.security.CurrentUser;
import com.teamflow.teamflow.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final CurrentUser currentUser;

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(
            @Valid @RequestBody ProjectRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projectService.createProject(request, currentUser.getUserId()));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getMyProjects() {
        return ResponseEntity.ok(projectService.getUserProjects(currentUser.getUserId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id, currentUser.getUserId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectRequest request) {
        return ResponseEntity.ok(projectService.updateProject(id, request, currentUser.getUserId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id, currentUser.getUserId());
        return ResponseEntity.noContent().build();
    }
}
