package io.github.comparecurrencyrate.integration;

import io.github.comparecurrencyrate.integration.model.GifInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "gif", url = "${app.integrations.gif-provider.baseUrl}")
public interface GifClient {

    @GetMapping
    GifInfo getGif(@RequestParam String api_key,
                   @RequestParam String tag,
                   @RequestParam int limit);
}
