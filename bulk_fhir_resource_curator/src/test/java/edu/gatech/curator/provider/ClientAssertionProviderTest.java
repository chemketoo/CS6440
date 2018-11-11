package edu.gatech.curator.provider;

import edu.gatech.curator.entity.SourceSystem;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientAssertionProviderTest extends BaseProviderTest {

    @Autowired
    ClientAssertionProvider subject;

    private SourceSystem sourceSystem;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    @Ignore
    public void create_returnsSignedJwtToken() {
        subject.create(sourceSystem);
    }
}

