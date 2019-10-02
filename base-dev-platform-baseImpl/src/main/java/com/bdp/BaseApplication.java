package com.bdp;

import com.bdp.common.helper.SpringContextHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @Description: 规则服务
 * @Auther: adtech
 * @Date: 2019-05-09
 */
@SpringBootApplication
@EnableSwagger2
@EnableAsync
@EnableScheduling
@EnableTransactionManagement
public class BaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class, args);
	}

	@Bean
	public SpringContextHelper SpringContextHelper(){
		return new SpringContextHelper();
	}

}
