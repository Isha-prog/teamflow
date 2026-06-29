package com.teamflow.teamflow.repository;

import com.teamflow.teamflow.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProjectId(Long projectId);

    List<Task> findByProjectIdAndStatus(Long projectId, Task.Status status);

    List<Task> findByProjectIdAndPriority(Long projectId, Task.Priority priority);
}
