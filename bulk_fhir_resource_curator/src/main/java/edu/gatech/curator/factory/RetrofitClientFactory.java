package edu.gatech.curator.factory;

import edu.gatech.curator.client.BulkFhirApiClient;
import edu.gatech.curator.entity.SourceSystem;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class RetrofitClientFactory {
    private enum  BULK_FHIR_API_TYPE {
        SMART //HL7, ONC, CERNER <- will add when impl is done
    }

    private static HashMap<String, BULK_FHIR_API_TYPE> API_CLIENT_MAP = new HashMap<String, BULK_FHIR_API_TYPE>() {{
        put("enigmatic-waters-34317.herokuapp.com", BULK_FHIR_API_TYPE.SMART);
    }};

    public BulkFhirApiClient getAPIClient(SourceSystem sourceSystem) {
        return null;
    }
}
