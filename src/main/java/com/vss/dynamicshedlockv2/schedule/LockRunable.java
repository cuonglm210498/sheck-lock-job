package com.vss.dynamicshedlockv2.schedule;

import com.vss.dynamicshedlockv2.modal.TaskDefinition;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.javacrumbs.shedlock.core.DefaultLockingTaskExecutor;
import net.javacrumbs.shedlock.core.LockConfiguration;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.core.LockingTaskExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.Duration;
import java.time.Instant;

@Data
@NoArgsConstructor
public class LockRunable implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(LockRunable.class);

    @Autowired
    private Runnable runnable;

    private String name;

    @Autowired
    private LockProvider lockProvider;

    public LockRunable(Runnable runnable, String name, LockProvider lockProvider) {
        this.runnable = runnable;
        this.name = name;
        this.lockProvider = lockProvider;
    }

    @Override
    public void run() {

        LockingTaskExecutor executor = new DefaultLockingTaskExecutor(lockProvider);
        executor.executeWithLock(runnable, new LockConfiguration(Instant.now(), name, Duration.ofSeconds(60), Duration.ofMillis(1000)));
    }

}
