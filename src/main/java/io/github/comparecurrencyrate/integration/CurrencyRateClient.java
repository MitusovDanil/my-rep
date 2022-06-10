package io.github.comparecurrencyrate.integration;

import io.github.comparecurrencyrate.integration.model.ExchangeRateInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "currency", url = "${app.integrations.currencies-rate.baseUrl}")
public interface CurrencyRateClient {

    @GetMapping("/latest.json")
    ExchangeRateInfo getCurrencyRateInfo(@RequestParam String app_id,
                                         @RequestParam String base,
                                         @RequestParam String symbols);

    @GetMapping("/historical/{date}")
    ExchangeRateInfo getHistoricalCurrencyRateInfo(@PathVariable String date,
                                                   @RequestParam String app_id,
                                                   @RequestParam String base,
                                                   @RequestParam String symbols);
}
