package edu.gatech.curator.service;

import edu.gatech.curator.entity.SourceSystem;
import edu.gatech.curator.fhir.model.ExportOutput;
import edu.gatech.curator.repository.SourceSystemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

@Service
public class CuratorService {

    @Autowired
    private SourceSystemService sourceSystemService;

    @Autowired
    private SourceSystemClient sourceSystemClient;

    @Autowired
    private SourceSystemsRepository sourceSystemsRepository;

    @Autowired
    private FhirResourceProcessorService resourceProcessor;

    public void start() {
        Set<SourceSystem> sourceSystems = sourceSystemService.retrieveSourceSystemPastDemarcationDate();

        sourceSystems.parallelStream().forEach(ss -> {
            ss.setAccessToken(sourceSystemClient.getAccessToken(ss));
            sourceSystemsRepository.save(ss);

            try {
                URL exportStatusUrl = sourceSystemClient.startPatientExportOperation(ss);
                List<ExportOutput> exportOutputs = sourceSystemClient.getExportOutputs(exportStatusUrl, ss);
                resourceProcessor.process(exportOutputs, ss);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return;
            }
        });
    }
}
