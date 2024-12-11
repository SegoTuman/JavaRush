package com.javarush.jira.bugtracking.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping(value = TaskController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @PostMapping("/tag/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void attachTag(@PathVariable("id") long taskId, @RequestBody Set<String> tags)
    {
        log.info("attached tags to task id={}", taskId);
        tagService.attachTag(taskId, tags);
    }
}
