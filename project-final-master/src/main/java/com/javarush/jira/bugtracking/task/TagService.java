package com.javarush.jira.bugtracking.task;
import com.javarush.jira.bugtracking.Handlers;
import com.javarush.jira.common.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TagService {

    private final Handlers.TaskExtHandler handler;

    @Transactional
    public void attachTag(long taskId, Set<String> tags) {
        TaskRepository taskRepository = handler.getRepository();
        if (taskRepository.existsById(taskId)) {
            Task task = taskRepository.getExisted(taskId);
            task.setTags(tags);
            taskRepository.save(task);
        } else {
            throw new NotFoundException("There are no tasks with  " + taskId + " found");
        }
    }
}
