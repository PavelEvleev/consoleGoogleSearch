package by.pavel;


import by.pavel.config.DefaultConfigLoader;
import by.pavel.service.*;

import java.io.IOException;

public class Runner {

    public static void main(String[] args) throws IOException {

        DefaultConfigLoader loader = new DefaultConfigLoader().init();

        System.out.println("Hello, it`s simple app for searching first results in Google.");

        loader.selectViewForDisplay();

        RequestSender requestSender = new DefaultRequestSender();

        DefaultElementExtractor extractor = new DefaultElementExtractor(
                loader.getFirstResults(),
                loader.getSearchedElement());

        BrowserAsk requester = new DefaultBrowserAsk(loader.getScanner(), requestSender, extractor, loader.getDisplayResult());

        requester.newSearch(loader.getBaseSearchUrl(), loader.getAcceptLanguage());

    }
}
