package edu.gatech.curator.provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OperationOutcomeTextUrlProviderTest {

    @Autowired
    OperationOutcomeTextUrlProvider subject;

    @Test
    public void parse() throws MalformedURLException {
        String div = "<div xmlns=\"http://www.w3.org/1999/xhtml\"><h1>Operation Outcome</h1><table border=\"0\"><tr><td style=\"font-weight:bold;\">information</td><td>[]</td><td><pre>Your request have been accepted. You can check it's status at &quot;https://fhir.com/bulkstatus&quot;</pre></td></tr></table></div>";
        URL expected = new URL("https://fhir.com/bulkstatus");

        assertThat(subject.parse(div)).isEqualTo(expected);
    }
}