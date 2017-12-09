package main;

import org.junit.Test;
import service.URLRepo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DomainTest {

    @Test
    public void searchTermTest() {
        URLRepo repo = new URLRepo();

        ArrayList<URL> urls = new ArrayList<>();
        try {
            urls.add(new URL("https://pastebin.com/raw/07aPR2K4"));
            urls.add(new URL("https://pastebin.com/raw/jzAtxp85"));
            urls.add(new URL("https://pastebin.com/raw/U7PDZqC7"));
            repo.store(urls);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        List<URL> urlsMatching = repo.searchTerm("qui");
        assert  urlsMatching.size() == 2;
    }
}