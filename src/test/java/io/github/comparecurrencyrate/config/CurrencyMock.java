package io.github.comparecurrencyrate.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


public class CurrencyMock {

    public static void setupMockCurrencyResponse(WireMockServer mockService) {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/latest.json?app_id=app_id&base=USD&symbols=EUR"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(RESP)));
    }

    public static final String RESP = """
            {
                "disclaimer": "Usage subject to terms: https://openexchangerates.org/terms",
                "license": "https://openexchangerates.org/license",
                "timestamp": 1654819199,
                "base": "USD",
                "rates": {
                    "EUR": 0.941659
                }
            }""";
}
