package edu.gatech.curator.service;

import edu.gatech.curator.entity.SourceSystem;
import edu.gatech.curator.fhir.model.ExportOutput;
import edu.gatech.curator.repository.SourceSystemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.List;

@Service
public class CuratorService {

    @Autowired
    private SourceSystemService sourceSystemService;

    @Autowired
    private SourceSystemsRepository sourceSystemsRepository;

    @Autowired
    private FhirResourceProcessorService resourceProcessor;

    public void start() {
        List<SourceSystem> sourceSystems = sourceSystemService.retrieveSourceSystemPastDemarcationDate();

        sourceSystems.parallelStream().forEach(ss -> {
            try {
                ss.setAccessToken(sourceSystemService.getAccessToken(ss));
            } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
                return;
            }

            try {
                URL exportStatusUrl = sourceSystemService.startPatientExportOperation(ss);
                List<ExportOutput> exportOutputs = sourceSystemService.getExportOutputs(exportStatusUrl, ss);
                resourceProcessor.process(exportOutputs, ss);
                ss.setLastUpdated(new Date());
                sourceSystemsRepository.save(ss);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return;
            }
        });
    }
}
