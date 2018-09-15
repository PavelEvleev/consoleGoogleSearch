package service;

import by.pavel.config.DefaultConfigLoader;
import by.pavel.service.DefaultBrowserAsk;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class DefaultBrowserAskTest {

    private DefaultConfigLoader loader = new DefaultConfigLoader();

    private Scanner scanner;

    @Before
    public void initScanner() {
        String data = "Человек паук";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        scanner = new Scanner(System.in);
    }

    @After
    public void returnState() {
        System.setIn(System.in);
    }

    @Test
    public void shouldReturnSearchTermPreparedForRequest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String shouldReturn = "Человек+паук";

        DefaultBrowserAsk ask = new DefaultBrowserAsk(scanner, null, null, null);

        Method inputSearch = DefaultBrowserAsk.class.getDeclaredMethod("inputSearch", null);
        inputSearch.setAccessible(true);
        String res = (String) inputSearch.invoke(ask, null);
        assertEquals(shouldReturn, res);
    }
}
