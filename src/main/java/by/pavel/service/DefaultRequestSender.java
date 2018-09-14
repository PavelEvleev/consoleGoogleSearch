package by.pavel.service;

import by.pavel.config.DefaultConfigLoader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class DefaultRequestSender implements RequestSender {


    private DefaultConfigLoader loader;

    public DefaultRequestSender(DefaultConfigLoader loader) {
        this.loader = loader;
    }

    @Override
    public Document sendRequest(String search) {
        String searchUrl = loader.getBaseSearchUrl() + "?q=" + search;
        Document doc = null;
        try {
            doc = Jsoup.connect(searchUrl).userAgent("Mozilla/5.0")
                    .header("accept-language", loader.getAcceptLanguage())
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

}
