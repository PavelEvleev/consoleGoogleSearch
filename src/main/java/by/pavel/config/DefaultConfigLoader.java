package by.pavel.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DefaultConfigLoader {

    private String baseSearchUrl;

    private String searchedElement;

    private String acceptLanguage;

    private int firstResults;

    private static final String CONFIG_FILE = "config.properties";

    public DefaultConfigLoader init() {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            prop.load(input);
            getBaseUrl(prop, QueryConstants.BASE_SEARCH_URL);
            getFirstNumbersOfResult(prop, QueryConstants.FIRST_TOP_RESULT);
            getSearchedElement(prop, QueryConstants.SEARCHED_ELEMENT);
            getAcceptLanguage(prop, QueryConstants.ACCEPT_LANGUAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    private void getAcceptLanguage(Properties prop, QueryConstants propertyKey){
        this.acceptLanguage = prop.getProperty(propertyKey.getValue());
    }

    private void getBaseUrl(Properties prop, QueryConstants propertyKey) {
        this.baseSearchUrl = prop.getProperty(propertyKey.getValue());
    }

    private void getFirstNumbersOfResult(Properties prop, QueryConstants propertyKey) {
        this.firstResults = Integer.parseInt(prop.getProperty(propertyKey.getValue()));
    }

    private void getSearchedElement(Properties prop, QueryConstants propertyKey) {
        this.searchedElement = prop.getProperty(propertyKey.getValue());
    }

    public String getBaseSearchUrl() {
        return baseSearchUrl;
    }

    public String getSearchedElement() {
        return searchedElement;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public int getFirstResults() {
        return firstResults;
    }
}
