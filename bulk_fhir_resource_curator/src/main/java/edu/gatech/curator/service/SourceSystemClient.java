package edu.gatech.curator.service;

import edu.gatech.curator.entity.SourceSystem;
import edu.gatech.curator.fhir.model.ExportOutput;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class SourceSystemClient {
    public String getAccessToken(SourceSystem sourceSystem) {
        return "TEST ME";
    }

    public URL startPatientExportOperation(SourceSystem sourceSystem) throws MalformedURLException {
        return new URL("https://example.net");
    }

    public List<ExportOutput> getExportOutputs(URL url, SourceSystem sourceSystem) {
        return new ArrayList<ExportOutput>();
    }
}
