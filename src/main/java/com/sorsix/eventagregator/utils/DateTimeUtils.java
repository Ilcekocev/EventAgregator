package com.sorsix.eventagregator.utils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class DateTimeUtils {

    private static final ZoneId ZONE_ID = ZoneId.systemDefault();
    private static DayOfWeek FIRST_DAY_OF_WEEK = WeekFields.of(Locale.ROOT).getFirstDayOfWeek();
    private static DayOfWeek LAST_DAY_OF_WEEK = DayOfWeek.of(((FIRST_DAY_OF_WEEK.getValue() + 5) % DayOfWeek.values().length) + 1);

    public static LocalDateTime getFirstDayOfWeek() {
        return LocalDateTime.now(ZONE_ID).with(TemporalAdjusters.previousOrSame(FIRST_DAY_OF_WEEK));
    }

    public static LocalDateTime getLastDayOfWeek() {
        return LocalDateTime.now(ZONE_ID).with(TemporalAdjusters.nextOrSame(LAST_DAY_OF_WEEK));
    }

    public static Long calculateDifference(LocalDateTime then) {
        return ChronoUnit.MINUTES.between(LocalDateTime.now(), then);
    }
}
