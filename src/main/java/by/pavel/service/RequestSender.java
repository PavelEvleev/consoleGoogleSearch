package by.pavel.service;

import org.jsoup.nodes.Document;

public interface RequestSender {

    Document sendRequest(String search);
}
