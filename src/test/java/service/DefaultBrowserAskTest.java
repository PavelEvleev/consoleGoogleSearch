package service;

import by.pavel.service.DefaultBrowserAsk;
import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DefaultBrowserAskTest {

    private Scanner scanner;

    private void initScanner(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        scanner = new Scanner(System.in);
    }

    @After
    public void returnState() {
        System.setIn(System.in);
    }

    @Test
    public void shouldReturnSearchTermPreparedForRequest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        initScanner("Человек паук");
        String shouldReturn = "Человек+паук";

        DefaultBrowserAsk ask = new DefaultBrowserAsk(scanner, null, null, null);
        Method inputSearch = DefaultBrowserAsk.class.getDeclaredMethod("inputSearch");
        inputSearch.setAccessible(true);

        String res = (String) inputSearch.invoke(ask);
        assertEquals(shouldReturn, res);
    }

    @Test
    public void shouldReturnTrue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final String[] input = {"Y", "Д", "y", "д"};
        final boolean[] shouldResult = {true, true, true, true};
        boolean[] results = new boolean[4];

        DefaultBrowserAsk ask = new DefaultBrowserAsk(scanner, null, null, null);
        Method inputSearch = DefaultBrowserAsk.class.getDeclaredMethod("isAgain", String.class);
        inputSearch.setAccessible(true);

        for (int i = 0; i < input.length; i++) {
            results[i] = (boolean) inputSearch.invoke(ask, input[i]);
        }

        assertArrayEquals(shouldResult, results);
    }


    @Test
    public void shouldReturnFalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final String[] input = {"a", "v", "p", ""};
        final boolean[] shouldResult = {false, false, false, false};
        boolean[] results = new boolean[4];

        DefaultBrowserAsk ask = new DefaultBrowserAsk(scanner, null, null, null);
        Method inputSearch = DefaultBrowserAsk.class.getDeclaredMethod("isAgain", String.class);
        inputSearch.setAccessible(true);

        for (int i = 0; i < input.length; i++) {
            results[i] = (boolean) inputSearch.invoke(ask, input[i]);
        }

        assertArrayEquals(shouldResult, results);
    }
}
