package io.github.comparecurrencyrate.domain.service;

import io.github.comparecurrencyrate.domain.ComparisonResult;
import io.github.comparecurrencyrate.domain.CurrencyRate;
import io.github.comparecurrencyrate.service.currency.CurrencyRateRetrieverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import static io.github.comparecurrencyrate.domain.ComparisonMark.LESS;
import static io.github.comparecurrencyrate.domain.ComparisonMark.MORE;

@Slf4j
@Service
public record CurrencyRateComparisonService(CurrencyRateRetrieverService current,
                                            CurrencyRateRetrieverService historical) {

    public ComparisonResult compareRates(String currencyToCompare) {
        var historyRate = historical.retrieveRate(currencyToCompare);
        var currentRate = current.retrieveRate(currencyToCompare);
        return compare(historyRate, currentRate);
    }

    private ComparisonResult compare(CurrencyRate historyRate, CurrencyRate currentRate) {
        boolean more = historyRate.rate().compareTo(currentRate.rate()) > 0;
        log.info("historical currency rate more than current rate is {}", more);
        return more ? new ComparisonResult(MORE) : new ComparisonResult(LESS);
    }
}
