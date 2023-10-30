package com.suzumiya.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * dag 上下文配置, 方便用户拉取配置清单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DagContextInfo {
    private int id;
    private String dagJobName;
    private String owner;
    private String priority;
    private String operator;
    private String extraInfo;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;

    public DagContextInfo(String dagJobName, String owner , String priority, String operator, String extraInfo) {
        this.dagJobName = dagJobName;
        this.owner = owner;
        this.priority = priority;
        this.operator = operator;
        this.extraInfo = extraInfo;
    }
}
