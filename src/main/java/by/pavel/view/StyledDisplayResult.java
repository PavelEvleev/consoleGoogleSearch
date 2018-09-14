package by.pavel.view;

import by.pavel.data.Result;

import java.util.List;

public class StyledDisplayResult implements DisplayResult {

    public void print(List<Result> results) {

        String separator = "---------------------------------";
        System.out.println(separator);
        for (Result result : results) {
            System.out.println(separator);

            System.out.println(result);

            System.out.println(separator);
        }
        System.out.println(separator);

    }
}
