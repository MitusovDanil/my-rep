package io.github.comparecurrencyrate.service.currency;

import io.github.comparecurrencyrate.domain.CurrencyRate;

public interface CurrencyRateRetrieverService {

    CurrencyRate retrieveRate(String currencyToCompare);
}
