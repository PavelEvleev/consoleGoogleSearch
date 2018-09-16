package by.pavel.service;

import by.pavel.data.Result;
import by.pavel.view.DisplayResult;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class DefaultBrowserAsk implements BrowserAsk {

    private Scanner scanner;

    private RequestSender requestSender;

    private DisplayResult displayResult;

    private DefaultElementExtractor extractor;

    public DefaultBrowserAsk(Scanner scanner, RequestSender sender,
                             DefaultElementExtractor extractor, DisplayResult displayResult) {
        this.scanner = scanner;
        this.requestSender = sender;
        this.extractor = extractor;
        this.displayResult = displayResult;
    }

    private boolean isAgain(String answer) {
        String AGAIN_US = "y";
        String AGAIN_RU = "д";
        return answer.equalsIgnoreCase(AGAIN_RU) || answer.equalsIgnoreCase(AGAIN_US);
    }

    private String inputSearch() {
        String search;
        System.out.print("Please enter search term: ");
        do {
            search = scanner.nextLine().replace(' ', '+');
            if (search.isEmpty()) {
                System.out.println("Sorry, but you need to write something for search.\nPlease, try again.");
            }
        } while (search.isEmpty());

        return search;
    }

    @Override
    public void newSearch(String url, String acceptLanguage) {
        String answer = "y";
        do {
            String search = inputSearch();

            try {
                System.out.println("Please wait...");

                Result[] results = extractor.extract(requestSender.sendRequest(search, url, acceptLanguage));
                displayResult.print(results);
                System.out.println("Do you want search something else? y/n or д/н");

                answer = scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage() + " Sorry, try again.");
            }

        } while (isAgain(answer));
    }
}
