package io.github.comparecurrencyrate.service.currency.impl;

import io.github.comparecurrencyrate.domain.CurrencyRate;
import io.github.comparecurrencyrate.domain.service.CurrencyRateMapper;
import io.github.comparecurrencyrate.integration.CurrencyRateClient;
import io.github.comparecurrencyrate.service.currency.CurrencyRateRetrieverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service("current")
@RequiredArgsConstructor
public class CurrentCurrencyRateService implements CurrencyRateRetrieverService {

    private final CurrencyRateClient rateClient;
    private final CurrencyRateMapper rateMapper;

    @Value("${app.integrations.currencies-rate.id}")
    private String appId;
    @Value("${app.integrations.currencies-rate.baseCurrency}")
    private String baseCurrency;

    @Override
    public CurrencyRate retrieveRate(String currencyToCompare) {
        var info = rateClient.getCurrencyRateInfo(appId, baseCurrency, currencyToCompare);
        log.info("currencies for today {}", info);
        return rateMapper.map(info, currencyToCompare);
    }
}
