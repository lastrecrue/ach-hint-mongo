package ach.hin.data.tasks;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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

	private static Set<String> DB_IGNORED_LIGNE = new HashSet<>();

	@Scheduled(cron = "${cron.expression}")
	public void reportCurrentTime() {
		try {
			File theFile = new File("src/main/resources/access_log");
			LineIterator it;

			it = FileUtils.lineIterator(theFile, "UTF-8");

			while (it.hasNext()) {
				String ligne = it.next();
				log.debug(ligne);

				LogAccess logAccess = logConverter.fromString(ligne);
				if (logAccess != null) {
					log.debug("from doamin name parsser");
					log.debug(logAccess.getSource());
				} else {
					log.error("logAccess is null");
					archiveIgnore(ligne);
				}
			}
		} catch (IOException e) {
			log.error("no file :^^", e);

		}
		archiveIgnore("end traitement");

		for (String string : DB_IGNORED_LIGNE) {
			log.info("ignored ligne : " + string);
		}
	}

	private void archiveIgnore(String ligne) {
		// TODO Auto-generated method stub
		log.error("last ignored ligne : " + ligne);
		DB_IGNORED_LIGNE.add(ligne);
		log.error("IGNORE_LIGNE : " + DB_IGNORED_LIGNE.size());
	}
}
