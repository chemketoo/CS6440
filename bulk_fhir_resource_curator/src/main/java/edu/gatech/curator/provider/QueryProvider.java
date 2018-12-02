package edu.gatech.curator.provider;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

@Component
public class QueryProvider {

	@Autowired
	private ResourceLoader resourceLoader;

	public String getObesityMetricsQuery() throws IOException {
		Resource fileResource = resourceLoader.getResource("classpath:/queries/obesity_metrics.sql");
		String query = IOUtils.toString(fileResource.getInputStream(), Charset.forName("UTF-8"));
		return query;
	}
}
