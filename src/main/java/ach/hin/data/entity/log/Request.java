package ach.hin.data.entity.log;

import java.util.StringTokenizer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author GVLJ3568
 *
 */
@Data
@NoArgsConstructor
@Slf4j
public class Request {
	public Request(String nextToken) {
		nextToken = nextToken.replaceAll("\"", "");
		if (!"-".equals(nextToken)) {
			StringTokenizer stringTokenizer = new StringTokenizer(nextToken);
			methode = Methode.byName(stringTokenizer.nextToken());
			log.debug("moethode : " + methode);
			url = stringTokenizer.nextToken();
			log.debug("url : " + url);
			protocol = stringTokenizer.nextToken();
			log.debug("protocol : " + protocol);
		}
	}

	private Methode methode;// GET
	private String url;// /twiki/bin/edit/Main/Double_bounce_sender?topicparent=Main.ConfigurationVariables
	private String protocol;// HTTP/1.1}
}
