package com.suzumiya;

import com.suzumiya.model.TraitCollect;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class TraitCollectServiceTest {

    @Autowired
    TraitCollectService traitCollectService;

    @Test
    public void insertOrUpdate() {
        TraitCollect one = new TraitCollect();
        one.setUserName("shiro");
        one.setGroupName("ai");
        one.setJob("dag_job");
        one.setComponentName("featureTransformComponent");
        one.setTraits("traitA,traitB");
        one.setDeployExist(false);
        one.setExtraInfo("");
        one.setDelete(false);
        traitCollectService.insertOrUpdate(one);
    }
}