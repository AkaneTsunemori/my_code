package com.suzumiya.dao;

import com.suzumiya.TraitCollectService;
import com.suzumiya.model.TraitCollect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-jdbc-context.xml")
public class ITraitCollectDaoTest {
    @Autowired
    private TraitCollectService traitCollectService;

    //    @Insert("insert into ml_standard_component_config(user,group,job,component_name,traits,is_deploy_exist,extra_info,gmt_create,gmt_modified,is_delete)
    @Test
    public void add() {
        TraitCollect one = new TraitCollect();
        one.setUserName("shiro");
        one.setGroupName("ai");
        one.setJob("dag_job");
        one.setComponentName("featureTransformComponent");
        one.setTraits("traitA,traitB");
        one.setDeployExist(false);
        one.setExtraInfo("");
        one.setDelete(false);
        one.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        one.setGmtModified(new Timestamp(System.currentTimeMillis()));
        System.out.println(one);
        traitCollectService.insertOrUpdate(one);
        System.out.println(one);
    }

}