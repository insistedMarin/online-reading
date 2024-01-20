package org.wy.online_reading;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Map;


/**
 * @author 89589
 */
@SpringBootApplication
@MapperScan("org.wy.online_reading.dao.mapper")
@EnableCaching
@EnableScheduling
@Slf4j
public class OnlineReadingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineReadingApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext context){
		return args -> {
			Map<String, CacheManager> beans = context.getBeansOfType(CacheManager.class);
			log.info("The following cache manager is loaded：");
			beans.forEach((k,v)->{
				log.info("{}:{}",k,v.getClass().getName());
				log.info("Cache：{}",v.getCacheNames());
			});

		};
	}

}
