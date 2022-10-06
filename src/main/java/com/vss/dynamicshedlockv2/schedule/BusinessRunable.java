package com.vss.dynamicshedlockv2.schedule;

import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javacrumbs.shedlock.core.LockProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Setter
public class BusinessRunable implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(BusinessRunable.class);

    private String name;

    public BusinessRunable(String name) {
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
        System.out.println("Business Runnable");
    }

}
