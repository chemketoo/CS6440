package edu.gatech.curator.provider;

import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class OperationOutcomeTextUrlProvider {
    public URL parse(String divString) throws MalformedURLException {
        Pattern pattern = Pattern.compile("(?<=&quot;)(.*)(?=&quot;)");

        Matcher m = pattern.matcher(divString);

        if (m.find()) {
            String urlString = m.group(0);
            return new URL(urlString);
        }
        throw new MalformedURLException("");
    }
}
