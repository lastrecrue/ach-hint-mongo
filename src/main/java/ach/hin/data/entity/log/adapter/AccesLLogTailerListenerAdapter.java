package ach.hin.data.entity.log.adapter;

import org.apache.commons.io.input.TailerListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ach.hin.data.entity.log.AccessLog;
import ach.hin.data.entity.log.ErrorAccessLog;
import ach.hin.data.entity.log.converter.LogConverter;
import ach.hin.data.repository.log.AccessLogRepository;
import ach.hin.data.repository.log.ErrorAccessLogRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccesLLogTailerListenerAdapter extends TailerListenerAdapter {

	@Autowired
	private LogConverter logConverter;

	@Autowired
	private ErrorAccessLogRepository errorAccessLogRepository;

	@Autowired
	private AccessLogRepository accessLogRepository;

	public void handle(String ligne) {
		log.debug(ligne);

		AccessLog logAccess = logConverter.fromString(ligne);
		if (logAccess != null) {
			log.debug("from doamin name parsser" + ligne);
			accessLogRepository.save(logAccess);
		} else {
			log.error("logAccess is null : " + ligne);
			archiveIgnore(ligne);
		}
	}

	private void archiveIgnore(String ligne) {
		errorAccessLogRepository.save(new ErrorAccessLog(ligne));
	}
}
