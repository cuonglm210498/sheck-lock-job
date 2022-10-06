package com.vss.dynamicshedlockv2;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class DynamicShedLockV2Application {

    public static void main(String[] args) {
        SpringApplication.run(DynamicShedLockV2Application.class, args);
    }

}
