package edu.gatech.curator.service;

import edu.gatech.curator.entity.SourceSystem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class SourceSystemService {
    public Set<SourceSystem> retrieveSourceSystemPastDemarcationDate() {
        return new HashSet<>();
    }
}
