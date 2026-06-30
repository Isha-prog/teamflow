package com.teamflow.teamflow.dto.request;

import com.teamflow.teamflow.model.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class TaskRequest {

    @NotBlank(message = "Task title is required")
    private String title;

    private String description;

    @NotNull(message = "Status is required")
    private Task.Status status;

    @NotNull(message = "Priority is required")
    private Task.Priority priority;

    private LocalDate dueDate;
}
