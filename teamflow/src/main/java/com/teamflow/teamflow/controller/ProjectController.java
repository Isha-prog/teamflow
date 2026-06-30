package com.teamflow.teamflow.controller;

import com.teamflow.teamflow.dto.request.ProjectRequest;
import com.teamflow.teamflow.dto.response.ProjectResponse;
import com.teamflow.teamflow.security.CurrentUser;
import com.teamflow.teamflow.service.ProjectService;
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
public class ProjectController {

    private final ProjectService projectService;
    private final CurrentUser currentUser;

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(
            @Valid @RequestBody ProjectRequest request,
            HttpServletRequest httpRequest) {
        Long userId = currentUser.getUserId(httpRequest);
        ProjectResponse response = projectService.createProject(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getMyProjects(
            HttpServletRequest httpRequest) {
        Long userId = currentUser.getUserId(httpRequest);
        return ResponseEntity.ok(projectService.getUserProjects(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProject(
            @PathVariable Long id,
            HttpServletRequest httpRequest) {
        Long userId = currentUser.getUserId(httpRequest);
        return ResponseEntity.ok(projectService.getProjectById(id, userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectRequest request,
            HttpServletRequest httpRequest) {
        Long userId = currentUser.getUserId(httpRequest);
        return ResponseEntity.ok(projectService.updateProject(id, request, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(
            @PathVariable Long id,
            HttpServletRequest httpRequest) {
        Long userId = currentUser.getUserId(httpRequest);
        projectService.deleteProject(id, userId);
        return ResponseEntity.noContent().build();
    }
}
