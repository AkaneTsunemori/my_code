package com.suzumiya.dao;


import com.suzumiya.model.DagContextInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface IDagContextInfoDao {


    @Insert("insert into dag_context_info(dag_job_name,owner,priority,operator,extra_info,gmt_create,gmt_modified) " +
            "values(#{dagJobName},#{owner},#{priority},#{operator},#{extraInfo},#{gmtCreate},#{gmtModified})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void add(DagContextInfo dagContextInfo);

    @Update("update dag_context_info set priority=#{priority},operator=#{operator},gmt_modified=now() where dag_job_name=#{dagJobName} ")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int update(DagContextInfo dagContextInfo);


    @Select("select * from dag_context_info where dag_job_name=#{dagJobName}")
    List<DagContextInfo> findAllByDagName(DagContextInfo dagContextInfo);
}
