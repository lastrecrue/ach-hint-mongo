package ach.hin.data;

import org.junit.Assert;
import org.junit.Test;

import ach.hin.data.entity.log.converter.impl.LogConverter;

public class LogConverterTest {
	private LogConverter logConverter = new LogConverter();

	@Test
	public void testWitheIp() {
		logConverter.fromString("64.242.88.10 - - [08/Mar/2004:14:42:44 -0800] \"GET /twiki/bin/view/TWiki/WebSearch?rev=1.11 HTTP/1.1\" 200 9419");
	}

	@Test
	public void testWithDomainName() {
		Assert.assertNotNull(logConverter.fromString("lordgun.org - - [07/Mar/2004:17:01:53 -0800] \"GET /razor.html HTTP/1.1\" 200 2869"));
	}
}
