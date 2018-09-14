package by.pavel.view;

import by.pavel.data.Result;

import java.util.List;


public class DefaultDisplayResult implements DisplayResult {

    public void print(List<Result> results) {
        for (Result result : results) {
            System.out.println(result);
        }
    }
}
