package edu.gatech.curator.service;

import edu.gatech.curator.entity.SourceSystem;
import edu.gatech.curator.provider.DateProvider;
import edu.gatech.curator.repository.SourceSystemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SourceSystemService {

    @Autowired
    private SourceSystemsRepository sourceSystemsRepository;

    @Autowired
    private DateProvider dateProvider;

    public List<SourceSystem> retrieveSourceSystemPastDemarcationDate() {
        return sourceSystemsRepository.findAllByLastUpdatedBefore(dateProvider.oneWeekAgo());
    }
}
