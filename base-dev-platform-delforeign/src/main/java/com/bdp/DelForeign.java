package com.bdp;


import com.bdp.ex.Assert;
import com.bdp.helper.FileHelper;
import com.bdp.helper.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.Map;

@SpringBootApplication
public class DelForeign {

	private static Logger log = LoggerFactory.getLogger(DelForeign.class);

    private static String SEP = "/";
    private static int count=0;
    private static String _SOURCE_PATH_ = "";
    private static Map<String,Map<String,String>> data=null;
    private static String _ACTION_ = "删除外键:";
    private static String _DEST_PATH_ = "";
    public static void main(String[] args) {

        SpringApplication.run(DelForeign.class, args);
        long start = System.currentTimeMillis();
        try {
            log.info(_ACTION_+"开始");
            String sourcePath = null;
            String destPath = null;

            //模板路径
            sourcePath = args[0];
            //目标路径
            destPath = args[1];

	       // sourcePath = "d:\\test\\db_physics\\";
	        //destPath = "d:\\test\\sql\\";

            Assert.isTrue(StringHelper.isNotEmpty(sourcePath), "参数错误:源文件夹为空");
            Assert.isTrue(StringHelper.isNotEmpty(destPath), "参数错误:目标文件夹为空");

            Assert.isTrue(FileHelper.dirExists(sourcePath), "参数错误:源文件夹不存在");
            FileHelper.deleteDir(destPath);
            FileHelper.mkdirsNotExists(destPath);
            Assert.isTrue(FileHelper.dirExists(destPath), "参数错误:目标文件夹不存在");

            sourcePath= FileHelper.dealPath(sourcePath);
            sourcePath = sourcePath.endsWith(SEP)?sourcePath:sourcePath + SEP;

            destPath= FileHelper.dealPath(destPath);
            destPath = destPath.endsWith(SEP)?destPath:destPath + SEP;

            _SOURCE_PATH_ = sourcePath;
            _DEST_PATH_ = destPath;
            //生成SQL文件
            delForeign(sourcePath, "");

        } catch (Exception e) {
            log.error(_ACTION_+"异常:" + e, e);
        } finally {
            long end = System.currentTimeMillis();
            log.info(_ACTION_+"结束:" + "共["+count+"]个文件,耗时[" + (end - start)+"毫秒]");
        }
    }

    private static void delForeign(String sourcePath,  String directory) {
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

                    String filePath = getDestFilePath(relaPath);
                    delForeignFile(relaPath,filePath);
                    log.info(_ACTION_ + "成功:" + file.getAbsolutePath());
                } catch (Exception e) {
                    log.error(_ACTION_+"失败:"+relaPath,e);
                }
            } else {
                delForeign(sourcePath, fileName);
            }

        }
    }

    private static String getDestFilePath(String url) {

        url = FileHelper.dealPath(url);
        url = _DEST_PATH_ +  url.substring(_SOURCE_PATH_.length());

        return url;
    }
    private static void delForeignFile(String url,String url2) {

        count++;
        File file=new File(url);
        File file2 = new File(url2);
        try {
            FileHelper.mkParentdirs(url2);
            file2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream is=null;
        BufferedReader br = null;
        String tmp;
        PrintWriter writer=null;
        int i=0;
        try {
            is=new BufferedInputStream(new FileInputStream(file));
            br = new BufferedReader(new InputStreamReader(is, "utf-8"));


            OutputStream outputStream = new FileOutputStream(file2);
            OutputStreamWriter osw = new OutputStreamWriter(outputStream,"utf-8");

            //writer = new FileWriter(url2, true);
            writer = new PrintWriter(osw);
            while((tmp=br.readLine())!=null){
                if(tmp.trim().length() > 0 && !tmp.trim().toLowerCase().startsWith("foreign")){
                   // System.out.println(tmp);
                    writer.println(tmp);
                    i++;
                }
            }
            writer.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
