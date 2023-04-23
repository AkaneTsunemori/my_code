package com.suzumiya.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraitCollect {
    private  int id ;
    private String userName;
    private String groupName;
    private String job;
    private String componentName;
    private String traits;
    private boolean isDeployExist;
    private String extraInfo;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    private boolean isDelete;
}
