package ach.hin.data;

import java.io.File;

import org.apache.commons.io.input.Tailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import ach.hin.data.entity.log.adapter.AccesLLogTailerListenerAdapter;

@SpringBootApplication
@EnableMongoAuditing
@EnableScheduling
public class MongoApplication {

	@Autowired
	private AccesLLogTailerListenerAdapter listener;

	@Value("${access_log.path}")
	private String accessLogPath;

	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);
	}

	@Bean
	public TaskExecutor taskExecutor() {
		return new SimpleAsyncTaskExecutor(); // Or use another one of your liking
	}

	@Bean
	public CommandLineRunner schedulingRunner(TaskExecutor executor) {
		File file = new File(accessLogPath);
		Tailer tailer = new Tailer(file, listener, 1000);
		return new CommandLineRunner() {
			public void run(String... args) throws Exception {
				executor.execute(tailer);
			}
		};
	}

}
