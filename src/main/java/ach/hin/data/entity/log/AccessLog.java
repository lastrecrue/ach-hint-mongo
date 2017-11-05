package ach.hin.data.entity.log;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * log= { source : 64.242.88.10 - - time : [07/Mar/2004:16:05:49 -0800] request : { methode : GET url :
 * /twiki/bin/edit/Main/Double_bounce_sender?topicparent=Main.ConfigurationVariables protocol : HTTP/1.1 } response : 401 FIXME : 12846 }
 * 
 * @author GVLJ3568
 *
 */
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessLog {

	@Id
	private String id;

	private String source;// : 64.242.88.10 - -
	private Date time;// : [07/Mar/2004:16:05:49 -0800]

	private Request request;

	private Integer response;// : 401
	private String FIXME;// : 12846

	public AccessLog(String source, Date time, Request request, Integer response, String fIXME) {
		super();
		this.source = source;
		this.time = time;
		this.request = request;
		this.response = response;
		FIXME = fIXME;
	}

}
