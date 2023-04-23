package com.suzumiya.dao;

import com.suzumiya.model.TraitCollect;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ITraitCollectDao {

    @Insert("insert into online_trait_collect(user_name,group_name,job,component_name,traits,is_deploy_exist,extra_info,gmt_create,gmt_modified,is_delete) " +
            "values(#{userName},#{groupName},#{job},#{componentName},#{traits},#{isDeployExist},#{extraInfo},#{gmtCreate},#{gmtModified},#{isDelete})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void add(TraitCollect traitCollect);


    @Select("select * from online_trait_collect where job=#{job} and component_name=#{componentName}")
    List<TraitCollect> findAllByJobComponent(TraitCollect traitCollect);


    @Update("update online_trait_collect set trait=#{traits},gmt_modified=now() where job=#{job} and component_name=#{componentName}")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int update(TraitCollect traitCollect);

}

