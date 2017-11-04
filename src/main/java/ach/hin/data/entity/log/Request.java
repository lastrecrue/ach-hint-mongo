package ach.hin.data.entity.log;

import java.util.StringTokenizer;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author GVLJ3568
 *
 */
@Data
@NoArgsConstructor
public class Request {
	public Request(String nextToken) {
		StringTokenizer stringTokenizer = new StringTokenizer(nextToken);
		methode =Methode.byName( stringTokenizer.nextToken());
		url=stringTokenizer.nextToken();
		protocol=stringTokenizer.nextToken();
	}

	private Methode methode;// GET
	private String url;// /twiki/bin/edit/Main/Double_bounce_sender?topicparent=Main.ConfigurationVariables
	private String protocol;// HTTP/1.1}
}
