package com.murphy.project.codeGeneration;

import com.murphy.project.config.CodeGenUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;

public class GeneratorBean {

    DataSource dataSource;

    @Before
    public void before_bean() throws Exception {
        HikariConfig configuration =new HikariConfig();
        configuration.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/boot_init?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false");
        configuration.setUsername("root");
        configuration.setPassword("123456");
        dataSource=new HikariDataSource(configuration);
    }

    /**
     * 生成后端代码
     * @throws Exception
     */
    @Test
    public void generatorAll() throws Exception {
        String entityPackageName = "com.yupi.project.model.ami";
        String daoPackageName = "com.yupi.project.dao.ami";
//		String servicePackageName = "com.yupi.project.service.scheme";
//		String controllerPackageName = "com.yupi.project.controller.scheme";
        String servicePackageName = "com.yupi.project.service.ami";
//		String daoPackageName = "";
        String controllerPackageName = "com.yupi.project.controller.ami";
        //		String queryTimeFiled=null;
        String queryTimeFiled="meterNumber";//createTime
        String tableName = "walkby_task_meter";
        CodeGenUtil.generatorAll(tableName, queryTimeFiled,entityPackageName, daoPackageName, servicePackageName,controllerPackageName, dataSource);
    }

    /**
     * 单独创建Entity
     * @throws Exception
     */
    @Test
    public void generatorEntity() throws Exception {
        String packageName = "com.murphy.project.model.sys";
        String tableName = "sys_role_menu";
        CodeGenUtil.generatorEntity(tableName, packageName, dataSource);
    }

    /**
     * 单独创建Dao
     * @throws Exception
     */
    @Test
    public void generatorDao() throws Exception {
        String repositoryPackageName = "com.murphy.project.mapper.sys";
        String keyType="Long"; //主键类型
        String entityPackageName="com.murphy.project.model.sys";
        String entityName="SysRole";
        CodeGenUtil.generatorMapper(repositoryPackageName, entityPackageName, entityName, keyType);
    }
}
