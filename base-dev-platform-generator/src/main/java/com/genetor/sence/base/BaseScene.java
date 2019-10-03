package com.genetor.sence.base;


import com.bdp.exception.Assert;
import com.bdp.helper.BaseHelper;
import com.bdp.helper.DateHelper;
import com.bdp.helper.StringHelper;
import com.genetor.model.Field;
import com.genetor.model.Table;
import com.genetor.sence.IScene;
import com.genetor.util.DBTool;
import com.genetor.util.FileHeper;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class BaseScene implements IScene {
    private static Logger logger = LoggerFactory.getLogger(BaseScene.class);
    protected Map<String,Object> data = new HashMap<String, Object>();
    private BaseConfig config;

    public BaseScene(BaseConfig config){
        try {
        	this.config = config;
            _init(config);
        } catch (Exception e) {
            logger.error("init error",e);
        }
    }

    private void _init(BaseConfig config) throws Exception {
        //读取配置文件
        FileHeper.dealPath(config.getOutpath());

       // data.putAll(JSON.parseObject(JsonHelper.toJSonString(config)));
		data.put("scene",config.getScene());
		data.put("schema",config.getSchema());
		data.put("tableName",config.getTableName());
		data.put("parent",config.getParent());
		data.put("outpath",config.getOutpath());
		data.put("author",config.getAuthor());
		data.put("version",config.getVersion());
		data.put("exclude",config.getExclude());
		data.put("scenceName",config.getScenceName());


		data.put("today", DateHelper.dateToStr(new Date(), "yyyy-MM-dd HH:mi:ss"));
        data.put("scene", config.getScene());

        //加载源表meta
        Table table = DBTool.table(config.getConn(),config.getSchema(),config.getTableName());

        Assert.isTrue(BaseHelper.isNotEmpty(table), "table not exists");
        data.put("clazz", table);


        //初始化base
		String base="";
		if(BaseHelper.isNotEmpty(config.getPrefix())){
			base = table.getTable().replaceFirst(config.getPrefix(),"");
		}else{
			base = table.getTable();
		}

		base = StringHelper.parseTuo(base);


	    data.put("base",base);
	    String baseCapture = StringHelper.captureName(base);
	    data.put("baseCapture",baseCapture);

        String exclude = (String)config.getExclude();
        exclude= exclude.substring(1,exclude.length()-1);
        String[] excludeArr = exclude.split(",");
        List<String> excludeList = Arrays.asList(excludeArr);


        List<Field> fields = table.getFields();
        List<Field> model_fields = table.getModel_fields();


        for (Field field : fields){
            if(!excludeList.contains(field.getName())){
                model_fields.add(field);
            }
        }
    }

    public void generator() throws Exception {


	    //logger.info(JSON.toJSONString(data, SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect));

        String scene = config.getScene();
        String base =String.valueOf(data.get("base"));
        String baseCapture =String.valueOf( data.get("baseCapture"));
        String outpath =String.valueOf( data.get("outpath"));

	    String basePath = getClass().getResource("/template/"+scene+"/ftl/").getPath();
	    List<String> fileNames = FileHeper.getAllFile(basePath,false);
	    if(fileNames != null && fileNames.size()>0){
		    for (int i = 0; i < fileNames.size(); i++) {
			    String fileName = fileNames.get(i);
			    if(fileName.indexOf("comment.ftl") == -1) {
				    String ftlName = fileName.substring(fileName.lastIndexOf("ftl") + 4);
				    String distName = fileName.substring(fileName.lastIndexOf("ftl") + 4);
				    distName = distName.replace("[BASE]",base);
				    distName = distName.replace("[PROJECT]",config.getProject());
				    distName = distName.replace("[project]",config.getProject());
				    distName = distName.replace("[PARENT]",config.getParent());
				    distName = distName.replace("[parent]",config.getParent());
				    distName = distName.replace("[BASE_CAPTURE]",baseCapture);
				    distName = distName.replace("[baseCapture]",baseCapture);
				    distName = outpath + distName;
				    makeFile(ftlName,distName);
			    }
		    }
	    }
    }
	public void makeFile(String ftlPath,String filePath) throws Exception {

        String scene =String.valueOf( data.get("scene"));

		logger.info("模板文件文件:"+ftlPath);
		logger.info("创建文件:"+filePath);
		FileHeper.mkParentdirs(filePath);
		FileOutputStream outputStream = null;
		PrintWriter writer = null;
		OutputStreamWriter osw = null;
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(BaseScene.class, "/template/"+scene+"/ftl/");
		/*TemplateLoader ldr = new ClassTemplateLoader(BaseScene.class.getClassLoader(), "/template/"+scene+"/ftl/");
		cfg.setTemplateLoader(ldr);*/
		cfg.setDefaultEncoding("utf-8");
		cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setClassicCompatible(true);
		Template temp = cfg.getTemplate(ftlPath);
		temp.setLocale(Locale.US);
		temp.setEncoding("utf-8");

		File file = new File(filePath);
		outputStream = new FileOutputStream(file);
		osw = new OutputStreamWriter(outputStream, "utf-8");
		writer = new PrintWriter(osw);

		temp.process(data, writer);
		writer.flush();
		outputStream.close();
		osw.close();
		writer.close();
	}
}
