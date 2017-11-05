package ach.hin.data.entity.log.converter.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import ach.hin.data.entity.log.AccessLog;
import ach.hin.data.entity.log.Request;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LogConverter {

	private final static Pattern TLC_REG_EX = getTlcRegex();

	private static Pattern getTlcRegex() {
		// "lordgun.org - - [07/Mar/2004:17:01:53 -0800] \"GET /razor.html HTTP/1.1\" 200 2869";

		final String re1 = "(^[a-zA-Z0-9][a-zA-Z0-9\\._\\-]*[a-zA-Z0-9])";// tlc
		final String re2 = ".*?"; // Non-greedy match on filler
		final String re3 = "(\\[.*?])"; // Square Braces 1
		final String re4 = ".*?"; // Non-greedy match on filler
		final String re5 = "(\".*?\")"; // Double Quote String 1
		final String re6 = ".*?"; // Non-greedy match on filler
		final String re7 = "(\\d{3})"; // Integer Number 1
		final String re8 = ".*?"; // Non-greedy match on filler
		final String re9 = "(\\d+|\\-)"; // Integer Number 2

		return Pattern.compile(re1 + re2 + re3 + re4 + re5 + re6 + re7 + re8 + re9, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	}

	public AccessLog fromString(String ligne) {
		Matcher m = TLC_REG_EX.matcher(ligne);
		AccessLog logAccess = null;

		if (m.find()) {
			String source = m.group(1);
			log.debug("source : " + source);
			String timeText = m.group(2).replace("]", "").replace("[", "");
			log.debug("timeText : " + timeText);
			String requestText = m.group(3);
			log.debug("requestText : " + requestText);
			String responseText = m.group(4);
			log.debug("responseText : " + responseText);
			String FIXMEText = m.group(5);
			log.debug("FIXMEText : " + FIXMEText);
			Date time = null;
			try {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss Z", Locale.US);
				time = simpleDateFormat.parse(timeText);
			} catch (ParseException e) {
				log.error("not time : ", e);
			}
			Request request = new Request(requestText);
			String FIXME = FIXMEText;
			logAccess = new AccessLog(source, time, request, Integer.valueOf(responseText), FIXME);

		}

		return logAccess;
	}

}
