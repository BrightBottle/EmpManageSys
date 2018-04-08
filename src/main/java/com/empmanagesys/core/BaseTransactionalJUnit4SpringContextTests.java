package com.empmanagesys.core;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * 单元测试基类
 * 
 * @author 张罗平
 * 
 * @version 1.0
 */
@ActiveProfiles("test")
public class BaseTransactionalJUnit4SpringContextTests extends AbstractTransactionalJUnit4SpringContextTests {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    // 注入测试数据源
    @Autowired
    protected DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
        this.dataSource = dataSource;
    }
}
