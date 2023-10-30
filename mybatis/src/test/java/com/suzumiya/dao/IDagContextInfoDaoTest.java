package com.suzumiya.dao;

import com.suzumiya.model.DagContextInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-jdbc-context.xml")
public class IDagContextInfoDaoTest {


    @Autowired
    private IDagContextInfoDao dagContextInfoDao;

    @Test
    public void add() {

        DagContextInfo  dagContextInfo = new DagContextInfo("suzumiya","haruhi","NORMAL","haruhi","");
        dagContextInfo.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        dagContextInfo.setGmtModified(new Timestamp(System.currentTimeMillis()));
        dagContextInfoDao.add(dagContextInfo);
    }
    @Test
    public void update() {
        DagContextInfo  dagContextInfo = new DagContextInfo("suzumiya","haruhi","S","shiro","");
        dagContextInfoDao.update(dagContextInfo);
    }

    @Test
    public void findAllByDagName(){
        DagContextInfo  dagContextInfo = new DagContextInfo();
        dagContextInfo.setDagJobName("suzumiya");
        System.out.println(dagContextInfoDao.findAllByDagName(dagContextInfo).get(0));
    }
}