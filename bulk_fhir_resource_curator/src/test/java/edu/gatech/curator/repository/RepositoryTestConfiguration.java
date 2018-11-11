package edu.gatech.curator.repository;

import edu.gatech.curator.factory.RetrofitClientFactory;
import edu.gatech.curator.provider.ClientAssertionProvider;
import edu.gatech.curator.provider.DateProvider;
import edu.gatech.curator.service.CuratorService;
import edu.gatech.curator.service.FhirResourceProcessorService;
import edu.gatech.curator.client.SmartReferenceImplApi;
import edu.gatech.curator.service.SourceSystemService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.mockito.Mockito.mock;

@TestConfiguration
@EnableTransactionManagement
@EnableJpaRepositories
@ComponentScan(basePackages = {"edu.gatech.curator.entity", "edu.gatech.curator.repository"})
public class RepositoryTestConfiguration {

    @Bean
    CuratorService curatorService() {
        return mock(CuratorService.class);
    }

    @Bean
    SourceSystemService sourceSystemService() {
        return mock(SourceSystemService.class);
    }

    @Bean
    DateProvider dateProvider() {
        return mock(DateProvider.class);
    }

    @Bean
    SmartReferenceImplApi sourceSystemClient() {
        return mock(SmartReferenceImplApi.class);
    }

    @Bean
    FhirResourceProcessorService fhirResourceProcessorService() {
        return mock(FhirResourceProcessorService.class);
    }

    @Bean
    ClientAssertionProvider clientAssertionProvider() {
        return mock(ClientAssertionProvider.class);
    }

    @Bean
    RetrofitClientFactory retrofitClientFactory() { return mock(RetrofitClientFactory.class); }
}
