package main;

import service.URLRepo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //final URLRepo repo = new URLRepo();
        final List<URL> urlList = new ArrayList<>();

        Summer summer = new Summer();
        summer.bean(URLRepo.class, new URLRepo());
        URLRepo repo = (URLRepo) summer.instanceOf(URLRepo.class);

        summer.addScope();
        summer.bean(URLRepo.class, new URLRepo());
        URLRepo repo1 = (URLRepo) summer.instanceOf(URLRepo.class);

        if (repo == repo1)
            System.out.println("ECHEC");
        else
            System.out.println("SUCCESS");

        try {
            urlList.add(new URL("https://en.wikipedia.org/wiki/Rafic_Hariri"));
            //repo.store(urlList);

            // Crawl URL
            //new Crawler(repo);
            //new Indexer(repo);

        } catch (final MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
