package com.vss.dynamicshedlockv2.service;

import com.vss.dynamicshedlockv2.modal.TaskDefinition;
import com.vss.dynamicshedlockv2.schedule.BusinessRunable;
import com.vss.dynamicshedlockv2.schedule.LockRunable;
import com.vss.dynamicshedlockv2.schedule.SynchronizeData;
import lombok.Data;
import net.javacrumbs.shedlock.core.LockProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
public class SchedulerManagerService {

    @Autowired
    private TaskScheduler taskScheduler;

    private Map<String, ScheduledFuture> jobsMap = new HashMap<>();

    private String lockName = "send_report_2";
    @Autowired
    private LockProvider lockProvider;
    @Autowired
    private BusinessRunable businessRunable;
    @Autowired
    private SynchronizeData synchronizeData;

    @PostConstruct
    public void initData() {

    }

    public SchedulerManagerService(TaskScheduler taskScheduler, LockProvider lockProvider) {
        this.taskScheduler = taskScheduler;
        this.lockProvider = lockProvider;
    }

    /**
     *  reschedule aaaaaaaaaa
     * @param taskDefinition input
     */
    public void scheduleATask(TaskDefinition taskDefinition) {
        businessRunable.setName(taskDefinition.getData());
        jobsMap.put(taskDefinition.getJobName(), taskScheduler.schedule(wrap(businessRunable, lockName), new CronTrigger(taskDefinition.getCronExpression())));
    }

    public void scheduleBTask(TaskDefinition taskDefinition) {
        synchronizeData.setName(taskDefinition.getData());
        jobsMap.put(taskDefinition.getJobName(), taskScheduler.schedule(wrap(synchronizeData, lockName), new CronTrigger(taskDefinition.getCronExpression())));
    }

    public void stopJob(String jobName) {
        var schedule = jobsMap.get(jobName);
        if (schedule != null) {
            schedule.cancel(false);
            jobsMap.remove(jobName);
        }
    }

    public Runnable wrap(Runnable runnable, String lockName) {
        return new LockRunable(runnable, lockName, lockProvider);
    }
}
