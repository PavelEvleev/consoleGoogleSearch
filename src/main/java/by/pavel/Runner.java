package by.pavel;


import by.pavel.config.DefaultConfigLoader;
import by.pavel.service.BrowserAsk;
import by.pavel.service.DefaultBrowserAsk;

import java.io.IOException;

public class Runner {

    public static void main(String[] args) throws IOException {

        DefaultConfigLoader loader = new DefaultConfigLoader().init();

        System.out.println("Hello, it`s simple app for searching first results in Google.");

        loader.selectViewForDisplay();

        BrowserAsk requester = new DefaultBrowserAsk(loader);

        requester.newSearch();

    }
}
