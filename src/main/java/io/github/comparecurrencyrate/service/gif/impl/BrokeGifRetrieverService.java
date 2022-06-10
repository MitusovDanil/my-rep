package io.github.comparecurrencyrate.service.gif.impl;

import io.github.comparecurrencyrate.integration.GifClient;
import io.github.comparecurrencyrate.integration.model.GifInfo;
import io.github.comparecurrencyrate.service.gif.GifRetrieverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service("LESS")
@RequiredArgsConstructor
public class BrokeGifRetrieverService implements GifRetrieverService {

    private final GifClient client;

    @Value("${app.integrations.gif-provider.key}")
    private String appId;
    @Value("${app.integrations.gif-provider.lessTag}")
    private String tag;
    @Value("${app.integrations.gif-provider.limit}")
    private int limit;

    @Override
    public GifInfo retrieveGif() {
        log.info("started retrieving a gif with tag {}", tag);
        return client.getGif(appId, tag, limit);
    }
}
