package ach.hin.data.entity.log.converter.impl;

import org.apache.commons.io.input.TailerListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import ach.hin.data.entity.log.LogAccess;
import ach.hin.data.tasks.ScheduledTasks;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class AccesLLogTailerListenerAdapter extends TailerListenerAdapter {

	@Autowired
	private LogConverter logConverter;

	public AccesLLogTailerListenerAdapter(LogConverter logConverter) {
		this.logConverter = logConverter;
	}

	public void handle(String ligne) {
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

	private void archiveIgnore(String ligne) {
		// TODO Auto-generated method stub
		log.error("last ignored ligne : " + ligne);
		ScheduledTasks.DB_IGNORED_LIGNE.add(ligne);
		log.error("IGNORE_LIGNE : " + ScheduledTasks.DB_IGNORED_LIGNE.size());
	}
}
