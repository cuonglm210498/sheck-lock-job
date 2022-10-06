package com.vss.dynamicshedlockv2.modal;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDefinition {

    private String jobName;
    private String cronExpression;
    private String actionType;
    private String data;
}
