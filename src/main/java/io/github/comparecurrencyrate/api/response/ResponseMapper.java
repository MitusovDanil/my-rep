package io.github.comparecurrencyrate.api.response;

import io.github.comparecurrencyrate.integration.model.GifInfo;
import org.springframework.stereotype.Service;

@Service
public class ResponseMapper {

    public GifResponse map(GifInfo info) {
        return new GifResponse(info.getData().getUrl());
    }
}
