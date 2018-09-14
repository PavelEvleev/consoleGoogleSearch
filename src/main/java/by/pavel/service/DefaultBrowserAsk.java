package by.pavel.service;

import by.pavel.config.DefaultConfigLoader;
import by.pavel.view.DisplayResult;

public class DefaultBrowserAsk implements BrowserAsk {

    private final DefaultConfigLoader loader;

    private RequestSender requestSender;

    private DisplayResult displayResult;

    private DefaultElementExtractor extractor;

    public DefaultBrowserAsk(DefaultConfigLoader loader) {
        this.loader = loader;
        this.requestSender = new DefaultRequestSender(loader);
        this.extractor = new DefaultElementExtractor(loader);
        this.displayResult = loader.getDisplayResult();
    }

    private boolean checkAnswer(String answer) {
        final String AGAIN_US = "y";
        final String AGAIN_RU = "д";
        return answer.equalsIgnoreCase(AGAIN_RU) || answer.equalsIgnoreCase(AGAIN_US);
    }

    @Override
    public void newSearch() {
        String answer;
        do {
            System.out.print("Please enter search term: ");

            String search = loader.getScanner().nextLine().replace(' ', '+');

            System.out.println(search);

            displayResult.print(extractor.extract(requestSender.sendRequest(search)));

            System.out.println("Do you want search something else? y/n or д/н");

            answer = loader.getScanner().nextLine();

        } while (checkAnswer(answer));
    }
}
