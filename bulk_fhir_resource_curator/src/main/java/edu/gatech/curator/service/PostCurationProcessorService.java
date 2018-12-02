package edu.gatech.curator.service;

import edu.gatech.curator.manager.ObesityMetricsDataManager;
import edu.gatech.curator.provider.QueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;

//import edu.gatech.curator.persistence.SqlRunner;

@Service
public class PostCurationProcessorService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

	@Autowired
	private QueryProvider queryProvider;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private ObesityMetricsDataManager obesityMetricsDataManager;


    public void start() {
        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("UpdatePatientBlockgroup");
            jdbcCall.execute();
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }

		try {
			String query = queryProvider.getObesityMetricsQuery();
			List<Object[]> results = entityManager.createNativeQuery(query).getResultList();
			results.forEach((result) -> obesityMetricsDataManager.save(result));
		} catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
		}
	}
}
