package service;


import by.pavel.config.DefaultConfigLoader;
import by.pavel.service.DefaultRequestSender;
import by.pavel.service.RequestSender;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DefaultRequestSenderTest {

    private DefaultConfigLoader loader = new DefaultConfigLoader();
    private RequestSender sender = new DefaultRequestSender();


    @Before
    public void initLoader() {
        loader.init();
    }

    @Test
    public void shouldReturnDocument() {
        Document document = sender.sendRequest("Человек+паук", loader.getBaseSearchUrl(), loader.getAcceptLanguage());
        assertNotNull(document);
    }

}
