package by.pavel.service;

import by.pavel.config.DefaultConfigLoader;
import by.pavel.data.Result;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class DefaultElementExtractor {

    private List<Result> results;
    private DefaultConfigLoader loader;

    public DefaultElementExtractor(DefaultConfigLoader loader) {
        this.loader = loader;
        this.results = new ArrayList<>(loader.getFirstResults());
    }

    public List<Result> extract(Document document) {
        Elements elements = document.select(loader.getSearchedElement());
        final int firstElements = loader.getFirstResults();
        if (this.results.size() > 0) {
            this.results.clear();
        }

        for (int i = 0; i < firstElements; i++) {
            this.results.add(i, new Result(getLink(elements.get(i)), getTitle(elements.get(i))));
        }

        return getResults();
    }

    private String getLink(Element element) {
        String result = element.attr("href");
        int endLink;
        if (result.contains("(") && result.indexOf("(") < result.indexOf("&")) {
            endLink = result.indexOf("(");
        } else {
            endLink = result.indexOf("&");
        }

        return result.substring(7, endLink);
    }

    private String getTitle(Element element) {
        return element.text();
    }

    public List<Result> getResults() {
        return results;
    }
}
