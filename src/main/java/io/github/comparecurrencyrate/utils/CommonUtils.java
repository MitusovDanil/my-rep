package io.github.comparecurrencyrate.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class CommonUtils {

    private static final String JSON = ".json";
    private static final long DAYS_TO_SUBTRACT = 1L;

    public static String dayMinusOneAsString() {
        var dayBeforeToday = LocalDate.now().minusDays(DAYS_TO_SUBTRACT);
        return dayBeforeToday + JSON;
    }
}
