package by.pavel.config;

import by.pavel.view.DefaultDisplayResult;
import by.pavel.view.DisplayResult;
import by.pavel.view.StyledDisplayResult;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class DefaultConfigLoader {

    private Scanner scanner;

    private DisplayResult displayResult;

    private String baseSearchUrl;

    private String searchedElement;

    private String acceptLanguage;

    private int firstResults;

    private static final String CONFIG_FILE = "config.properties";

    public DefaultConfigLoader init() {

        changeEncodingForOs();

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

    private void getAcceptLanguage(Properties prop, QueryConstants propertyKey) {
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

    private void changeEncodingForOs() {
        String os = System.getProperty("os.name");
        System.out.println("You run app under " + os);
        if (os.startsWith("Windows")) {
            try {
                System.setOut(new PrintStream(System.out, true, "cp866"));
                this.scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in, "cp866")));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            this.scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        }
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

    public Scanner getScanner() {
        return scanner;
    }

    public DisplayResult selectViewForDisplay() {
        int result;

        do {
            System.out.println("How would you like see the result: default(1) or styled(2)?");
            result = Integer.parseInt(getScanner().nextLine());

            switch (result) {
                case 1:
                    this.displayResult = new DefaultDisplayResult();
                    break;
                case 2:
                    this.displayResult = new StyledDisplayResult();
                    break;
                default:
                    result = 0;
                    System.out.println("Sorry, but we haven`t that answer./n" +
                            "Please, try again.");
                    break;
            }
        }
        while (result == 0);

        return getDisplayResult();
    }

    public DisplayResult getDisplayResult() {
        return displayResult;
    }
}

