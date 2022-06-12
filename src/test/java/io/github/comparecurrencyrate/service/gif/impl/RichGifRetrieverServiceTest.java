package io.github.comparecurrencyrate.service.gif.impl;

import io.github.comparecurrencyrate.integration.GifClient;
import io.github.comparecurrencyrate.integration.model.GifInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RichGifRetrieverServiceTest {


    private final static String URL = "http://localhost/gifUrl";

    @MockBean
    private GifClient client;

    @Autowired
    private RichGifRetrieverService service;

    @Test
    void test_retrieveGif_dataNotNull() {
        //when
        when(client.getGif(anyString(), anyString(), anyInt()))
                .thenReturn(getData());
        //then
        var gifInfo = service.retrieveGif();
        assertThat(gifInfo.getData()).isNotNull();
    }

    @Test
    void test_retrieveGif_assertUrl() {
        //when
        when(client.getGif(anyString(), anyString(), anyInt()))
                .thenReturn(getData());
        //then
        var gifInfo = service.retrieveGif();
        assertThat(gifInfo.getData().getUrl()).isNotNull();
        assertThat(gifInfo.getData().getUrl()).isEqualTo(URL);
    }

    private GifInfo getData() {
        var data = new GifInfo.Data();
        data.setUrl(URL);
        var info = new GifInfo();
        info.setData(data);
        return info;
    }
}