package by.pavel.view;

import by.pavel.data.Result;

import java.util.Arrays;

public class StyledDisplayResult implements DisplayResult {

    private String separator;

    public StyledDisplayResult() {
        char[] del = new char[60];
        Arrays.fill(del, '-');
        this.separator = new String(del);
    }

    public void print(Result[] results) {

        System.out.println(this.separator);
        for (Result result : results) {
            System.out.println(this.separator);

            System.out.println(result);

            System.out.println(this.separator);
        }
        System.out.println(this.separator);

    }
}
