package edu.gatech.curator;

import edu.gatech.curator.service.CuratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CuratorApplication implements CommandLineRunner {

	@Autowired
	private CuratorService curatorService;

	private static Logger LOG = LoggerFactory
			.getLogger(CuratorApplication.class);

	public static void main(String[] args) {
        LOG.info("STARTING THE CURATION PROCESS");
		SpringApplication.run(CuratorApplication.class, args);
        LOG.info("CURATION PROCESS FINISHED");
	}

	@Override
	public void run(String... args) throws Exception {
		curatorService.start();
	}
}
