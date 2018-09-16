package by.pavel.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class DefaultRequestSender implements RequestSender {
    @Override
    public Document sendRequest(String search, String url, String acceptLanguage) {

        String searchUrl = url + search;
        Document doc = null;
        try {
            doc = Jsoup.connect(searchUrl).userAgent("Mozilla/5.0")
                    .header("accept-language", acceptLanguage)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
}
