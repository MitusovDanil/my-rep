package io.github.comparecurrencyrate.api.validator;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Currency;

@Component
public class IsoCurrencyValidator implements ConstraintValidator<ValidCurrency, String> {

    @Override
    public void initialize(ValidCurrency contactNumber) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean containsIsoCode;
        var currencies = Currency.getAvailableCurrencies();
        try {
            containsIsoCode = currencies.contains(Currency.getInstance(value));
        } catch (IllegalArgumentException e) {
            return false;
        }
        return containsIsoCode;
    }
}
