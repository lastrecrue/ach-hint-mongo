package ach.hin.data.tasks;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ach.hin.data.entity.log.LogAccess;
import ach.hin.data.entity.log.converter.impl.LogConverter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduledTasks {

	@Autowired
	private LogConverter logConverter;

	@Scheduled(cron = "${cron.expression}")
	public void reportCurrentTime() {
		try {
			File theFile = new File("src/main/resources/access_log");
			LineIterator it;

			it = FileUtils.lineIterator(theFile, "UTF-8");

			while (it.hasNext()) {
				String next = it.next();
				log.debug(next);
				LogAccess logAccess = logConverter.fromString(next);
				log.debug(logAccess.getSource());
			}
		} catch (IOException e) {
			log.error("no file :^^", e);

		}

	}
}
