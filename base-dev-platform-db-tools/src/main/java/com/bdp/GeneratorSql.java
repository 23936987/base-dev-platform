package com.bdp;

import com.bdp.ex.Assert;
import com.bdp.helper.FileHelper;
import com.bdp.helper.PropertyHelper;
import com.bdp.helper.SpringContextHelper;
import com.bdp.helper.StringHelper;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class GeneratorSql {

    private static Logger log = LoggerFactory.getLogger(GeneratorSql.class);

    private static String SEP = "/";
    private static int count=0;
    private static String _SOURCE_PATH_ = "";
    private static Map<String,Map<String,String>> data=null;
    private static String _ACTION_ = "生成SQL文件";
    private static String _DEST_PATH_ = "";
    private static JdbcTemplate jdbcTemplate= null;


    @Bean
    public SpringContextHelper SpringContextHelper(){
        return new SpringContextHelper();
    }

    public static void main(String[] args) {
        SpringApplication.run(GeneratorSql.class, args);

	    jdbcTemplate = SpringContextHelper.getBeanById("primaryJdbcTemplate", JdbcTemplate.class);
        long start = System.currentTimeMillis();
        try {
            log.info(_ACTION_+"开始");
            String sourcePath = null;
            String destPath = null;

             //模板路径
             sourcePath = args[0];
             //目标路径
            destPath = args[1];

       //      sourcePath = "d:\\test\\db_logic\\";
           //  destPath = "d:\\test\\db_physics\\";

            Assert.isTrue(StringHelper.isNotEmpty(sourcePath), "参数错误:源文件夹为空");
            Assert.isTrue(StringHelper.isNotEmpty(destPath), "参数错误:目标文件夹为空");

            FileHelper.deleteDir(destPath);
            FileHelper.mkdirsNotExists(destPath);

            sourcePath= FileHelper.dealPath(sourcePath);
            sourcePath = sourcePath.endsWith(SEP)?sourcePath:sourcePath + SEP;

            destPath= FileHelper.dealPath(destPath);
            destPath = destPath.endsWith(SEP)?destPath:destPath + SEP;

            _SOURCE_PATH_ = sourcePath;
            _DEST_PATH_ = destPath;
             //加载数据对象
            loadData();
            //生成SQL文件
            generatorSql(sourcePath,"");

        } catch (Exception e) {
            log.error(_ACTION_+"异常:" + e, e);
        } finally {
            long end = System.currentTimeMillis();
            log.info(_ACTION_+"结束:" + "共["+count+"]个文件,耗时[" + (end - start)+"毫秒]");
        }
    }



    private static void generatorSql(String sourcePath, String directory) {
        sourcePath= FileHelper.dealPath(sourcePath);
        sourcePath = sourcePath.endsWith(SEP)?sourcePath:sourcePath + SEP;
        String sourcePathNew = StringHelper.isEmpty(directory) ? sourcePath : sourcePath + directory + SEP;

        File source = new File(sourcePathNew);
        File[] files = source.listFiles();
        if (files == null || files.length == 0) return;

        for (int i = 0; i < files.length; i++) {
            File file = files[i];

            String fileName = file.getName();

            if (!file.isDirectory()) {
                String relaPath = file.getAbsolutePath();
                try{

                    String ftlName = relaPath.substring(_SOURCE_PATH_.length());
                    String filePath = getDestFilePath(_DEST_PATH_ + ftlName);

                    generatorSqlFile(ftlName, filePath);
                    log.info(_ACTION_ + "成功:" + file.getAbsolutePath());
                } catch (Exception e) {
                    log.error(_ACTION_+"失败:"+relaPath,e);
                }
            } else {
                generatorSql(sourcePath, fileName);
            }

        }
    }

    private static String getDestFilePath(String url) {

         url = FileHelper.dealPath(url);
        url = url.substring(0,url.lastIndexOf(".ftl")) + ".sql";
        return url;
    }

    public static void generatorSqlFile(String ftlName,String filePath) throws Exception {

        FileHelper.mkParentdirs(filePath);
        FileOutputStream outputStream = null;
        PrintWriter writer = null;
        OutputStreamWriter osw = null;
        Configuration cfg = new Configuration();
        //cfg.setClassForTemplateLoading(Test.class, "/template/");
        System.out.println(filePath);
        cfg.setDirectoryForTemplateLoading(new File(_SOURCE_PATH_));
        cfg.setDefaultEncoding("utf-8");
        cfg.setObjectWrapper(new DefaultObjectWrapper());

        Template temp = cfg.getTemplate(ftlName);
        temp.setEncoding("utf-8");

        File file = new File(filePath);
        outputStream = new FileOutputStream(file);
        osw = new OutputStreamWriter(outputStream,"utf-8");
        writer = new PrintWriter(osw);

        count++;
        temp.process(data, writer);
        writer.flush();
        outputStream.close();
        osw.close();
        writer.close();
    }

    private static void loadData() {
        data = new HashMap<>();
        String tableName = PropertyHelper.getCommonSetting("tableName");
        loadTable(tableName);

       // System.out.println(JSON.toJSONString(data));
    }
    private static void loadTable(String table) {


        String sql="select name_cn nameCn,name_en nameEn from " + table;

        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
        if(list != null && list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                Map<String,Object> map = list.get(i);

                String nameCn =(String) map.get("nameCn");
                final String nameEn =(String) map.get("nameEn");
                final String memo =(String) map.get("memo");


                data.put(nameCn ,new HashMap<String,String>(){{
                    put("en",nameEn);
                    put("comment",memo);
                }});

            }
        }
    }
}
