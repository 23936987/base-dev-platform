package com.bdp.helper;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class PropertyHelper {
	/**
	 * 读取common.properties类
	 */
	private static PropertyHelper instance = new PropertyHelper();
	/**
	 * 属性变量
	 */
	private Properties prop =  new Properties();

	/**
	 * 无参构造方法
	 */
	private PropertyHelper(){

	}

	/**
	 * 读取配置实例
	 * @return 返回系统配置实例
	 */
	public static PropertyHelper getInstance(){
		return instance;
	}

	public void load(){
		InputStream is = null;
		InputStreamReader isr = null;
		try{
			is =  PropertyHelper.class.getResourceAsStream("/conf/init.properties");
			isr = new InputStreamReader(is,"UTF-8");
			prop.load(isr);
		}catch (Exception e){
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public String get(String key) {
		load();
		String value = prop.getProperty(key);
		return value;
	}
	public Map<String,Object> getAll() {
		load();
		Map<String,Object> setting = new HashMap<String, Object>();

		Set<Object> keys= prop.keySet();
		for (Iterator<Object> it = keys.iterator();it.hasNext();) {
			String key = String.valueOf(it.next());
			setting.put(key,get(key));
		}
		return setting;
	}
	public static  String getCommonSetting(String key) {

		String value =PropertyHelper.getInstance().get(key);
		return value;
	}
	public static  Map<String,Object> getCommonSetting() {
		 return PropertyHelper.getInstance().getAll();
	}


}
