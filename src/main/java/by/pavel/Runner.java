package by.pavel;


import by.pavel.config.DefaultConfigLoader;
import by.pavel.service.DefaultElementExtractor;
import by.pavel.service.DefaultRequestSender;
import by.pavel.service.RequestSender;
import by.pavel.view.DefaultDisplayResult;
import by.pavel.view.DisplayResult;

import java.io.IOException;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) throws IOException {

//        String search = String.join(" ", args);

        Scanner in = new Scanner(System.in);
        System.out.println("Введите поисквой запрос");

        String search = in.nextLine();

        DefaultConfigLoader loader = new DefaultConfigLoader().init();

        RequestSender requestSender = new DefaultRequestSender(loader);

        DefaultElementExtractor extractor = new DefaultElementExtractor(loader, requestSender.sendRequest(search));

        DisplayResult displayResult = new DefaultDisplayResult();

        displayResult.print(extractor.getResults());

    }
}
