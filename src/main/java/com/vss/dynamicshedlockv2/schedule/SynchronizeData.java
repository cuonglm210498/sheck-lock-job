package com.vss.dynamicshedlockv2.schedule;

import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javacrumbs.shedlock.core.LockProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author CuongLM18
 * @created 26/09/2022 - 4:38 PM
 * @project dynamic-shed-lock-v2
 */
@Component
@NoArgsConstructor
@Setter
public class SynchronizeData implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(SynchronizeData.class);

    private String name;

    public SynchronizeData(String name) {
        this.name = name;
    }

    @Autowired
    private LockProvider lockProvider;
    @Override
    public void run() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Synchronize Data");
    }
}
