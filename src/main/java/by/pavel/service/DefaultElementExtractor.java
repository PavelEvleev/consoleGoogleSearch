package by.pavel.service;

import by.pavel.data.Result;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DefaultElementExtractor {

    private Result[] results;
    private int firstResults;
    private String searchedElement;

    public DefaultElementExtractor(int firstResults, String searchedElement) {
        this.searchedElement = searchedElement;
        this.firstResults = firstResults;
        this.results = new Result[firstResults];

    }

    /**
     * Getting specific html tag from document and returning
     * result list which contain titles and links.
     *
     * @param document html document
     * @return list of objects which containing title and link
     */

    public Result[] extract(Document document) {
        Elements elements = document.select(this.searchedElement);

        for (int i = 0; i < this.firstResults; i++) {
            if (this.results[i] == null) {
                this.results[i] = new Result(getLink(elements.get(i)), getTitle(elements.get(i)));
                continue;
            }
            this.results[i].setTitle(getTitle(elements.get(i)));
            this.results[i].setLink(getLink(elements.get(i)));
        }

        return getResults();
    }

    /**
     * Extract link from html tag and remove all unnecessary.
     *
     * @param element html tag element
     * @return link pure string
     */
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

    /**
     * @return list of objects which containing title and link
     */
    public Result[] getResults() {
        return results;
    }
}
