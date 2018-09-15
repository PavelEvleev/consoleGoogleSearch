package by.pavel.view;

import by.pavel.data.Result;


public class DefaultDisplayResult implements DisplayResult {

    public void print(Result[] results) {
        for (Result result : results) {
            System.out.println(result);
        }
    }
}
