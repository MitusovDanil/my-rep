package io.github.comparecurrencyrate.domain.service;

import io.github.comparecurrencyrate.integration.model.ExchangeRateInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Map;

import static io.github.comparecurrencyrate.service.currency.CurrencyRateComparisonServiceTest.BASE_CURRENCY;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CurrencyRateMapperTest {

    @Autowired
    private CurrencyRateMapper mapper;

    @Test
    void test_map() {
        var rate = mapper.map(getData(), BASE_CURRENCY);
        assertThat(rate).isNotNull();
        assertThat(rate.rate()).isEqualTo(new BigDecimal("100"));
    }

    @Test
    void test_map_noRate() {
        var rate = mapper.map(getData(), "EUR");
        assertThat(rate).isNotNull();
        assertThat(rate.rate()).isEqualTo(new BigDecimal("0"));
    }

    private ExchangeRateInfo getData() {
        var rates = Map.of("USD", "100");
        return ExchangeRateInfo.builder()
                .rates(rates)
                .build();
    }
}