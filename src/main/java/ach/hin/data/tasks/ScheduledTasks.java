package ach.hin.data.tasks;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ach.hin.data.entity.log.adapter.AccesLLogTailerListenerAdapter;
import ach.hin.data.entity.log.converter.impl.LogConverter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduledTasks {

	@Autowired
	private LogConverter logConverter;

	public static Set<String> DB_IGNORED_LIGNE = new HashSet<>();

	@Value("${access_log.path}")
	private String accessLogPath;

	@Scheduled(cron = "${cron.expression}")
	public void tailAccessLog() {

		File file = new File(accessLogPath);
		TailerListener listener = new AccesLLogTailerListenerAdapter(logConverter);
		Tailer tailer = new Tailer(file, listener, 1000);

		// stupid executor impl. for demo purposes
		Executor executor = new Executor() {
			public void execute(Runnable command) {
				command.run();
			}
		};

		executor.execute(tailer);

	}

}
