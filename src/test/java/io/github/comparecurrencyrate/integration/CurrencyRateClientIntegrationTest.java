package io.github.comparecurrencyrate.integration;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.github.comparecurrencyrate.config.CurrencyMock;
import io.github.comparecurrencyrate.config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.github.comparecurrencyrate.service.currency.CurrencyRateComparisonServiceTest.BASE_CURRENCY;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
public class CurrencyRateClientIntegrationTest {

    private final static String APP_ID = "app_id";
    private final static String EUR = "EUR";

    @Autowired
    private WireMockServer server;

    @Autowired
    private CurrencyRateClient client;

    @BeforeEach
    void setUp() {
        CurrencyMock.setupMockCurrencyResponse(server);
    }

    @Test
    void test_client_responsePresent() {
        var currencyRateInfo = client.getCurrencyRateInfo(APP_ID, BASE_CURRENCY, EUR);
        assertThat(currencyRateInfo).isNotNull();
        assertThat(currencyRateInfo.getRates()).isNotNull();
        assertThat(currencyRateInfo.getRates()).isNotEmpty();
    }

    @Test
    void test_client_assertResponse() {
        var currencyRateInfo = client.getCurrencyRateInfo(APP_ID, BASE_CURRENCY, EUR);
        assertThat(currencyRateInfo.getRates().get(EUR)).isNotNull();
        assertThat(currencyRateInfo.getRates().get(EUR)).isEqualTo("0.941659");
    }
}
