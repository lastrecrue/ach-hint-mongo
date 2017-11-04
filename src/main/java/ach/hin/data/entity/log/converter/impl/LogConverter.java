package ach.hin.data.entity.log.converter.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.stereotype.Service;

import ach.hin.data.entity.log.LogAccess;
import ach.hin.data.entity.log.Request;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LogConverter {

	private Matcher logPattern(String txt) {
		// String txt="64.242.88.10 - - [08/Mar/2004:13:24:49 -0800] \"GET
		// /twiki/bin/rdiff/Main/RelayGateway?rev1=1.3&rev2=1.2 HTTP/1.1\" 200 5181";

		 String re1 = "((?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))(?![\\d])"; // IPv4
//		String re1 = "[a-z0-9]+[\\.]{1}[a-z0-9]+[\\.]{1}[a-z0-9]+[\\.]{1}[a-z0-9]+"; // IP
		// String re11 = "((?:[a-z][a-z\\.\\d\\-]+)\\.(?:[a-z][a-z\\-]+))(?![\\w\\.])"; // Address 1
		String re2 = ".*?"; // Non-greedy match on filler
		String re3 = "(\\[.*?])"; // Square Braces 1
		String re4 = ".*?"; // Non-greedy match on filler
		String re5 = "(\".*?\")"; // Double Quote String 1
		String re6 = ".*?"; // Non-greedy match on filler
		String re7 = "(\\d{3})"; // Integer Number 1
		String re8 = ".*?"; // Non-greedy match on filler
		String re9 = "(\\d+)"; // Integer Number 2

		Pattern p = Pattern.compile(re1 + re2 + re3 + re4 + re5 + re6 + re7 + re8 + re9, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m = p.matcher(txt);
		return m;
	}

	public LogAccess fromString(String ligne) {
		Matcher m = logPattern(ligne);
		LogAccess logAccess = null;

		if (m.find()) {
			String source = m.group(1);
			String timeText = m.group(2).replace("]", "").replace("[", "");
			String requestText = m.group(3);
			String responseText = m.group(4);
			String FIXMEText = m.group(5);
			log.debug("source : " + source);
			log.debug("timeText : " + timeText);
			log.debug("requestText : " + requestText);
			log.debug("FIXMEText : " + FIXMEText);
			Date time = null;
			// time = ISODateTimeFormat.dateParser().parseDateTime(timeText);
			try {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss Z", Locale.US);
				time = simpleDateFormat.parse(timeText);
			} catch (ParseException e) {
				log.error("not time : ", e);
			}
			Request request = new Request(requestText);
			Integer FIXME = Integer.valueOf(FIXMEText);
			logAccess = new LogAccess(source, time, request, Integer.valueOf(responseText), FIXME);

		}

		return logAccess;
	}

}
