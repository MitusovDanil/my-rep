package io.github.comparecurrencyrate.domain.service;

import io.github.comparecurrencyrate.domain.CurrencyRate;
import io.github.comparecurrencyrate.integration.model.ExchangeRateInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
@Service
public class CurrencyRateMapper {

    public CurrencyRate map(ExchangeRateInfo info, String currency) {
        var rates = info.getRates();
        var valueStr = rates.get(currency);
        var value = new BigDecimal(Objects.requireNonNullElse(valueStr, "0"));
        log.info("currency rate value {}", value);
        return new CurrencyRate(value);
    }
}
