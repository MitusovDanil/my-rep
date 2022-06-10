package io.github.comparecurrencyrate.domain.service;

import io.github.comparecurrencyrate.integration.model.GifInfo;
import io.github.comparecurrencyrate.service.currency.CurrencyRateComparisonService;
import io.github.comparecurrencyrate.service.gif.GifRetrieverService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public record ChooseGifService(Map<String, GifRetrieverService> services,
                               CurrencyRateComparisonService comparisonService) {

    public GifInfo chooseGif(String currencyToCompare) {
        var comparisonResult = comparisonService.compareRates(currencyToCompare);
        return services.get(comparisonResult.mark().name())
                .retrieveGif();
    }
}
