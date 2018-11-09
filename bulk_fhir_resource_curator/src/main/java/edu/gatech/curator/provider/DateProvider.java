package edu.gatech.curator.provider;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateProvider {
    private static long DAY_IN_MS = 1000 * 60 * 60 * 24;

    public Date oneWeekFromNow() {
        return new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
    }
}
