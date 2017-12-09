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
        final URLRepo repo = new URLRepo();

        final ArrayList<URL> urls = new ArrayList<>();
        try {
            urls.add(new URL("https://pastebin.com/raw/07aPR2K4"));
            urls.add(new URL("https://pastebin.com/raw/jzAtxp85"));
            urls.add(new URL("https://pastebin.com/raw/U7PDZqC7"));
            repo.store(urls);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        final List<URL> urlsMatching = repo.searchTerm("qui");
        assert  urlsMatching.size() == 2;
    }

    @Test
    public void searchTermTest2() {
        final URLRepo repo = new URLRepo();

        final ArrayList<URL> urls = new ArrayList<>();
        try {
            urls.add(new URL("https://pastebin.com/raw/p1rixE68"));
            urls.add(new URL("https://pastebin.com/raw/HV7RHk5d"));
            urls.add(new URL("https://pastebin.com/raw/SS0QnUd3"));
            urls.add(new URL("https://pastebin.com/raw/6BN8TC0d"));
            repo.store(urls);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        final List<URL> urlsMatching = repo.searchTerm("untitl");
        assert  urlsMatching.size() == 3;
    }
}