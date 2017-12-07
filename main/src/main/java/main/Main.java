package main;

import service.Crawler;
import service.Indexer;
import service.URLRepo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final URLRepo repo = new URLRepo();
        final List<URL> urlList = new ArrayList<>();

        ProviderSingleton

        try {
            urlList.add(new URL("https://en.wikipedia.org/wiki/Rafic_Hariri"));
            repo.store(urlList);

            // Crawl URL
            new Crawler(repo);
            new Indexer(repo);

        } catch (final MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
