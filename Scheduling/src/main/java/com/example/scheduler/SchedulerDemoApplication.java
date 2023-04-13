package com.example.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@SpringBootApplication
@EnableScheduling // We can define the condition when can it be enabled.
@Component
public class SchedulerDemoApplication {

	Logger logger = LoggerFactory.getLogger(SchedulerDemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SchedulerDemoApplication.class, args);
	}

	@Scheduled(fixedDelayString = "PT2S", initialDelay = 2000L) // Will define millisecond as value as a fix date (2s).
	/*
	* There will be 1 single thread for the Schedulers to run. If another thread is used, the second one will be waiting
	* 'spring.task.scheduling.pool.size = 10' we can change num of pools accordingly for thread needs.
	* fixedRate = Time that will take to re-execute.
	* We can change fixedRate for fixedDelay. Time that will wait until execute again.
	* InitialDelay: Time after the application can wait context has started
	* fixedDelayString: 'P' will define duration of period, 'T' time of period & number'SMH' (sec, min, hour)
	* We can define such values in the application.properties as well.
	* For sophisticated times like, every Monday at ss:mm:hh we can use cron*/
	public void job() throws InterruptedException{
		logger.info("Job Current time : " + new Date());
		Thread.sleep(1000L); // Wait 1s.
	}

	@Scheduled(fixedRate = 1000L, initialDelay = 2000L) // Will define millisecond as value as a fix date (2s).
	/*fixedRate = Time that will take to re-execute.*/
	public void job2() throws InterruptedException {
		logger.info("Job Current time : " + new Date());
		Thread.sleep(1000L); // Wait 1s.
	}

	/*
	 * For sophisticated times like, every Monday at ss:mm:hh we can use cron.
	 * All 6 param will be differentiated with a space.
	 * More info at: crontab.guru
	 * */
	@Scheduled(cron = "*/2 * * * * *") // Will define millisecond as value as a fix date (2s).
	public void job3() throws InterruptedException{
		logger.info("Job Current time : " + new Date());
		Thread.sleep(1000L); // Wait 1s.
	}

}
