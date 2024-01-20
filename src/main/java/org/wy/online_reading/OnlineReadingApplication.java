package org.wy.online_reading;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 89589
 */
@SpringBootApplication
@MapperScan("org.wy.online_reading.dao.mapper")
public class OnlineReadingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineReadingApplication.class, args);
	}

}
