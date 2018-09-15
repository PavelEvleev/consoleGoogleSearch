package by.pavel.view;

import by.pavel.data.Result;

public class StyledDisplayResult implements DisplayResult {

    public void print(Result[] results) {

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
