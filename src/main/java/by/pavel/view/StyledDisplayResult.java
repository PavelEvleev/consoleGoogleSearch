package by.pavel.view;

import by.pavel.data.Result;

import java.util.List;

public class StyledDisplayResult implements DisplayResult {
    @Override
    public void print(List<Result> results) {

        String separator = "---------------------------------";
        System.out.println(separator);

        for (Result result : results) {
            System.out.println(separator);
            System.out.println("Link: " + result.getLink());
            System.out.println("Title: " + result.getTitle());
            System.out.println(separator);
        }

        System.out.println(separator);

    }
}
