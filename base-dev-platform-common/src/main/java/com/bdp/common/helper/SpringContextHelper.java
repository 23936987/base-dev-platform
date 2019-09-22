/***
 *
 * @ClassName: SpringContextHelper
 * @Description: Spring工具类
 * @Auther: yecao
 * @Date: 2019/9/21 20:16
 * @version : 1.0
 */
package com.bdp.common.helper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;

/**
 *
 * spring工具类
 * <p>完成日期：2016年6月12日</p>
 *
 * @version 1.0
 */
public class SpringContextHelper implements ApplicationContextAware {
    /**
     * spring上下文
     */
    private static ApplicationContext ctx;


    /**
     * 无参构造方法
     */
    public SpringContextHelper() {
    }

    /**
     * 注入spring上下文
     * @param applicationContext
     * @throws BeansException
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    /**
     * 按id获取对象实例
     * @param id 键
     * @return 对象实例
     */
    public static Object getBeanById(String id) {
        try {
            return ctx.getBean(id);
        } catch (BeansException e) {
            return null;
        }
    }
    public static <T> T  getBeanById(String id,Class<T> cls) {

        try {
            Object object =  ctx.getBean(id);
            return (T)object;
        } catch (BeansException e) {
            return null;
        }
    }

    /**
     * 按id获取对象实例
     * @param beanName 键
     * @return 对象实例
     * @throws BeansException
     * @throws ClassNotFoundException
     */
    public static <T> T  getBean(String beanName,Class<T> cls) throws BeansException, ClassNotFoundException {
        return (T)ctx.getBean(beanName);
    }
	public static String[]  getBeansController() throws BeansException, ClassNotFoundException {
		return ctx.getBeanNamesForAnnotation(Controller.class);
	}

    /**
     * 按类类型获取对象实例
     * @param cls 类类型
     * @return 对象实例
     * @throws BeansException
     */
    public static <T> T  getBean(Class<T> cls) throws BeansException {
        return ctx.getBean(cls);
    }

    public static ApplicationContext getContext(){
    	return ctx;
    }
}
