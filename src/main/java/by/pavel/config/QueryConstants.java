package by.pavel.config;


public enum QueryConstants {
    FIRST_TOP_RESULT("FIRST_TOP_RESULTS"), BASE_SEARCH_URL("BASE_SEARCH_URL"),
    SEARCHED_ELEMENT("SEARCHED_ELEMENT"), ACCEPT_LANGUAGE("ACCEPT_LANGUAGE");

    private String value;

    QueryConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
