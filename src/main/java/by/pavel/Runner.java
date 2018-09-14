package by.pavel;


import by.pavel.config.DefaultConfigLoader;
import by.pavel.service.*;
import by.pavel.view.DefaultDisplayResult;
import by.pavel.view.DisplayResult;
import by.pavel.view.StyledDisplayResult;

import java.io.IOException;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) throws IOException {

        DefaultConfigLoader loader = new DefaultConfigLoader().init();

        System.out.println("Hello, it`s simple app for searching first results in Google.");

        loader.selectViewForDisplay();

        BrowserAsk requester = new DefaultBrowserAsk(loader);

        requester.newSearch();

    }
}
