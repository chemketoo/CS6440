package edu.gatech.curator.factory;

import edu.gatech.curator.client.BulkFhirApiClient;
import edu.gatech.curator.entity.SourceSystemEntity;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.MalformedURLException;

@Component
public class RetrofitClientFactory {

    public BulkFhirApiClient getAPIClient(SourceSystemEntity sourceSystem) throws MalformedURLException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(sourceSystem.getBaseUrl())
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(NdJsonConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        BulkFhirApiClient client = retrofit.create(BulkFhirApiClient.class);
        return client;
    }
}
