package io.github.comparecurrencyrate.service.currency;

import io.github.comparecurrencyrate.domain.CurrencyRate;
import io.github.comparecurrencyrate.domain.service.CurrencyRateComparisonService;
import io.github.comparecurrencyrate.service.currency.impl.CurrentCurrencyRateService;
import io.github.comparecurrencyrate.service.currency.impl.HistoricalCurrencyRateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static io.github.comparecurrencyrate.domain.ComparisonMark.LESS;
import static io.github.comparecurrencyrate.domain.ComparisonMark.MORE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CurrencyRateComparisonServiceTest {

    public final static String BASE_CURRENCY = "USD";

    @MockBean
    private CurrentCurrencyRateService current;

    @MockBean
    private HistoricalCurrencyRateService historical;

    @Autowired
    private CurrencyRateComparisonService service;


    @Test
    void test_compareRates_historicalRateLess() {
        //given
        var curr = new CurrencyRate(new BigDecimal("100"));
        var hist = new CurrencyRate(new BigDecimal("10"));
        //when
        when(this.current.retrieveRate(anyString()))
                .thenReturn(curr);
        when(historical.retrieveRate(anyString()))
                .thenReturn(hist);
        //then
        var comparisonResult = service.compareRates(BASE_CURRENCY);
        assertThat(comparisonResult).isNotNull();
        assertThat(comparisonResult.mark()).isEqualTo(LESS);
    }

    @Test
    void test_compareRates_currentRateLess() {
        //given
        var curr = new CurrencyRate(new BigDecimal("10"));
        var hist = new CurrencyRate(new BigDecimal("100"));
        //when
        when(this.current.retrieveRate(anyString()))
                .thenReturn(curr);
        when(historical.retrieveRate(anyString()))
                .thenReturn(hist);
        //then
        var comparisonResult = service.compareRates(BASE_CURRENCY);
        assertThat(comparisonResult).isNotNull();
        assertThat(comparisonResult.mark()).isEqualTo(MORE);
    }
}