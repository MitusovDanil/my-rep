package io.github.comparecurrencyrate.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.github.comparecurrencyrate.utils.CommonUtils.dayMinusOneAsString;
import static org.assertj.core.api.Assertions.assertThat;

class CommonUtilsTest {

    @Test
    void test_dayMinusOneAsString() {
        assertThat(dayMinusOneAsString())
                .isEqualTo(LocalDate.now().minusDays(1L) + ".json");
    }
}