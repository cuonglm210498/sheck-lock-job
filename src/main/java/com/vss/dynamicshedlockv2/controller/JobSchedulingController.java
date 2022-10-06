package com.vss.dynamicshedlockv2.controller;

import com.vss.dynamicshedlockv2.modal.TaskDefinition;
import com.vss.dynamicshedlockv2.service.SchedulerManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class JobSchedulingController {

    @Autowired
    private SchedulerManagerService schedulerManager;

    @PostMapping(path="/taskdef", consumes = "application/json", produces="application/json")
    public void scheduleATask(@RequestBody TaskDefinition taskDefinition) {
        schedulerManager.scheduleATask(taskDefinition);
    }

    @PostMapping(path="/sync", consumes = "application/json", produces="application/json")
    public void scheduleBTask(@RequestBody TaskDefinition taskDefinition) {
        schedulerManager.scheduleBTask(taskDefinition);
    }
}
