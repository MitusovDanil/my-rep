package io.github.comparecurrencyrate.integration.model;

import lombok.Data;

import java.util.Map;

@Data
public class ExchangeRateInfo {
    private Map<String, String> rates;
}
