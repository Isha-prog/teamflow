package com.teamflow.teamflow.service;

import com.teamflow.teamflow.dto.request.ProjectRequest;
import com.teamflow.teamflow.dto.response.ProjectResponse;
import com.teamflow.teamflow.exception.ResourceNotFoundException;
import com.teamflow.teamflow.model.Project;
import com.teamflow.teamflow.model.User;
import com.teamflow.teamflow.repository.ProjectRepository;
import com.teamflow.teamflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectResponse createProject(ProjectRequest request, Long userId) {
        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Project project = Project.builder()
                .name(request.getName())
                .description(request.getDescription())
                .owner(owner)
                .build();

        Project saved = projectRepository.save(project);
        return mapToResponse(saved);
    }

    public List<ProjectResponse> getUserProjects(Long userId) {
        return projectRepository.findByOwnerId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ProjectResponse getProjectById(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        if (!project.getOwner().getId().equals(userId)) {
            throw new ResourceNotFoundException("Project not found");
        }

        return mapToResponse(project);
    }

    public ProjectResponse updateProject(Long projectId, ProjectRequest request, Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        if (!project.getOwner().getId().equals(userId)) {
            throw new ResourceNotFoundException("Project not found");
        }

        project.setName(request.getName());
        project.setDescription(request.getDescription());

        Project updated = projectRepository.save(project);
        return mapToResponse(updated);
    }

    public void deleteProject(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        if (!project.getOwner().getId().equals(userId)) {
            throw new ResourceNotFoundException("Project not found");
        }

        projectRepository.delete(project);
    }

    private ProjectResponse mapToResponse(Project project) {
        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .ownerName(project.getOwner().getName())
                .createdAt(project.getCreatedAt())
                .taskCount(project.getTasks().size())
                .build();
    }
}
