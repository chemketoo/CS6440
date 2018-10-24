package edu.gatech.curator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CuratorApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory
			.getLogger(CuratorApplication.class);

	public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
		SpringApplication.run(CuratorApplication.class, args);
        LOG.info("APPLICATION FINISHED");
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("EXECUTING : command line runner");

		for (int i = 0; i < 10; ++i) {
			LOG.info("times: {}", i);
		}
	}
}
