package by.pavel.service;

import by.pavel.view.DisplayResult;

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
        String answer;
        do {
            String search = inputSearch();

            displayResult.print(extractor.extract(requestSender.sendRequest(search, url, acceptLanguage)));

            System.out.println("Do you want search something else? y/n or д/н");

            answer = scanner.nextLine();
        } while (isAgain(answer));
    }
}
