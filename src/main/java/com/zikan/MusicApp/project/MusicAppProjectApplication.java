package com.zikan.MusicApp.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MusicAppProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicAppProjectApplication.class, args);
	}

}
