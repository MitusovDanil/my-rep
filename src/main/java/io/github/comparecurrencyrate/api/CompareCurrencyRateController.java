package io.github.comparecurrencyrate.api;

import io.github.comparecurrencyrate.api.response.GifResponse;
import io.github.comparecurrencyrate.api.response.ResponseMapper;
import io.github.comparecurrencyrate.api.validator.ValidCurrency;
import io.github.comparecurrencyrate.domain.service.ChooseGifService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;


@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comparison/rate")
public class CompareCurrencyRateController {

    private final ChooseGifService service;
    private final ResponseMapper mapper;

    @Operation(summary = "Возвращает рандомную гифку в  зависимомти от курса валют", tags = {"currency-rate-comparison"})
    @ApiResponse(responseCode = "200", description = "Если отношение курсов валют меньше чем вчера " +
            "использовать один ресурс иначе другой", content = @Content(schema = @Schema(implementation = String.class)))
    @GetMapping(value = "/v1/currencies/{currency}")
    public ResponseEntity<GifResponse> getCurrenciesRateComparison(@PathVariable @ValidCurrency String currency) {
        log.info("started processing a request for currency {}", currency);
        var gifInfo = service.chooseGif(currency);
        return ResponseEntity.ok(mapper.map(gifInfo));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException() {
        log.info("not ISO currency code was provided");
        return "not IOS currency code";
    }
}
