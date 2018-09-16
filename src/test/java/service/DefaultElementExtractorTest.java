package service;


import by.pavel.config.DefaultConfigLoader;
import by.pavel.data.Result;
import by.pavel.service.DefaultElementExtractor;
import by.pavel.service.DefaultRequestSender;
import by.pavel.service.RequestSender;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DefaultElementExtractorTest {
    private DefaultConfigLoader loader = new DefaultConfigLoader();

    private RequestSender sender = new DefaultRequestSender();

    private DefaultElementExtractor extractor;

    private Document document;

    @Before
    public void initTest() {
        loader.init();
        document = sender.sendRequest("Человек+паук", loader.getBaseSearchUrl(), loader.getAcceptLanguage());
        extractor = new DefaultElementExtractor(loader.getFirstResults(), loader.getSearchedElement());
    }

    @Test
    public void shouldReturnListElements() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method method = extractor.getClass().getDeclaredMethod("selectElements", Document.class);
        method.setAccessible(true);

        Elements elements = (Elements) method.invoke(extractor, document);

        assertFalse(elements.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowException() throws Throwable {
        document = sender.sendRequest("", loader.getBaseSearchUrl(), loader.getAcceptLanguage());

        Method method = extractor.getClass().getDeclaredMethod("selectElements", Document.class);
        method.setAccessible(true);

        try {
            method.invoke(extractor, document);
        } catch (InvocationTargetException e) {
            if (e.getTargetException().getClass().equals(NoSuchElementException.class)) {
                throw e.getTargetException();
            }
        }

    }

    @Test
    public void shouldReturnLink() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final String linkStart = "http";
        Element element = document.selectFirst(loader.getSearchedElement());

        Method method = extractor.getClass().getDeclaredMethod("getLink", Element.class);
        method.setAccessible(true);

        String result = (String) method.invoke(extractor, element);

        assertTrue(result.contains(linkStart));
    }

    @Test
    public void shouldReturnTitle() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Element element = document.selectFirst(loader.getSearchedElement());

        Method method = extractor.getClass().getDeclaredMethod("getTitle", Element.class);
        method.setAccessible(true);

        String result = (String) method.invoke(extractor, element);

        assertFalse(result.isEmpty());
    }


    @Test
    public void shouldReturnArrayResults() {
        Result[] results = extractor.extract(document);

        assertEquals(results.length, loader.getFirstResults());
    }

}

