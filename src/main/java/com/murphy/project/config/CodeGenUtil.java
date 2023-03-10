package com.murphy.project.config;

import com.murphy.project.common.utils.DateUtils;
import com.murphy.project.common.utils.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;


import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeGenUtil {

    /**
     * 生成entity
     * @param tableName
     * @param packageName
     * @throws Exception
     */
    public static Map<String, Object> generatorEntity(String tableName , String packageName, DataSource dataSource) throws Exception {

        String basePath = System.getProperty("user.dir");
        String tempPath =basePath+"\\src\\main\\resources\\bean_template";
        String beanPath =basePath+"\\src\\main\\java\\";
        String replaceAll = packageName.replaceAll("\\.", "\\\\");
        beanPath=beanPath+replaceAll;
        if(StringUtils.isBlank(tableName)||StringUtils.isBlank(packageName)) {
            return null;
        }

        String beanName =StringUtils.toCapitalizeCamelCase(tableName);
        boolean isTree=false;
        List<Map<String, String>> colList = new ArrayList<Map<String, String>>(); // 存储字段信息
        Connection connection = dataSource.getConnection();
        Statement st = connection.createStatement();
        String keyType="";
        String keyName="";
        ResultSet columnRs = st.executeQuery("show full columns from " + tableName);
        while (columnRs.next()) {
            Map<String, String> colMap = new HashMap<String, String>();
            String column = columnRs.getString(1);//列名
            colMap.put("column", column);//列名
            colMap.put("filed", StringUtils.toCamelCase(column));//字段名
            String database_type = columnRs.getString(2);

            colMap.put("database_type", database_type);//数据库类型
            String filedType = StringUtils.typeTrans(columnRs.getString(2));

            colMap.put("bean_type",filedType);//字段类型
            String key = columnRs.getString(5);

            if(key.equals("PRI")&&StringUtils.isBlank(keyType)) {
                keyType=filedType;
                keyName=StringUtils.toCamelCase(column);
            }
            colMap.put("isId", key);
            String comment = columnRs.getString(9);
            String[] split = comment.split("@");
            colMap.put("comment",split[0]);
            if(split.length==3) {
                colMap.put("comment",split[0]+split[2]);
            }
            colMap.put("nullable",columnRs.getString(4));
            if(colMap.get("filed").equals("hasChild")){
                isTree=true;
            }
            colList.add(colMap);
        }

        //第一步:实例化Freemarker的配置类
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(tempPath));
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("table_name", tableName);
            dataMap.put("entity_package_name", packageName);
            dataMap.put("key_type", keyType);
            dataMap.put("key_name", keyName);
            dataMap.put("bean_name", beanName);
            dataMap.put("col_List", colList);
            dataMap.put("c_time", DateUtils.now());
            dataMap.put("isTree", isTree);
            dataMap.put("hasDelFlag", true);
            // step4 加载模版文件
            Template template = configuration.getTemplate("Bean.ftl");
            // step5 生成数据
            //判断文件夹是否存在
            File docFile = new File( beanPath+"\\" + beanName+".java");
            FileOutputStream fos = FileUtils.openOutputStream(docFile);
            out = new BufferedWriter(new OutputStreamWriter(fos));
            // step6 输出文件
            template.process(dataMap, out);
            System.out.println(beanName+"已经生成完成  位置 在 "+beanPath);
            return dataMap;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                    out.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;

    }

    /**
     * 生成repository
     * @param repositoryPackageName  生成到哪里
     * @param entityPackageName  entity所在的包  用于导入包
     * @param entityName 对应的 entity名字
     * @param keyType entity的主键类型
     *
     */
    public static Map<String, Object> generatorMapper(String repositoryPackageName,String entityPackageName,String entityName,String keyType){
        String basePath = System.getProperty("user.dir");
        String tempPath =basePath+"\\src\\main\\resources\\bean_template";
        String beanPath =basePath+"\\src\\main\\java\\";
        String replaceAll = repositoryPackageName.replaceAll("\\.", "\\\\");
        beanPath=beanPath+replaceAll;
        if(StringUtils.isBlank(keyType)) {
            keyType="Long";
        }
        if(StringUtils.isBlank(repositoryPackageName)||StringUtils.isBlank(entityPackageName)||StringUtils.isBlank(entityName)) {
            return null;
        }
        //第一步:实例化Freemarker的配置类
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(tempPath));
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("rep_package_name", repositoryPackageName);
            dataMap.put("entity_package_name", entityPackageName);
            dataMap.put("key_type", keyType);
            dataMap.put("entity_name", entityName);
            dataMap.put("c_time", DateUtils.now());
            // step4 加载模版文件
            Template template = configuration.getTemplate("Mapper.ftl");
            // step5 生成数据
            //判断文件夹是否存在
            File docFile = new File( beanPath+"\\" + entityName+"Mapper.java");
            FileOutputStream fos =FileUtils.openOutputStream(docFile);
            out = new BufferedWriter(new OutputStreamWriter(fos));
            // step6 输出文件
            template.process(dataMap, out);
            System.out.println(entityName+"Mapper"+"已经生成完成  位置 在 "+beanPath);
            return dataMap;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                    out.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;

    }

    /**
     * 生成 service 和serviceImpl
     * @param queryTimeFiled
     * @param servicePackageName
     * @param repositoryPackageName
     * @param entityPackageName
     * @param entityName
     * @param keyType
     * @return
     */
    public static Map<String, Object> generatorService(String queryTimeFiled,String servicePackageName,String repositoryPackageName,
                                                       String entityPackageName,String entityName,String keyType,String keyName,boolean isTree,boolean hasDelFlag){
        String basePath = System.getProperty("user.dir");
        String tempPath =basePath+"\\src\\main\\resources\\bean_template";
        String beanPath =basePath+"\\src\\main\\java\\";
        String replaceAll = servicePackageName.replaceAll("\\.", "\\\\");
        beanPath=beanPath+replaceAll;
        String implPath=beanPath+"\\impl\\";
        if(StringUtils.isBlank(keyType)) {
            keyType="Long";
        }
        if(StringUtils.isBlank(servicePackageName)||StringUtils.isBlank(repositoryPackageName)||StringUtils.isBlank(entityPackageName)||StringUtils.isBlank(entityName)||StringUtils.isBlank(keyType)) {
            return null;
        }
        //第一步:实例化Freemarker的配置类
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

        Writer out = null;
        Writer outImpl = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(tempPath));
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("service_package_name", servicePackageName);
            dataMap.put("rep_package_name", repositoryPackageName);
            dataMap.put("entity_package_name", entityPackageName);
            dataMap.put("key_type", keyType);
            dataMap.put("key_name", keyName);
            dataMap.put("isTree", isTree);
            dataMap.put("hasDelFlag", hasDelFlag);
            dataMap.put("entity_name", entityName);
            dataMap.put("model_name", StringUtils.toLowerCaseFirstOne(entityName));
            dataMap.put("c_time", DateUtils.now());
            dataMap.put("queryTimeFiled", queryTimeFiled);
            // step4 加载模版文件
            Template templateService = configuration.getTemplate("Service.ftl");
            Template templateServiceImpl = configuration.getTemplate("ServiceImpl.ftl");
            // step5 生成数据
            //判断文件夹是否存在
            File docFile = new File( beanPath+"\\" + entityName+"Service.java");
            File docFileImpl = new File( implPath+"\\" + entityName+"ServiceImpl.java");
            FileOutputStream fos =FileUtils.openOutputStream(docFile);
            FileOutputStream fosImpl =FileUtils.openOutputStream(docFileImpl);
            out = new BufferedWriter(new OutputStreamWriter(fos));
            outImpl = new BufferedWriter(new OutputStreamWriter(fosImpl));
            // step6 输出文件
            templateService.process(dataMap, out);
            templateServiceImpl.process(dataMap, outImpl);
            System.out.println(entityName+"Service"+"已经生成完成  位置 在 "+beanPath);
            return dataMap;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                    out.close();
                }
                if (null != outImpl) {
                    outImpl.flush();
                    outImpl.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    /**
     *
     * @param controllerPackageName
     * @param servicePackageName
     * @param entityPackageName
     * @param entityName
     */
    public static void generatorController(String controllerPackageName, String servicePackageName,
                                           String entityPackageName, String entityName,String keyType,boolean isTree) {

        String basePath = System.getProperty("user.dir");
        String tempPath =basePath+"\\src\\main\\resources\\bean_template";
        String beanPath =basePath+"\\src\\main\\java\\";
        String replaceAll = controllerPackageName.replaceAll("\\.", "\\\\");
        beanPath=beanPath+replaceAll;
        if(StringUtils.isBlank(controllerPackageName)
                ||StringUtils.isBlank(servicePackageName)
                ||StringUtils.isBlank(entityPackageName)
                ||StringUtils.isBlank(entityName)
        ) {
            return ;
        }
        //第一步:实例化Freemarker的配置类
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(tempPath));
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("controller_package_name", controllerPackageName);
            dataMap.put("entity_package_name", entityPackageName);
            dataMap.put("service_package_name", servicePackageName);
            dataMap.put("entity_name", entityName);
            dataMap.put("c_time", DateUtils.now());
            dataMap.put("isTree", isTree);
            dataMap.put("model_name", StringUtils.toLowerCaseFirstOne(entityName));
            dataMap.put("key_type",keyType);
            // step4 加载模版文件
            Template template = configuration.getTemplate("Controller.ftl");
            // step5 生成数据
            //判断文件夹是否存在
            File docFile = new File( beanPath+"\\" + entityName+"Controller.java");
            FileOutputStream fos =FileUtils.openOutputStream(docFile);
            out = new BufferedWriter(new OutputStreamWriter(fos));
            // step6 输出文件
            template.process(dataMap, out);
            System.out.println(entityName+"Controller"+"已经生成完成  位置 在 "+beanPath);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                    out.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return;
    }

    /**
     * 生成 entity repository service
     * @param tableName 表的名字
     * @param entityPackageName
     * @param repositoryPackageName
     * @param servicePackageName
     * @param dataSource
     * @throws Exception
     */
    public static void generatorAll(String tableName ,String queryTimeFiled,String entityPackageName,String repositoryPackageName,
                                    String servicePackageName,String controllerPackageName ,DataSource dataSource) throws Exception {
        Map<String, Object> map = generatorEntity(tableName ,entityPackageName,dataSource);
        map.put("queryTimeFiled", queryTimeFiled);
        generatorMapper(repositoryPackageName, entityPackageName, (String)map.get("bean_name"), (String)map.get("key_type"));
        generatorService(queryTimeFiled,servicePackageName, repositoryPackageName, entityPackageName, (String)map.get("bean_name"), (String)map.get("key_type"),(String)map.get("key_name"),(boolean) map.get("isTree"),(boolean) map.get("hasDelFlag"));
        generatorController(controllerPackageName, servicePackageName, entityPackageName, (String)map.get("bean_name"), (String)map.get("key_type"),(boolean) map.get("isTree"));
        //generatorHtml(controllerPackageName, servicePackageName, entityPackageName, (String)map.get("bean_name"), (String)map.get("key_type"));
        //generatorAsyncService(asyncServicePackageName, servicePackageName, entityPackageName, (String)map.get("bean_name"), (String)map.get("key_type"));
    }

}
